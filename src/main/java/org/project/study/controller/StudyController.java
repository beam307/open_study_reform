package org.project.study.controller;

import org.project.study.model.Category;
import org.project.study.model.MajorRegion;
import org.project.study.model.MinorRegion;
import org.project.study.model.Study;
import org.project.study.service.StudyService;
import org.project.study.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/study")
public class StudyController {

    @Autowired
    private StudyService studyService;

    @Autowired
    private UploadService uploadService;

    @PostMapping("/create")
    public ResponseEntity<String> saveStudy(@RequestBody Study study) {
        try {
            studyService.insertStudy(study);

           return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Study>> studyList() {
        return ResponseEntity.ok(studyService.getStudyList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Study> studyOne(@PathVariable Long id) {
        Study study = studyService.getStudy(id);

        if(study == null) {
            return ResponseEntity.notFound().build();
        }
        

        return ResponseEntity.ok(studyService.getStudy(id));
    }

    @GetMapping("/category")
    public Map<String, List<Category>> getCategoryList() {
        return studyService.getCategoryList();
    }

    @GetMapping("/minorRegion")
    public Map<Integer, List<MinorRegion>> getMinorRegionList() {
        return studyService.getMinorRegionList();
    }

    @GetMapping("/majorRegion")
    public List<MajorRegion> getMajorRegionList() {
        return studyService.getMajorRegionList();
    }

    @PostMapping("/saveTempImage")
    public ResponseEntity<String> saveTempImage(MultipartRequest multipartRequest) {
        MultipartFile multipartFile = multipartRequest.getFile("studyImage");

        String replaceFileName = uploadService.saveTempImage(multipartFile);
        if (replaceFileName == null) {
            return ResponseEntity.badRequest().body("");
        }
        return ResponseEntity.ok(replaceFileName);
    }

    @PostMapping("/deleteTempImage")
    public Integer deleteTempImage(@RequestBody Map<String, String> body) {
        String fileName = body.getOrDefault("fileName", null);

        Integer result = uploadService.deleteTempImage(fileName);

        return result;

    }
}
