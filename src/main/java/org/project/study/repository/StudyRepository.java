package org.project.study.repository;

import org.project.study.model.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Page<Study> findByIdIn(List<Long> ids, Pageable pageable);

    Page<Study> findByIdInAndMajorRegionId(List<Long> ids, Integer region, Pageable pageable);

    Page<Study> findByMajorRegionId(Integer region, Pageable pageable);

    List<Study> findByIdIn(List<Long> ids);

    List<Study> findByStudyWriterId(Long userId);

    Study findByIdAndStudyWriterId(Long studyId, Long userId);
}