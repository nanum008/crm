<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basepath %>">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="Shortcut Icon" href="/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css"/>
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-market-activity-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${requestScope.marketActivity.name}" placeholder="" id="title_ipt" name="">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">
                <span class="c-red">*</span>所有者：
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <span class="select-box">
				    <select class="select" id="owner_ipt_edit">
					    <option>请选择</option>
				    </select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">成本价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="" id="cost_ipt" placeholder="" value="${requestScope.marketActivity.cost}" class="input-text" style="width:90%"> 元
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">开始时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" autocomplete="off" value="${requestScope.marketActivity.startDate}" onfocus="WdatePicker({ minDate:'%y-%M-%d' })" id="datemin_ipt" class="input-text Wdate" style="width:180px;">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">结束时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" autocomplete="off" value="${requestScope.marketActivity.endDate}" onfocus="WdatePicker({ minDate:'%y-%M-%d' })" id="datemax_ipt" class="input-text Wdate" style="width:180px;">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">活动描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="description_ipt" name="" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="$.Huitextarealength(this,200)">${requestScope.marketActivity.description}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2" style="margin-bottom: 20px">
                <button onClick="editActivity()" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont">&#xe632;</i> 修改市场活动
                </button>
                <button onclick="closeEditPage()" id="cancle_btn_edit" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
                </button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="<%= basepath %>lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script>
// 创建【所有者】option选项。
$.ajax({
    url: 'workbench/user/list',
    dataType: 'json',
    method: 'GET',
    success: function (result) {
        if (result.status == 'Fail') return;
        let userList = result.data.userList;
        if (userList.length == 0) return;
        let owner_ipt = $('#owner_ipt_edit');
        for (let i = 0; i < userList.length; i++) {
            let user = userList[i];
            let option = document.createElement('option');
            option.value = user.id;
            option.text = user.name;
            owner_ipt.append(option);
        }
        $("#owner_ipt_edit option[value=${requestScope.marketActivity.owner}]").prop("selected","selected")
    }
})
// 创建市场活动。
function editActivity() {
    let title = $('#title_ipt').val();
    let description = $('#description_ipt').val();
    let owner = $('#owner_ipt_edit').val();
    let cost = $('#cost_ipt').val();
    let startDate = $('#datemin_ipt').val();
    let endDate = $('#datemax_ipt').val();
    let params = {
        name: title, description, cost, startDate, endDate, owner,id:'${requestScope.marketActivity.id}'
    }
    $.post('business/marketActivity/edit.do', params, function (result) {
        if (result.status == 'Success') {
            layer.msg('修改成功!', {icon: 1,time:2000},function () {
                layer.closeAll()
                location.replace(location.href)
            });
        }else if(result.status == 'Fail') {
            layer.msg('修改失败，原因是：'+result.data.errorMsg, {icon: 2,time:2000,anim:6},function () {
                if (result.data.errorCode == 3330005) {
                    layer.msg('你还未登录，即将跳转到登录页面。', {icon: 2,time:2000},function () {
                        window.parent.location.href = '<%= basepath%>workbench/user/login';
                    })
                }
            });
        }
    },'JSON')
}
// 关闭弹出层
function closeEditPage() {
    layer.close(layer.index)
}
</script>
</body>
</html>