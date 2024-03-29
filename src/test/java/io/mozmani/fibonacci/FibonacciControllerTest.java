package io.mozmani.fibonacci;

import io.mozmani.fibonacci.controllers.FibonacciController;
import io.mozmani.fibonacci.dtos.FibonacciResponse;
import io.mozmani.fibonacci.services.FibonacciService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FibonacciControllerTest {
    @Mock
    FibonacciService fibService;

    @InjectMocks
    private FibonacciController fibController;

    @Test
    public void indexShouldNotGoNegative() {
        when(fibService.processSequence(-1)).thenReturn(generateErrorResponse());
        FibonacciResponse res = fibController.getPreviousSequence();
        assertThat(res.msg()).isEqualTo(generateErrorResponse().msg());
        assertThat(fibController.getIndex()).isEqualTo(0);
        assertThat(res.idx()).isEqualTo(-1);
    }

    @Test
    public void currentIndexShouldDisplaySequence() {
        when(fibService.processSequence(0)).thenReturn(generateCurrentZeroResponse());
        FibonacciResponse res = fibController.getCurrentSequence();
        assertThat(res.msg()).isEqualTo(generateCurrentZeroResponse().msg());
        assertThat(fibController.getIndex()).isEqualTo(0);
        assertThat(res.idx()).isEqualTo(0);
    }

    @Test
    public void nextIndexShouldDisplaySequence() {
        when(fibService.processSequence(1)).thenReturn(generateOneResponse());
        FibonacciResponse res = fibController.getNextSequence();
        assertThat(res.msg()).isEqualTo(generateOneResponse().msg());
        assertThat(fibController.getIndex()).isEqualTo(1);
        assertThat(res.idx()).isEqualTo(1);
    }

    @Test
    public void previousIndexShouldDisplaySequence() {
        when(fibService.processSequence(1)).thenReturn(generateOneResponse());
        when(fibService.processSequence(0)).thenReturn(generateCurrentZeroResponse());
        // Moving index to 1 so previous works.
        FibonacciResponse res = fibController.getNextSequence();
        res = fibController.getPreviousSequence();
        assertThat(res.msg()).isEqualTo(generateCurrentZeroResponse().msg());
        assertThat(fibController.getIndex()).isEqualTo(0);
        assertThat(res.idx()).isEqualTo(0);
    }

    private FibonacciResponse generateErrorResponse() {
        return new FibonacciResponse(
                "Your attempt to find the fibonacci sequence for: -1 is invalid because it's negative",
                -1, List.of(0));
    }

    private FibonacciResponse generateCurrentZeroResponse() {
        return new FibonacciResponse(
                "Here is the fibonacci sequence for 0",
                0, List.of(0));
    }

    private FibonacciResponse generateOneResponse() {
        return new FibonacciResponse(
                "Here is the fibonacci sequence for 1",
                1, List.of(0,1));
    }
}
