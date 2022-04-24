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
<%--    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css"/>--%>
<%--    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css"/>--%>
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css"/>
<%--    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin"/>--%>
<%--    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css"/>--%>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap-4.6.1-dist/css/bootstrap.min.css"/>
    <title>市场活动管理</title>
</head>
<body >
<nav class="" style="padding: 0 20px;height: 39px;line-height: 39px;background-color:#f3f3f3;">
    <i class="Hui-iconfont">&#xe67f;</i>
    首页
    <span class="c-gray en">&gt;</span>
    市场活动管理
    <span class="c-gray en">&gt;</span>
    市场活动管理
    <a class="btn btn-success btn-sm float-right" style="line-height:1.3em;margin-top:3px"
       href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container" style="padding:0 20px;">
    <!--按条件查询-->
    <div class="" style="height:45px; align-items:center; display: flex; justify-content: center;margin: 20px 0">
        <button onclick="removeIframe()" class="btn btn-primary btn-sm" style="margin-right: 10px">关闭选项卡</button>
        日期范围：
        <input type="text" placeholder="开始日期" autocomplete="off" onfocus="WdatePicker({  })" id="startDate_ipt" class="form-control Wdate" style="width:120px;height: 35px;"> -
        <input type="text" placeholder="结束日期" autocomplete="off" onfocus="WdatePicker({ })" id="endDate_ipt" class="form-control Wdate" style="width:120px;height: 35px;">
        <input type="text" name="" id="name_ipt" placeholder="名称" style="width:100px;margin-left: 10px;height: 35px;" class="form-control">
        <input type="text" name="" id="owner_ipt" placeholder="所有者" style="width:100px;margin-left: 10px;height: 35px;" class="form-control">
        <button style="margin-left: 15px" name="" id="query_btn" class="btn btn-success btn-sm" type="submit"><i
                class="Hui-iconfont">&#xe665;</i>查询
        </button>
    </div>

    <!--操作-->
    <div class="" style="padding-bottom: 20px ;">
        <span>
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger btn-sm"><i class="Hui-iconfont Hui-iconfont-del2"></i> 批量删除</a>
            <a class="btn btn-primary btn-sm" data-title="创建市场活动" data-href="business/marketActivity/insert" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont Hui-iconfont-gengduo2"></i> 创建市场活动</a>
            <span class="btn btn-primary btn-sm"> <i class="Hui-iconfont Hui-iconfont-chuku"></i>导出到Excel</span>
            <span class="btn btn-primary btn-sm"> <i class="Hui-iconfont Hui-iconfont-daoru"></i>从Excel导入</span>
        </span>
        <span class="float-right">共有数据&nbsp;<span class="text-danger" id="total"></span>&nbsp;条</span>
    </div>

    <!--表格-->
    <div class="mt-20">
        <table class="table table-bordered table-hover table-sm">
            <!--表头-->
            <thead class="">
             <tr class="text-c">
                <th width="20"><input type="checkbox" name="" value=""></th>
                <th width="20">#</th>
                <th width="80">标题</th>
                <th width="80">所有者</th>
                <th width="80">成本</th>
                <th width="120">描述</th>
                 <th width="60">创建者</th>
                <th width="100">开始时间</th>
                <th width="100">结束时间</th>
                 <th width="60">最后修改者</th>
                 <th width="100">最后修改时间</th>
                <th width="50">操作</th>
            </tr>
            </thead>
            <!--表格体-->
            <tbody id="tbody_tbl">
            </tbody>
        </table>
        <!--分页盒子-->
        <ul id="pageBox"></ul>
    </div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/bootstrap-4.6.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-4.6.1-dist/js/bootstrap-paginator.min.js"></script>
