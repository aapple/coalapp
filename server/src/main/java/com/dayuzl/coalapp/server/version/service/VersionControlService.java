package com.dayuzl.coalapp.server.version.service;

import com.dayuzl.coalapp.server.version.domain.VersionControl;
import org.springframework.data.domain.Page;

public interface VersionControlService {

    void saveNewVerion(VersionControl version);

    VersionControl checkNewVersion(VersionControl version);
}
