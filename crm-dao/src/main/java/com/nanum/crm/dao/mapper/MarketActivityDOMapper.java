package com.nanum.crm.dao.mapper;

import com.nanum.crm.dao.dataobject.MarketActivityDO;

import java.util.List;
import java.util.Map;

public interface MarketActivityDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(MarketActivityDO record);

    int insertSelective(MarketActivityDO record);

    MarketActivityDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MarketActivityDO record);

    int updateByPrimaryKey(MarketActivityDO record);

    // 根据条件查询
    List<MarketActivityDO> queryByCondition(Map<String, Object> params);
}