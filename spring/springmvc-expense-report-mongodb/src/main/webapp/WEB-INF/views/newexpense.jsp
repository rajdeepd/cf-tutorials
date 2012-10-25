<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>New Expense</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
<style type="text/css">
* {
	font-family: Verdana;
	font-size: 96%;
}

label {
	width: 10em;
	float: left;
}

label.error {
	float: none;
	color: red;
	vertical-align: bottom;
	padding: 100px 0px 0px 0px;
	display: inline
}

p {
	clear: both;
}

.submit {
	margin-left: 12em;
}

em {
	font-weight: bold;
	padding-right: 1em;
	vertical-align: top;
}
</style>
<script>
	$(document).ready(function() {
		$("#newexpenseForm").validate({
			rules : {
				description : "required",
				amount : "required",
			},
			messages : {
				description : "Please enter description",
				amount : "Please enter amount",
			}
		});
		$("#editExpenseForm").validate({
			rules : {
				description : "required",
				amount : "required digits",
			},
			messages : {
				description : "Please enter description",
				amount : "Please enter amount",
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
					<li><a href="#">Hi ${sessionScope.user.userName}</a></li>
					<li>|</li>
					<li><a href="<c:url value="/j_spring_security_logout" />">Logout</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="nav">
			<ul>
				<li><a href="${pageContext.request.contextPath}/">My
						Expenses</a></li>
				<li><a
					href="${pageContext.request.contextPath}/createNewExpense">New
						Expense</a></li>
				<c:if test="${sessionScope.user.role.roleName eq 'ROLE_MANAGER'}">
					<li><a
						href="${pageContext.request.contextPath}/loadApprovalExpenses">Approvals</a>
					</li>
				</c:if>
			</ul>
		</div>


		<c:choose>
			<c:when test="${isEdit eq 'true'}">
				<div class="mc-a">
					<form action="${pageContext.request.contextPath}/updateExpense"
						class="mtop80" method="post" enctype="multipart/form-data"
						id="editExpenseForm">
						<h2 class="header label-2 mbot20">Editing an expense</h2>
						<div class="action-a">
							<label class="label-name" for="expense_description">Description</label>
							<textarea class="textbox-a" cols="40" id="description"
								name="description" placeholder="description" rows="20"
								required="required">${expense.description}</textarea>
						</div>
						<input type="hidden" name="expenseId" id="expenseId"
							value="${expense.expenseId}" />
						<div class="action-a">
							<label class="label-name" for="expense_amount">Amount</label> <input
								class="textbox-a dd-b flt-left mleft0" id="amount" name="amount"
								placeholder="Amount" size="30" type="text"
								required="required digits" name="amount" max="100000"
								value="${expense.amount}"> <a class="label-7 flt-right"
								href="#" id="attachment-holder"><img class="clip-a-img"
								src="<c:url value="/resources/images/clip-a.png" />"><span
								id="attachment-name">Attach Receipt</span> </a> <input id="file"
								name="file" value="${expense.attachment}" style="display: none;"
								type="file">
						</div>
						<div class="action-a">
							<label class="label-name" for="expenseTypeId">Expense
								type</label> <select id="expenseTypeId" name="expenseTypeId"
								required="required" class="textbox-a dd-a mleft0">
								<c:forEach var="expenseType" items="${expenseTypeList}">

									<c:if test="${expense.expenseType.name eq expenseType.name }">
										<option value="${expenseType.expenseTypeId}" selected="selected">${expenseType.name}</option>
									</c:if>
									<c:if test="${expense.expenseType.name ne expenseType.name }">
										<option value="${expenseType.expenseTypeId}">${expenseType.name}</option>
									</c:if>
								</c:forEach>
							</select><br>
						</div>
						<button class="btn-a add-expense-btn">SAVE EXPENSE</button>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div class="mc-a">
					<form
						action="${pageContext.request.contextPath}/createNewExpenseReport"
						class="mtop80" method="post" enctype="multipart/form-data"
						id="newexpenseForm">
						<h2 class="header label-2 mbot20">Adding new expense</h2>
						<div class="action-a">
							<label class="label-name" for="expense_description">Description</label>
							<textarea class="textbox-a" cols="40" id="description"
								name="description" placeholder="description" rows="20"
								required="required"></textarea>
						</div>
						<div class="action-a">
							<label class="label-name" for="expense_amount">Amount</label> <input
								class="textbox-a dd-b flt-left mleft0" id="amount" name="amount"
								placeholder="Amount" size="30" type="text"
								required="required digits" name="amount" max="100000">

							<a class="label-7 flt-right" href="#" id="attachment-holder"><img
								class="clip-a-img"
								src="<c:url value="/resources/images/clip-a.png" />"><span
								id="attachment-name">Attach Receipt</span> </a> <input id="file"
								name="file" style="display: none;" type="file">
						</div>

						<div class="action-a">
							<label class="label-name" for="expenseTypeId">Expense
								type</label> <select id="expenseTypeId" name="expenseTypeId"
								class="textbox-a dd-a mleft0">
								<option value=""></option>
								<c:forEach var="expenseType" items="${expenseTypeList}">
									<option value="${expenseType.expenseTypeId}">${expenseType.name}
									</option>
								</c:forEach>
							</select><br>
						</div>
						<button class="btn-a add-expense-btn">ADD EXPENSE</button>
					</form>
					<script type="text/javascript">
						$(document)
								.ready(
										function() {
											$('#attachment-holder').live(
													'click',
													function() {
														$('#file').trigger(
																'click');
													});

											$("input[type='file']")
													.change(
															function() {
																if ($(this)
																		.val() == "")
																	$(
																			'#attachment-name')
																			.html(
																					"Attach Receipt");
																else
																	$(
																			'#attachment-name')
																			.html(
																					$(
																							this)
																							.val()
																							.replace(
																									/^.*\\/g,
																									''));
															});
										})
					</script>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>