package org.project.study.controller;

import org.project.study.model.Reply;
import org.project.study.model.User;
import org.project.study.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @PostMapping("")
    public ResponseEntity setReply(@RequestBody Reply reply, @AuthenticationPrincipal User user) {
        reply.setUserId(user.getId());
        try {
            replyService.setReply(reply);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @GetMapping("/{studyId}")
    public ResponseEntity<List<Reply>> getReplies(@PathVariable Long studyId) {

        return ResponseEntity.ok(replyService.getReplies(studyId));
    }
}
