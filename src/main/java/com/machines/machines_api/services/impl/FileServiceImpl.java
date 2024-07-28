package com.machines.machines_api.services.impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.machines.machines_api.enums.FileType;
import com.machines.machines_api.exceptions.files.FileNotFoundException;
import com.machines.machines_api.exceptions.files.UnsupportedFileTypeException;
import com.machines.machines_api.repositories.FileRepository;
import com.machines.machines_api.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final MessageSource messageSource;

    @Override
    public List<com.machines.machines_api.models.entity.File> upload(MultipartFile[] multipartFiles) throws IOException {
        List<com.machines.machines_api.models.entity.File> savedFiles = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));


//            File file = this.convertToFile(multipartFile, fileName);

            savedFiles.add(this.uploadFile(multipartFile, fileName));
        }
        return savedFiles;
    }

    @Override
    public com.machines.machines_api.models.entity.File uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
        String extension = getExtension(fileName);
        System.out.println("EXTENSION " + extension);

        if (!FileType.isSupportedExtension(extension) && !(extension.startsWith(".com"))) {
            throw new UnsupportedFileTypeException(messageSource);
        }

        BlobId blobId = BlobId.of("localweb-428009.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream inputStream = FileServiceImpl.class.getClassLoader().getResourceAsStream("firebase.json");

        if (inputStream == null) {
            return null;
        }

        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, multipartFile.getBytes());

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/localweb-428009.appspot.com/o/%s?alt=media";
        String path = String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        return saveFileToDatabase(fileName, path, extension, multipartFile.getSize());
    }

    @Override
    public com.machines.machines_api.models.entity.File getEntityById(UUID id) {
        var file = fileRepository.findByIdAndDeletedAtIsNull(id);

        if (file.isEmpty()) {
            throw new FileNotFoundException();
        }

        return file.get();
    }

    private com.machines.machines_api.models.entity.File saveFileToDatabase(String fileName, String path, String extension, long size) {
        com.machines.machines_api.models.entity.File fileUpload = new com.machines.machines_api.models.entity.File();
        fileUpload.setName(fileName);
        fileUpload.setPath(path);
        fileUpload.setType(extension);
        fileUpload.setSize(size);
        return fileRepository.save(fileUpload);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}