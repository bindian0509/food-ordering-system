package com.bharat.food.ordering.system.order.service.dataaccess.order.entity;

import com.bharat.food.ordering.system.domain.vo.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    private UUID id;
    private UUID customerId;
    private UUID restaurantId;
    private UUID trackingId;
    private BigDecimal price;
    private OrderStatus orderStatus;
    private String failureMessages;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private OrderAddressEntity address;


}
