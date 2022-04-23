package com.nanum.crm.dao.dataobject;

import lombok.Data;

@Data
public class MarketActivityDO {
    private String id;

    private String owner;

    private String name;

    private String startDate;

    private String endDate;

    private String cost;

    private String description;

    private String createTime;

    private String createBy;

    private String editTime;

    private String editBy;

}