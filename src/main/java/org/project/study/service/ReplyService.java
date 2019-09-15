package org.project.study.service;

import org.project.study.model.Reply;
import org.project.study.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    public void setReply(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> getReplies(Long studyId) {

        return replyRepository.findAllByStudyIdOrderByCreatedAtDesc(studyId);
    }
}

