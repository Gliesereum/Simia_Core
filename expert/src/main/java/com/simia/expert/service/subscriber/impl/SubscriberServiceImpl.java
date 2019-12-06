package com.simia.expert.service.subscriber.impl;

import com.simia.expert.model.entity.subscriber.SubscriberEntity;
import com.simia.expert.model.repository.jpa.subscriber.SubscriberRepository;
import com.simia.expert.service.content.ContentService;
import com.simia.expert.service.expert.ExpertService;
import com.simia.expert.service.subscriber.SubscriberService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.exchange.service.account.WalletExchangeService;
import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import com.simia.share.common.model.dto.account.wallet.TransactionRequestDto;
import com.simia.share.common.model.dto.expert.content.ContentFullDto;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.dto.expert.subscriber.SubscriberDto;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.NOT_ENOUGH_MONEY;
import static com.simia.share.common.exception.messages.ExpertExceptionMessage.UNEXPECTED_ERROR;


@Slf4j
@Service
public class SubscriberServiceImpl extends AuditableServiceImpl<SubscriberDto, SubscriberEntity> implements SubscriberService {

    private static final Class<SubscriberDto> DTO_CLASS = SubscriberDto.class;
    private static final Class<SubscriberEntity> ENTITY_CLASS = SubscriberEntity.class;

    private final SubscriberRepository subscriberRepository;

    @Autowired
    private WalletExchangeService walletExchangeService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository subscriberRepository, DefaultConverter defaultConverter) {
        super(subscriberRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public List<SubscriberDto> mySubscriptions() {
        List<SubscriberDto> result = null;

        SecurityUtil.checkUserByBanStatus();
        List<SubscriberEntity> entities = subscriberRepository.getByUserId(SecurityUtil.getUserId());

        if (CollectionUtils.isNotEmpty(entities)) {
            result = converter.convert(entities, dtoClass);

            if (CollectionUtils.isNotEmpty(result)) {
                Set<UUID> contentIds = result.stream().map(SubscriberDto::getContentId).collect(Collectors.toSet());
                List<ContentFullDto> contents = contentService.getFullByIds(new ArrayList<>(contentIds));

                if (CollectionUtils.isNotEmpty(contents)) {
                    Map<UUID, ContentFullDto> map = contents.stream().collect(Collectors.toMap(ContentFullDto::getId, content -> content));
                    if (MapUtils.isNotEmpty(map)) {
                        result.forEach(f -> {
                            f.setContent(map.get(f.getContentId()));
                        });
                    }
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public SubscriberDto subscribeToContent(UUID contentId) {
        SecurityUtil.checkUserByBanStatus();
        SubscriberDto result = null;
        if (contentId != null) {
            ContentFullDto content = contentService.getFullById(contentId);
            if (content != null) {
                buyContent(content);
                result = new SubscriberDto();
                result.setContent(content);
                result.setContentId(contentId);
                result.setContentType(content.getContentType());
                result.setUserId(SecurityUtil.getUserId());
                result = create(result);
            }
        }
        return result;
    }

    private void buyContent(ContentFullDto content) {
        if (content != null && content.getPrice() != null && content.getPrice() > 0) {
            ExpertDto expert = expertService.getById(content.getAuthorId());
            if(expert != null){
                TransactionRequestDto request = new TransactionRequestDto();
                request.setAmount(content.getPrice());
                request.setUserToId(expert.getUserId());
                request.setUserFromId(SecurityUtil.getUserId());
                TransactionResultType result = walletExchangeService.makeTransaction(request);
                switch (result) {
                    case SUCCESS:
                        break;
                    case NOT_ENOUGH_MONEY:
                        throw new ClientException(NOT_ENOUGH_MONEY);
                    case UNEXPECTED_ERROR:
                        throw new ClientException(UNEXPECTED_ERROR);
                }
            }
        }
    }
}