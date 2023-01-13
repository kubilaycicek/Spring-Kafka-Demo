package com.kubilaycicek.demo.config.kafka.consumer;

import com.kubilaycicek.demo.constant.KafkaConstant;
import com.kubilaycicek.demo.dto.MessageDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, MessageDto> consumerFactory() {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_ADDRESS);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.GROUP_ID);
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MessageDto.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }
}
