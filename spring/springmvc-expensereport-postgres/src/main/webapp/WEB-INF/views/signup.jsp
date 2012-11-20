<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
      <script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
    <style type="text/css">
     * { font-family: Verdana; font-size: 96%; }
    label { width: 10em; float: left; }
    label.error { float: none; color: red; vertical-align: bottom;
            padding:20px 0px 10px 0px; display:inline}
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
    <div id="contentWrapper">
    <div class="blue-b">
    <div class="inside-nav">
        <div class="logo">
            <img src="<c:url value="/resources/images/logo.png" />" />
        </div>
        
        <ul class="nav-a">
            <li>
            <a href="${pageContext.request.contextPath}/login" class="">Login</a>
            </li>
                 <li>|</li>
            <li>
            <a class="selected" href="#">Register</a>
            
            </li>
        </ul>
    </div>
    </div>
    
    <div class="mc-a">
    <c:if test="${not empty param.errorMsg}">
    
        <div class="errorblock">
            ${param.errorMsg}
        </div>
    </c:if>
    <div class="signing-form">
     <form action="${pageContext.request.contextPath}/signup" class="new_user" method="post" id="signupForm">
         <div>
              <label>Username</label>
              <input class="textbox-a" id="userName" name="userName" required="required"  placeholder="UserName" size="30" type="text">
        </div>
        <div>
              <label>Password</label>
              <input class="textbox-a" id="password" name="password" placeholder="Password" size="30" type="password" required="required">
        </div>
        <div>
              <label>Email</label>
              <input class="textbox-a" id="email" name="email" placeholder="email" size="30" type="email" required="required">
        </div>
        <div>
              <input class="btn-a" name="commit" type="submit" value="Sign Up">
        </div>
    </form>
    </div>
    </div>
    </div>
    </body>
</html>