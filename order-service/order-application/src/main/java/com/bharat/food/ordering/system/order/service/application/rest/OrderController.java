package com.bharat.food.ordering.system.order.service.application.rest;

import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderReponse;
import com.bharat.food.ordering.system.order.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackOrderReponse> getOrderByTrackingId (@PathVariable UUID trackingId) {
        TrackOrderReponse trackOrderReponse = orderApplicationService.trackOrder(
                TrackOrderQuery.builder().orderTrackingId(trackingId).build());
        log.info("returning order status with tracking id : {} ", trackOrderReponse.getOrderTrackingId());
        return ResponseEntity.ok(trackOrderReponse);
    }

}
