package com.bharat.food.ordering.system.restaurant.service.domain.valueobject;
/*
 * @author bharat.verma
 * @created Sunday, 29 January 2023
 */

import com.bharat.food.ordering.system.domain.vo.BaseId;

import java.util.UUID;

public class OrderApprovalId extends BaseId<UUID> {
    public OrderApprovalId(UUID value) {
        super(value);
    }
}
