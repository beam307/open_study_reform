package org.project.study.controller;

import org.project.study.model.Category;
import org.project.study.model.MajorRegion;
import org.project.study.model.MinorRegion;
import org.project.study.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/study")
public class StudyController {

    @Autowired
    private StudyService studyService;

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
}
