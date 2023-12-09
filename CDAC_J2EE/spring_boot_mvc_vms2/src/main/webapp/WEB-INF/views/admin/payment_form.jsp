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
	<spring:url var="url" value="/admin/location_form" />
	<form:form action="${url}" method="post" modelAttribute="paymentMode">
		<table style="background-color: cyan; margin: auto;">
			<tr>
				<td>Choose Payment Type</td>

				<td><form:select path="paymentType">
						<form:options items="${requestScope.modes}" />
					</form:select></td>
			</tr>
			<tr>
				<td>Enter Payment Amount</td>
				<td><form:input type="number" path="paymentAmount" /></td>
			</tr>
			<tr>
				<td>Choose Payment Date</td>
				<td><form:input type="date" path="paymentDate" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Proceed To Location Info" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>