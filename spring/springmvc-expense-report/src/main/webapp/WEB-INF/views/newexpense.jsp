<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>3</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	</head>
	<body>
		<div class="blue-b">
			<div class="inside-nav">
				<div class="topper">
					<img class="bg-logo-b" src="<c:url value="/resources/images/logo-b.png" />" />
					<ul class="nav-a">
						<li>
							<a href="#">Hi ${sessionScope.user.userName}</a> 
						</li>
						<li>
							|
						</li>
						<li>
							<a href="<c:url value="/j_spring_security_logout" />">Logout</a>
						</li>
					</ul>
				</div>
				<ul class="ul-b">
					<li>
						<a class="label-1" href="${pageContext.request.contextPath}/">My Expenses</a>
					</li>
					<li>
						<a class="label-1" href="${pageContext.request.contextPath}/createNewExpense">New Expense</a>
						<i class="bg-arrow-right-a"><img class="arrow-right-a-img" src="<c:url value="/resources/images/arrow-right-a.png" />"></i>
					</li>
					<c:if test="${sessionScope.user.role.roleName eq 'ROLE_MANAGER'}">
						<li>
							<a class="label-1" href="${pageContext.request.contextPath}/loadApprovalExpenses">Approvals</a>
						</li>
				   </c:if>
				</ul>
			</div>
		</div>
		<c:choose>
		<c:when test="${isEdit eq 'true'}">
			<div class="mc-a">
			<button class="btn-b my-expenses-btn"><img class="arrow-left-a-img" src="<c:url value="/resources/images/arrow-left-a.png" /> ">My Expenses
			</button>
			<h2 class="header label-2"> Editing an expense </h2>
			<form action="${pageContext.request.contextPath}/updateExpense" method="post" enctype="multipart/form-data">
			<textarea class="textbox-a" id="description" name="description" placeholder="description">${expense.description}</textarea><br>
			<input type="hidden" name="expenseId" id="expenseId" value="${expense.id}" />
			<input class="textbox-a" type="text" placeholder="Amount" name="amount" value="${expense.amount}"></input>
			<div class="action-a">
				<!-- <div class="textbox-a dd-a flt-left">
				Select Type <img class="arrow-down-c-img" src="<c:url value="/resources/images/arrow-down-c.png" />">  -->
				<select id="expenseTypeId" name="expenseTypeId">
					<c:forEach var="expenseType" items="${expenseTypeList}">
						
  					    <c:if test="${expense.expenseType.name eq expenseType.name }">
							<option value="${expenseType.id}" selected="selected">${expenseType.name}</option>
						</c:if>
						 <c:if test="${expense.expenseType.name ne expenseType.name }">
							<option value="${expenseType.id}">${expenseType.name}</option>
						</c:if>
					</c:forEach>
				</select>
				<!-- </div> -->
				<%-- <a class="label-7 flt-right" href="#"><img class="clip-a-img" src="<c:url value="/resources/images/clip-a.png" />"> --%>
				 <input type="file" name="file" id="file" class="file" /> <!-- Attach Receipt </a> -->
			</div>
			<button class="btn-a add-expense-btn">SAVE EXPENSE</button>
			</form>
		</div>
		</c:when>
		<c:otherwise>
		<div class="mc-a">
			<button class="btn-b my-expenses-btn"><img class="arrow-left-a-img" src="<c:url value="/resources/images/arrow-left-a.png" /> ">My Expenses
			</button>
			<h2 class="header label-2"> Adding new expense </h2>
			<form action="${pageContext.request.contextPath}/createNewExpenseReport" method="post" enctype="multipart/form-data">
			<textarea class="textbox-a" id="description" name="description" placeholder="description" required="required"  ></textarea><br>
			<input class="textbox-a" type="number" placeholder="Amount" name="amount" required="required"  max="100000" ></input>
			<div class="action-a">
			<!-- 	<div class="textbox-a dd-a flt-left"> -->
				<select id="expenseTypeId" name="expenseTypeId" style="	border: 1px solid #b8b8b8;
	            box-shadow: 0px 2px 0px 0px #b8b8b8;padding: 12px;font-size: 11px;color: #a7a5a6;width:100px;	margin:10px;">
					<option value="">                         </option>
					<c:forEach var="expenseType" items="${expenseTypeList}">
						<option value="${expenseType.id}">    ${expenseType.name}    </option>
					</c:forEach>
				</select>
				<!-- </div>
				 <a class="label-7 flt-right" href="#"><img class="clip-a-img" src="<c:url value="/resources/images/clip-a.png" />">  -->
				 <input type="file" name="file" id="file" title="Attachment"  class="file"/>
			    <!-- </a> -->
			</div>
			<button class="btn-a add-expense-btn">ADD EXPENSE</button>
			</form>
		</div>
		</c:otherwise>
		</c:choose>
	</body>
</html>