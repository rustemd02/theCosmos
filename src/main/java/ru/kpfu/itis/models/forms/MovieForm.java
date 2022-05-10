package ru.kpfu.itis.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.entities.Director;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieForm {
    private Long id;

    private String title;

    private String description;

    private String poster_link;

    private Director director;

    private String age_restriction;

}
