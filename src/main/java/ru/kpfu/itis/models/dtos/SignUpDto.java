package ru.kpfu.itis.models.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class SignUpDto {
    private String name;
    private String email;
    private String password;
    private String repassword;
}
