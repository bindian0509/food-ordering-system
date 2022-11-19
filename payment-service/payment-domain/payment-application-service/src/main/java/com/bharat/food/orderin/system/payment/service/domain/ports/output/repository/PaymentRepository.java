package com.bharat.food.orderin.system.payment.service.domain.ports.output.repository;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.payment.service.domain.entity.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Payment save(Payment payment);
    Optional<Payment> findByOrderId (UUID orderId);
}
