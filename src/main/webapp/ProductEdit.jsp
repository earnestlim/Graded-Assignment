<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Product Edit</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Product Edit Page </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/ProductListServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${!empty Product.name}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${empty Product.name}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${!empty Product.name}">Edit Product
</c:if>
						<c:if test="${empty Product.name}">Edit Product
</c:if>
					</h2>
				</caption>
				<c:if test="${Product != null}">
					<input type="hidden" name="oriName"
						value="<c:out
value='${Product.name}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out
value='${Product.name}' />"
						class="form-control" name="quantity" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Quantity</label> <input type="text"
						value="<c:out
value='${Product.quantity}' />" class="form-control"
						name="size">
				</fieldset>
				<fieldset class="form-group">
					<label>Size</label> <input type="text"
						value="<c:out
						
						
value='${Product.size}' />"
						class="form-control" name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>Color</label> <input type="text"
						value="<c:out
value='${Product.color}' />" class="form-control"
						name="color">


				</fieldset>





				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>
</body>
</html>