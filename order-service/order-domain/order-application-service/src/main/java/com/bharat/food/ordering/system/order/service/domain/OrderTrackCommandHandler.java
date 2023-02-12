package com.bharat.food.ordering.system.order.service.domain;

import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.bharat.food.ordering.system.order.service.domain.entity.Order;
import com.bharat.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.bharat.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.bharat.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.bharat.food.ordering.system.order.service.domain.vo.TrackingId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult =
                orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));

        if (orderResult.isEmpty()) {
            log.warn("Couldn't find order with tracking id : {} ",trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Couldn't find order with tracking id : "+trackOrderQuery.getOrderTrackingId());
        }

        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
