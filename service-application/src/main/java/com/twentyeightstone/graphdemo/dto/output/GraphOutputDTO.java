package com.twentyeightstone.graphdemo.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GraphOutputDTO extends BaseOutputDTO {

    List<VertexOutputDTO> vertices;

    @Builder
    public GraphOutputDTO(Long id, String name, List<VertexOutputDTO> vertices) {
        super(id, name);
        this.vertices = vertices;
    }
}
