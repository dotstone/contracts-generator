package net.devtoon.generator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ComponentTest {

    @LocalServerPort
    private int ownPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Source source;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    void test() {
        // send request
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + ownPort + "/", String.class);
        assertThat(entity.getStatusCode().value()).isEqualTo(200);
        assertThat(entity.getBody()).isEqualTo("Success");

        // verify that messages were sent
        for (int i = 0; i < 10; i++) {
            Message<String> received = getNextMessage();
            assertThat(received).as("Message " + i + " was not sent").isNotNull();
            assertThat(received.getPayload()).as("Message content of message " + i + " is invalid").matches("!-?\\d+");
        }
        assertThat(getNextMessage()).as("Too many messages sent!").isNull();
    }

    private Message<String> getNextMessage() {
        return (Message<String>) messageCollector.forChannel(source.output()).poll();
    }
}
