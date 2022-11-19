package com.bharat.food.orderin.system.payment.service.domain.ports.output.repository;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import com.bharat.food.ordering.system.domain.vo.CustomerId;
import com.bharat.food.ordering.system.payment.service.domain.entity.CreditHistory;

import java.util.List;
import java.util.Optional;

public interface CreditHistoryRepository {

    CreditHistory save (CreditHistory creditHistory);

    Optional<List<CreditHistory>> findByCustomerId (CustomerId customerId);
}
