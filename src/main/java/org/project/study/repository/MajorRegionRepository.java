package org.project.study.repository;

import org.project.study.model.MajorRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MajorRegionRepository extends JpaRepository<MajorRegion, Long> {
}
