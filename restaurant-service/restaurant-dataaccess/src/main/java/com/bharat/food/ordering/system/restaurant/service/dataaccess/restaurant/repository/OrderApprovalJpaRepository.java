package com.bharat.food.ordering.system.restaurant.service.dataaccess.restaurant.repository;
/*
 * @author bharat.verma
 * @created Tuesday, 07 February 2023
 */


import com.bharat.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.OrderApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {


}
