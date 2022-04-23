package com.nanum.crm.dao;

import com.nanum.crm.dao.dataobject.MarketActivityDO;

public interface MarketActivityDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(MarketActivityDO record);

    int insertSelective(MarketActivityDO record);

    MarketActivityDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MarketActivityDO record);

    int updateByPrimaryKey(MarketActivityDO record);
}