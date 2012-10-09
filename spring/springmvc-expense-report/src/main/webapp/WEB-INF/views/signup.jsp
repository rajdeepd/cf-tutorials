<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>1</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
  		<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
		<style type="text/css">
		* { font-family: Verdana; font-size: 96%; }
			label { width: 10em; float: left; }
			label.error { float: none; color: red; vertical-align: bottom;
	        padding:100px 0px 0px 0px; display:inline}
			p { clear: both; }
			.submit { margin-left: 12em; }
			em { font-weight: bold; padding-right: 1em; vertical-align: top; }
		</style>
  		<script>
  			$(document).ready(function(){
    		$("#signupForm").validate({
    			   rules: {
    				     userName: "required",
    				     password: "required",
    				     email: {
    				       required: true,
    				       email: true
    				     }
    				   },
    				   messages: {
    				     userName: "Please enter your user name",
    				     password: "Please enter your password",
    				     email: {
    				       required: "Please enter your email",
    				       email: "Your email address must be in the format of name@domain.com"
    				     }
    				   }
    				});
  			});
  		</script>
	
	</head>
	<body>
		<div class="blue-a">
			<div class="login-nav">
				
				<ul class="ul-a">
					<li>
						<i class="bg-logo-a"><img src="<c:url value="/resources/images/logo-a.png" /> "></i>
					</li>
					<li>
						<a class="label-1" href="${pageContext.request.contextPath}/login">Login</a>
					</li>
					<li>
						<i class="bg-arrow-right-a"><img class="arrow-right-a-img" src="<c:url value="/resources/images/arrow-right-a.png" /> "></i>
						<a class="label-1" href="#">Register</a>
						
					</li>
				</ul>
			</div>
		</div>
		
		<div class="main-a">
			<c:if test="${not empty param.errorMsg}">
			
				<div class="errorblock">
					${param.errorMsg}
				</div>
			</c:if>
		<br><br><br>
			 <form action="${pageContext.request.contextPath}/signUpUser" method="post" id="signupForm">
					<input class="textbox-a text-email" type="email" placeholder="Email" id="email" name="email" required="required" >
					<input class="textbox-a" type="text" placeholder="UserName" id="userName" name="userName" required="required" >
					<input class="textbox-a" type="password" placeholder="Password" id="password" name="password" required="required">
					<button class="btn-a">Submit</button>
			</form>
		</div>
		
		
	</body>
</html>