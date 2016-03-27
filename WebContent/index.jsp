<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" type="text/css" media="all">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HandBook JavaScript</title>
</head>
<body>
	<form action="CheckForwardServlet" align="center" method="POST">
		<input class="input-text-name" type="text" name="defenition"></input>
		<input class="button" type="submit" name="search" value="Search"></input>
		<a href ="edit.html">
			<input class="button" type="button" name="edit" value="Edit"></input>
		</a>
	</form>
	<%
		String result = (String) session.getAttribute("getDefenition");
		if (result == null) {
			result = " ";
		}
	%>
	<p class ="text-style" align="center"><%=result%></p>

</body>
</html>