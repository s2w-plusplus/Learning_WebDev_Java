<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Hello, Welcome to JSP</h1>
	<p>Current Time is <%=LocalDateTime.now()%></p>
	<h3><a href="login.jsp">Let's get you logged in</a></h3>

</body>
</html>