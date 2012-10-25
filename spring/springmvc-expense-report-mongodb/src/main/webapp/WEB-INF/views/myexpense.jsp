<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html>
	<head>
		<title>My Expense</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="<c:url value="resources/javascript/jquery-1.8.1.js"/>" ></script>
		<script src="<c:url value="resources/javascript/jquery.tablesorter.js"/>" ></script>
		<script src="<c:url value="resources/javascript/jquery.tablesorter.min.js"/>" ></script>
		<script src="<c:url value="resources/javascript/jquery.tablesorter.pager.js"/>" ></script>

		
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
			  	</div>
			  	<div id="nav">
				<ul>
					<li>
						<a  href="${pageContext.request.contextPath}/">My Expenses</a>
					</li>
					<li>
						<a  href="${pageContext.request.contextPath}/createNewExpense">New Expense</a>
					</li>
					<c:if test="${sessionScope.user.role.roleName eq 'ROLE_MANAGER'}">
						<li>
							<a href="${pageContext.request.contextPath}/loadApprovalExpenses">Approvals</a>
						</li>
				   </c:if>
				</ul>
				</div>
		
		<div class="mc-a">
			<h2 class="header label-2">
				Expenses
			</h2>
			
		<c:if test="${fn:length(pendingExpenseList) gt 0 }">
			<table  id="table-a" class="table-a mtop80">
				<thead>
					<tr>
					<td class="td-a">
						<h5 class="label-3">Attachment <img class="clip-b-img" src="<c:url value="/resources/images/clip-b.png" />"></h5>
					</td>
						<td class="td-b">
							<h5 class="label-3">Date </h5>
						</td>
						<td  class="td-c">
							<h5 class="label-3">Expense Type </h5>
						</td>
						<td  class="td-d">
							<h5 class="label-3">Description </h5>
						</td>
						<td  class="td-d">
							<h5 class="label-3">Status </h5>
						</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="expense" items="${pendingExpenseList}">
					<tr class="tr-odd">
						<td>
							<c:if test="${not empty expense.attachment.fileName}">
							 <a href="${pageContext.request.contextPath}/download?attachmentId=${expense.attachment.attachmentId}"><img class="clip-b-img" src="<c:url value="/resources/images/clip-b.png" />"></a>
							</c:if>
						</td>
						<td>
							<h5 class="label-4">${expense.expenseDate}</h5>
						</td>
						<td>
							<h5 class="label-4">${expense.expenseType.name} </h5>
						</td>
						<td>
							<h5 class="label-4">${expense.description} </h5>
						</td>
						<td>
							<h5 class="label-4">
								<c:if test="${expense.state eq 'NEW' }">
								<i class="alt-a label-5 blue">
									<img class="right-a-img" src="<c:url value="/resources/images/question-a.png" /> ">
									 Pending</i>
								<i class="alt-b label-6">
									<img class="question-a-img" src="<c:url value="/resources/images/question-a.png" />" > Pending
									<a href="${pageContext.request.contextPath}/editExpense?expenseId=${expense.expenseId}" class="bg-edit-b">
									     <img class="clip-b-img" src="<c:url value="/resources/images/edit-a.png" />"> </a>
									<a href="${pageContext.request.contextPath}/deleteExpense?expenseId=${expense.expenseId}" class="bg-cross-b">
									     <img class="cross-b-img" src="<c:url value="/resources/images/cross-b.png" />"> </a>
								</i>
								
								</c:if>
								<c:if test="${expense.state eq 'APPROVED' }">
								<i class="alt-a label-5 green">
									<img class="right-a-img" src="<c:url value="/resources/images/right-a.png" /> ">
									 Approved</i>
								<i class="alt-b label-6">
									<img class="right-b-img" src="<c:url value="/resources/images/right-b.png" />" > Approved
									<a href="${pageContext.request.contextPath}/editExpense?expenseId=${expense.expenseId}" class="bg-edit-b">
									  <img class="clip-b-img" src="<c:url value="/resources/images/edit-a.png" />"> </a>
									<a href="${pageContext.request.contextPath}/deleteExpense?expenseId=${expense.expenseId}" class="bg-cross-b">
									  <img class="cross-b-img" src="<c:url value="/resources/images/cross-b.png" />"> </a>
								</i>
								</c:if>
								<c:if test="${expense.state eq 'REJECTED' }">
								 <i class="alt-a label-5 red"><img class="cross-a-img" src="<c:url value="/resources/images/cross-a.png" />" > Rejected</i> 
								<i class="alt-b label-6">
									<img class="right-b-img" src="<c:url value="/resources/images/cross-a.png" />" > Rejected
									<a href="${pageContext.request.contextPath}/editExpense?expenseId=${expense.expenseId}" class="bg-edit-b">
									   <img class="cclip-b-img" src="<c:url value="/resources/images/edit-a.png" />"> </a>
									<a href="${pageContext.request.contextPath}/deleteExpense?expenseId=${expense.expenseId}" class="bg-cross-b">
									   <img class="cross-b-img" src="<c:url value="/resources/images/cross-b.png" />" > </a>
								</i>
								</c:if>
							</h5>
						</td>
					</tr>	
				</c:forEach>
				</tbody>
			</table>
						
		<div id="pager"  class="pagination pagination-1" >

		<form>

			<img src="<c:url value="/resources/images/first.png"/>"  class="first"/>

			<img src="<c:url value="/resources/images/prev.png"/>"  class="prev"/>

			<input type="text"  class="pagedisplay"/>

			<img src="<c:url value="/resources/images/next1.png" />"  class="next"/>

			<img src="<c:url value="/resources/images/last.png" />"  class="last"/>

			<select  class="pagesize">

				<option selected="selected"  value="10">10</option>

				<option value="20">20</option>

				<option value="30">30</option>

				<option  value="40">40</option>

			</select>

		</form>

	</div>
		</c:if>
			
		</div>

		
				<script>

    jQuery(document).ready(function() {
    	

    jQuery("#table-a")

    .tablesorter({widthFixed: true, widgets: ['zebra']})
    .tablesorterPager({container: $("#pager")}); 

    });

</script>
</div>
	</body>
</html>





















