package com.dayuzl.coalapp.server.version.controller;

import com.dayuzl.coalapp.server.framework.util.ResponseUtils;
import com.dayuzl.coalapp.server.version.domain.VersionControl;
import com.dayuzl.coalapp.server.version.service.VersionControlService;
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
@RequestMapping("/app/version")
public class VersionController {

    @Autowired
    private VersionControlService versionControlService;

    @RequestMapping("/addVersion")
    public void addVersionControl(){
        VersionControl versionControl = new VersionControl();
        /*versionControl.setForceUpdate(false);
        versionControl.setSystemType("android");
        versionControl.setVersionNum("0.0.1");
        versionControl.setVersionAddr("testAddress");*/

        versionControlService.saveNewVerion(versionControl);
    }

    @RequestMapping("/checkNewVersion")
    public String getVersionControlList(@RequestBody VersionControl version){

        VersionControl result = versionControlService.checkNewVersion(version);

        return ResponseUtils.toJSONString(result);
    }
}
