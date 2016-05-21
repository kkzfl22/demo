<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>测试方法调用页面</title>
</head>
<body>
	测试方法调用:
	<br/><sec:authentication property="name"></sec:authentication>
	<a href='${pageContext.request.contextPath}/security/testall.do?name=<sec:authentication property="name"></sec:authentication>'>登陆所有用户都能调用</a>
	
	<br/>
	<a href='${pageContext.request.contextPath}/security/tesuser.do?name=<sec:authentication property="name"></sec:authentication>'>user调用</a>
	
</body>
</html>