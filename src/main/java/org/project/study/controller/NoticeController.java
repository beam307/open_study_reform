package org.project.study.controller;

import org.project.study.model.Notice;
import org.project.study.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/list")
    public List<Notice> getNoticeList() {
        return noticeService.getNoticeList();
    }

    @GetMapping("/{id}")
    public Notice getNotice(@PathVariable Long id) {
        return noticeService.getNotice(id);
    }

}
