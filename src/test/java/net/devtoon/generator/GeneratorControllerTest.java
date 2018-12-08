package net.devtoon.generator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeneratorControllerTest {

    @InjectMocks
    private GeneratorController controller;

    @Mock
    private GeneratorService service;

    @Test
    void testController() {
        when(service.generateWork()).thenReturn("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");
        String response = controller.generate();
        verify(service, times(10)).generateWork();
        assertThat(response).isEqualTo(IntStream.range(1, 11).mapToObj(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
    }
}
