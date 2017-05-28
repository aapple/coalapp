package com.skyblue.coalapp.server.Information.service;

import com.skyblue.coalapp.server.Information.domain.InfoDepartment;

import java.util.List;

/**
 * 信息部 service
 */

public interface InfoDepartService {

    void addInfoDepartment(InfoDepartment infoDepart);

    InfoDepartment findOne(InfoDepartment infoDepart);

    List<InfoDepartment> findAll(InfoDepartment infoDepartment);
}