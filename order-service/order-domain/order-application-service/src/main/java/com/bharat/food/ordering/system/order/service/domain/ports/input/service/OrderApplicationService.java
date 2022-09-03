package com.bharat.food.ordering.system.order.service.domain.ports.input.service;

import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.bharat.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.bharat.food.ordering.system.order.service.domain.dto.track.TrackOrderReponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderReponse trackOrder (@Valid TrackOrderQuery trackOrderQuery);


}
