package org.project.study.service;

import org.project.study.model.Notice;
import org.project.study.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    public List<Notice> getNoticeList() {
        return noticeRepository.findByActive(true);
    }

    public Notice getNotice(Long id) {
        return noticeRepository.findById(id).get();
    }
}
