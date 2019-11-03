package org.project.study.controller;

import com.google.common.collect.ImmutableMap;
import org.project.study.model.Graph;
import org.project.study.model.User;
import org.project.study.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    @Autowired
    GraphService graphService;

    @PostMapping("like")
    public ResponseEntity setLike(@AuthenticationPrincipal User user, @RequestBody Map<String, Long> body) {

        try {
            boolean result = graphService.setLike(user.getId(), body.get("studyId"));
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("like")
    public ResponseEntity getLike(@AuthenticationPrincipal User user, @RequestParam Long studyId) {
        if(user == null) {
            return ResponseEntity.ok(ImmutableMap.of("like", false));
        }

        Graph graph = graphService.getLike(user.getId(), studyId);

        if (graph == null) {
            return ResponseEntity.ok(ImmutableMap.of("like", false));
        }
        return ResponseEntity.ok(ImmutableMap.of("like", true));
    }

}
