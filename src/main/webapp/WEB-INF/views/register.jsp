<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" 	href="resources/css/style.css">

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Order Trackor</a>
			</div>
		</div>
	</nav>

	<div class="content">

		<sf:form class="reg-form" action="register" method="POST" modelAttribute="customer">
			<c:if test="${userExisted}">
				<p>The username has been registered. Try another one.</p>
			</c:if>
			<div class="form-group">
				<label for="username" class="col-2 col-form-label">Username</label>
				<div class="col-6">
					<sf:input class="form-control" type="text" placeholder="Your username"
						name="username" id="username" path="username" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-2 col-form-label">Password</label>
				<div class="col-6">
					<sf:input class="form-control" type="password"
						placeholder="Your password" name="password" id="password" path="password"/>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-2 col-form-label">Name</label>
				<div class="col-6">
					<sf:input class="form-control" type="text"
						placeholder="Your full name" name="name" id="name" path="name"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Register</button>
		</sf:form>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>