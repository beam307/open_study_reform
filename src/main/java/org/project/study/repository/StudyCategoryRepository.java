package org.project.study.repository;

import org.project.study.model.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyCategoryRepository extends JpaRepository<StudyCategory, Long> {
    List<StudyCategory> findByCategoryId(Integer categoryId);
}
