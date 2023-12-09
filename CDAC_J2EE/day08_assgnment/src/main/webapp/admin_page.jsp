<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello, Admin  ${sessionScope.voter_info.name}</h1>
	<br>
	<hr><h2>Top 2 Candidates in current poll:</h2><hr>
		<table>
			<tr>
				<th>Name</th><th>Party</th><th>Votes</th>
			</tr>
<c:forEach items="${sessionScope.cDao_Instance.top2Analysis()}" var="canList">			
			<tr>
				<td>${canList.name.toUpperCase()}</td>
				<td>${canList.party.toUpperCase()}</td>
				<td>${canList.votes}</td>
			</tr>
</c:forEach>
		</table><hr>
	<br><br><br><br>
	<hr><h2>Partywise Analysis</h2><hr>
	<table>
			<tr>
				<th>Party</th><th>Votes</th>
			</tr>
<c:forEach items="${sessionScope.cDao_Instance.partywiseAnalysis().entrySet()}" var="canEntry">			
			<tr>
				<td>${canEntry.getKey().toUpperCase()}</td>
				<td>${canEntry.getValue()}</td>
			</tr>
</c:forEach>
	</table><hr>
	<br><br>
	<h1><a href="candidate_register.jsp">Register a new Candidate</a></h1>
	<br>
	<h3 style="text-align: center;">${sessionScope.candidate.statusMessage}</h3>
</body>
</html>