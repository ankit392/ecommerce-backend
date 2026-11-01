package com.mybasket.app.service;

import com.mybasket.app.entity.FileMetaData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    FileMetaData uploadFile(MultipartFile file) throws IOException;

}
