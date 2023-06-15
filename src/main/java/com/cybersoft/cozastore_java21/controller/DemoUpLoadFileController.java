package com.cybersoft.cozastore_java21.controller;


import com.cybersoft.cozastore_java21.exception.CustomFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/demo")
public class DemoUpLoadFileController {
    //Path: chua ham ho tro san lien quan den duong dan
    @Value("${path.root}")
    private String spath;


    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) {
        // Dinh nghia duong dan
        System.out.println("Spanth " + spath);
        Path rootPath = Paths.get(spath);
        try {
            if (!Files.exists(rootPath)) {
                // Tao folder moi neu khong ton tai
                Files.createDirectories(rootPath);
            }
//            /e/Java_BE_Cybersoft/image21/abc.jpg
//            resolve tuong duong /
//          file.getOriginalFilename() Lay ten file va dinh dang

            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Loi " + e.getLocalizedMessage());
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {


        try {
            Path rootPath = Paths.get(spath);
            System.out.println("download url " + rootPath);
            Resource resource = new UrlResource(rootPath.resolve(fileName).toUri());
            if (resource.exists()) {
                // Neu ton tai cho phep download
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new CustomFileNotFoundException(200,"File not found");
            }

        } catch (Exception e) {
            throw new CustomFileNotFoundException(200,"File not found");
        }

    }
}
