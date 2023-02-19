package com.bharat.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.repository;
/*
 * @author bharat.verma
 * @created Sunday, 19 February 2023
 */

import com.bharat.food.ordering.system.outbox.OutboxStatus;
import com.bharat.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.entity.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderOutboxJpaRepository extends JpaRepository<OrderOutboxEntity, UUID> {

    Optional<List<OrderOutboxEntity>> findByTypeAndOutboxStatus(String type, OutboxStatus outboxStatus);

    Optional<OrderOutboxEntity> findByTypeAndSagaIdAndOutboxStatus(String type, UUID sagaId, OutboxStatus outboxStatus);

    void deleteByTypeAndOutboxStatus(String type, OutboxStatus outboxStatus);

}