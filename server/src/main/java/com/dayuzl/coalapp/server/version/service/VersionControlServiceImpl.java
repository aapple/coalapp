package com.dayuzl.coalapp.server.version.service;

import com.dayuzl.coalapp.server.Information.domain.InfoDepartment;
import com.dayuzl.coalapp.server.version.domain.VersionControl;
import com.dayuzl.coalapp.server.version.repository.VersionRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionControlServiceImpl implements VersionControlService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private VersionRepository versionControlRepository;

    @Override
    //@CacheEvict(value="versionControlList",allEntries=true)
    public void saveNewVerion(VersionControl version) {
        versionControlRepository.save(version);
    }

    @Override
    //@Cacheable(value = "versionControlList", key="#version.systemType",unless="!(#result!=null)")
    public VersionControl checkNewVersion(VersionControl version){

        VersionControl result =  version;
        result.setIsNeedUpdate(false);
        if(StringUtils.isNotBlank(version.getVersionNum()) && StringUtils.isNotEmpty(version.getSystemType())){

            VersionControl exapVerion = new VersionControl();
            exapVerion.setSystemType(version.getSystemType());

            ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("focus");

            Example<VersionControl> ex = Example.of(exapVerion, matcher);

            Sort sort = new Sort(Sort.Direction.DESC,"updateTime");

            List<VersionControl> versions = versionControlRepository.findAll(ex,sort);

            if(versions != null && versions.size()>0){
                VersionControl lastVersion =  versions.get(0);

                String[] reqVersionNum = version.getVersionNum().split("\\.");
                String[] lastVersionNum = lastVersion.getVersionNum().split("\\.");

                for(int i = 0; i<lastVersionNum.length; i++){
                    if(Integer.parseInt(lastVersionNum[i]) > Integer.parseInt(reqVersionNum[i])){
                        result = lastVersion;
                        result.setIsNeedUpdate(true);
                        break;
                    }else if(Integer.parseInt(reqVersionNum[i]) > Integer.parseInt(lastVersionNum[i])){
                        logger.error("请求版本高于最新版本"+" versionNum : " + version.getVersionNum() +" system：" + version.getSystemType());
                        break;
                    }
                }
            }else{
                logger.error("没有找到任何版本"+" versionNum : " + version.getVersionNum() +" system : "+ version.getSystemType());
            }
        }else {
            logger.error("request 版本信息有误 ： "+" versionNum : " + version.getVersionNum() +" system : "+ version.getSystemType());
        }

        return result;
    }
}
