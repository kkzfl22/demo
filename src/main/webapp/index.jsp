<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>首页</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/j_spring_cas_security_logout">sso退出</a>
	<br/>
	<a href="${pageContext.request.contextPath}/j_spring_security_logout">退出</a>

	这是首页，
	<br/>
	信息:
	<br/>
	欢迎您:SPRING_SECURITY_LAST_USERNAME==${sessionScope["SPRING_SECURITY_LAST_USERNAME"]}
	<br/>
	context:${sessionScope["SPRING_SECURITY_CONTEXT"]}
	<br/>
	<br/>
	显示用户名的标签:<sec:authentication property="name"></sec:authentication>
	<br/>
	<!-- 使用权限标签进行检查权限 -->
	<br/>
	<a href="${pageContext.request.contextPath}/jsp/security/testMedInvoke.jsp">进入testMedInvoke.jsp</a>
	<br/>
	<a href="${pageContext.request.contextPath}/jsp/security/updRole.jsp">进行访问修改权限的表</a>
	
</body>
</html>