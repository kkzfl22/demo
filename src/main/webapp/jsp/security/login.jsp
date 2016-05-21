<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="java.util.Map" %>	
<%@page import="java.util.Map.Entry" %>	
<%@page import="org.jasig.cas.client.authentication.AttributePrincipal" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登陆页面</title>
</head>
<body>
	<h3>用户登陆</h3>
	<h4>异常信息: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message};
	<br/>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</h4>
	<form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
		用户名:<input type="text" name="j_username" /> 
		密 码:<input	type="password" name="j_password" /> 
		
		<input type="submit" value="登陆" />
		
		<%
		AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
		Map<String, Object> map  = null;
		if (null != principal) {

			map = principal.getAttributes();

			if (null != map && !map.isEmpty()) {
				for (Entry<String, Object> item : map.entrySet()) {
					System.out.print("UsernamePasswordAuthenticationFilter info item, key:" + item.getKey() + ";value:" + item.getValue());
				}
			}
		}
		%>
	</form>
</body>
</html>