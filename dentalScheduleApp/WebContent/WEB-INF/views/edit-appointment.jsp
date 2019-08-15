<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/styles/bootstrap.min.css">
<title>Edit appointment</title>
</head>
<body>
	<%@ include file="loggedInNavBar.jsp"%>
	<h2>Edit Appointment</h2>
	<hr>
	<div class="container">
		<form action="${pageContext.request.contextPath}/update-appt/${currentAppt.getId()}" method="POST">
			<label>Change date:</label> 
			<input type="text" name="datePicker" value="${currentAppt.getDateOfApptFormattedForEdit()}" />
			
			<br>
			<br>
			<button type="submit" value="submit" class="btn btn-primary">Update</button>
		</form>
	</div>
	<%-- Appt Id: ${currentAppt.getDateOfApptFormattedForEdit()} --%>
	<%-- Appt id: ${currentAppt.getId() } --%>
</body>
<%@ include file="script-files.jsp"%>
</html>