package net.devtoon.generator;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeneratorServiceTest extends AbstractServiceTest {

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
