package com.simia.expert.service.record.impl;

import com.simia.expert.model.entity.record.OrderEntity;
import com.simia.expert.model.repository.jpa.record.OrderRepository;
import com.simia.expert.service.record.OrderService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.record.OrderDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class OrderServiceImpl extends DefaultServiceImpl<OrderDto, OrderEntity> implements OrderService {

    private static final Class<OrderDto> DTO_CLASS = OrderDto.class;
    private static final Class<OrderEntity> ENTITY_CLASS = OrderEntity.class;

    public OrderServiceImpl(OrderRepository repository, DefaultConverter defaultConverter) {
        super(repository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
    }
}
