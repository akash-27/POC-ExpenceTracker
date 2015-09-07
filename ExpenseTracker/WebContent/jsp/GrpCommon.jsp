<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@  taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/style.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>

<script type="text/javascript">
	
</script>

<style type="text/css">
//
type



 



css



 



class






.
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header Page</title>
</head>
<body>
	<select name="username" id="username">
		<c:forEach var="item" items="${sessionScope.grpList}">
				<option value="${item.grpId}" selected="selected">${item.grpName}</option>
		</c:forEach>
	</select>
</body>
</html>