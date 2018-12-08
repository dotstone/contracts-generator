package net.devtoon.generator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class GeneratorControllerTest {

    @InjectMocks
    private GeneratorController controller;

    @Mock
    private GeneratorService service;

    @Test
    void testController() {
        String response = controller.generate();
        verify(service, times(10)).generateWork();
        assertThat(response).isEqualTo("Success");
    }
}
