package com.nanum.crm.dao.dataobject;

import lombok.Data;

@Data
public class UserDO {
    private String id;

    private String loginAct;

    private String name;

    private String loginPwd;

    private String email;

    private String expireTime;

    private String lockState;

    private String deptno;

    private String allowIps;

    private String createtime;

    private String createBy;

    private String editTime;

    private String editBy;

}