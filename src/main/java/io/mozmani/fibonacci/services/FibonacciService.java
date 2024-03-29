package io.mozmani.fibonacci.services;

import io.mozmani.fibonacci.dtos.FibonacciResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FibonacciService {

    // Simple memory cache to reduce redundant processing.
    private final Map<Integer, FibonacciResponse> resultCache = new HashMap<>();


    public FibonacciResponse processSequence(Integer index) {
        if (index <= 0) {
            return new FibonacciResponse(processMessage(index), index, List.of(0));
        } else if (index == 1) {
            return new FibonacciResponse(processMessage(index), index, List.of(0, 1));
        }
        // If cached, return from cache.
        if (resultCache.containsKey(index)) {
            return resultCache.get(index);
        }

        List<Integer> seqList = new java.util.ArrayList<>(List.of(0, 1));
        int n1 = 0;
        int n2 = 1;
        int tempN;
        for (int i = 2; i <= index; i++) {
            tempN = n1 + n2;
            n1 = n2;
            n2 = tempN;
            seqList.add(n2);
        }
        FibonacciResponse result = new FibonacciResponse(processMessage(index), index, seqList);
        resultCache.put(index, result);
        return result;
    }

    private String processMessage(Integer idx) {
        if (idx < 0) {
            return String.format("Your attempt to find the fibonacci sequence for: %s " +
                    "is invalid because it's negative", idx);
        }
        return String.format("Here is the fibonacci sequence for %s", idx);
    }
}
