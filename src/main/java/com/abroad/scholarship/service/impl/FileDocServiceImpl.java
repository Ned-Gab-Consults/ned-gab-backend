package com.abroad.scholarship.service.impl;

import com.abroad.scholarship.exception.FileNotFoundException;
import com.abroad.scholarship.models.FileDoc;
import com.abroad.scholarship.models.Person;
import com.abroad.scholarship.repository.FileDocRepository;
import com.abroad.scholarship.repository.PersonRepository;
import com.abroad.scholarship.service.FileDocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileDocServiceImpl implements FileDocService {
    private final PersonRepository personRepo;
    private final FileDocRepository fileDocRepo;
    @Value("${file.location}")
    private String root;
    @Override
    public ResponseEntity<FileDoc> createFile(MultipartFile file, String name) {
        Person p = getPerson();
        String[] docData = upload(file);
        FileDoc fd = FileDoc.builder()
                .fileName(name)
                .fileDocName(docData[0])
                .fileLocation(docData[1])
                .person(p)
                .build();
        return ResponseEntity.ok(fileDocRepo.save(fd));
    }

    private String[] upload(MultipartFile inputFile) {
        double random = Math.random();
        String randomNumber = Double.toString(random);
        String result;
        String[] toReturn =  null;

        if (!inputFile.isEmpty()) {
            try {
                String originalFilename = inputFile.getOriginalFilename();
                File destinationFile = new File(root + File.separator + originalFilename);
                if (destinationFile.exists()) {
                    originalFilename = randomNumber.concat(Objects.requireNonNull(inputFile.getOriginalFilename()));
                    destinationFile = new File(root + File.separator + originalFilename);
                }
                inputFile.transferTo(destinationFile);
                result = destinationFile.toString();

                toReturn = new String[]{originalFilename,result};

            } catch (Exception e) {
                log.error("File unable to be saved");
                throw new FileNotFoundException("File pass error");
            }
        } else {
            throw new FileNotFoundException("File Not found");
        }

        return toReturn;
    }

    @Override
    public ResponseEntity<List<FileDoc>> fetchFiles(long id) {
        Person p = personRepo.findById(id).get();
        List<FileDoc> fileDocList = fileDocRepo.findFileDocsByPerson(p);
        return ResponseEntity.ok(fileDocList);
    }

    @Override
    public ResponseEntity<FileDoc> upDate(MultipartFile file, long id) {
        FileDoc fd = fileDocRepo.findById(id).get();
        String[] docData = upload(file);
        fd.setFileLocation(docData[1]);
        fd.setFileName(docData[0]);
        return ResponseEntity.ok(fileDocRepo.save(fd));
    }

    @Override
    public void downloadFile(HttpServletResponse response, long id) {
        FileDoc fd = fileDocRepo.findById(id).get();
        try {

            InputStream inputStream = new FileInputStream(fd.getFileLocation());
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + fd.getFileDocName());
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception) {
            System.out.println("File does not Exist");
        }
    }

    private Person getPerson(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person p = personRepo.findPersonByEmail(user.getUsername()).get();
        return p;
    }
}
