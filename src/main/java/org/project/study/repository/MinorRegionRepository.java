package org.project.study.repository;

import org.project.study.model.MinorRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinorRegionRepository extends JpaRepository<MinorRegion, Long> {
}
