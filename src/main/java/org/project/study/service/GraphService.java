package org.project.study.service;

import org.project.study.model.Graph;
import org.project.study.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GraphService {

    @Autowired
    GraphRepository graphRepository;

    @Transactional
    public boolean setLike(Long userId, Long studyId) {
        Graph graph = graphRepository.findByUserIdAndStudyIdAndType(userId, studyId, "like");
        if (graph == null) {
            Graph likeGraph = Graph.builder().userId(userId).studyId(studyId).type("like").build();
            graphRepository.save(likeGraph);
            return true;
        } else {
            graphRepository.delete(graph);
            return false;
        }
    }

    public Graph getLike(Long userId, Long studyId) {
        return graphRepository.findByUserIdAndStudyIdAndType(userId, studyId, "like");
    }

}

