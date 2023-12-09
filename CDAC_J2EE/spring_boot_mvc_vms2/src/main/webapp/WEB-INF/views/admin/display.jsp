<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>Vendor : ${sessionScope.vendor}</h5>
<h5>Payment Mode : ${sessionScope.vendor.mode}</h5>
<h5>Location : ${sessionScope.vendor.vendorLocation}</h5>
</body>
</html>