package com.nanum.crm.service.impl;

import com.nanum.crm.dao.UserDOMapper;
import com.nanum.crm.dao.dataobject.UserDO;
import com.nanum.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDOMapper userDOMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(UserDO record) {
        return 0;
    }

    @Override
    public int insertSelective(UserDO record) {
        return 0;
    }

    @Override
    public UserDO selectByPrimaryKey(String id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserDO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(UserDO record) {
        return 0;
    }

    @Override
    public UserDO login(String email, String pwd) {
        // 根据用户邮箱地址查询用户
        // 查询结果为空：登录失败
        UserDO userDO = userDOMapper.queryByEmail(email);
        if (userDO == null) {

        }

        return null;
    }
}
