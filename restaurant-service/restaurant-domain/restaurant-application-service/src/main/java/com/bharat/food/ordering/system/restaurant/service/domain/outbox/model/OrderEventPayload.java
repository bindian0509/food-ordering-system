package com.bharat.food.ordering.system.restaurant.service.domain.outbox.model;
/*
 * @author bharat.verma
 * @created Sunday, 19 February 2023
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderEventPayload {

    @JsonProperty
    private String orderId;

    @JsonProperty
    private String restaurantId;

    @JsonProperty
    private ZonedDateTime createdAt;

    @JsonProperty
    private String orderApprovalStatus;

    @JsonProperty
    private List<String> failureMessages;


}
