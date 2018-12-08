package net.devtoon.generator;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
class GeneratorServiceTest {

    @Autowired
    private GeneratorService generator;

    @Test
    void testGeneration() {
        String response = "hello world";
        WireMock.stubFor(WireMock.get(WireMock.urlMatching("\\/work\\?cmd=![\\d]+"))
            .willReturn(WireMock.aResponse().withBody(response).withStatus(200)));

        String actualResponse = generator.generateWork();
        assertThat(actualResponse).isEqualTo(response);
    }
}
