<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>
<h1>Logowanie</h1>
<p>podaj login i hasło</p>
<form method="Post" action="j_security_check">login : <input
	type="text" size="15" maxlength="25" name="j_username"><br>
<br>
hasło : <input type="password" size="15" maxlength="25"
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