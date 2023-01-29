package com.bharat.food.ordering.system.payment.service.dataaccess.creditentry.repository;
/*
 * @author bharat.verma
 * @created Thursday, 26 January 2023
 */

import com.bharat.food.ordering.system.payment.service.dataaccess.creditentry.entity.CreditEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {

    Optional<CreditEntryEntity> findByCustomerId(UUID customerId);

}
