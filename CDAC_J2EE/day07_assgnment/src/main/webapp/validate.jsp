<%@page import="java.util.HashMap"%>
<%@page import="pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%! 
HashMap<String,User> usermap;
public void jspInit(){
	usermap=new HashMap<>();
	usermap.put("Steve", new User("Steve","steve#123",25));
	usermap.put("Clark", new User("Clark","clark#123",30));
	usermap.put("Bruce", new User("Bruce","bruce#123",21));
}
%>
<body>
<%
	String name=request.getParameter("name");
	String password=request.getParameter("password");
	User user = usermap.get(name);
	if(user != null){
		if(user.getPasswd().equals(password)){
			session.setAttribute("user", user);
			response.sendRedirect("details.jsp");
		}
		else{
%>
				<h5>Invalid Password , Please <a href="login.jsp">Try Again</a></h5>
<% 	
		}
	}
	else{
%>
				<h5>Invalid User, Please <a href="login.jsp">Try Again</a></h5>	
<%
} 
%>
</body>
<%!
public void jspDestroy() {
		System.out.println("in jsp destroy..");
	}
%>
</html>