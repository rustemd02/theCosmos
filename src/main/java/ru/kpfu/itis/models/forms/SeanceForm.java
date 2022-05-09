package ru.kpfu.itis.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.entities.Movie;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeanceForm {
    private Long id;

    private Movie movie;

    private Timestamp time;

    private Integer ticketsAmount;

    private Integer price;
}
