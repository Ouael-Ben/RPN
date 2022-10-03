package com.rpn.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StackResponse<Identifier, Elements> {
    private Identifier id;
    private Elements stack;
}
