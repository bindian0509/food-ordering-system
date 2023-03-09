package com.bharat.food.ordering.system.customer.service.dataaccess.customer.entity;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Entity
public class CustomerEntity {

    @Id
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
}
