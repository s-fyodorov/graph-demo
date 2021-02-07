package com.twentyeightstone.graphdemo.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeleteEdgeDTO {

    @NotBlank
    private String firstVertexName;

    @NotBlank
    private String secondVertexName;
}
