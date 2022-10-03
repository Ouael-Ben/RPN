package com.rpn.api.controller;

import com.rpn.api.domain.Operation;
import com.rpn.api.domain.StackResponse;
import com.rpn.api.dto.Operator;
import com.rpn.api.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

@RestController
@RequestMapping("/api/rpn")
@CrossOrigin(origins = "http://localhost:4200/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
public class RpnController {

    private final Calculator<String, Double, Stack<Double>> calculatorProvider;

    @Autowired
    public RpnController(Calculator<String, Double, Stack<Double>> calculatorProvider) {
        this.calculatorProvider = calculatorProvider;
    }

    @GetMapping("/op")
    public ResponseEntity<List<String>> getListAllOperand() {
        return ResponseEntity.ok(Operation.getAllOperand());
    }

    @PostMapping("/stack")
    public ResponseEntity<StackResponse<String, Stack<Double>>> createStack() {
        return ResponseEntity.ok(calculatorProvider.create());
    }

    @GetMapping("/stack")
    public ResponseEntity<List<StackResponse<String, Stack<Double>>>> getListStacks() {
        return ResponseEntity.ok(calculatorProvider.listElements());
    }

    @DeleteMapping("/stack/{stock_id}")
    public ResponseEntity<Void> deleteStack(@PathVariable("stock_id") String stockId) {
        calculatorProvider.remove(stockId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stack/{stock_id}")
    public ResponseEntity<StackResponse<String, Stack<Double>>> addValue(@PathVariable("stock_id") String stockId, @RequestBody Map<String, Double> value) {
        if(Objects.isNull(value.get("value")))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(calculatorProvider.push(stockId, value.get("value")));
    }

    @PostMapping("/op/stack/{stock_id}")
    public ResponseEntity<StackResponse<String, Stack<Double>>> applyOperator(@RequestBody Operator operator, @PathVariable("stock_id") String stockId) {
        return ResponseEntity.ok(calculatorProvider.calculate(stockId, operator.getOperator()));
    }

    @GetMapping("/stack/{stock_id}")
    public ResponseEntity<StackResponse<String, Stack<Double>>> getStack(@PathVariable("stock_id") String stockId) {
        return ResponseEntity.ok(calculatorProvider.loadElement(stockId));
    }

}
