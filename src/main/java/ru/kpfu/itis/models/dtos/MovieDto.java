package ru.kpfu.itis.models.dtos;


import lombok.Data;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String description;
    private String ageRestriction;
    private String director;
    private String genre;

}
