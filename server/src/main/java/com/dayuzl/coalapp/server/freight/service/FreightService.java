package com.dayuzl.coalapp.server.freight.service;

import com.dayuzl.coalapp.server.framework.domain.PageParam;
import com.dayuzl.coalapp.server.freight.domain.FreightInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FreightService {

    void save(FreightInfo freightInfo);

    List<FreightInfo> getList(FreightInfo freightInfo);

    Page<FreightInfo> getPage(FreightInfo freightInfo);

    void delete(FreightInfo freightInfo);
}
