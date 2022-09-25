package com.bharat.food.ordering.system.order.service.application.rest;

import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bharat.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {


    private final OrderApplicationService orderApplicationService;

    public OrderController(OrderApplicationService orderApplicationService) {
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder (@RequestBody CreateOrderCommand createOrderCommand) {

        log.info("creating order for customer : {} at restaurant : {} ", createOrderCommand.getCustomerId(),
                createOrderCommand.getRestaurantId());

        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);
        log.info("order created with tracking id : {}  ", createOrderResponse.getOrderTrackingId());
        return ResponseEntity.ok(createOrderResponse);
    }


}
