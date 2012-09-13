<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>
<h3>Logowanie - podaj login i hasło</h3>
<form method="Post" action="j_security_check">
login: <input type="text" size="15" maxlength="25" name="j_username"><br>
<br>
hasło: <input type="password" size="15" maxlength="25"
	name="j_password"><br>
<br>
<input value="Login" type="submit">
&nbsp;&nbsp;&nbsp;&nbsp;
<input type="reset" onclick="history.go(-1)" value="wyjdz">
</form>
<br>
<a href="<%=request.getContextPath()%>/">do strony głównej</a>
</body>
</html>