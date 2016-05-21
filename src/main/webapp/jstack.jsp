<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>jstack信息</title>
</head>
<body>
<%
	for(Map.Entry<Thread,StackTraceElement[]> stacktrace : Thread.getAllStackTraces().entrySet())
	{
		Thread thread = stacktrace.getKey();
		StackTraceElement[] stack = stacktrace.getValue();
		if(thread.equals(Thread.currentThread()))
		{
			continue;
		}
		out.print("\n 线程:"+thread.getName() + "\n<br/>");
		for(StackTraceElement element : stack)
		{
			out.print("\t"+element+"\n<br/>");
		}
	}
%>
</body>
</html>