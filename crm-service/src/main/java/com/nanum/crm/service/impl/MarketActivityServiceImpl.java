package com.nanum.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nanum.crm.dao.mapper.MarketActivityDOMapper;
import com.nanum.crm.dao.dataobject.MarketActivityDO;
import com.nanum.crm.service.MarketActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MarketActivityServiceImpl implements MarketActivityService {

    @Autowired
    MarketActivityDOMapper marketActivityDOMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return marketActivityDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MarketActivityDO record) {
        return marketActivityDOMapper.insert(record);
    }

    @Override
    public int insertSelective(MarketActivityDO record) {
        return marketActivityDOMapper.insertSelective(record);
    }

    @Override
    public MarketActivityDO selectByPrimaryKey(String id) {
        return marketActivityDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MarketActivityDO record) {
        return marketActivityDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MarketActivityDO record) {
        return marketActivityDOMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<MarketActivityDO> queryByCondition(Map<String, Object> params) {
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<MarketActivityDO> marketActivityDOList = marketActivityDOMapper.queryByCondition(params);
        PageInfo<MarketActivityDO> pageInfo = new PageInfo<>(marketActivityDOList);
        return pageInfo;
    }
}
