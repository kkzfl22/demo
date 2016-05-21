<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 1，引入extjs的样式 -->
<link rel="styleSheet" type="text/css" href="js/extjs/resources/css/ext-all.css" />
<!-- 2,引入extjs的核心文件 -->
<script type="text/javascript" charset="UTF-8" src="js/extjs/ext-all-debug.js" ></script>
<!-- 3，引入国际化文件 -->
<script type="text/javascript" charset="UTF-8" src="js/extjs/ext-lang-zh_CN.js"></script>

<script type="text/javascript" charset="UTF-8" src="app/helloword/helloword.js">
</script>
</head>
<body>

</body>
</html>