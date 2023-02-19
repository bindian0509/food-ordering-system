package com.bharat.food.ordering.system.restaurant.service.domain;
/*
 * @author bharat.verma
 * @created Monday, 06 February 2023
 */


import com.bharat.food.ordering.system.domain.vo.OrderApprovalStatus;
import com.bharat.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.bharat.food.ordering.system.domain.DomainConstants.UTC;


@Slf4j
public class RestaurantDomainServiceImpl implements RestaurantDomainService {

    @Override
    public OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages) {
        restaurant.validateOrder(failureMessages);
        log.info("Validating order with id: {}", restaurant.getOrderDetail().getId().getValue());

        if (failureMessages.isEmpty()) {
            log.info("Order is approved for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.APPROVED);
            return new OrderApprovedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)));
        } else {
            log.info("Order is rejected for order id: {}", restaurant.getOrderDetail().getId().getValue());
            restaurant.constructOrderApproval(OrderApprovalStatus.REJECTED);
            return new OrderRejectedEvent(restaurant.getOrderApproval(),
                    restaurant.getId(),
                    failureMessages,
                    ZonedDateTime.now(ZoneId.of(UTC)));
        }
    }
}