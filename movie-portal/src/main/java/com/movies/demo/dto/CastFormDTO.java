package com.movies.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CastFormDTO {
    private Long movieId;
    private Long actorId;
    private String role;
}
