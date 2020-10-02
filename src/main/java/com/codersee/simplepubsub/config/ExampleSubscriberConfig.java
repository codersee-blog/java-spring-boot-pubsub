package com.codersee.simplepubsub.config;

import com.codersee.simplepubsub.consumer.ExampleConsumer;
import com.codersee.simplepubsub.consumer.PubSubConsumer;
import com.codersee.simplepubsub.model.MessageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ExampleSubscriberConfig {

    private static final Logger log = LoggerFactory.getLogger(ExampleSubscriberConfig.class);

    private final PubSubTemplate pubSubTemplate;
    private final PubSubConsumer<MessageEntity> exampleConsumer;

    public ExampleSubscriberConfig(PubSubTemplate pubSubTemplate, ExampleConsumer exampleConsumer) {
        this.pubSubTemplate = pubSubTemplate;
        this.exampleConsumer = exampleConsumer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        log.info("Subscribing to {}", exampleConsumer.subscription());

        pubSubTemplate.subscribeAndConvert(
            exampleConsumer.subscription(),
            exampleConsumer.messageConsumer(),
            exampleConsumer.payloadType()
        );
    }
}
