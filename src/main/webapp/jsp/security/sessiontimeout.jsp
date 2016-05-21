<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>会话超时</title>
</head>
<body>
	<h3>当前会话超时，请重新登陆</h3>
	  <a href="${pageContext.request.contextPath}/jsp/security/login.jsp">登陆 </a>
	  <br/>
	  <a href="${pageContext.request.contextPath}">sso登陆 </a>
	  <!--
	<a href="${pageContext.request.contextPath}">登陆 </a>
	-->
</body>
</html>