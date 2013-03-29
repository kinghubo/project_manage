<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>项目管理系统</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css" />
	</head>
	<body>
		<!-- 系统初始化信息显示DIV -->
		<div id="loading-mask" style=""></div>
		<div class="loading-indicator">
			<img src="css/images/loading.gif" width="32" height="32" style="margin-right: 8px; float: left; vertical-align: top;" />
			项目管理系统<br />
			<span id="loading-msg">正在载入...</span>
		</div>
		<div id="loading"></div>
		<%
			String children = (String)request.getSession().getAttribute("children");
		%>
		<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载核心 API...';</script>
		<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '加载 UI 组件...';</script>
		<script type="text/javascript" src="ext/ext-all.js"></script>
		<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '系统初始化...';</script>
		<script type="text/javascript" src="script/common.js"></script>
		<script type="text/javascript" src="script/industry.js"></script>
		<script type="text/javascript" src="script/location.js"></script>
		
		<script type="text/javascript">
			var children = "<%= children%>";
			var children = eval('['+children+']');
			var root =  new Ext.tree.AsyncTreeNode({id:"root",text:"根节点",expanded:true,leaf:false,children: children});
		</script>
		<script type="text/javascript" src="script/init.js"></script>
		
	</body>
</html>
