<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
<link REL="STYLESHEET" href="css/login.css" />

</head>
<%
		String msg = request.getParameter("msg");
	%>
<body>
	<div class="main">
		<form action="loginServlet" method="post">
			Telephone: <input type="text" name="telephone"><br>
			Password : <input type="password" name="password">
			<div class="pull-right">
				<a href="register.jsp">立即注册</a>
			</div>
			<br> <input type="submit" class="login" value="登录"><br>
			<p style="color: #ff2600">
				<% if(msg!=null){%>
				<%=msg%>
				<%}%>
			</p>
		</form>

	</div>


</body>
</html>