package io.mozmani.fibonacci.controllers;

import io.mozmani.fibonacci.dtos.FibonacciResponse;
import io.mozmani.fibonacci.services.FibonacciService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FibonacciController {

    private final FibonacciService fibService;
    @Getter
    private Integer index = 0;

    @GetMapping("/current")
    public FibonacciResponse getCurrentSequence() {
        return fibService.processSequence(index);
    }

    @GetMapping("/next")
    public FibonacciResponse getNextSequence() {
        index++;
        return fibService.processSequence(index);
    }

    @GetMapping("/previous")
    public FibonacciResponse getPreviousSequence() {
        if (index <= 0) {
            return fibService.processSequence(index - 1);
        }
        index--;
        return fibService.processSequence(index);
    }

}
