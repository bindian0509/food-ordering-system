package com.bharat.food.ordering.system.order.service.dataaccess.order.adapter;

import com.bharat.food.ordering.system.order.service.dataaccess.order.mapper.OrderDataAccessMapper;
import com.bharat.food.ordering.system.order.service.dataaccess.order.repository.OrderJpaRespository;
import com.bharat.food.ordering.system.order.service.domain.entity.Order;
import com.bharat.food.ordering.system.order.service.domain.ports.output.repository.OrderRepository;
import com.bharat.food.ordering.system.order.service.domain.vo.TrackingId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRespository orderJpaRespository;
    private final OrderDataAccessMapper  orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRespository orderJpaRespository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRespository = orderJpaRespository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(orderJpaRespository.save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRespository.findByTrackingId(trackingId.getValue())
                .map(orderDataAccessMapper::orderEntityToOrder);
    }
}
