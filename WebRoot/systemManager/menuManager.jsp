<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>菜单管理</title>
    <%
		String tbar = (String)request.getAttribute("tbar");
	%>
	<script type="text/javascript">
		var tbar = "<%= tbar%>";
	</script>
    <script type="text/javascript" src="<%=basePath %>systemManager/script/menuManager.js"></script>
  </head>
  
  <body>
    <div id="menuManagerDiv"></div>
  </body>
</html>
