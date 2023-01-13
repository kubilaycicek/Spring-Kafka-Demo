package com.kubilaycicek.demo.api;

import com.kubilaycicek.demo.constant.KafkaConstant;
import com.kubilaycicek.demo.dto.MessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/message/")
public class MessageController {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public MessageController(KafkaTemplate<String, MessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value = "send")
    public void sendMessage(@RequestBody MessageDto messageDto) {
        kafkaTemplate.send(KafkaConstant.KAFKA_TOPIC, UUID.randomUUID().toString(), messageDto);
    }

}

