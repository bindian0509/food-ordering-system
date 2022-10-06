package com.bharat.food.ordering.system.order.service.messaging.listener.kafka;

import com.bharat.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.bharat.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;

import java.util.List;

public class PaymentResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {
    @Override
    public void receive(List<PaymentResponseAvroModel> messages, List<Long> keys, List<Integer> partitions, List<Long> offsets) {

    }
}
