package com.devko.magnet.dto.project;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProjectImageDto {
    MultipartFile file;
}
