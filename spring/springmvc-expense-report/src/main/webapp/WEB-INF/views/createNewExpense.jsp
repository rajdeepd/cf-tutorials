<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create New Expense </title>
</head>
<body>
	<h3>New Expense :</h3>
	<form action="${pageContext.request.contextPath}/createNewExpenseReport" method="post" enctype="multipart/form-data">
		<table><tr><td>Description :</td><td><input type="text" name="description" id="description" /></td></tr>
	
		<tr><td>File Name:</td><td><input type="text" name="fileName" id="fileName" /></td></tr>
	
		<tr><td>Type :</td><td><input type="text" name="type" id="type" /></td></tr>
		
		<tr>
        	<td>Attachment :</td>
     	   <td><input type="file" name="file" id="file"></input></td>
  	   </tr>
	
		<tr><td colspan="2"><input type="submit" name="submit" /></td></tr></table> 
	</form>
</body>
</html>