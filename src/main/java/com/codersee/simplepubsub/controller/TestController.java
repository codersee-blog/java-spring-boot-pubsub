package com.codersee.simplepubsub.controller;

import com.codersee.simplepubsub.model.MessageEntity;
import com.codersee.simplepubsub.publisher.ExamplePublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TestController {

    private final ExamplePublisher publisher;

    public TestController(ExamplePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping("/topic")
    public void publish(@RequestBody String message) {
        MessageEntity entity = new MessageEntity(LocalDateTime.now(), message);
        publisher.publish(entity);
    }
}
