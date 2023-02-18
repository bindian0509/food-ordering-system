package com.bharat.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.mapper;
/*
 * @author bharat.verma
 * @created Thursday, 16 February 2023
 */


import com.bharat.food.ordering.system.order.service.dataaccess.outbox.restaurantapproval.entity.ApprovalOutboxEntity;
import com.bharat.food.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import org.springframework.stereotype.Component;

@Component
public class ApprovalOutboxDataAccessMapper {

    public ApprovalOutboxEntity orderCreatedOutboxMessageToOutboxEntity(OrderApprovalOutboxMessage
                                                                                orderApprovalOutboxMessage) {
        return ApprovalOutboxEntity.builder()
                .id(orderApprovalOutboxMessage.getId())
                .sagaId(orderApprovalOutboxMessage.getSagaId())
                .createdAt(orderApprovalOutboxMessage.getCreatedAt())
                .type(orderApprovalOutboxMessage.getType())
                .payload(orderApprovalOutboxMessage.getPayload())
                .orderStatus(orderApprovalOutboxMessage.getOrderStatus())
                .sagaStatus(orderApprovalOutboxMessage.getSagaStatus())
                .outboxStatus(orderApprovalOutboxMessage.getOutboxStatus())
                .version(orderApprovalOutboxMessage.getVersion())
                .build();
    }

    public OrderApprovalOutboxMessage approvalOutboxEntityToOrderApprovalOutboxMessage(ApprovalOutboxEntity
                                                                                               approvalOutboxEntity) {
        return OrderApprovalOutboxMessage.builder()
                .id(approvalOutboxEntity.getId())
                .sagaId(approvalOutboxEntity.getSagaId())
                .createdAt(approvalOutboxEntity.getCreatedAt())
                .type(approvalOutboxEntity.getType())
                .payload(approvalOutboxEntity.getPayload())
                .orderStatus(approvalOutboxEntity.getOrderStatus())
                .sagaStatus(approvalOutboxEntity.getSagaStatus())
                .outboxStatus(approvalOutboxEntity.getOutboxStatus())
                .version(approvalOutboxEntity.getVersion())
                .build();
    }

}