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
    public ResponseEntity<String> saveTempImage(MultipartRequest multipartRequest) {
        MultipartFile multipartFile = multipartRequest.getFile("image");

        String replaceFileName = uploadService.saveTempImage(multipartFile);
        if (replaceFileName == null) {
            return ResponseEntity.badRequest().body("");
        }
        return ResponseEntity.ok(replaceFileName);
    }

    @PostMapping("/delete")
    public Integer deleteTempImage(@RequestBody Map<String, String> body) {
        String fileName = body.getOrDefault("fileName", null);

        Integer result = uploadService.deleteTempImage(fileName);

        return result;

    }
}
