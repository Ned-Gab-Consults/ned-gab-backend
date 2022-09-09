package com.abroad.scholarship.controller;

import com.abroad.scholarship.models.FileDoc;
import com.abroad.scholarship.service.FileDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@CrossOrigin("*")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileDocController {
    private final FileDocService fileDocService;

    @PostMapping("/{name}")
    public ResponseEntity<FileDoc> createAfile(@RequestPart MultipartFile file, @PathVariable String name){
        return fileDocService.createFile(file, name);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FileDoc>> fetchFiles(@PathVariable long userId){
        return fileDocService.fetchFiles(userId);
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<FileDoc> upDateFile(@RequestPart MultipartFile file,@PathVariable long fileId){
        return fileDocService.upDate(file,fileId);
    }

    @GetMapping("/download/{fileId}")
    public void downloadfile(HttpServletResponse response, @PathVariable long fileId){
        fileDocService.downloadFile(response, fileId);
    }


}
