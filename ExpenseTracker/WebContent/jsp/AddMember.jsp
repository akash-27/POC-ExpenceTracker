<%@ page import ="java.util.*"%>
<%@ page import ="com.gl.expencetracker.*" %>
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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../resources/content.js"></script>

<script type="text/javascript">
function validateEmail() {
	var email = document.getElementById("emailid1").value;
	var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	if (!regex.test(email)) {
		alert('!!!Invalid E-mail id!!!')
		document.getElementById("emailid1").focus();
		$( "#phoneno1" ).prop( "disabled", false );
		$( "#usrname1" ).prop( "disabled", false );
		return false;
	}else{
		$.ajax({
			url : 'VerifyEmail',
			type : 'GET',
			dataType : 'text',
			data : {
				emailid1 : email
			},
			success : function(data) {
				if (data != "SUCCESS") {
					alert("Email id does not exists");
					
					return false;
				} else if (data == "SUCCESS") {
					return true;
				}

			}
		});
		
		$( "#phoneno1" ).prop( "disabled", true );
		$( "#usrname1" ).prop( "disabled", true );
		
	}
	return true;
}

function validatePhone() {
	var ph = document.getElementById("phoneno1").value;
	var regex = /^[789]\d{9}$/;
	if (!regex.test(ph)) {
		alert('!!!Invalid mobile number!!!');
		document.getElementById("phoneno1").focus();
		$( "#emailid1" ).prop( "disabled", false );
		$( "#usrname1" ).prop( "disabled", false );
		return false;
	}else{
		$.ajax({
			url : 'VerifyPhone',
			type : 'GET',
			dataType : 'text',
			data : {
				phoneno1 : ph
			},
			success : function(data) {
				if (data != "SUCCESS") {
					alert("Phone does not exists");
					
					return false;
				} else if (data == "SUCCESS") {
					return true;
				}

			}
		});
		$( "#emailid1" ).prop( "disabled", true );
		$( "#usrname1" ).prop( "disabled", true );
		
	}
	return true;
}
</script>

<style type="text/css">
//type css class. 

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Member</title>
</head>
<body> 
<%-- <%@include file="Header.jsp"%> --%>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <div id="my-tab-content1" class="tab-content">
						<div class="tab-pane active" id="addmember1">
						<H4 align="center"><font color="Green">Add Members to Group</font></H4>
               		    <img class="img-thumbnail center-block" src="../resources/groups.jpg" alt="">
						<form class="form-signin" id="addmem1" action="AddMemberDetails" method="post">
						<input type="hidden" class="form-control" name="addgrpname1" placeholder="Groupname" value='<%=session.getAttribute("grpname")%>' > <br>
						<%@include file="./UserIdName.jsp" %><br>
						Mobile Number: <input type="text" class="form-control" name="phoneno1" id="phoneno1" placeholder="phone" onblur="return validatePhone();"> <br>
						Email id:<input type="text" class="form-control" name="emailid1" id="emailid1" placeholder="emailid"  onblur="return validateEmail();"><br>
						<div >
					    <input type="submit" class="btn btn-lg btn-default btn-block" value="Add Member" />
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