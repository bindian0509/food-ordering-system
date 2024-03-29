package com.bharat.food.ordering.system.order.service.domain;
/*
 * @author bharat.verma
 * @created Thursday, 09 February 2023
 */
import com.bharat.food.ordering.system.domain.vo.OrderId;
import com.bharat.food.ordering.system.domain.vo.OrderStatus;
import com.bharat.food.ordering.system.order.service.domain.entity.Order;
import com.bharat.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.bharat.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.bharat.food.ordering.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderSagaHelper {

    private final OrderRepository orderRepository;

    public OrderSagaHelper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    Order findOrder(String orderId) {
        Optional<Order> orderResponse = orderRepository.findById(new OrderId(UUID.fromString(orderId)));
        if (orderResponse.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderId);
            throw new OrderNotFoundException("Order with id " + orderId + " could not be found!");
        }
        return orderResponse.get();
    }

    void saveOrder(Order order) {
        orderRepository.save(order);
    }

    SagaStatus orderStatusToSagaStatus(OrderStatus orderStatus) {
        switch (orderStatus) {
            case PAID:
                return SagaStatus.PROCESSING;
            case APPROVED:
                return SagaStatus.SUCCEEDED;
            case CANCELLING:
                return SagaStatus.COMPENSATING;
            case CANCELLED:
                return SagaStatus.COMPENSATED;
            default:
                return SagaStatus.STARTED;
        }
    }
}
