<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/signin.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">

<title>注册页面</title>
</head>
<%
		String message = request.getParameter("message");
	%>
<body>
	<div class="container">
		<form class="form-signin" action="registerServlet">
			<h2 class="form-signin-heading ">立即注册</h2>
			<label for="inputUserName" class="sr-only">User Name</label> <input
				type="text" name="name" id="inputUserName" class="form-control"
				placeholder="User name" required autofocus> <label
				for="inputNumber" class="sr-only">Telephone Number</label><input
				type="text" name=teleNum id="inputNumber" class="form-control"
				placeholder="Telephone Number" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="password1" id="inputPassword"
				class="form-control" placeholder="Password" required> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="password2" id="inputPassword"
				class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="radio" name="gender" value="male">
					男性
				</label> <label> <input type="radio" name="gender" value="female">
					女性
				</label>
			</div>
			<div class="pull-right">
				<a href="login.jsp">立即登录</a>
			</div>
			<button type="submit" class="register" id="registerButton">注册</button>
			<br>
			<p style="color: #ff2600">
				<% if(message!=null){%>
				<%=message%>
				<%}%>
			</p>
		</form>

	</div>
	<!-- /container -->
</body>
</html>