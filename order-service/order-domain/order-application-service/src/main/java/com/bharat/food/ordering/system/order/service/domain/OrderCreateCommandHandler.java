package com.bharat.food.ordering.system.order.service.domain;

import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bharat.food.ordering.system.order.service.domain.entity.Customer;
import com.bharat.food.ordering.system.order.service.domain.entity.Restaurant;
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
public class OrderCreateCommandHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateCommandHandler(OrderDomainService orderDomainService, OrderRepository orderRepository,
                                     CustomerRepository customerRepository, RestaurantRepository restaurantRepository,
                                     OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public CreateOrderResponse createOrder (CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        return null;
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
}
