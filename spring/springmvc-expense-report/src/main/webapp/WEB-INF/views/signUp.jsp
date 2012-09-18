<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>1</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<!-- <style type="text/css">
		
		input:required {
 			border-style:solid;
 			border-width:thick;
		}
		input:valid {
 			background-color: #adff2f;
		}
		input:invalid {
 			background-color: #f08080;
		}
		input:out-of-range {
 			background-color: #808080;
		}
		input:in-range {
			background-color: #8a2be2;
		}
		
		</style> -->
		<script type="text/javascript">
		
		function ValidateEmail() 
		{
	     var email = document.getElementById("email");
	     var errorblock = document.getElementById("errorblock");
	     if(email!=null && email.value!=""){
		 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email.value))
		  {
			 alert("email1"+email.value);
		    return true;
		  }
		 	errorblock.innerHTML = "Please Enter valid Email Address";
		    return false;
	     }else{
	    	 alert("please enter email address!");
	    	 return false;
	     }
		}
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
			 <form action="${pageContext.request.contextPath}/signUpUser" method="post">
					<input class="textbox-a text-email" type="email" placeholder="Email" id="email" name="email" required="required" >
					<input class="textbox-a" type="text" placeholder="UserName" id="userName" name="userName" required="required">
					<input class="textbox-a" type="password" placeholder="Password" id="password" name="password" required="required">
					<button class="btn-a">Submit</button>
			</form>
		</div>
		
		
	</body>
</html>