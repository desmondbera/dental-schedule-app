<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="resources/assets/styles/animate.css">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/perfect-scrollbar.css">

<link rel="stylesheet" type="text/css" href="resources/assets/styles/util.css">
<link rel="stylesheet" type="text/css" href="resources/assets/styles/main.css">

<title>Welcome ${username}</title>
</head>
<body>
	<%@ include file="loggedInNavBar.jsp"%>
	
	
	<%-- <h2>Welcome ${username}!</h2> --%>
	<!-- <br> -->

	<%
		if (session.getAttribute("apptListSize") != null) {
	%>
	<p>Appointment size list is not null</p>

	<%
		} else {
	%>
	<!-- <p>Appointment size list is null aka nothing in it.</p> -->
	<%-- <p>${username}, you have ${userApptCount} appointments scheduled.</p> --%>
	
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
			<h3 style="padding: 1rem;">Welcome ${username}!</h3>
			<div class="alert alert-warning" role="alert">You have ${userApptCount} appointments scheduled.</div>
				<div class="table">
					<div class="row header">
						<div class="cell">
							Appointment Id
						</div>
						<div class="cell">
							Hygienist Name
						</div>
						<div class="cell">
							Date Of Appointment
						</div>
						<div class="cell">
							
						</div>
					</div>
					
					<c:forEach var="appt" items="${apptList}">
					<div class="row">
						<div class="cell" data-title="#">${appt.getId()}</div>
						<div class="cell" data-title="Hygienist Name">${appt.getHygienistName()}</div>
						<div class="cell" data-title="Date Of Appointment">						
						 	${appt.getDateOfApptFormatted()} - ${appt.getTimeOfApptFormatted() } 
						</div>
						<div class="cell" data-title="">
							<a href="update-appt/${appt.getId()}">Update</a> |
							<a href="cancel-appt/${appt.getId()}">Cancel</a>
						</div>
					</div>
					</c:forEach>
				</div>
				<%-- <p>Userid: ${userId}</p> --%>
				<br>
				<a href="schedule-appointment/user/${userId}">
					<button class="btn btn-success">Add Appointment</button>
				</a>
				
			</div>
		</div>
	</div>
	
	
	<%-- <table class="table table-hover">
		<thead>
			<tr>
				<th scope="col">#</th>
				<th scope="col">Hygienist Name</th>
				<th scope="col">Date Of Appointment</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach var="appt" items="${apptList}">
			<tr>
				<th scope="row">1</th>
				<td>${appt.getHygienistName()}</td>
				<td>
				
				 	${appt.getDateOfApptFormatted()} - ${appt.getTimeOfApptFormatted() } 
					<fmt:formatDate value="${appt.getDate()}}" pattern="yyyy-MM-dd" var="parstedDate" />
					<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
				</td>
				<td>
					<a href="update-appt/${appt.getId()}">Update</a> |
					<a href="cancel-appt/${appt.getId()}">Cancel</a>
				</td>
			</tr>
		
		</c:forEach>
		</tbody>
	</table> --%>

	<%
		}
	%>



</body>
<%@ include file="script-files.jsp"%>
</html>