package com.rpn.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Operand {
    @NotNull
    private Double value;
}
