package com.dayuzl.coalapp.server.notice.controller;

import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.notice.domain.NoticeMsg;
import com.dayuzl.coalapp.server.notice.service.NoticeMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 信息部 controller
 */

@RestController
@RequestMapping("/app/notice")
public class NoticeMsgController {

    @Autowired
    private NoticeMsgService noticeMsgService;

    @RequestMapping("/addNoticeMsg")
    public void addNoticeMsg(@RequestBody NoticeMsg noticeMsg){
        noticeMsgService.saveOrUpdate(noticeMsg);
    }

    @RequestMapping("/getNoticeMsgList")
    public String getNoticeMsgList(Integer pageNumber){

        if(pageNumber == null){
            pageNumber = new Integer(0);
        }

        Page<NoticeMsg> resultList = noticeMsgService.findList(pageNumber);

        return ResponseUtils.toJSONString(resultList);
    }
}
