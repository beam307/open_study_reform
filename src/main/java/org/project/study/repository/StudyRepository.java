package org.project.study.repository;

import org.project.study.model.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.project.study.model.Study.Status;

public interface StudyRepository extends JpaRepository<Study, Long> {

    Page<Study> findByIdInAndStatus(List<Long> ids, Status status, Pageable pageable);

    Page<Study> findByIdInAndMajorRegionIdAndStatus(List<Long> ids, Integer region, Status status, Pageable pageable);

    Page<Study> findByMajorRegionIdAndStatus(Integer region, Status status, Pageable pageable);

    Page<Study> findByStatus(Status status, Pageable pageable);

    List<Study> findByIdIn(List<Long> ids);

    List<Study> findByStudyWriterIdAndStatus(Long userId, Status status);

    Study findByIdAndStudyWriterId(Long studyId, Long userId);
}