package org.project.study.service;

import org.project.study.model.Reply;
import org.project.study.model.Study;
import org.project.study.repository.ReplyRepository;
import org.project.study.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    StudyRepository studyRepository;

    public void setReply(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> getReplies(Long readUserId, Long studyId) {

        List<Reply> replies = replyRepository.findAllByStudyIdOrderByCreatedAtDesc(studyId);
        Long writerId = studyRepository.findById(studyId).get().getStudyWriterId();

        return replies.stream().filter(r -> !r.isSecret() || readUserId.equals(r.getUserId()) || readUserId.equals(writerId)).collect(Collectors.toList());
    }
}

