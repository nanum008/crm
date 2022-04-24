package com.nanum.crm.service.impl;

import com.nanum.crm.common.error.BusinessException;
import com.nanum.crm.common.error.EmBusinessError;
import com.nanum.crm.dao.mapper.UserDOMapper;
import com.nanum.crm.dao.dataobject.UserDO;
import com.nanum.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserDO login(String email, String pwd) throws BusinessException {
        // 根据用户邮箱地址查询用户
        // 1、查询结果为空，用户不存在：登录失败。
        UserDO userDO = userDOMapper.queryByEmail(email);
        if (userDO == null) throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        // 2、密码比对失败：登录失败。
        if (!userDO.getLoginPwd().equals(pwd)) throw new BusinessException(EmBusinessError.USER_PASSWORD_DONT_MATCH);
        // 3、账户锁定：登陆失败。
        if (!"1".equals(userDO.getLockState())) throw new BusinessException(EmBusinessError.USER_LOCK_STATE);
        return userDO;
    }


    @Override
    public List<UserDO> queryAll() {
        return userDOMapper.queryAll();
    }
}
