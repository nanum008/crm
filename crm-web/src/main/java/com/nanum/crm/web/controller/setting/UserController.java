package com.nanum.crm.web.controller.setting;

import com.nanum.crm.common.error.BusinessException;
import com.nanum.crm.common.error.EmBusinessError;
import com.nanum.crm.common.response.CommonResponse;
import com.nanum.crm.dao.dataobject.UserDO;
import com.nanum.crm.service.UserService;
import com.nanum.crm.web.controller.BaseController;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    public static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    HttpServletResponse httpServletResponse;

    // 转发到用户登录页面
    @GetMapping("/login")
    public String toLogin() throws NoSuchAlgorithmException {
        return "settings/qx/user/login";
    }

    //用户登录
    @PostMapping("/login.do")
    @ResponseBody
    public CommonResponse login(@RequestParam(name = "name") String username,
                                @RequestParam(name = "pwd") String password,
                                @RequestParam(name = "isRemember", defaultValue = "false") Boolean isRemember,
                                HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException, BusinessException {
        // 调用登录服务。
        UserDO userDO = userService.login(username, encrypt(password));
        boolean isAllowIp = false;
        String remoteHost = httpServletRequest.getRemoteHost();
        String allowIps_str = userDO.getAllowIps();
        String[] allowIps = allowIps_str.split(",");
        for (String allowIp : allowIps) {
            if (allowIp.equals(remoteHost)) isAllowIp = true;
        }
        if (!isAllowIp) throw new BusinessException(EmBusinessError.USER_IP_DONT_MATCH);
        if (userDO != null && isAllowIp) httpServletRequest.getSession().setAttribute("user", userDO);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", userDO);
        return CommonResponse.create(userInfo);
    }

    @PostMapping("/register")
    @ResponseBody
    public CommonResponse register() {

        return null;
    }

    // 密码加密
    public static String encrypt(String txt) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptTxt = encoder.encodeToString(md5.digest(txt.getBytes(StandardCharsets.UTF_8)));
        String s = Base64.getDecoder().decode(encryptTxt).toString();
        return encryptTxt;
    }
}
