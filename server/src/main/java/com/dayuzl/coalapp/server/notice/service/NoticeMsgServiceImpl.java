package com.dayuzl.coalapp.server.notice.service;

import com.dayuzl.coalapp.server.notice.domain.NoticeMsg;
import com.dayuzl.coalapp.server.notice.repository.NoticeMsgRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NoticeMsgServiceImpl implements NoticeMsgService {

    @Autowired
    private NoticeMsgRepository noticeMsgRepository;

    @Override
    @CacheEvict(value="noticeMsgList",allEntries=true)
    public void saveOrUpdate(NoticeMsg noticeMsg) {
        noticeMsgRepository.save(noticeMsg);
    }

    @Override
    @Cacheable(value = "noticeMsgList", key="#pageNumber",unless="!(#result!=null)")
    public Page<NoticeMsg> findList(Integer pageNumber){

        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageRequest = new PageRequest(pageNumber, 10, sort);

        Page<NoticeMsg> noticeMsgs = noticeMsgRepository.findAll(pageRequest);

        return noticeMsgs;
    }

    @CacheEvict(value="noticeMsgList",allEntries=true)
    @Scheduled(fixedDelay = 10*60*1000)
    public void clearCache(){
    }
}
