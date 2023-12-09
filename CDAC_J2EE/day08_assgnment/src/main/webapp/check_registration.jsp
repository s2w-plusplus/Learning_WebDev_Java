<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="candidate" class="javaBeans.CandidateBean" scope="session"/>
<jsp:setProperty property="*"  name="candidate"/>
<body>

<c:redirect  url="${sessionScope.candidate. validateCandidate()}.jsp"/>
</body>
</html>