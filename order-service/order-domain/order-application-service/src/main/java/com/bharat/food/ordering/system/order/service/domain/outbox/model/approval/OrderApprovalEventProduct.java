package com.bharat.food.ordering.system.order.service.domain.outbox.model.approval;
/*
 * @author bharat.verma
 * @created Monday, 13 February 2023
 */


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderApprovalEventProduct {
    @JsonProperty
    private String id;
    @JsonProperty
    private Integer quantity;
}
