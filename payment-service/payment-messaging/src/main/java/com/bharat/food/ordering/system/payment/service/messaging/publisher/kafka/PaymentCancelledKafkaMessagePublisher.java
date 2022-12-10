package com.bharat.food.ordering.system.payment.service.messaging.publisher.kafka;
/*
 * @author bharat.verma
 * @created Sunday, 29 January 2023
 */

import com.bharat.food.orderin.system.payment.service.domain.config.PaymentServiceConfigData;
import com.bharat.food.orderin.system.payment.service.domain.ports.output.message.publisher.PaymentCancelledMessagePublisher;
import com.bharat.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.bharat.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.bharat.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.bharat.food.ordering.system.payment.service.domain.event.PaymentCancelledEvent;
import com.bharat.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCancelledKafkaMessagePublisher implements PaymentCancelledMessagePublisher {


    private final PaymentMessagingDataMapper paymentMessagingDataMapper;
    private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
    private final PaymentServiceConfigData paymentServiceConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;

    public PaymentCancelledKafkaMessagePublisher(PaymentMessagingDataMapper paymentMessagingDataMapper,
                                                 KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer,
                                                 PaymentServiceConfigData paymentServiceConfigData,
                                                 KafkaMessageHelper orderKafkaMessageHelper) {
        this.paymentMessagingDataMapper = paymentMessagingDataMapper;
        this.kafkaProducer = kafkaProducer;
        this.paymentServiceConfigData = paymentServiceConfigData;
        this.kafkaMessageHelper = orderKafkaMessageHelper;
    }

    @Override
    public void publish(PaymentCancelledEvent domainEvent) {

        String orderId = domainEvent.getPayment().getOrderId().getValue().toString();

        log.info("Received paymentCancelledEvent for orderId : {} ", orderId);

        try {
            PaymentResponseAvroModel paymentResponseAvroModel =
                    paymentMessagingDataMapper.paymentCancelledEventToPaymentResponseAvroModel(domainEvent);

            kafkaProducer.send(paymentServiceConfigData.getPaymentResponseTopicName(),
                    orderId,
                    paymentResponseAvroModel,
                    kafkaMessageHelper.getKafkaCallback(
                            paymentServiceConfigData.getPaymentResponseTopicName(),
                            paymentResponseAvroModel,
                            orderId,
                            "PaymentResponseAvroModel")
                    );
            log.info("PaymentResponseAvroModel sent to Kafka for orderId : {} ", orderId);
        } catch (Exception e) {
            log.error("Error while sending PaymentResponseAvroModel message "+
                    " to kafka with orderId : {} and error  : {}", orderId, e.getMessage());
        }
    }

}
