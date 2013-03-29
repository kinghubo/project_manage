<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目管理系统</title>
    
	<link rel="stylesheet" href="ext/resources/css/ext-all.css" type="text/css"></link>
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="script/login.js"></script>
  </head>
  
	<body style="background-color: #15428b;">
		<div id="login"></div>
		<div id="loginMessagerDiv" align="center"></div>
	</body>
</html>
