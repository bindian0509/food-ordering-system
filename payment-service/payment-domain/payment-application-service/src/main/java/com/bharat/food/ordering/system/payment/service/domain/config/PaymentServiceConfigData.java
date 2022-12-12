package com.bharat.food.ordering.system.payment.service.domain.config;
/*
 * @author bharat.verma
 * @created Monday, 23 January 2023
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "payment-service")
public class PaymentServiceConfigData {

    private String paymentRequestTopicName;
    private String paymentResponseTopicName;
}
