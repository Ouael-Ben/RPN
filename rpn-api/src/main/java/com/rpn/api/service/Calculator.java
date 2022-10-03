package com.rpn.api.service;

import com.rpn.api.domain.StackResponse;

import java.util.List;

public interface Calculator <Identifier, Type , Elements extends Iterable<Type>>{
    StackResponse<Identifier, Elements> create();
    StackResponse<Identifier, Elements> push(Identifier id, Type value);
    StackResponse<Identifier, Elements> calculate(Identifier id, String operand);
    List<StackResponse<Identifier, Elements>> listElements();
    StackResponse<Identifier, Elements> loadElement(Identifier id);
    void remove(Identifier id);
}
