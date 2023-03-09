package com.bharat.food.ordering.system.customer.service.domain.mapper;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */


import com.bharat.food.ordering.system.customer.service.domain.create.CreateCustomerCommand;
import com.bharat.food.ordering.system.customer.service.domain.create.CreateCustomerResponse;
import com.bharat.food.ordering.system.customer.service.domain.entity.Customer;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataMapper {

    public Customer createCustomerCommandToCustomer(CreateCustomerCommand createCustomerCommand) {
        return new Customer(new CustomerId(createCustomerCommand.getCustomerId()),
                createCustomerCommand.getUsername(),
                createCustomerCommand.getFirstName(),
                createCustomerCommand.getLastName());
    }

    public CreateCustomerResponse customerToCreateCustomerResponse(Customer customer, String message) {
        return new CreateCustomerResponse(customer.getId().getValue(), message);
    }
}
