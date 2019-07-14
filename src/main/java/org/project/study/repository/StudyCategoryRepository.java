package org.project.study.repository;

import org.project.study.model.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCategoryRepository extends JpaRepository<StudyCategory, Long> {
}
