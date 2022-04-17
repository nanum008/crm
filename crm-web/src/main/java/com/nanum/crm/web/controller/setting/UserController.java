package com.nanum.crm.web.controller.setting;

import com.nanum.crm.common.response.CommonResponse;
import com.nanum.crm.dao.dataobject.UserDO;
import com.nanum.crm.service.UserService;
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

@Controller
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    HttpServletResponse httpServletResponse;

    // 转发到用户登录页面
    @GetMapping("/login")
    public String toLogin() {
        return "settings/qx/user/login";
    }

    //用户登录
    @PostMapping("/login.do")
    @ResponseBody
    public CommonResponse login(@RequestParam(name = "name") String username,
                                @RequestParam(name = "pwd") String password,
                                @RequestParam(name = "isRemember", defaultValue = "false") Boolean isRemember,
                                @RequestParam HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException {
        // 获取加密后的密码。
        String entryStr = encrypt(password);
        // 调用登录服务。
        UserDO userDO = userService.login(username, entryStr);
        // 为空：登陆失败。
        if (userDO == null) return CommonResponse.create("failed", userDO);
        // 不为空：返回登录对象。
        return CommonResponse.create(userDO);
    }

    // 密码加密
    public String encrypt(String txt) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptTxt = encoder.encodeToString(md5.digest(txt.getBytes(StandardCharsets.UTF_8)));
        System.out.println("加密文本 = " + encryptTxt);
        String s = Base64.getDecoder().decode(encryptTxt).toString();
        System.out.println("s = " + s);
        return encryptTxt;
    }
}
