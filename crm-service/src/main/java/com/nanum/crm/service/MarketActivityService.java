package com.nanum.crm.service;

import com.nanum.crm.dao.dataobject.MarketActivityDO;

public interface MarketActivityService {
    int deleteByPrimaryKey(String id);

    int insert(MarketActivityDO record);

    int insertSelective(MarketActivityDO record);

    MarketActivityDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MarketActivityDO record);

    int updateByPrimaryKey(MarketActivityDO record);
}
