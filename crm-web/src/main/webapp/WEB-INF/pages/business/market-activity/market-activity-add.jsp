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
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
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
    <form action="" method="post" class="form form-horizontal" id="form-article-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="title_ipt" name="">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>所有者：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" id="owner_ipt">
					<option>请选择</option>
				</select>
				</span></div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">成本价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="" id="cost_ipt" placeholder="" value="" class="input-text" style="width:90%">
                元
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">开始时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text"
                       onfocus="WdatePicker({ minDate:'%y-%M-%d' })"
                       id="datemin_ipt" class="input-text Wdate" style="width:180px;">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">结束时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text"
                       onfocus="WdatePicker({ minDate:'%y-%M-%d' })"
                       id="datemax_ipt" class="input-text Wdate" style="width:180px;">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">活动描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea id="description_ipt" name="" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符"
                          datatype="*10-100"
                          dragonfly="true" nullmsg="备注不能为空！" onKeyUp="$.Huitextarealength(this,200)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button onClick="createActivity()" class="btn btn-primary radius" type="button"><i
                        class="Hui-iconfont">&#xe632;</i> 创建市场活动
                </button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">
                    &nbsp;&nbsp;取消&nbsp;&nbsp;
                </button>
            </div>
        </div>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<%--日期插件--%>
<script type="text/javascript" src="<%= basepath %>lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script>
    $(function () {
        // 创建所有者的option选项。
        $.ajax({
            url: 'user/list',
            dataType: 'json',
            method: 'GET',
            success: function (result) {
                if (result.status == 'Fail') return;
                let userList = result.data.userList;
                if (userList.length == 0) return;
                let owner_ipt = $('#owner_ipt');
                for (let i = 0; i < userList.length; i++) {
                    let user = userList[i];
                    let option = document.createElement('option');
                    option.value = user.id;
                    option.text = user.name;
                    owner_ipt.append(option);
                }
            }
        })
    })

    function createActivity() {
        let title = $('#title_ipt').val();
        let description = $('#description_ipt').val();
        let owner = $('#owner_ipt').val();
        let cost = $('#cost_ipt').val();
        let datemin = $('#datemin_ipt').val();
        let datemax = $('#datemax_ipt').val();
        let params = {
            name: title,
            description,
            cost,
            startDate: datemin,
            endDate: datemax,
            owner
        }
        $.post('business/market-activity/add.do', params, function (result) {
            if (result.status == 'Success') {
                alert('活动创建成功');
                removeIframe();
            }
            if (result.status == 'Fail') alert('活动创建失败，原因是：' + result.data.errorMsg);
            if (result.data.errorCode = 3330005) window.parent.location.href = '<%= basepath%>';
        },'JSON')
    }
</script>
</body>
</html>