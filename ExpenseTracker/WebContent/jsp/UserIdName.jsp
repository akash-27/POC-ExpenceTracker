<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>

<script type="text/javascript">
function checkname() {
	var name = document.getElementById("usrname1");
	if (name.value == "") {
		$( "#phoneno1" ).prop( "disabled", false );
		$( "#emailid1" ).prop( "disabled", false );
		return false;
	}else{
		$( "#phoneno1" ).prop( "disabled", true );
		$( "#emailid1" ).prop( "disabled", true );
		
	}
	return true;
}
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
	User Number:
	<select class="form-control" style="" name="usrname1" id="usrname1"
		style="width:200px" onblur="checkname()">
		 <option> 
    	</option> 
		<c:forEach var="item" items="${sessionScope.usrList}">
			<option value="${item.userId}" >${item.userName}</option>
		</c:forEach>
	</select> 
</body>
</html>