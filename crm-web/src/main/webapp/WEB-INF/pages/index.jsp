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
</head>
<body>
<script type="text/javascript">
    document.location.href = "workbench/user/login";
</script>
</body>
</html>