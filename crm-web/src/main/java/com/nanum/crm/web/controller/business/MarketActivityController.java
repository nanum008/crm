package com.nanum.crm.web.controller.business;

import com.github.pagehelper.PageInfo;
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
import com.nanum.crm.web.controller.workbench.UserController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/business/marketActivity")
public class MarketActivityController extends BaseController {
    private static Logger logger = Logger.getLogger(MarketActivityController.class);

    @Autowired
    MarketActivityService marketActivityService;

    // 转发到【管理市场活动页面】
    @GetMapping("/list")
    public String toIndex() {
        return "business/market-activity/market-activity-list";
    }


    // 转发到【创建市场活动页面】
    @GetMapping("/insert")
    public String toAdd() {
        return "business/market-activity/market-activity-add";
    }    // 转发到【创建市场活动页面】

    @GetMapping("/edit")
    public String toEdit(@RequestParam String id,
                         HttpServletRequest httpServletRequest) throws BusinessException {

        MarketActivityDO marketActivityDO = marketActivityService.selectByPrimaryKey(id);
        httpServletRequest.setAttribute("marketActivity", marketActivityDO);

        return "business/market-activity/market-activity-edit";
    }


    // 创建活动
    @PostMapping("/insert.do")
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
        if (i < 0) return CommonResponse.create(Constant.HANDLE_FAIL, marketActivityDO);
        return CommonResponse.create(marketActivityDO);
    }

    // 修改活动
    @PostMapping("/edit.do")
    @ResponseBody
    public CommonResponse edit(MarketActivityDO marketActivityDO,
                               HttpSession httpSession) throws BusinessException {
        // 判断用户是否登录，未登录直接报异常。
        UserDO userDO = (UserDO) httpSession.getAttribute("user");
        if (userDO == null) throw new BusinessException(EmBusinessError.USER_DONT_LOGIN);
        // 设置‘创建时间’。
        marketActivityDO.setEditTime(DateUtils.formatDateTime(new Date()));
        // 设置‘创建人’，也就是登录此次流程的发起人（登录者）
        marketActivityDO.setEditBy(userDO.getId());
        // 插入do
        int i = marketActivityService.updateByPrimaryKeySelective(marketActivityDO);
        if (i < 0) return CommonResponse.create(Constant.HANDLE_FAIL, marketActivityDO);
        return CommonResponse.create(marketActivityDO);
    }

    // 删除活动
    @PostMapping("/delete.do")
    @ResponseBody
    public CommonResponse delete(@RequestParam String id,
                               HttpSession httpSession) throws BusinessException {
        // 判断用户是否登录，未登录直接报异常。
        UserDO userDO = (UserDO) httpSession.getAttribute("user");
        if (userDO == null) throw new BusinessException(EmBusinessError.USER_DONT_LOGIN);

        // 删除
        int i = marketActivityService.deleteByPrimaryKey(id);
        if (i < 0) return CommonResponse.create(Constant.HANDLE_FAIL, id);
        return CommonResponse.create(id);
    }

    // 根据条件查询：日期范围、名称、所有者
    @GetMapping("/queryByCondition.do")
    @ResponseBody
    public CommonResponse queryByCondition(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) String owner,
                                           @RequestParam(required = false) String startDate,
                                           @RequestParam(required = false) String endDate,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        // 封装请求参数。
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("owner", owner);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("pageNum", pageNum);
        params.put("pageSize", pageSize);
        // 按条件查询。
        PageInfo<MarketActivityDO> pageInfo = marketActivityService.queryByCondition(params);

        // 返回结果。
        Map<String, Object> data = new HashMap<>();
        data.put("pageInfo", pageInfo);
        return CommonResponse.create(data);
    }
}
