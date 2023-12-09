<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	
	 <form method="post">
      <table style="background-color: lightgrey; margin: auto">
        <tr>
          <td>Email</td>
          <td><input type="email" name="email" autofocus></td>
        </tr>
        <tr>
          <td>Password</td>
          <td><input type="password" name="password"></td>
        </tr>

        <tr>
          	<td><input type="submit" value="Login"></td> 
        </tr>
        
      </table>
    </form>
 
 ${requestScope.message}

</body>
</html>