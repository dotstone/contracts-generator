package net.devtoon.generator;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.util.ReflectionTestUtils;

@AutoConfigureWireMock
class AbstractServiceTest {

    @Value("${wiremock.server.port}")
    private Integer workerPort;

    @Autowired
    private GeneratorService generatorService;

    @BeforeEach
    final void setupWorkerPort() {
        ReflectionTestUtils.setField(generatorService, "workerPort", String.valueOf(workerPort));
    }
}
