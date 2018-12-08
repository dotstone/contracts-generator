package net.devtoon.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@PropertySource("classpath:application.properties")
class GeneratorService {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorService.class);

    private final Random rng = new Random(0x1337);

    @Value("${workerPort}")
    private String workerPort;

    String generateWork() {
        String payload = "!" + String.valueOf(rng.nextLong()).replace("-", "");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + workerPort + "/work?cmd=" + payload, String.class);
        LOG.info("Server responded with " + response.getStatusCode() + ": " + response.getBody());
        return response.getBody();
    }
}
