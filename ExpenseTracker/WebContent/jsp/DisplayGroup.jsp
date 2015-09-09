<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.gl.expencetracker.*"%>
	
<!DOCTYPE html>
<html>
<head>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/style.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>

<script type="text/javascript">
	//write here java script coding.

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
p {text-align:center;}
</style>
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.grpList.size() > 0}">
			<%@ include file="./GroupDesign.jsp"%>
		</c:when>
		<c:otherwise>
			
			<div class="container">
			<H2 align="center"> <font color="Green">Tracking your expenses & money
						is now simpler!</font></H2>
				<img src="../resources/groups.jpg"
					alt="Center Align image in bootstrap"
					class="img-rounded center-block">
					<a href="" data-toggle="modal" data-target="#create" class="text-center"><font
				color="Blue"><h3>Add Group</h3></font> </a>
			</div>
		</c:otherwise>
	</c:choose>

</body>
</html>