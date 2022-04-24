package com.nanum.crm.service;

import com.github.pagehelper.PageInfo;
import com.nanum.crm.dao.dataobject.MarketActivityDO;

import java.util.List;
import java.util.Map;

public interface MarketActivityService {
    int deleteByPrimaryKey(String id);

    int insert(MarketActivityDO record);

    int insertSelective(MarketActivityDO record);

    MarketActivityDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MarketActivityDO record);

    int updateByPrimaryKey(MarketActivityDO record);

    PageInfo<MarketActivityDO> queryByCondition(Map<String, Object> params);
}
