package io.mozmani.fibonacci.dtos;

import java.util.List;

public record FibonacciResponse(String msg, Integer idx, List<Integer> result) {

}
