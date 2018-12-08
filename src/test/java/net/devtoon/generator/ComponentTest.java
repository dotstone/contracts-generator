package net.devtoon.generator;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ComponentTest extends AbstractServiceTest {

    @LocalServerPort
    private int ownPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test() {
        String singleResponse = "expected response";
        WireMock.stubFor(WireMock.get(WireMock.urlMatching("\\/work\\?cmd=![\\d]+"))
                .willReturn(WireMock.aResponse().withBody(singleResponse).withStatus(200)));

        String expectedResponse = IntStream.range(0, 10).mapToObj(i -> singleResponse).collect(Collectors.joining(System.lineSeparator()));

        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + ownPort + "/generate", String.class);
        assertThat(entity.getStatusCode().value()).isEqualTo(200);
        assertThat(entity.getBody()).isEqualTo(expectedResponse);
    }
}
