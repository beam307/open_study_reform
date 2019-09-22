package org.project.study.repository;

import org.project.study.model.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    Page<Study> findByMajorRegionId(Integer majorRegion, Pageable pageable);
}
