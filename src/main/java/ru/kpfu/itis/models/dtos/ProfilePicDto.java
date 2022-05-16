package ru.kpfu.itis.models.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfilePicDto {
    private Long userId;
    private MultipartFile profilePicFile;
}
