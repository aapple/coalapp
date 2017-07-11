package com.dayuzl.coalapp.server.notice.repository;

import com.dayuzl.coalapp.server.notice.domain.NoticeMsg;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeMsgRepository extends JpaRepository<NoticeMsg,Long> {
}
