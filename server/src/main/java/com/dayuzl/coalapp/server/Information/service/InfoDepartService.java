package com.dayuzl.coalapp.server.Information.service;

import com.dayuzl.coalapp.server.Information.domain.InfoDepartment;

import java.util.List;

/**
 * 信息部 service
 */

public interface InfoDepartService {

    void addInfoDepartment(InfoDepartment infoDepart);

    InfoDepartment findOne(InfoDepartment infoDepart);

    List<InfoDepartment> findAll(InfoDepartment infoDepartment);

    void deleteById(InfoDepartment infoDepart);
}