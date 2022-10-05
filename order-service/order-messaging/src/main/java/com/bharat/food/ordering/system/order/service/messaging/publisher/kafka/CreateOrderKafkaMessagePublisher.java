package com.bharat.food.ordering.system.order.service.messaging.publisher.kafka;

import com.bharat.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.bharat.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.bharat.food.ordering.system.order.service.domain.config.OrderServiceConfigData;
import com.bharat.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.bharat.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.bharat.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;

    public CreateOrderKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                            OrderServiceConfigData orderServiceConfigData,
                                            KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {

        String orderId = domainEvent.getOrder().getId().getValue().toString();
        log.info("Received OrderCreatedEvent for order id : {} ", orderId);

        try {
            PaymentRequestAvroModel paymentRequestAvroModel =
                    orderMessagingDataMapper.orderCreatedEventToPaymentRequestAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getPaymentRequestTopicName(),
                    orderId,
                    paymentRequestAvroModel,
                    getKafkaCallback(orderServiceConfigData.getPaymentResponseTopicName(), paymentRequestAvroModel));

            log.info("PaymentRequestAvroModel sent to Kafka for Order Id : {} ", paymentRequestAvroModel.getOrderId());
        } catch (Exception ex) {
            log.error("Error while sending PaymentRequestAvroModel message " +
                    " to kafka with order id : {} and error : {} ", orderId, ex.getMessage());
        }

    }

    private ListenableFutureCallback<SendResult<String, PaymentRequestAvroModel>> getKafkaCallback(
            String paymentResponseTopicName, PaymentRequestAvroModel paymentRequestAvroModel) {
        return new ListenableFutureCallback<SendResult<String, PaymentRequestAvroModel>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending PaymentRequestAvroModel " +
                        "message : {} to topic : {}", paymentRequestAvroModel.toString(), paymentResponseTopicName, ex);
            }

            @Override
            public void onSuccess(SendResult<String, PaymentRequestAvroModel> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful response from kafka for order id : {} " +
                        " Topic : {} Partition : {} Offset : {} TimeStamp : {}",
                        paymentRequestAvroModel.getOrderId(),
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }
        };
    }


}
