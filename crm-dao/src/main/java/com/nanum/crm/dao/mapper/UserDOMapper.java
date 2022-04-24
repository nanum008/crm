package com.nanum.crm.dao.mapper;

import com.nanum.crm.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    List<UserDO> queryAll();
}