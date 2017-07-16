package com.dayuzl.coalapp.server.notice.service;

import com.dayuzl.coalapp.server.notice.domain.NoticeMsg;
import org.springframework.data.domain.Page;

public interface NoticeMsgService {

    void saveOrUpdate(NoticeMsg noticeMsg);

    Page<NoticeMsg> findList(Integer pageNumber);
}
