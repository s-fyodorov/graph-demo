package com.twentyeightstone.graphdemo.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EdgeInputDTO {

    @NotBlank
    private String edgeName;

    @NotBlank
    private String fromVertexName;

    @NotBlank
    private String toVertexName;
}
