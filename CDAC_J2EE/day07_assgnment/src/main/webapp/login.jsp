<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h5>JSessionID: <%= session.getId() %></h5>
	<form action="validate.jsp" method="post">
		<table style="background-color: whitesnow; margin: auto">
			<tr>
				<td>Enter User Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
</html>