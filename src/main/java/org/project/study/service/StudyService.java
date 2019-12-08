package org.project.study.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.project.study.exception.ValidateException;
import org.project.study.model.*;
import org.project.study.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.project.study.model.Study.Status;

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

    @Autowired
    UserAdditionalRepository userAdditionalRepository;

    @Autowired
    GraphRepository graphRepository;

    @Autowired
    ObjectMapper objectMapper;

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

    public Map<String, Object> getStudyList(SearchDTO searchDTO, String type, Long userId) {
        if (type.equals("best")) {
            List<Long> studyIds = graphRepository.findByLikeCount();
            List<Study> studies = studyRepository.findByIdIn(studyIds).stream().limit(6).collect(Collectors.toList());
            this.setWriter(studies);
            return ImmutableMap.of("studies", studies);
        } else if (type.equals("recommend") && userId != null) {
            UserAdditional userAdditional = userAdditionalRepository.findByUserId(userId);
            if (userAdditional != null) {
                List<Integer> list = objectMapper.convertValue(userAdditional.getCategories(), List.class);
                List<Long> studyIds = studyCategoryRepository.findByCategoryIdIn(list).stream().map(sc -> sc.getStudyId()).collect(Collectors.toList());
                List<Study> studies = studyRepository.findByIdIn(studyIds);
                this.setWriter(studies);
                return ImmutableMap.of("studies", studies);
            }
        }
        Page<Study> studyPage = this.searchStudy(searchDTO);
        List<Study> studies = studyPage.getContent();
        this.setWriter(studies);

        return ImmutableMap.of("studies", studies, "total", studyPage.getTotalPages());
    }

    public Study getStudy(Long id) {
        Study study = studyRepository.findById(id).orElse(null);
        User user = userRepository.findById(study.getStudyWriterId()).get();
        study.setWriter(ImmutableMap.of("id", user.getId(), "name", user.getNickname(), "image", user.getImage()));
        return study;
    }

    public List<Study> getLikeStudyList(Long userId) {
        List<Graph> likeGraph = graphRepository.findByUserIdAndType(userId, "like");
        List<Long> studyIds = likeGraph.stream().map(g -> g.getStudyId()).collect(Collectors.toList());

        List<Study> studies = studyRepository.findByIdIn(studyIds);
        this.setWriter(studies);
        return studies;
    }

    public List<Study> getApplyStudyList(Long userId) {
        List<Graph> applyGraph = graphRepository.findByUserIdAndType(userId, "apply");
        List<Long> studyIds = applyGraph.stream().map(g -> g.getStudyId()).collect(Collectors.toList());

        List<Study> studies = studyRepository.findByIdIn(studyIds);
        this.setWriter(studies);
        return studies;
    }

    public List<Study> getMyStudyList(Long userId, Status status) {
        List<Study> studies = studyRepository.findByStudyWriterIdAndStatus(userId, status);
        this.setWriter(studies);
        return studies;
    }

    public void setStudyStatus(Long userId, Long studyId, Status status) throws ValidateException {
        Study study = studyRepository.findByIdAndStudyWriterId(studyId, userId);
        if (study == null) {
            throw new ValidateException();
        }
        study.setStatus(status);

        studyRepository.save(study);

    }

    public void setStudyApply(Long userId, Long studyId) throws ValidateException {
        Graph graph = graphRepository.findByUserIdAndStudyIdAndType(userId, studyId, "apply");
        if (graph != null) {
            throw new ValidateException();
        }

        Graph applyGraph = Graph.builder().userId(userId).studyId(studyId).type("apply").build();

        graphRepository.save(applyGraph);

    }

    private void setWriter(List<Study> studies) {
        List<Long> writerIds = studies.stream().map(s -> s.getStudyWriterId()).collect(Collectors.toList());
        List<User> users = userRepository.findByIdIn(writerIds);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getId(), Function.identity()));
        studies.forEach(study -> {
            User user = userMap.get(study.getStudyWriterId());
            study.setWriter(ImmutableMap.of("id", user.getId(), "name", user.getNickname(), "image", user.getImage()));
        });
    }

    private Page<Study> searchStudy(SearchDTO searchDTO) {
        Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getCount(), Sort.by(Sort.Direction.DESC, searchDTO.getFilter()));

        // TODO 검색 기능 수정해야할듯...
        List<Long> studyIds = null;
        if (searchDTO.getCategory() != null) {
            studyIds = studyCategoryRepository.findByCategoryId(searchDTO.getCategory())
                    .stream()
                    .map(sc -> sc.getStudyId())
                    .collect(Collectors.toList());
        }

        if (studyIds != null) {
            return searchDTO.getRegion() != null ?
                    studyRepository.findByIdInAndMajorRegionIdAndStatus(studyIds, searchDTO.getRegion(), Status.ACTIVE, pageable) : studyRepository.findByIdInAndStatus(studyIds, Status.ACTIVE, pageable);
        } else {
            return searchDTO.getRegion() != null ? studyRepository.findByMajorRegionIdAndStatus(searchDTO.getRegion(), Status.ACTIVE, pageable) : studyRepository.findByStatus(Status.ACTIVE, pageable);
        }
    }
}

