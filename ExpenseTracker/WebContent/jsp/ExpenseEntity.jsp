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

	<div style="width: 100%;">
		<!-- Main Div -->
		<h4>!!!Add your expenses and share your
			wallet!!!</h4>
		<div style="float: left; width: 48%;">
			<h2>
				Expenses for
				<%=curGroup.getGrpName()%>
				:
			</h2>

			<c:forEach var="item" items="${sessionScope.expenselist}">
				<div class="fff">
					<div class="thumbnail">

						<div class="caption">
							<h4 id="spend">${item.expenseName}</h4>
							<p>${item.amount}${item.createdDate}</p>
						</div>
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
						required="required" autofocus> Amount(&#8377;):<input type="number"
						id="price" name="price" class="form-control"
						placeholder="Price of entity ..." required> <input
						type="submit" class="btn btn-lg btn-default btn-block" value="ADD" />
				</form>
			</div>
			<!-- Set Div As your requirement -->
		</div>
	</div>
</body>
</html>