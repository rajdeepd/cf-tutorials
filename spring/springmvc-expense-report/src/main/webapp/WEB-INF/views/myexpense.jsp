<!DOCTYPE HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html>
	<head>
		<title>2</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="<c:url value="resources/javascript/jquery-1.8.1.js"/>" ></script>
		<script src="<c:url value="resources/javascript/jquery.tablesorter.js"/>" ></script>
		<script src="<c:url value="resources/javascript/jquery.tablesorter.min.js"/>" ></script>
		<script src="<c:url value="resources/javascript/jquery.tablesorter.pager.js"/>" ></script>

		
	</head>
	<body>
		<div class="blue-b">
			<div class="inside-nav">
				<div class="topper">
					<img class="bg-logo-b" src="<c:url value="/resources/images/logo-b.png" />" />
					<ul class="nav-a">
						<li>
							<a href="#">Hi ${sessionScope.user.userName} </a> 
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
						<a class="label-1" href="#">My Expenses</a>
						<i class="bg-arrow-right-a"><img class="arrow-right-a-img" src="<c:url value="/resources/images/arrow-right-a.png" />" /></i>
					</li>
					<li>
						<a class="label-1" href="${pageContext.request.contextPath}/createNewExpense">New Expenses</a>
						
					</li>
					<c:if test="${sessionScope.user.role.roleName eq 'ROLE_MANAGER'}">
						<li>
							<a class="label-1" href="${pageContext.request.contextPath}/loadApprovalExpenses">Approvals</a>
						
						</li>
					</c:if>
				</ul>
			</div>
		</div>
		
		<div class="mc-a">
			<h2 class="header label-2">
				Expenses
			</h2>
			
		<c:if test="${fn:length(pendingExpenseList) gt 0 }">
			<table  id="table-a" class="table-a">
				<thead>
					<tr>
					<td class="td-b">
						<h5 class="label-3">Attachment <img class="clip-b-img" src="<c:url value="/resources/images/clip-b.png" />"></h5>
					</td>
						<td class="td-b">
							<h5 class="label-3">Date </h5>
						</td>
						<td  class="td-b">
							<h5 class="label-3">Expense Type </h5>
						</td>
						<td  class="td-c">
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
							 <a href="${pageContext.request.contextPath}/download?attachmentId=${expense.attachment.id}"><img class="clip-b-img" src="<c:url value="/resources/images/clip-b.png" />"></a>
							</c:if>
						</td>
						<td>
							<h5 class="label-4">${expense.expenseDate} </h5>
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
									<img class="right-b-img" src="<c:url value="/resources/images/right-b.png" />" > Pending
									<a href="${pageContext.request.contextPath}/deleteExpense?expenseId=${expense.id}" class="bg-cross-b"><img class="cross-b-img" src="<c:url value="/resources/images/cross-b.png" />"> </a>
									<a href="${pageContext.request.contextPath}/editExpense?expenseId=${expense.id}" class="bg-cross-c"><img class="cross-b-img" src="<c:url value="/resources/images/edit1.jpg" />"> </a>
								</i>
								
								</c:if>
								<c:if test="${expense.state eq 'APPROVED' }">
								<i class="alt-a label-5 green">
									<img class="right-a-img" src="<c:url value="/resources/images/right-a.png" /> ">
									 Approved</i>
								<i class="alt-b label-6">
									<img class="right-b-img" src="<c:url value="/resources/images/right-b.png" />" > Approved
									<a href="${pageContext.request.contextPath}/deleteExpense?expenseId=${expense.id}" class="bg-cross-b"><img class="cross-b-img" src="<c:url value="/resources/images/cross-b.png" />"> </a>
									<a href="${pageContext.request.contextPath}/editExpense?expenseId=${expense.id}" class="bg-cross-c"><img class="cross-b-img" src="<c:url value="/resources/images/edit1.jpg" />"> </a>
								</i>
								</c:if>
								<c:if test="${expense.state eq 'REJECTED' }">
								 <i class="alt-a label-5 red"><img class="cross-a-img" src="<c:url value="/resources/images/cross-a.png" />" > Rejected</i> 
								<i class="alt-b label-6">
									<img class="right-b-img" src="<c:url value="/resources/images/cross-a.png" />" > Rejected
									<a href="${pageContext.request.contextPath}/deleteExpense?expenseId=${expense.id}" class="bg-cross-b"><img class="cross-b-img" src="<c:url value="/resources/images/cross-b.png" />" > </a>
									<a href="${pageContext.request.contextPath}/editExpense?expenseId=${expense.id}" class="bg-cross-c"><img class="cross-b-img" src="<c:url value="/resources/images/edit1.jpg" />"> </a>
								</i>
								</c:if>
							</h5>
						</td>
					</tr>	
				</c:forEach>
				</tbody>
			</table>
   <!-- 
			<div class="pagination pagination-1">
						<ul id="tata">
							<li class="disabled">
								<a href="#">< &nbsp;&nbsp; previous</a>
							</li>
							<li class="active">
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">4</a>
							</li>
							<li>
								<a href="#">next &nbsp;&nbsp;></a>
							</li>
						</ul>
					</div> -->
					
		
		
		
	
						
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
	</body>
</html>





















