package org.project.study.service;

import com.google.common.collect.ImmutableList;
import org.project.study.model.*;
import org.project.study.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudyService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MajorRegionRepository majorRegionRepository;

    @Autowired
    MinorRegionRepository minorRegionRepository;

    @Autowired
    StudyRepository studyRepository;

    @Autowired
    StudyCategoryRepository studyCategoryRepository;

    public void insertStudy(Study study) {
        studyRepository.saveAndFlush(study);
        Long studyId = study.getId();

        List<StudyCategory> categories = study.getCategories().stream()
                .map(c -> new StudyCategory(studyId, c))
                .collect(Collectors.toList());

        studyCategoryRepository.saveAll(categories);
    }

    public Map<String, List<Category>> getCategoryList() {
        List<Category> categories = categoryRepository.findAll();

        Map<String, List<Category>> categoryGrouping = categories.stream()
                .collect(Collectors.groupingBy(Category::getTitle));

        return categoryGrouping;
    }

    public Map<Integer, List<MinorRegion>> getMinorRegionList() {
        List<MinorRegion> minorRegions = minorRegionRepository.findAll();
        Map<Integer, List<MinorRegion>> minorRegionGroupings = minorRegions.stream().collect(Collectors.groupingBy(MinorRegion::getCode));
        return minorRegionGroupings;
    }

    public List<MajorRegion> getMajorRegionList() {
        List<MajorRegion> majorRegions = majorRegionRepository.findAll();

        return majorRegions;
    }

    public List<Study> getStudyList() {
        return studyRepository.findAll();
    }

    public Study getStudy(Long id) {
        return studyRepository.findById(id).orElse(null);
    }
}

