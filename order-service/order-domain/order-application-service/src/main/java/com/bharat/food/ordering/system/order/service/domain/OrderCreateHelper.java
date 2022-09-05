package com.bharat.food.ordering.system.order.service.domain;

import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.entity.Customer;
import com.bharat.food.ordering.system.order.service.domain.entity.Order;
import com.bharat.food.ordering.system.order.service.domain.entity.Restaurant;
import com.bharat.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.bharat.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.bharat.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.bharat.food.ordering.system.order.service.domain.ports.output.repository.CustomerRepository;
import com.bharat.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.bharat.food.ordering.system.order.service.domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateHelper {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateHelper(OrderDomainService orderDomainService, OrderRepository orderRepository,
                             RestaurantRepository restaurantRepository, CustomerRepository customerRepository,
                             OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerRepository = customerRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public OrderCreatedEvent persistOrder (CreateOrderCommand createOrderCommand) {

        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        saveOrder(order);
        log.info("Order created with order id : {} ", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
    }
    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findRestaurantInformation(restaurant);

        if(restaurantOptional.isEmpty()) {
            log.warn("Couldn't find restaurant with restaurant id : {} ", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("Couldn't find restaurant with restaurant id : "+createOrderCommand.getRestaurantId());
        }
        return restaurantOptional.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);

        if(customer.isEmpty()) {
            log.warn("Couldn't find customer with customer id : {} ", customerId);
            throw new OrderDomainException("Couldn't find customer with customer id : "+customerId);
        }
    }

    private Order saveOrder (Order order) {
        Order orderResult = orderRepository.save(order);
        if(orderResult == null) {
            log.warn("Error occured while saving the order!");
            throw new OrderDomainException("Order result is null, couldn't save order");
        }
        log.info("Order saved with order id : {} ", orderResult.getId().getValue());
        return orderResult;
    }
}
