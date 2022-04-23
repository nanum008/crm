package com.nanum.crm.service;

import com.nanum.crm.common.error.BusinessException;
import com.nanum.crm.dao.dataobject.UserDO;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(String id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    UserDO login(String email, String pwd) throws BusinessException;

    List<UserDO> queryAll();
}
