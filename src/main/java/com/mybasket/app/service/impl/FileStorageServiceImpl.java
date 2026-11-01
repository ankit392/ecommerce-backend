package com.mybasket.app.service.impl;

import com.mybasket.app.entity.FileMetaData;
import com.mybasket.app.repository.FileMetaDataRepo;
import com.mybasket.app.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {


    @Value("${app.files.upload.folder.products}")
    String productUploadFolder;


    private final FileMetaDataRepo fileMetaDataRepo;

    @Override
    public FileMetaData uploadFile(MultipartFile file) throws IOException {


        String fileName= file.getOriginalFilename();
        String storeName= System.currentTimeMillis() +"_"+file.getOriginalFilename();


        //folder
        Path path = Files.createDirectories(Paths.get(productUploadFolder));

        Path filepath = path.resolve(storeName);


        // copy files from file obj to the file path
        Files.copy(file.getInputStream(),filepath, StandardCopyOption.REPLACE_EXISTING);


        FileMetaData fileMetaData = new FileMetaData();

        fileMetaData.setFileSize(file.getSize());
        fileMetaData.setOriginalName(file.getOriginalFilename());
        fileMetaData.setStoredName(storeName);

        fileMetaData.setFileType(file.getContentType());
        fileMetaData.setFileUrl(filepath.toString());
        fileMetaData.setStorageType("local");


        FileMetaData save = fileMetaDataRepo.save(fileMetaData);



        return save;
    }
}
