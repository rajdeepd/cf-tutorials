<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h3>Message : Welcome</h3>	
	<h3>Username : ABCD </h3>	
 
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
	<a href="<c:url value="/createNewExpense" />" target="">Create A New Expense </a>
 
</body>
</html>