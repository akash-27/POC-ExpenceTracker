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
	$(document).ready(
			function() {
				$(".dropdown").hover(
						function() {
							$('.dropdown-menu', this).not('.in .dropdown-menu')
									.stop(true, true).slideDown("400");
							$(this).toggleClass('open');
						},
						function() {
							$('.dropdown-menu', this).not('.in .dropdown-menu')
									.stop(true, true).slideUp("400");
							$(this).toggleClass('open');
						});
			});
</script>
<script type="text/javascript">

	
	function checkLength() {
		var pass1 = document.getElementById("grp");
		var ok = true;
		if (pass1.value == null ) {
			alert('can not be null');
			return false;
		}
		if (pass1.value.length < 4) {
			alert('length must be greter than or equal to 4 characters');
			document.getElementById('grp').focus();
			return false;
		}
		return ok;
	}
	function checkLength1() {
		var pass1 = document.getElementById("grp1");
		var ok = true;
		if (pass1.value == null ) {
			alert('can not be null');
			return false;
		}
		if (pass1.value.length < 4) {
			alert('length must be greter than or equal to 4 characters');
			document.getElementById('grp1').focus();
			return false;
		}
		return ok;
	}
	function validpass() {
		var pass1 = document.getElementById("pass1");
		var pass2 = document.getElementById("pass2");
		var p3 = document.getElementById("oldpass");
		var ok = true;
		if (pass1.value == null || pass2.value == null) {
			alert('can not be null');
			return false;
		}
		if (pass1.value.length < 8) {
			alert('password too short');
			document.getElementById('pass1').focus();
			return false;
		}
		if (p3.value == pass1.value) {
			alert("Old Password and New Password is Same!!!");
			ok = false;
		}
		if (pass1.value != pass2.value) {
			document.getElementById("pass1").style.borderColor = "#E34234";
			document.getElementById("pass2").style.borderColor = "#E34234";
			alert("Passwords Does Not Match!!!");
			ok = false;
		}
		return ok;
	}
	function validateEmail() {
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
	function PasswordValidate() {
		var password = document.getElementById("oldpass").value
		$.ajax({
			url : 'VerifyOldPass',
			type : 'GET',
			dataType : 'text',
			data : {
				oldpass : password
			},
			success : function(data) {
				if (data != "SUCCESS") {
					alert("Old Password Mismatch");
					$("#pass").hide();
					return false;
				} else if (data == "SUCCESS") {
					$("#pass").show();
					return true;
				}

			}
		});
		return false;
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
	<%!ExpenseUser curUser;%>
	<%
		curUser = (ExpenseUser) session.getAttribute("user");
	%>
	<div class="container">
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target=".js-navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="welcome.jsp">HOME</a>
			</div>
			<div class="collapse navbar-collapse js-navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Manage
							Group <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" data-toggle="modal" data-target="#create">Create
									Group</a></li>
							<li><a href="#" data-toggle="modal" data-target="#edit">Edit
									Group</a></li>
							<li><a href="#" data-toggle="modal" data-target="#delete">Delete
									Group</a></li>
						</ul></li>

					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">Edit
								User Profile <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#" data-toggle="modal" data-target="#editprof">Edit
										Profile</a></li>
								<li><a href="#" data-toggle="modal" data-target="#changpwd">Change
										Password</a></li>
							</ul></li>
						<li><a href="#" class="btn btn-primary btn-lg " data-toggle="modal" data-target="#addexpense">Add Expenses</a></li>
						<li><a href="Logout" class="btn btn-primary btn-lg "> Logout</a></li>
						<li><font color="Green">&nbsp; Welcome <%=curUser.getUserName()%></font></li>
					</ul>
			</div>

			<div class="modal fade" id="create" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Create Group</h4>
						</div>
						<div class="modal-body">
							<form class="form-manage" action="CreateGroup" method="post">
								Group Name: <input type="text" class="form-control" placeholder="Groupname" id="grp" name="grp" required autofocus> 
<!-- 								Created By:	<input type="text" -->
<!-- 									class="form-control" name="usr" placeholder="Createdby" required> -->
								<input type="submit" class="btn btn-lg btn-default btn-block" value="Create" onclick="return checkLength()"/>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Edit Group</h4>
						</div>
						<div class="modal-body">
							<form class="form-manage" action="EditGroup" method="post">
									<%@include file="./GrpCommon.jsp" %><br>
									New Group Name: <input type="text" name="newgrpname" class="form-control" placeholder="NewGroupname" id="grp1" name="grp1" onblur="return checkLength1()"   required><br>
								   <input type="submit" class="btn btn-lg btn-default btn-block" value="Edit" />
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="delete" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Delete Group</h4>
						</div>
						<div class="modal-body">
							<form class="form-manage" action="DeleteGroup" method="post">
							<%@include file="./GrpCommon.jsp" %><br>
<!-- 								<input type="text" class="form-control" placeholder="Groupname" -->
<!-- 									required autofocus> <input type="text" -->
<!-- 									class="form-control" placeholder="Createdby" required> -->
								<input type="submit" class="btn btn-lg btn-default btn-block"
									value="Delete" />
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
						<div class="modal fade" id="editprof" role="dialog">
							<div class="modal-dialog">

								
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Edit User Profile</h4>
									</div>
									<div class="modal-body">
										<form class="form-manage" action="EditProf" method="post">
											User Name: <input type="text" class="form-control" name="username" placeholder="Username" value='<%=curUser.getUserName()%>' disabled="disabled" required > <br>
											Mobile Number: <input type="text" class="form-control" name="phone" id="phone" placeholder="phone" value='<%=curUser.getMobNumber()%>' onblur="validatePhone()"> <br>
											Address: <input type="text" class="form-control" name="address" placeholder="Address" value='<%=curUser.getAddress()%>'><br>
											Email id:<input type="text" class="form-control" name="email" id="email" placeholder="emailid" value='<%=curUser.geteMail()%>' onblur="validateEmail()"><br>

											<input type="submit" class="btn btn-lg btn-default btn-block" value="submit" />
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
			<div class="modal fade" id="changpwd" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Change Password</h4>
						</div>
						<div class="modal-body">
							<form class="form-manage" action="ChangePassword" method="post">
								 Old Password: <input type="password" class="form-control" placeholder="Old Password" name="oldpass" id="oldpass" onblur="return PasswordValidate()" required><br>
								 New Password: <input	type="password" class="form-control" id="pass1" name="newpass" placeholder="New Password" required><br>
								 Confirm Password: <input	type="password" class="form-control" id="pass2" name="confpass"	placeholder="Confirm Password" required><br>
								 <input	type="submit" id="pass" class="btn btn-lg btn-default btn-block" value="submit" onclick="return validpass()">
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="addexpense" role="dialog">
							<div class="modal-dialog">								
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Add Expense Details</h4>
									</div>
									<div class="modal-body">
										<form class="form-manage" action="AddExpense" method="post">
											Select Group: <input type="text" class="form-control" name="grpName" placeholder="Expense Entity" required/>
											<input type="submit" class="btn btn-lg btn-default btn-block" value="submit" />
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
			
		</nav>
	</div>
	
</body>
</html>
