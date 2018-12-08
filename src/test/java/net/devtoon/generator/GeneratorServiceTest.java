package net.devtoon.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeneratorServiceTest {

    @Autowired
    private GeneratorService generator;

    @Autowired
    private Source source;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    void testGeneration() {
        generator.generateWork();
        Message<String> received = (Message<String>) messageCollector.forChannel(source.output()).poll();
        assertThat(received).isNotNull();
        assertThat(received.getPayload()).matches("!-?\\d+");
    }
}
