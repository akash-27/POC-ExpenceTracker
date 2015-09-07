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

<script type="text/javascript">
 //write here java script coding.
 
 function checkPasswordLength()
 {
	 var p1 = document.getElementById('p1');
	 
	if (p1.value.length < 8) {
			alert('password too short');
			document.getElementById('p1').focus();
			return false;
		}
	}

	function checkPasswordMatch() {
		var p1 = document.getElementById('p1');
		var p2 = document.getElementById('p2');

		if (p1.value == null || p2.value == null) {
			alert('can not be null');
			return false;
		}

		if (p1.value != p2.value) {
			alert('Password mismatch!');
			document.getElementById('p1').focus();
			return false;
		}
	}
	function validateEmail() {
		var email = document.getElementById('emailid');
	    var regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	    if (!regex.test(email.value)) {
	    	alert('!!!Invalid E-mail id!!!')
	    	document.getElementById('emailid').focus();
	    	return false;;
	    }
	}
	
	function checkPhoneLength() {
		var ph = document.getElementById('phone');
		var regex = /^[789]\d{9}$/;
		if (!regex.test(ph.value)) {
			alert('!!!Invalid mobile number!!!');
			document.getElementById('phone').focus();
			return false;
		}
	}
	
	function validateForm() {
		
// 		if(!checkPasswordMatch()) {
// 			alert ('form invalid');
// 			return false;
		}		
	}
</script>

<style type="text/css">
//type css class. 

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <div id="my-tab-content" class="tab-content">
						<div class="tab-pane active" id="login">
               		    <img class="img-thumbnail center-block" src="../resources/front-page-logo.jpg" alt="">
               			<form class="form-signin" id="loginForm" action="LoginServlet" method="post">
               				User Name:<input type="text" name="user" class="form-control" placeholder="Username" required autofocus>
               				Password:<input type="password" name="password" class="form-control" placeholder="Password" required>
               				<input type="submit" class="btn btn-lg btn-default btn-block" value="Sign In" />
               			</form>
               			<div id="tabs" data-tabs="tabs">
               				<p class="text-center"><a href="#register" data-toggle="tab">Need an Account?</a></p>
              				</div>
						</div>
						<div class="tab-pane" id="register">
						<img class="img-thumbnail center-block" src="../resources/logoExpenseTracker.png" alt="">
							<form class="form-signin" id="regsterForm" action="RegisterServlet" method="post">
								User Name:<input type="text" name="user" class="form-control" placeholder="User Name ..." required autofocus>
								Email ID:<input type="email" id="emailid" name="email" class="form-control" placeholder="Emaill Address ..." 
								onblur="return validateEmail();" required>
								Password:<input type="password" id="p1" name="password" class="form-control" placeholder="Password min 8 letters..." 
								onblur ="return checkPasswordLength();"  required>
								Confirm Password:<input type="password" id="p2" name="repassword" class="form-control" placeholder="Confirm Password ..."
								 onblur="return checkPasswordMatch();" required>
								Phone Number:<input type="text" id="phone" name="mobno" class="form-control" placeholder="Mobile No starting with 7,8 or 9..." maxlength="10" 
								onblur="return checkPhoneLength();" required>
								<input type="submit" class="btn btn-lg btn-default btn-block" value="Sign Up" onclick="return validateForm();" />
							</form>
							<div id="tabs" data-tabs="tabs">
               			<p class="text-center"><a href="#login" data-toggle="tab">Have an Account?</a></p>
              			</div>
						</div>
					</div>
            </div>
        </div>
    </div>
</div>

</body>
</html>