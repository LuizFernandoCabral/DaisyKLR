<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Daisy KLR</title>
</head>
<body>
	<p>DAISY KLR</p>
	
	<form method="post" action="Login">
	<p>
		<label for="numUSP">Nusp:</label>
		<input type="text" name="nusp" id="nusp" autofocus required>
		<br />
       	<label for="password">Senha:</label>
       	<input type="password" name="password" id="password" required/>
       	<br />
       	<input type="submit" value="Entrar" />
	</p>
	</form>
	
</body>
</html>