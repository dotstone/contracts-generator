package net.devtoon.generator;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public abstract class BaseContractTest extends AbstractServiceTest {

    @Value("${wiremock.server.port}")
    private Integer workerPort;

    @Autowired
    private GeneratorController controller;

    @Autowired
    private GeneratorService generatorService;

    @Before
    public void setupWorkerStub() {
        ReflectionTestUtils.setField(generatorService, "workerPort", String.valueOf(workerPort));

        RestAssuredMockMvc.standaloneSetup(controller);

        String singleResponse = "expected response";
        WireMock.stubFor(WireMock.get(WireMock.urlMatching("\\/work\\?cmd=![\\d]+"))
                .willReturn(WireMock.aResponse().withBody(singleResponse).withStatus(200)));
    }
}
