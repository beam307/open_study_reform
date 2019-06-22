package org.project.study.service;

import com.google.common.collect.ImmutableList;
import org.project.study.model.Category;
import org.project.study.model.MajorRegion;
import org.project.study.model.MinorRegion;
import org.project.study.repository.CategoryRepository;
import org.project.study.repository.MajorRegionRepository;
import org.project.study.repository.MinorRegionRepository;
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
}

