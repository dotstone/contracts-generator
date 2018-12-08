package net.devtoon.generator;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
@AutoConfigureMessageVerifier
public abstract class BaseContractTest {

    @Autowired
    private GeneratorController controller;

    @Before
    public void setupWorkerStub() {
        RestAssuredMockMvc.standaloneSetup(controller);
    }

    public void triggerWork() {
        controller.generate();
    }
}
