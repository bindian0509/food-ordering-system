package com.bharat.food.orderin.system.payment.service.domain.ports.output.repository;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditEntry;

import java.util.Optional;

public interface CreditEntryRepository {

    CreditEntry save (CreditEntry creditEntry);

    Optional<CreditEntry> findByCustomerId (CustomerId customerId);
}
