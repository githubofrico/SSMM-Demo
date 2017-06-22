<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function toRegist() {
		document.getElementById("form").setAttribute("action",
				"choice/toregist");
	}
</script>
</head>

<body>
	<div align="center">
		<h4>We Bare Bears</h4>
	</div>
	<div align="center">
		<img alt="三兄弟" src="img/3b.png">
	</div>
	<br>
	<div align="center">
		<form action="choice/tologin" id="form" method="post">
			<input type="submit" name="login" value="去登录">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
				name="regist" value="去注册" onclick="toRegist()">
		</form>
	</div>
</body>
</html>
