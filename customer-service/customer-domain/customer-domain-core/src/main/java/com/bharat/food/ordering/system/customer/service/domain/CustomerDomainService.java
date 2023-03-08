package com.bharat.food.ordering.system.customer.service.domain;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import com.bharat.food.ordering.system.customer.service.domain.entity.Customer;
import com.bharat.food.ordering.system.customer.service.domain.event.CustomerCreatedEvent;

public interface CustomerDomainService {

    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

}