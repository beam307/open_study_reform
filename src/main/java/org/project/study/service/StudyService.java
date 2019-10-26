package org.project.study.service;

import com.google.common.collect.ImmutableMap;
import org.project.study.model.*;
import org.project.study.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    @Autowired
    UserRepository userRepository;

    public void insertStudy(Study study) {
        studyRepository.saveAndFlush(study);
        Long studyId = study.getId();

        List<StudyCategory> categories = study.getCategoryIds()
                .stream()
                .map(c -> new StudyCategory(studyId, c))
                .collect(Collectors.toList());

        studyCategoryRepository.saveAll(categories);
    }

    public Map<String, List<Category>> getCategoryGroupList() {
        List<Category> categories = categoryRepository.findAll();

        Map<String, List<Category>> categoryGrouping = categories.stream()
                .collect(Collectors.groupingBy(Category::getTitle));

        return categoryGrouping;
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
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

    public Map<String, Object> getStudyList(SearchDTO searchDTO) {
        Page<Study> studies = this.searchStudy(searchDTO);

        List<Long> writerIds = studies.stream().map(s -> s.getStudyWriterId()).collect(Collectors.toList());
        List<User> users = userRepository.findByIdIn(writerIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getId(), Function.identity()));
        studies.getContent().forEach(study -> {
            User user = userMap.get(study.getStudyWriterId());
            study.setWriter(ImmutableMap.of("id", user.getId(), "name", user.getNickname(), "image", user.getImage()));
        });
        return ImmutableMap.of("studies", studies.getContent(), "total", studies.getTotalPages());
    }

    public Study getStudy(Long id) {
        return studyRepository.findById(id).orElse(null);
    }

    private Page<Study> searchStudy(SearchDTO searchDTO) {
        Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getCount(), Sort.by(Sort.Direction.DESC, searchDTO.getFilter()));

        List<Long> studyIds = null;
        if (searchDTO.getCategory() != null) {
            studyIds = studyCategoryRepository.findByCategoryId(searchDTO.getCategory())
                    .stream()
                    .map(sc -> sc.getStudyId())
                    .collect(Collectors.toList());
        }

        if (studyIds != null) {
            return searchDTO.getRegion() != null ?
                    studyRepository.findByIdInAndMajorRegionId(studyIds, searchDTO.getRegion(), pageable) : studyRepository.findByIdIn(studyIds, pageable);
        } else {
            return searchDTO.getRegion() != null ? studyRepository.findByMajorRegionId(searchDTO.getRegion(), pageable) : studyRepository.findAll(pageable);
        }
    }
}

