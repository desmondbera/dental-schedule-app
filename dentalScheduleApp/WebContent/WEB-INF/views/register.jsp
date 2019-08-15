<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.dentalScheduleApp.entities.DentalOffice"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Register</title>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<%
		if (session.getAttribute("username") == null) {
	%>
	<!-- <h2>Register</h2> -->
	<p>${isUserCreated}</p>

	<div id="contact-info">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="contact-title">
						<i class="fa fa-envelope"></i>
						<h2>Register</h2>
						<p>To access our hygienists and schedule an appointment please
							create an account</p>
					</div>
				</div>
				
				<div class="contact col-md-6 wow fadeIn delay-08s">
					<div class="col-md-10 col-md-offset-1">
						<form:form action="register" method="POST" modelAttribute="registerUser" class="contactForm">
							<div class="form-group">
								<label>Username:</label>
								<form:input type="text" class="form-control" placeholder="Enter username"
									path="username" />
								<br>
							</div>
							
							<div class="form-group">
								<label>Password:</label>
								<form:input type="password" class="form-control" placeholder="Enter Password"
									path="password" />
								<br>
							</div>
							<div class="form-group">
								<label>Confirm Password:</label>
								<form:input type="password" class="form-control" placeholder="Confirm password"
									path="confirmPassword" />
								<br>
							</div>
							<div class="form-group">
								<label>First name:</label>
								<form:input type="text" class="form-control" placeholder="Enter first name"
									path="name" />
								<br>
							</div>
							<div class="form-group">
								<label>Address:</label>
								<form:input type="text" class="form-control" placeholder="Enter address"
									path="address" />
								<br>
							</div>
							<div class="form-group">
								<label>Phone number:</label>
								<form:input type="text" class="form-control" placeholder="Enter phone number"
									path="phoneNumber" />
								<br>
							</div>

							<div class="form-group">
								<label class="primary_dental_office">Primary Dental Office:</label> 
								<select class="form-control" name="dentalOfficeId">
									<option value="0" label="Select primary dental office" />
									<c:forEach var="office" items="${dentalOfficeList}" varStatus="officeCount">
										<option id="office-${officeCount.count}" value=${officeCount.count}>${office.getOfficeName()}</option>
									</c:forEach>
								</select>
								<%-- <form:select path="primaryDentalOffice">
				<form:option value="0" label="Select"/>
				<form:options items="${dentalOfficeList}" itemValue="id" itemLabel="officeName"/>
			</form:select> --%>

							</div>
							<div class="form-group">

								<%-- <label>Preferred Hygienist:</label>
			<select class="form-control" name="dentalHygienistId">
				<option value="0" label="Select preferred hygienist"/>
				<c:forEach var="hyg" items="${hygList}" varStatus="hygCount">
					<option id="hyg-${hygCount.count}" value="${hyg.getId()}">${hyg.getName()}</option>
				</c:forEach>
			</select> --%>

								<label class="hygienist_label">Preferred Hygienist:</label>
								<c:forEach var="item" items="${hygList}">
									<div class="checkbox">
										<label class="checkbox_label"> <input type="checkbox" name="dentalHygienistId" value="${item.getId()}" />
											${item.getName()}
										</label>
									</div>
								</c:forEach>
							</div>

							<button class="btn btn-primary" type="submit" value="submit">Submit</button>
							<br>
							<form:errors style="color:red" path="username" />
							<br>
							<form:errors style="color:red" path="password" />
							<br>
							<form:errors style="color:red" path="confirmPassword" />
							<br>
							<form:errors style="color:red" path="name" />
							<br>
							<form:errors style="color:red" path="address" />
							<br>
							<form:errors style="color:red" path="phoneNumber" />

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%-- <div id="contact-info">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<div class="contact-title">
						<i class="fa fa-envelope"></i>
						<h2>Register</h2>
						<p>To access our hygienists and schedule an appointment please
							create an account</p>
					</div>
				</div>
				<div class="contact col-md-6 wow fadeIn delay-08s">
					<div class="col-md-10 col-md-offset-1">
						<div id="note"></div>
						<!-- <div id="sendmessage">Your message has been sent. Thank you!</div>
              <div id="errormessage"></div> -->
						<form:form action="register" method="POST"
							modelAttribute="registerUser">
							<!-- <div class="form-group">
								<input type="text" path="username" name="username" class="form-control" id="username"
									placeholder="Your User Name" data-rule="minlen:4"
									data-msg="Please enter at least 4 chars" />
								<div class="validation"></div>
							</div>
							<div class="form-group">
								<input type="password" path="password"  class="form-control" name="password"
									id="password" placeholder="Your Password" data-rule="password"
									data-msg="Please enter a valid password" />
								<div class="validation"></div>
							</div>

							<div class="form-group">
								<input type="password" path="confirmPassword" class="form-control" name="password"
									id="password" placeholder="Confirm Password"
									data-rule="password" data-msg="Please enter a valid password" />
								<div class="validation"></div>
							</div>

							<div class="form-group">
								<input type="text" path="name" class="form-control" name="name"
									id="firstName" placeholder="First Name" data-rule="minlen:2"
									data-msg="Please enter at least 2 chars of subject" />
								<div class="validation"></div>
							</div>
							
							<div class="form-group">
								<input type="text" path="address" class="form-control" name="address"
									id="address" placeholder="Address" data-rule="minlen:2"
									data-msg="Please enter at least 2 chars of subject" />
								<div class="validation"></div>
							</div>
							
							<div class="form-group">
								<input type="text" path="phoneNumber" class="form-control" name="phoneNumber"
									id="phoneNumber" placeholder="Phone Number" data-rule="minlen:2"
									data-msg="Please enter at least 2 chars of subject" />
								<div class="validation"></div>
							</div> -->

							<div>
								<label>Username:</label>
								<form:input type="text" placeholder="Enter username"
									path="username" />
								<br>
							</div>
							<div>
								<label>Password:</label>
								<form:input type="password" placeholder="Enter Password"
									path="password" />
								<br>
							</div>
							<div>
								<label>Confirm Password:</label>
								<form:input type="password" placeholder="Confirm password"
									path="confirmPassword" />
								<br>
							</div>
							<div>
								<label>First name:</label>
								<form:input type="text" placeholder="Enter first name"
									path="name" />
								<br>
							</div>
							<div>
								<label>Address:</label>
								<form:input type="text" placeholder="Enter address"
									path="address" />
								<br>
							</div>
							<div>
								<label>Phone number:</label>
								<form:input type="text" placeholder="Enter phone number"
									path="phoneNumber" />
								<br>
							</div>

							<div class="form-group">
								<div>
									<select class="form-control" name="dentalOfficeId">
										<option value="0" label="Select primary dental office" />
										<c:forEach var="office" items="${dentalOfficeList}"
											varStatus="officeCount">
											<option id="office-${officeCount.count}"
												value=${officeCount.count}>${office.getOfficeName()}</option>
										</c:forEach>
									</select>
								</div>

							</div>

							<div class="form-group">
								<div>
									<label>Preferred Hygienist:</label>
									<c:forEach var="item" items="${hygList}">
										<div class="checkbox">
											<label> <input type="checkbox"
												name="dentalHygienistId" value="${item.getId()}" />
												${item.getName()}
											</label>
										</div>
									</c:forEach>
								</div>
							</div>


							<div class="text-center">

								<button type="submit" class="contact-submit" value="submit">Submit</button>
							</div>
							<br>
							<form:errors style="color:red" path="username" />
							<br>
							<form:errors style="color:red" path="password" />
							<br>
							<form:errors style="color:red" path="confirmPassword" />
							<br>
							<form:errors style="color:red" path="name" />
							<br>
							<form:errors style="color:red" path="address" />
							<br>
							<form:errors style="color:red" path="phoneNumber" />


						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div> --%>


	<%
		} else {
	%>
	<div class="col-md-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">You've successfully signed up!</h3>
			</div>
			<!-- <div class="panel-body"><a href="loginForm">Go to Login</a></div> -->
			<a class="btn btn-success" href="loginForm" role="button">Go to
				Login</a>

		</div>
	</div>
	<%
		}
	%>

</body>
<%@ include file="script-files.jsp"%>
</html>