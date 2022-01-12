package com.devko.magnet.controller;

import com.devko.magnet.dto.project.ProjectImageDto;
import com.devko.magnet.service.image.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final S3UploadService s3UploadService; // Autowired 대신 생성자 주입을 해 보았다.

    @PostMapping("image-test")
    public ResponseEntity home(ProjectImageDto projectImage) throws IOException {
        return  s3UploadService.upload(projectImage.getFile(), "test");
    }

    @GetMapping
    public ResponseEntity executeTest() {
        return new ResponseEntity("Hello, World! (ver.Jenkins4)", HttpStatus.OK);
    }
}
