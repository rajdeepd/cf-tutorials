<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>1</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	</head>
	<body>
		<div class="blue-a">
			<div class="login-nav">
				
				<ul class="ul-a">
					<li>
						<i class="bg-logo-a"><img src="<c:url value="/resources/images/logo-a.png" /> "></i>
					</li>
					<li>
						<a class="label-1" href="#">Login</a>
						<i class="bg-arrow-right-a"><img class="arrow-right-a-img" src="<c:url value="/resources/images/arrow-right-a.png"/>" ></i>
					</li>
					<li>
						<a class="label-1" href="${pageContext.request.contextPath}/signUp">Register</a>
						
					</li>
				</ul>
			</div>
		</div>
		<div class="main-a">
			<c:if test="${not empty error}">
				<div class="errorblock">
					Your login attempt was not successful, try again.<br /> Caused :
					${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				</div>
			</c:if>
			${errorMsg}
			<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
				<input class="textbox-a text-email" type="text" placeholder="Username" name="j_username" required="required">
				<input class="textbox-a" type="password" placeholder="Password" name="j_password" required="required">
				<button class="btn-a">Login</button>
			</form>
		</div>
		
		
	</body>
</html>