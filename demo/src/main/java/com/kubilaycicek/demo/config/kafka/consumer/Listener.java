package com.kubilaycicek.demo.config.kafka.consumer;

import com.kubilaycicek.demo.constant.KafkaConstant;
import com.kubilaycicek.demo.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Listener {
    @KafkaListener(
            topics = KafkaConstant.KAFKA_TOPIC,
            groupId = KafkaConstant.GROUP_ID
    )
    public void listen(@Payload MessageDto messageDto) {
        log.info("Message received.. MessageID : {} Message: {} Date : {}",
                messageDto.getId(),
                messageDto.getMessage(),
                messageDto.getMessageDate());
    }
}
