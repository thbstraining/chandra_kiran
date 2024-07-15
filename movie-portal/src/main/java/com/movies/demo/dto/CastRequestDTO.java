package com.movies.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class CastRequestDTO {

	    private Long actorId;
	    private String role;
	
}
