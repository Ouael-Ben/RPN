package com.rpn.api;

import com.rpn.api.domain.StackResponse;
import com.rpn.api.service.Calculator;
import com.rpn.api.service.CalculatorProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Stack;

@SpringBootTest
public class CalculatorProviderTest {

    @Autowired
    private Calculator calculatorProvider = new CalculatorProvider();

    @Test
    public void shouldRemoveStack() {
        StackResponse<String, Stack<Double>> stack = calculatorProvider.create();
        calculatorProvider.remove(stack.getId());
        Assertions.assertThat(calculatorProvider.listElements().size()).isEqualTo(0);
    }
}
