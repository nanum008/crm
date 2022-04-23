package com.nanum.crm.web.controller.workbench;

import com.nanum.crm.common.Constant;
import com.nanum.crm.common.error.BusinessException;
import com.nanum.crm.common.error.EmBusinessError;
import com.nanum.crm.common.response.CommonResponse;
import com.nanum.crm.common.utils.MessageDigestUtil;
import com.nanum.crm.dao.dataobject.UserDO;
import com.nanum.crm.service.UserService;
import com.nanum.crm.web.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    public static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // 转发到用户登录页面
    @GetMapping("/login")
    public String toLogin() {
        return "workbench/user/login";
    }

    //用户登录
    @PostMapping("/login.do")
    @ResponseBody
    public CommonResponse login(@RequestParam(name = "name") String username,
                                @RequestParam(name = "pwd") String password,
                                @RequestParam(name = "isRemember", defaultValue = "false") Boolean isRemember,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws NoSuchAlgorithmException, BusinessException {
        // 调用登录服务。
        UserDO userDO = userService.login(username, MessageDigestUtil.encrypt(password, Constant.ENCRYPT_KEY));
        // IP检测【暂时关闭】
        //boolean isAllowIp = false;
        //String remoteHost = httpServletRequest.getRemoteHost();
        //String allowIps_str = userDO.getAllowIps();
        //String[] allowIps = allowIps_str.split(",");
        //for (String allowIp : allowIps) {
        //    if (allowIp.equals(remoteHost)) {
        //        isAllowIp = true;
        //        break;
        //    }
        //}
        //if (!isAllowIp) throw new BusinessException(EmBusinessError.USER_IP_DONT_MATCH);
        httpServletRequest.getSession().setAttribute("user", userDO);
        // 记住密码
        if (isRemember) {
            Cookie cookie = new Cookie("email", userDO.getEmail());
            int i = 60 * 60 * 24 * 10;
            cookie.setMaxAge(i);
            Cookie cookie1 = new Cookie("pwd", MessageDigestUtil.decrypt(userDO.getLoginPwd(), Constant.ENCRYPT_KEY));
            cookie1.setMaxAge(i);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.addCookie(cookie1);
        } else {
            Cookie cookie = new Cookie("email", "null");
            cookie.setMaxAge(0);
            Cookie cookie1 = new Cookie("pwd", "null");
            cookie1.setMaxAge(0);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.addCookie(cookie1);
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", userDO);
        return CommonResponse.create(userInfo);
    }

    @PostMapping("/register")
    @ResponseBody
    public CommonResponse register() {

        return null;
    }

    @GetMapping("/list")
    @ResponseBody
    public CommonResponse list() throws BusinessException {
        List<UserDO> userDOList = userService.queryAll();
        if (userDOList == null) throw new BusinessException(EmBusinessError.UNKNOWN_ERROR);
        Map<String, Object> data = new HashMap<>();
        data.put("userList", userDOList);
        return CommonResponse.create(data);
    }

    // 安全退出
    @GetMapping("/logout.do")
    public String logout(HttpSession httpSession, HttpServletResponse httpServletResponse) {
        // 清空浏览器关于用户账号信息的Cookie。
        Cookie cookie = new Cookie("email", "null");
        cookie.setMaxAge(0);
        Cookie cookie1 = new Cookie("pwd", "null");
        cookie1.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        httpServletResponse.addCookie(cookie1);
        // 销毁Session，释放内存。
        httpSession.invalidate();
        // 跳转至首页（重定向）。
        return "redirect:/user/login";
    }
}
