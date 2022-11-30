package com.bharat.food.ordering.system.payment.service.dataacess.payment.repository;
/*
 * @author bharat.verma
 * @created Thursday, 26 January 2023
 */

import com.bharat.food.ordering.system.payment.service.dataacess.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByOrderId(UUID orderId);

}
