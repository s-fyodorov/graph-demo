package com.twentyeightstone.graphdemo.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VertexOutputDTO extends BaseOutputDTO {

    List<EdgeOutputDTO> outcomeEdges;

    @Builder
    public VertexOutputDTO(Long id, String name, List<EdgeOutputDTO> outcomeEdges) {
        super(id, name);
        this.outcomeEdges = outcomeEdges;
    }
}