<script type="text/javascript">

    function toLogin() {
        layer.msg('你还未登录，即将跳转到登录页面。', {icon: 2,time:2000},function () {
            window.parent.location.href = '<%= basepath%>workbench/user/login';
        })
    }
    // BootStrap-Pagination插件配置选项。
    let options = {
        currentPage: 1, //当前页
        totalPages: 1, //总页数
        numberOfPages: 6, //设置控件显示的页码数
        bootstrapMajorVersion: 3,//如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
        useBootstrapTooltip: false,//是否显示tip提示框
        itemTexts: function (type, page, current) {//文字翻译可有可无
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "尾页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {
            <!--取值-->
            let name = $.trim($('#name_ipt').val())
            let owner = $.trim($('#owner_ipt').val())
            let startDate = $.trim($('#startDate_ipt').val())
            let endDate = $.trim($('#endDate_ipt').val())
            let pageNum = page;
            let params = {
                name, owner, startDate, endDate,pageNum
            }
            loadPage(page,params);//页面跳转后调用ajax函数进行页面信息展示以及DOM操作
        }
    }

    function loadPage(pageNum,params) {
        if (params!=null) params.pageNum = pageNum;
        <!--发起请求-->
        $.get('business/marketActivity/queryByCondition.do', params, function (result) {
            if (result.status == 'Fail') {
                alert('查询失败');
                return;
            }
            let pageInfo = result.data.pageInfo;
            let htmlStr = '';
            $.each(pageInfo.list, function (index, item) {
                let str = `
               <tr class="text-c">
                   <td><input type="checkbox" value="" name=""></td>
                   <td>\${index+1}</td>
                   <td>\${item.name}</td>
                   <td >\${item.owner}</td>
                   <td >\${item.cost}</td>
                   <td >\${item.description}</td>
                   <td class="td-status"><span class="label label-success radius">\${item.createBy}</span></td>
                   <td>\${item.startDate}</td>
                   <td>\${item.endDate}</td>
                   <td >\${item.editBy}</td>
                   <td >\${item.editTime}</td>
                   <td class="d-flex justify-content-center">
                        <a style="text-decoration:none" class="ml-5" onClick="activity_edit('\${item.id}')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
                        <a style="text-decoration:none" class="ml-5" onClick="ctivity_del('\${item.id}','\${item.name}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
                    </td>
              </tr>
            `;
                htmlStr += str;
            });
            $('#tbody_tbl').html(htmlStr);
            options.currentPage = pageInfo.pageNum;
            options.totalPages = pageInfo.pages;
            $("#total").text(pageInfo.total);
            initPagination(options);
        }, 'JSON')
    }

    loadPage(1,null);

    // 点击按条件查询
    $('#query_btn').click(function () {
            <!--取值-->
            let name = $.trim($('#name_ipt').val())
            let owner = $.trim($('#owner_ipt').val())
            let startDate = $.trim($('#startDate_ipt').val())
            let endDate = $.trim($('#endDate_ipt').val())
            let params = {
                name, owner, startDate, endDate
            }
            loadPage(1,params);
    });

    function initPagination(options) {
            $('#pageBox').bootstrapPaginator(options);
            $('#pageBox').addClass("pagination pagination-sm");
            $('#pageBox').find('li').addClass("page-item");
            $('#pageBox').find("a").addClass("page-link");
    }
    function activity_edit(id) {
        $.get('business/marketActivity/edit',{id},function (str) {
            layer.open({
                type:1,
                content:str,
                area: '800px',
                title:'创建市场活动',
            })
        })
    }


    function ctivity_del(id,title) {
        layer.confirm('你确定删除活动【'+title+'】吗?', {icon: 3, title:'提示'}, function(index){
            $.post('business/marketActivity/delete.do',{id},function (result) {
                if (result.status == 'Fail'){
                    layer.msg('删除失败！原因是：'+result.data.errorMsg ,{
                        anim: 6,
                        icon: 2,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    },function () {
                        if (result.data.errorCode == 3330005) {
                            toLogin()
                        }
                    });
                }else if (result.status == 'Success'){
                    layer.msg('删除成功！', {
                        icon: 1,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                       layer.close(layer.index)
                        // 刷新
                        location.replace(location.href)
                    });
                }
            },'JSON')
        });

    }
</script>
</body>
</html>
