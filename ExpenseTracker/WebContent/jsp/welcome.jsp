<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.gl.expencetracker.ExpenseGroups"%>
<%@page import="com.gl.expencetracker.ExpenseUser"%>
<%@page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/style.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
<%!ExpenseUser curUser ; 
 %> 
<% curUser = (ExpenseUser) session.getAttribute("user");  
 %> 
	<p>
		Welcome
		<%=curUser %>
		<br>
		
<TR>
	<TH>Name</TH>
	<TH>Id</TH>
	<TH>Admin</TH>
</TR>
<c:forEach var="item" items="${sessionScope.grpList}">

 <tr>
   <td><c:out value="${item.grpName}" /></td>
   <td><c:out value="${item.grpId}" /></td>
   <td><c:out value="${item.createdBy}" /></td>
 </tr>		
</c:forEach>
	<TABLE BORDER="1">
                <TR>
                    <TH>ID</TH>
                    <TH>Name</TH>
                </TR>
                <TR>
                    <TD> 1 </TD>
                    <TD> akash </TD>
                </TR>
            </TABLE>
</body>
</html>
