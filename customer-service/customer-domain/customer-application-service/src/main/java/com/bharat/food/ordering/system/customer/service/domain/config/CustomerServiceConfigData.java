package com.bharat.food.ordering.system.customer.service.domain.config;
/*
 * @author bharat.verma
 * @created Saturday, 11 March 2023
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "customer-service")
public class CustomerServiceConfigData {
    private String customerTopicName;
}
