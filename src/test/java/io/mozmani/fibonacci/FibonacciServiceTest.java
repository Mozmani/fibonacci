package io.mozmani.fibonacci;

import io.mozmani.fibonacci.dtos.FibonacciResponse;
import io.mozmani.fibonacci.services.FibonacciService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {
    @InjectMocks
    private FibonacciService fibService;

    @Test
    public void attemptingNegative_SendsError() {
        String negativeResponse = "Your attempt to find the fibonacci sequence for: -1 is invalid because it's negative";
        FibonacciResponse res = fibService.processSequence(-1);
        assertThat(res.msg()).isEqualTo(negativeResponse);
        assertThat(res.result()).isEqualTo(List.of(0));
    }
}
