<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>View Attachment</title>
</head>
<body>
 
<h2>Attachment</h2>
 
<h3>Add new document</h3>
<form method="post" action="${pageContext.request.contextPath}/save" enctype="multipart/form-data">
    <table>
    <tr>
        <td>Name :</td>
        <td><input type="text" name="" id=""  /></td> 
    </tr>
    <tr>
        <td>Attachment :</td>
        <td><input type="file" name="file" id="file"></input></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Add Document"/>
        </td>
    </tr>
</table>  
</form>
 
<br/>
<h3>Document List</h3>
<c:if  test="${!empty documentList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Description</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${documentList}" var="document">
    <tr>
        <td width="100px">${document.fileName}</td>
        <td width="250px">${document.contentType}</td>
        <td width="20px">
            <a href="${pageContext.request.contextPath}/download/${document.id}.html"><img
                src="${pageContext.request.contextPath}/img/save_icon.gif" border="0"
                title="Download this document"/></a> 
         
            <a href="${pageContext.request.contextPath}/remove/${document.id}.html"
                onclick="return confirm('Are you sure you want to delete this document?')"><img
                src="${pageContext.request.contextPath}/img/delete_icon.gif" border="0"
                title="Delete this document"/></a> 
        </td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>