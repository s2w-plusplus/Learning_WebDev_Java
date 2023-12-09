<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Hello, ${sessionScope.user.name}</h3>
	<%
	session.invalidate();
	%>
	<h4>You have been logged out....</h4>
	<h5>
		<a href="index.jsp">Visit Again</a>
	</h5>
</body>
</html>