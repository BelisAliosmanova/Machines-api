package com.machines.machines_api.services;

import com.machines.machines_api.models.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileService {
    File upload(MultipartFile multipartFiles) throws IOException;

    File uploadFile(MultipartFile multipartFile, String fileName) throws IOException;

    File getEntityById(UUID id);
}