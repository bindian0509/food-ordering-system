package com.bharat.food.ordering.system.payment.service.dataacess.credithistory.repository;
/*
 * @author bharat.verma
 * @created Thursday, 26 January 2023
 */


import com.bharat.food.ordering.system.payment.service.dataacess.credithistory.entity.CreditHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditHistoryJpaRepository extends JpaRepository<CreditHistoryEntity, UUID> {

    Optional<List<CreditHistoryEntity>> findByCustomerId(UUID customerId);

}
