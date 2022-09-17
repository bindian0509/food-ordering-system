package com.bharat.food.ordering.system.kafka.producer.service;

import com.google.common.util.concurrent.ListenableFuture;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {

    void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K,V>> callback);

}
