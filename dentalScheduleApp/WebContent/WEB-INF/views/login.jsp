<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Lobster|Open+Sans:400,400italic,300italic,300|Raleway:300,400,600"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/font-awesome.min.css">
<link rel="stylesheet" href="resources/assets/styles/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/animate.css">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/style.css">
<title>Login</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	
	<div id="contact-info">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="contact-title">
						<i class="fa fa-wrench"></i>
						<h2>Login</h2>
						<p>Access your account to schedule an appointment, edit preferences, find your preferred hygienist.</p>
					</div>
				</div>
				<div class="contact col-md-6 wow fadeIn delay-08s">
					<div class="col-md-12">
						<form:form action="loginForm" method="POST" modelAttribute="loginUser" class="contactForm">
							<div class="form-group">
								<label>Username: </label>
								<form:input type="text" placeholder="Enter username" path="username" class="form-control" />
							
							</div>
							<div class="form-group">
								<label>Password: </label> 
								<form:input type="password" placeholder="Enter password" path="password" class="form-control" />
									
							</div>
							<p style="color:red">${loginStatus}</p>
							<button class="btn btn-primary" type="submit" value="Login">Login</button>
							
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<%@ include file="script-files.jsp"%>
</html>