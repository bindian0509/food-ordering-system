package com.bharat.food.ordering.system.order.service.domain.dto.message;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CustomerModel {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
}
