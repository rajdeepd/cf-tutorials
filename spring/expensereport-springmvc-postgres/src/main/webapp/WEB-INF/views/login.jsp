<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
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
            <a class="selected" href="#">Login</a>
            </li>
            <li>|</li>
            <li>
            <a class="" href="${pageContext.request.contextPath}/signup">Register</a>
            </li>
        </ul>
    </div>
    </div>
    <div class="mc-a">
    <div class="signing-form">
    <c:if test="${not empty error}">
        <div class="errorblock">
            Your login attempt was not successful, try again.<br /> Caused :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <c:if test="${not empty param.succMsg }">
        <div class="successblock">
         ${param.succMsg}
        </div>
    
    </c:if>
    ${errorMsg}
    <form action="<c:url value='j_spring_security_check' />" method='POST'>
        <div>
            <label>Username</label>
              <input class="textbox-a" id="username" name="j_username" placeholder="UserName" type="text" required="required">
          </div>
          <div>
              <label>Password</label>
              <input class="textbox-a" id="password" name="j_password" placeholder="Password" type="password" required="required">
          </div>
          <div>
              <input class="btn-a" name="commit" type="submit" value="Log in">
          </div>
    </form>
    </div>
    
    </div>
    </div>
    </body>
</html>