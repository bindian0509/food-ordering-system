package com.bharat.food.ordering.system.order.service.domain.ports.input.message.listener.customer;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import com.bharat.food.ordering.system.order.service.domain.dto.message.CustomerModel;

public interface CustomerMessageListener {

    void customerCreated(CustomerModel customerModel);
}
