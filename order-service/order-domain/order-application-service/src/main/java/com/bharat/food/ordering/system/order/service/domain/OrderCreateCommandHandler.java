package com.bharat.food.ordering.system.order.service.domain;

import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    public CreateOrderResponse createOrder (CreateOrderCommand createOrderCommand) {
        return null;
    }
}
