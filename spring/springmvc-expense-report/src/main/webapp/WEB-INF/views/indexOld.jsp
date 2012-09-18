<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Message : ${message}</h3>	
	<h3>Username : ${userName}</h3>	
 
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
	<a href="<c:url value="/createNewExpense" />" target="">Create A New Expense </a>
	
</body>
</html>