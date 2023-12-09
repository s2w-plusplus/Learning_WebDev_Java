<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--For enabling form binding technique , import spring supplied form tag lib. --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<spring:url var="url" value="/admin/register" />
	<form:form action="${url}" method="post" modelAttribute="location">
		<table style="background-color: cyan; margin: auto;">
			<tr>
				<td>Enter City</td>
				<td><form:input path="city" /></td>
			</tr>
			<tr>
				<td>Enter State</td>
				<td><form:input path="state" /></td>
			</tr>
			<tr>
				<td>Enter Country</td>
				<td><form:input path="country" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Proceed To Registration" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>