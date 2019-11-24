package org.project.study.controller;

import org.project.study.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/image")
    public ResponseEntity<Map> saveTempImage(MultipartRequest multipartRequest) {
        MultipartFile multipartFile = multipartRequest.getFile("image");

        Map replaceFileName = uploadService.saveS3Image(multipartFile);
        if (replaceFileName == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(replaceFileName);
    }

    @PostMapping("/delete")
    public ResponseEntity deleteTempImage(@RequestBody Map<String, String> body) {
        String fileName = body.getOrDefault("fileName", null);

        uploadService.deleteS3Image(fileName);

        return ResponseEntity.ok().build();
    }
}
