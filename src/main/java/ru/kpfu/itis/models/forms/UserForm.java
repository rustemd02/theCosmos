package ru.kpfu.itis.models.forms;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    private String name;
    private String email;
    private String password;

}