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

    @GetMapping("/{id}")
    public ResponseEntity<List<FileDoc>> fetchFiles(@PathVariable long id){
        return fileDocService.fetchFiles(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FileDoc> upDateFile(@PathVariable long id, @RequestPart MultipartFile file){
        return fileDocService.upDate(file,id);
    }

    @GetMapping("/download/{id}")
    public void downloadfile(HttpServletResponse response, @PathVariable long id){
        fileDocService.downloadFile(response, id);
    }


}
