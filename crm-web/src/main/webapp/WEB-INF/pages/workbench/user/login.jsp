<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basepath %>">
    <meta charset="UTF-8">
    <title>用户登录-CRM</title>
    <link rel="stylesheet" href="static/css/login.css">
<%--    <script src="assets/plugins/jquery/jquery-1.11.1-min.js"></script>--%>
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script>
        $(function () {
            // 按Enter键登录。
            $(window).keydown(function (event) {
                if (event.keyCode == 13) $("#submitBtn").click();
            })

            // 点击登录按钮登录。
            $("#submitBtn").click(function () {
                $("#submitBtn").attr("disabled", true);
                let username = $.trim($("#userName_ipt").val());
                let password = $.trim($("#password_ipt").val());
                let isRemember = $.trim($("#isRemember_ipt").prop("checked"));
                let msg = $("#msg");
                // 参数校验
                // 1、非空校验
                if (username === '') {
                    alert('请输入用户名！');
                    $("#submitBtn").attr("disabled", false);
                    return;
                } else if (password === '') {
                    alert('请输入密码！')
                    $("#submitBtn").attr("disabled", false);
                    return;
                }
                // 2、非法字符校验（略）
                // 3、长度校验（略）
                $.ajax({
                    url: 'user/login.do',
                    type: 'post',
                    data: {
                        name: username,
                        pwd: password,
                        isRemember
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result.status === 'Fail') {
                            msg.text(result.data.errorMsg);
                        } else if (result.status === 'Success' && result.data != null) {
                            window.location.href = 'workbench/index'
                        } else msg.text("未知错误");
                        $("#submitBtn").attr("disabled", false);
                    }
                })
            });
        })
    </script>
</head>

<body>
<div class="container">
    <!-- 头部 -->
    <div class="head"><span>CRM</span><i style="font-size: 12px;font-style: normal;margin-left: 5px">客户关系管理系统</i>
    </div>
    <!-- 主体 -->
    <div class="body">
        <div class="main" id="main">
            <div class="title">
                <h1 id="login_label">账号登录</h1>
                <small>请输入邮箱地址和密码！</small>
            </div>

            <div id="login">
                <div class="wrapper">
                    <ul>
                        <li>
                            <label>邮箱地址</label>
                        </li>
                        <li>
                            <input id="userName_ipt" type="text" value="${cookie.email.value}">
                        </li>
                        <li>
                            <label>密码</label>
                        </li>
                        <li>
                            <input id="password_ipt" type="password" value="${cookie.pwd.value}">
                        </li>
                    </ul>
                    <div class="remember">
                        <c:if test="${not empty cookie.email and not empty cookie.pwd}">
                            <input type="checkbox" name="isRemember" id="isRemember_ipt" checked>
                        </c:if>
                        <c:if test="${ empty cookie.email or empty cookie.pwd}">
                            <input type="checkbox" name="isRemember" id="isRemember_ipt">
                        </c:if>
                        <label for="isRemember_ipt">10天内免登录</label>
                        <span id="msg" style="font-size: 14px; color: darkred"></span>
                    </div>
                    <div class="submit">
                        <button id="submitBtn">登录</button>
                    </div>
                </div>
            </div>

        </div>
        <div class="cover"></div>
    </div>
    <div class="footer">
        <p>CopyRight ©Nanum</p>
    </div>
</div>
</body>

</html>