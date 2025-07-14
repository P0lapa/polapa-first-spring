package com.example.first_spring_project.controller;

import com.example.first_spring_project.dto.PhotoResponse;
import com.example.first_spring_project.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<PhotoResponse>> uploadPhoto(@RequestParam("files") MultipartFile[] files) {
        try {
            List<PhotoResponse> savedPhotos = photoService.saveAll(files);
            return ResponseEntity.ok(savedPhotos);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}


