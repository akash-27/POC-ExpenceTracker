<%@ page import ="java.util.*"%>
<%@ page import ="com.gl.expencetracker.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/style.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>
<script type="text/javascript">
function formvalidate() {
	var email = document.getElementById('email');
	var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!regex.test(email.value)) {
		alert('!!!Invalid E-mail id!!!')
		document.getElementById('email').focus();
		return false;
		;
	}
}

function validatePhone() {
	var ph = document.getElementById('phone');
	var regex = /^[789]\d{9}$/;
	if (!regex.test(ph.value)) {
		alert('!!!Invalid mobile number!!!');
		document.getElementById('phone').focus();
		return false;
	}
}
</script>

<style type="text/css">
//type css class. 

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Member</title>
</head>
<body> 
<%@include file="./Header.jsp" %><br>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <div id="my-tab-content" class="tab-content">
						<div class="tab-pane active" id="login">
						<H4 align="center"><font color="Green">Add Member to Group</font></H4>
               		    <img class="img-thumbnail center-block" src="../resources/groups.jpg" alt="">
						<form class="form-signin" id="loginForm" action="AddMemberDetails" method="post">
						<input type="hidden" class="form-control" name="grpname" placeholder="Groupname" value='<%=session.getAttribute("grpname")%>' > <br>
						<%@include file="./UserIdName.jsp" %><br>
						Mobile Number: <input type="text" class="form-control" name="phone" id="phone" placeholder="phone" onblur="return validatePhone()"> <br>
						Email id:<input type="text" class="form-control" name="email" id="email" placeholder="emailid"  onblur="return validateEmail()"><br>
						<div >
					    <input type="submit" class="btn btn-lg btn-default btn-block" value="submit" />
					    </div>
 					   </form>
  </div>
  </div>
  </div>
  </div>
  </div>
  </div>
</body>
</html>