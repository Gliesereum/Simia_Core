package com.simia.expert.service.pincode.impl;

import com.simia.expert.model.entity.pincode.UserPinCodeEntity;
import com.simia.expert.model.repository.jpa.pincode.UserPinCodeRepository;
import com.simia.expert.service.pincode.UserPinCodeService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.exchange.service.account.UserPhoneExchangeService;
import com.simia.share.common.exchange.service.mail.MailExchangeService;
import com.simia.share.common.model.dto.account.user.UserPhoneDto;
import com.simia.share.common.model.dto.expert.pincode.UserPinCodeDto;
import com.simia.share.common.service.DefaultServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.PIN_DOES_NOT_FOUNT;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Slf4j
@Service
public class UserPinCodeServiceImpl extends DefaultServiceImpl<UserPinCodeDto, UserPinCodeEntity> implements UserPinCodeService {

    private static final Class<UserPinCodeDto> DTO_CLASS = UserPinCodeDto.class;
    private static final Class<UserPinCodeEntity> ENTITY_CLASS = UserPinCodeEntity.class;

    private final UserPinCodeRepository userPinCodeRepository;

    @Autowired
    private MailExchangeService mailExchangeService;

    @Autowired
    private UserPhoneExchangeService userPhoneExchangeService;

    @Autowired
    public UserPinCodeServiceImpl(UserPinCodeRepository userPinCodeRepository, DefaultConverter defaultConverter) {
        super(userPinCodeRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.userPinCodeRepository = userPinCodeRepository;
    }

    @Override
    public UserPinCodeDto getByUserId(UUID userId) {
        UserPinCodeDto result = null;
        if (userId != null) {
            UserPinCodeEntity entity = userPinCodeRepository.findByUserId(userId);
            result = converter.convert(entity, dtoClass);
        }
        return result;
    }

    @Override
    public UserPinCodeDto save(UserPinCodeDto userPinCode) {
        UserPinCodeDto result = null;
        if (userPinCode != null) {
            UserPinCodeEntity entity = userPinCodeRepository.findByUserId(userPinCode.getUserId());
            if (entity == null) {
                entity = new UserPinCodeEntity();
                entity.setUserId(userPinCode.getUserId());
            }
            entity.setPinCode(userPinCode.getPinCode());
            entity = userPinCodeRepository.saveAndFlush(entity);
            result = converter.convert(entity, dtoClass);
        }
        return result;
    }

    @Override
    public void remindMe() {
        UUID userId = SecurityUtil.getUserId();
        UserPinCodeDto pin = getByUserId(userId);
        if (pin == null) {
            throw new ClientException(PIN_DOES_NOT_FOUNT);
        }
        final String pinString = "Your pin code: ".concat(pin.getPinCode());
        List<UserPhoneDto> phones = userPhoneExchangeService.findUserPhoneByUserIds(Arrays.asList(userId));
        if (CollectionUtils.isNotEmpty(phones)) {
            mailExchangeService.sendMessagePhone(phones.get(0).getPhone(), pinString);
        }
    }

    @Override
    public void deleteByUserId(UUID userId) {
        UserPinCodeDto userPinCode = getByUserId(userId);
        if (userPinCode != null) {
            userPinCodeRepository.deleteById(userPinCode.getId());
        }
    }
}
