package com.bharat.food.ordering.system.customer.service.dataaccess.customer.repository;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */


import com.bharat.food.ordering.system.customer.service.dataaccess.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {


}
