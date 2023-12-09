<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>${requestScope.message}</h3>
<h3>${requestScope.user_details}</h3>
<table style="background-color: lightgrey; margin: auto">
<c:forEach var="vendor" items="${requestScope.vendor_list}">

	<tr>
		<td>${vendor.name}</td>
		<td>${vendor.email}</td>
		<td>${vendor.regAmount}</td>
		<td>${vendor.regDate}</td>
		<td><a href="<spring:url value='/admin/update?vid=${vendor.id}'/>">Update</a></td>
		<td><a href="<spring:url value='/admin/delete?vid=${vendor.id}'/>">Delete</a></td>
	</tr>	
</c:forEach>						
</table>
<h5>
	<a href="<spring:url value='/admin/add'/>">Add New Vendor</a>
</h5>
<h5>
	<a href="<spring:url value='/user/logout'/>">Log Me Out</a>
</h5>
</body>
</html>