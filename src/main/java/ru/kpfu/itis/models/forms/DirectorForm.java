package ru.kpfu.itis.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.entities.Movie;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorForm {
    private Long id;

    private String name;

    private Movie movies;
}
