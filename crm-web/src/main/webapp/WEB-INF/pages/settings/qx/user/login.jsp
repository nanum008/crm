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
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#submitBtn").click(function () {
                $("#submitBtn").attr("disabled", true);
                let username = $.trim($("#userName_ipt").val());
                let password = $.trim($("#password_ipt").val());
                let isRemember = $.trim($("#isRemember_ipt").prop("checked"));
                // 参数校验
                // 1、非空校验
                if (username === '') {
                    alert('请输入用户名！');
                    return;
                } else if (password === '') {
                    alert('请输入密码！')
                    return;
                }
                // 2、非法字符校验（略）
                // 3、长度校验（略）
                $.ajax({
                    url: 'user/login.do',
                    type: 'post',
                    data: {
                        username,
                        password,
                        isRemember
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result == '') {
                            alert('登陆失败');
                        } else if (result.status === 'failed') {
                            alert('登陆失败');
                        } else if (result.status === 'success' && result.data != null) {
                            alert('登陆成功：' + result.data);
                        } else alert('未知错误');
                        $("#submitBtn").attr("disabled", false);
                    }
                })
            });
        })
    </script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;">&copy;2019&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <div class="form-group form-group-lg">
            <div style="width: 350px;">
                <input id="userName_ipt" class="form-control" type="text" placeholder="用户名">
            </div>
            <div style="width: 350px; position: relative;top: 20px;">
                <input id="password_ipt" class="form-control" type="password" placeholder="密码">
            </div>
            <div class="checkbox" style="position: relative;top: 30px; left: 10px;">
                <label>
                    <input id="isRemember_ipt" type="checkbox"> 十天内免登录
                </label>
                &nbsp;&nbsp;
                <span id="msg"></span>
            </div>
            <button class="btn btn-primary btn-lg btn-block" id="submitBtn"
                    style="width: 350px; position: relative;top: 45px;">登录
            </button>
        </div>
    </div>
</div>
</body>
</html>