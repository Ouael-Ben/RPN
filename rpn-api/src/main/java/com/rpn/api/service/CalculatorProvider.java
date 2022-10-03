package com.rpn.api.service;

import com.rpn.api.domain.Operation;
import com.rpn.api.domain.StackResponse;
import com.rpn.api.exception.RpnApiException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

@Service
public class CalculatorProvider implements Calculator<String, Double, Stack<Double>> {

    Map<String, Stack<Double>> stacksMap = new HashMap<>();

    @Override
    public StackResponse<String, Stack<Double>> create() {
        String id = generateId();
        stacksMap.put(id, new Stack<>());
        return new StackResponse<>(id, stacksMap.get(id));
    }

    @Override
    public StackResponse<String, Stack<Double>> push(String id, Double value) {

        return Optional.ofNullable(stacksMap.get(id)).map((Stack<Double> stack) -> {
            // side effect to add element to the stack
            stack.add(value);
            return new StackResponse<>(id, stack);
        }).orElseThrow(() -> new RpnApiException("this stack {" + id + "} not exist"));
    }

    @Override
    public StackResponse<String, Stack<Double>> calculate(String id, String operand) {
        return Optional.ofNullable(stacksMap.get(id))
                .map((Stack<Double> stack) -> {
                    if (stack.size() > 1) {
                        stack.push(Operation.getOperationBySymbole(operand).calculate(stack.pop(), stack.pop()));
                    }
                    return new StackResponse<>(id, stack);
                }).orElseThrow(() -> new RpnApiException("this stack {" + id + "} doesn't exist"));
    }

    @Override
    public List<StackResponse<String, Stack<Double>>> listElements() {
        return Optional.of(stacksMap.entrySet())
                .map((Set<Map.Entry<String, Stack<Double>>> entries) -> entries.stream()
                        .map((Map.Entry<String, Stack<Double>> entry) -> new StackResponse<>(entry.getKey(), entry.getValue()))).get().toList();
    }

    @Override
    public StackResponse<String, Stack<Double>> loadElement(String id) {
        return Optional.ofNullable(stacksMap.get(id))
                .map((Stack<Double> stack) -> new StackResponse<>(id, stack))
                .orElseThrow(() -> new RpnApiException("this stack {" + id + "} not exist"));
    }

    @Override
    public void remove(String id) {
        stacksMap.remove(id);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}
