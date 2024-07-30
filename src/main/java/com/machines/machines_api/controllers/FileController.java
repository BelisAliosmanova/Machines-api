package com.machines.machines_api.controllers;

import com.machines.machines_api.models.entity.File;
import com.machines.machines_api.services.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@Tag(name = "Files")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public File upload(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        return fileService.upload(multipartFile);
    }
}