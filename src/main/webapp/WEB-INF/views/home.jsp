<%@page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Order Trackor</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="home">Home</a></li>
				<li><a href="neworder">New Order</a></li>
				<li><a href="#">Other page</a></li>
				<li><a href="#">Other page</a></li>
			</ul>
		</div>
	</nav>
	<div class="content">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Current Location</th>
					<th>Receiver Name</th>
					<th>Delivery Address</th>
					<th>Estimate Delivery</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${loginUser.orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.courier.currentLocation}</td>
					<td>${order.receiverName}</td>
					<td>${order.receiverAddress}</td>
					<td>${order.estimateDeliveryDate}</td>
					<td><a href="editorder?orderId=${order.orderId}"
						class="btn btn-primary" role="button">Edit Order</a></td>
				</tr>
			</c:forEach>
		</table>
		<div id="map"></div>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="resources/js/gmap.js"></script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAdGoi8JtufKX2Uoj4DOsD1nO7gWrwWzEc&callback=initMap">
    </script>

</body>
</html>