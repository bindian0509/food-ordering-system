package com.bharat.food.ordering.system.order.service.messaging.publisher.kafka;

import com.bharat.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class OrderKafkaMessageHelper {

    public  ListenableFutureCallback<SendResult<String, PaymentRequestAvroModel>> getKafkaCallback(
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
