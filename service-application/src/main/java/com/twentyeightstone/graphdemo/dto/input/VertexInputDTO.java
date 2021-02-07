package com.twentyeightstone.graphdemo.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VertexInputDTO {

    @NotBlank
    private String vertexName;
}
