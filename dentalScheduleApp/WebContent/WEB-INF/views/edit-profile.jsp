<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/styles/bootstrap.min.css">
<title>Edit Profile</title>
</head>
<body>
	<%@ include file="loggedInNavBar.jsp"%>
	<h2>Edit profile</h2>
	<hr>
	${messageResult}
	<div class="container col-md-6">
		<form:form action="${pageContext.request.contextPath}/edit-profile/user/${userId}" method="POST" modelAttribute="user" >
		  <div class="form-group">
		  	<form:input type="hidden" class="form-control" path="id" value="${user.id }" readonly="readonly"/>
		  	
		    <label for="username">Username</label>
		    <form:input type="text" class="form-control" path="username" value="${user.username}"/>
		  </div>
		  
		 <div class="form-group">
		    <label for="firstname">First name:</label>
		    <form:input type="text" class="form-control" path="name" value="${user.name}"/>
		  </div>
		  
		  <div class="form-group">
		    <label for="address">Address:</label>
		   <form:input type="text" class="form-control" path="address" value="${user.address}" />
		  </div>
		  
		  <div class="form-group">
		    <label for="phonenumber">Phone Number:</label>
		   <form:input type="text" class="form-control" path="phoneNumber" value="${user.phoneNumber}" />
		  </div>
	  
	  <!-- <div class="form-group form-check">
	    <input type="checkbox" class="form-check-input" id="exampleCheck1">
	    <label class="form-check-label" for="exampleCheck1">Check me out</label>
	  </div> -->
  			<button type="submit" class="btn btn-primary">Save Changes</button>
		</form:form>
	</div>
	
	
	<%-- <form:form action="${pageContext.request.contextPath}/edit-profile/user/${userId}" method="POST" modelAttribute="user" class="contactForm">
		<div class="form-group">
			<form:input type="hidden" class="form-control" path="id" value="${user.id }" readonly="readonly"/>
			
			<label>Username:</label>
			<form:input type="text" class="form-control" path="username" value="${user.username}" />
			<br>
			
			<label>First name:</label>
			<form:input type="text" class="form-control" path="name" value="${user.name}" />
			<br>
			
			<label>Address:</label>
			<form:input type="text" class="form-control" path="address" value="${user.address}" />
			<br>
			
			<label>Phone number:</label>
			<form:input type="text" class="form-control" path="phoneNumber" value="${user.phoneNumber}" />
			<br>
		</div>
		<input type="submit" value="Save"/>
	</form:form>  --%>
	
	<div class="container col-md-6">
		<hr>
		<p>Current Primary Dental Office: ${currentDentalOffice.officeName }</p>
		<label class="primary_dental_office">Primary Dental Office:</label> 
	
		<form action="${pageContext.request.contextPath}/edit-profile/user/primary-dental-office/${userId}" method="POST">
			<select name="dentalOfficeId">
				<option value="0" label="Select primary dental office"/>
				<c:forEach var="office" items="${dentalOffices}" varStatus="officeCount">
					<option id="office-${officeCount.count}" value=${officeCount.count}>${ office.getOfficeName()}</option>
				</c:forEach>
			</select>
		
			<!-- <input type="submit" value="Update"/> -->
			<br>
			<br>
			<button type="submit" class="btn btn-primary">Update Primary Dental Office</button>
		</form>
		<hr>
	<!-- </div> -->
	
	<!-- <div class="container"> -->
		<p>Current Hygienists you've selected: </p>
		<c:forEach items="${currentListOfHygienists}" var="hyg" varStatus="hygCount">
				<form action="${pageContext.request.contextPath}/edit-profile/user/remove-hygienist/${userId}" method="POST">
					<p>${hygCount.count} - ${hyg.name}</p>
					<input type="hidden" name="hygienistId" value="${hyg.id}"/>
					<!-- <input type="submit" value="Remove"/> -->
					<button type="submit" class="btn btn-danger">Remove</button>
				</form>
		</c:forEach>
		<br>
		<label class="primary_dental_office">Preferred Hygienists:</label> 
		
		<form action="${pageContext.request.contextPath}/edit-profile/user/favorite-hygienist/${userId}" method="POST">
			
			<c:forEach var="item" items="${allHygienists}">
				<div class="checkbox">
					<label class="checkbox_label"> <input type="checkbox" name="dentalHygienistId" value="${item.getId()}" />
						${item.getName()}
					</label>
				</div>
			</c:forEach>
		
			<!-- <input type="submit" value="Update"/> -->
			<button type="submit" class="btn btn-primary">Add Hygienist(s)</button>
		</form>
		<br>
		<hr>
		
		<p style="color: red;">Delete Your Account Permanently</p>
		<p style="font-style: italic;">Delete all your appointments, favorite hygienist list, and data.</p>
		<div>
			<a href="${pageContext.request.contextPath}/edit-profile/user/delete-profile/${userId}"><button type="submit" class="btn btn-danger">Delete Profile</button></a>
		</div> 
	</div>
	<%-- <select class="form-control" name="dentalOfficeId">
		<option value="0" label="Select primary dental office" />
		<c:forEach var="office" items="${dentalOffices}" varStatus="officeCount">
			<option id="office-${officeCount.count}" value=${officeCount.count}>${office.getOfficeName()}</option>
		</c:forEach>
	</select> --%>


	
	<%-- <form:select path="primaryDentalOffice" class="form-control" name="dentalOfficeId">

		<option value="0" label="Select primary dental office" />
		<form:options items="${dentalOffices}" itemLabel="officeName" itemValue="id" />
		
		<c:forEach var="office" items="${dentalOfficeList}" varStatus="officeCount">
			<option id="office-${officeCount.count}" value=${officeCount.count}>${office.getOfficeName()}</option>	
		</c:forEach>
	</form:select> --%>
	
	<br>
	<%-- <p>username: ${username}</p>
	<p>userid: ${userId}</p> --%>

</body>
<%@ include file="script-files.jsp"%>
</html>