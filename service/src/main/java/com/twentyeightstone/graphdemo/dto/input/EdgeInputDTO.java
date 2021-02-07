package com.twentyeightstone.graphdemo.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdgeInputDTO {

    private String edgeName;

    private String tailVertexName;

    private String headVertexName;
}
