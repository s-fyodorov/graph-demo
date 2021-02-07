package com.twentyeightstone.graphdemo.dto.output;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EdgeOutputDTO extends BaseOutputDTO {

    private Long headVertexId;

    private String headVertexName;

    @Builder
    public EdgeOutputDTO(Long id, String name, Long headVertexId, String headVertexName) {
        super(id, name);
        this.headVertexId = headVertexId;
        this.headVertexName = headVertexName;
    }
}
