package org.project.study.repository;

import org.project.study.model.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraphRepository extends JpaRepository<Graph, Long> {

    Graph findByUserIdAndStudyIdAndType(Long userId, Long studyId, String type);

    List<Graph> findByUserIdAndType(Long userId, String type);
}
