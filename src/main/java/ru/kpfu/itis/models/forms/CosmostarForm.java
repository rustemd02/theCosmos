package ru.kpfu.itis.models.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.models.entities.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CosmostarForm {

    private Long id;
    private Integer points;
    private User user;
}
