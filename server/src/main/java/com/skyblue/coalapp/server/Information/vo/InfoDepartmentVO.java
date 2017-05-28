package com.skyblue.coalapp.server.Information.vo;

import com.skyblue.coalapp.server.Information.domain.InfoDepartment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoDepartmentVO extends InfoDepartment {

    String managerName;
    String telephone;
}
