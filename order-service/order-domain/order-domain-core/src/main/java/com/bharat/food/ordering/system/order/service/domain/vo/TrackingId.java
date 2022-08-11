package com.bharat.food.ordering.system.order.service.domain.vo;

import com.bharat.food.ordering.system.domain.vo.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
