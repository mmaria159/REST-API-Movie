package com.endava.movie.model;

import lombok.Data;

@Data
public class MovieDTO {
    private Long id;
    private String description;
    private String name;
    private Double budget;
}
