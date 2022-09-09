package com.abroad.scholarship.service;

import com.abroad.scholarship.models.FileDoc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileDocService {
    ResponseEntity<FileDoc> createFile(MultipartFile file, String name);

    ResponseEntity<List<FileDoc>> fetchFiles(long id);

    ResponseEntity<FileDoc> upDate(MultipartFile file, long id);

    void downloadFile(HttpServletResponse response, long id);
}
