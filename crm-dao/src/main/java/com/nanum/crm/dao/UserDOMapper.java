package com.nanum.crm.dao;

import com.nanum.crm.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    //登录：用户名加密码
    UserDO login(String name, String psd);

    // 根据用户邮箱地址查询用户
    UserDO queryByEmail(String emailAddress);
}