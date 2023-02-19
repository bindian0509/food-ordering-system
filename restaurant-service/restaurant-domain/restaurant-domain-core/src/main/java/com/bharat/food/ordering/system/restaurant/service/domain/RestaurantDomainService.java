package com.bharat.food.ordering.system.restaurant.service.domain;
/*
 * @author bharat.verma
 * @created Monday, 06 February 2023
 */

import com.bharat.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.bharat.food.ordering.system.domain.vo.OrderApprovalStatus;
import com.bharat.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderApprovedEvent;
import com.bharat.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public interface RestaurantDomainService {

    OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages);
}
