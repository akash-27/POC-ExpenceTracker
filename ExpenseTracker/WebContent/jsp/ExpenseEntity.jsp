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
<title>Expense-Entity</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<div style="width: 100%;">
		<!-- Main Div -->

	</div>
	<%!ExpenseGroups curGroup;%>
	<%
		curGroup = (ExpenseGroups) session.getAttribute("curgroup");
	%>

	<div  style="width:100%; border:2px solid #ccc;">
		<!-- Main Div -->
		<div class="fff">
			<div  align="center" class="thumbnail">
				<h3><font color="Magenta"> The Group members are :</font></h3>
				<h4>
					<font color="Orange"> <c:forEach var="item"
							items="${sessionScope.grpMemberlist}">
					${item} &nbsp; &nbsp; &nbsp;  
				</c:forEach>
					</font>
				</h4>
			</div>
		</div>
		<h4>
		<font color="Brown">!!!Add your expenses and share your wallet!!!</font></h4>

		<div style="float: left; width: 25%;">
			<h2>
			<font color="Blue">
				Expenses for
				<%=curGroup.getGrpName()%>
				:
				</font>
			</h2>

			<c:forEach var="item" items="${sessionScope.expenselist}">
				<div class="fff">
					<div class="thumbnail">
						<h6 style="float: right">
							<font color="Blue">Dated: ${item.createdDate}</font>
						</h6>
						<h4>
							<font color="Brown">Item: ${item.expenseName}</font>
						</h4>

						<c:if test="${item.isProcessed}">
							<h5 style="float: right">
								<font color="Red"> PROCESSED</font>
							</h5>
						</c:if>
						<h6>
							<font color="Green">Price: ${item.amount}</font>
						</h6>
					</div>
				</div>
			</c:forEach>

		</div>
		<div style="float: right; width: 49%; margin-left: 10px;">
			<div class="" id="entity">
				<img class="img-thumbnail center-block"
					src="../resources/Logo134.png" alt="" height="300" width="200">
				<form class="form-signin" id="regsterForm" action="AddExpense"
					method="post">
					Commodity Name:<input type="text" name="expensename"
						class="form-control" placeholder="Expense entity ..."
						required="required" autofocus> Amount(&#8377;):<input
						type="number" id="price" name="price" class="form-control"
						placeholder="Price of entity ..." required> <input
						type="submit" class="btn btn-lg btn-default btn-block" value="ADD" />
				</form>
			</div>
			<!-- Set Div As your requirement -->
		</div>
	</div>
</body>
</html>