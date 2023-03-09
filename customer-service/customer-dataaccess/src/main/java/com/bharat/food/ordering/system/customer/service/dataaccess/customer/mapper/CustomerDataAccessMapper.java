package com.bharat.food.ordering.system.customer.service.dataaccess.customer.mapper;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */


import com.bharat.food.ordering.system.customer.service.dataaccess.customer.entity.CustomerEntity;
import com.bharat.food.ordering.system.customer.service.domain.entity.Customer;
import com.bharat.food.ordering.system.domain.vo.CustomerId;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity) {
        return new Customer(new CustomerId(customerEntity.getId()),
                customerEntity.getUsername(),
                customerEntity.getFirstName(),
                customerEntity.getLastName());
    }

    public CustomerEntity customerToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .username(customer.getUsername())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

}
