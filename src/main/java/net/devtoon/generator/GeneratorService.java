package net.devtoon.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
class GeneratorService {

    private final Random rng = new Random(0x1337);

    @Autowired
    private Source source;

    void generateWork() {
        String payload = "!" + String.valueOf(rng.nextLong()).replace("-", "");
        source.output().send(MessageBuilder.withPayload(payload).build());
    }
}
