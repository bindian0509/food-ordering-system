package com.bharat.food.ordering.system.payment.service.domain.valueobject;
/*
 * @author bharat.verma
 * @created Monday, 16 January 2023
 */

import com.bharat.food.ordering.system.domain.vo.BaseId;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {
    protected PaymentId(UUID value) {
        super(value);
    }
}
