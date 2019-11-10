package org.project.study.controller;

import org.project.study.exception.ValidateException;
import org.project.study.model.*;
import org.project.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.project.study.model.Study.Status;

@RestController
@RequestMapping("/api/study")
public class StudyController {

    @Autowired
    private StudyService studyService;

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
    public ResponseEntity<Map> studyList(@ModelAttribute SearchDTO searchDTO) {
        return ResponseEntity.ok(studyService.getStudyList(searchDTO));
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
    public Map<String, List<Category>> getCategoryGroupList() {
        return studyService.getCategoryGroupList();
    }

    @GetMapping("/category/all")
    public List<Category> getCategoryList() {
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

    @GetMapping("/like")
    public List<Study> getLikeStudyList(@AuthenticationPrincipal User user) {
        return studyService.getLikeStudyList(user.getId());
    }

    @GetMapping("/recruit")
    public List<Study> getRecruitStudyList(@AuthenticationPrincipal User user) {
        return studyService.getMyStudyList(user.getId(), Status.ACTIVE);
    }

    @GetMapping("/apply")
    public List<Study> getApplyStudyList(@AuthenticationPrincipal User user) {
        return studyService.getApplyStudyList(user.getId());
    }

    @GetMapping("/finish")
    public List<Study> getFinishStudyList(@AuthenticationPrincipal User user) {
        return studyService.getMyStudyList(user.getId(), Status.FINISH);
    }

    @PostMapping("/{studyId}/finish")
    public ResponseEntity setStudyFinish(@AuthenticationPrincipal User user, @PathVariable Long studyId) {
        try {
            studyService.setStudyStatus(user.getId(), studyId, Status.FINISH);
            return ResponseEntity.ok().build();
        } catch (ValidateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{studyId}/delete")
    public ResponseEntity setStudyDelete(@AuthenticationPrincipal User user, @PathVariable Long studyId) {
        try {
            studyService.setStudyStatus(user.getId(), studyId, Status.DELETE);
            return ResponseEntity.ok().build();
        } catch (ValidateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{studyId}/apply")
    public ResponseEntity setStudyApply(@AuthenticationPrincipal User user, @PathVariable Long studyId) {
        try {
            studyService.setStudyApply(user.getId(), studyId);
            return ResponseEntity.ok().build();
        } catch (ValidateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
