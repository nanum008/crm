package com.nanum.crm.web.controller.business;

import com.nanum.crm.common.Constant;
import com.nanum.crm.common.error.BusinessException;
import com.nanum.crm.common.error.EmBusinessError;
import com.nanum.crm.common.response.CommonResponse;
import com.nanum.crm.common.utils.DateUtils;
import com.nanum.crm.common.utils.UUIDUtils;
import com.nanum.crm.dao.dataobject.MarketActivityDO;
import com.nanum.crm.dao.dataobject.UserDO;
import com.nanum.crm.service.MarketActivityService;
import com.nanum.crm.web.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class MarketActivityController extends BaseController {
    private static Logger logger = Logger.getLogger(MarketActivityController.class);

    @Autowired
    MarketActivityService marketActivityService;

    @GetMapping("/business/market-activity-list")
    public String toIndex() {
        return "business/market-activity/market-activity-list";
    }


    @GetMapping("/business/market-activity-add")
    public String toAdd() {
        return "business/market-activity/market-activity-add";
    }

    @PostMapping("/business/market-activity/add.do")
    @ResponseBody
    public CommonResponse add(MarketActivityDO marketActivityDO,
                              HttpSession httpSession) throws BusinessException {
        // 判断用户是否登录，未登录直接报异常。
        UserDO userDO = (UserDO) httpSession.getAttribute("user");
        if (userDO == null) throw new BusinessException(EmBusinessError.USER_DONT_LOGIN);
        // 设置‘创建ID’。
        marketActivityDO.setId(UUIDUtils.getID());
        // 设置‘创建时间’。
        marketActivityDO.setCreateTime(DateUtils.formatDateTime(new Date()));
        // 设置‘创建人’，也就是登录此次流程的发起人（登录者）
        marketActivityDO.setCreateBy(userDO.getId());
        // 插入do
        int i = marketActivityService.insertSelective(marketActivityDO);
        if (i<0) return CommonResponse.create(Constant.HANDLE_FAIL, marketActivityDO);
        return CommonResponse.create(marketActivityDO);
    }
}
