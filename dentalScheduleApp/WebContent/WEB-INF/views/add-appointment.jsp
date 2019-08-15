<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/styles/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="loggedInNavBar.jsp"%>
	<h2>Schedule Appointment</h2>
	<hr>
	<div class="container">
		<form:form action="${pageContext.request.contextPath}/schedule-appointment/user/${userId }" method="POST" modelAttribute="appointmentForm">
			<label>Pick a date:</label>
			<input type="text" name="datePicker" class="datePicker" path="dateOfAppt" placeholder="YYYY.MM.DD" />
			<br>
			<br>
			<button type="submit" value="submit" class="btn btn-success">Submit</button>
		</form:form>
	</div>
	

	<!-- 	<div style="overflow: hidden;">
		<div class="form-group">
			<div class="row">
				<div class="col-md-8">
					<div class="datePicker"></div>
				</div>
			</div>
			<button type="submit" value="submit">Submit</button>
		</div>
	</div> -->


	<%-- <c:forEach var="i" items="${apptList}">
		<p>Hygienist: ${i.hygienistName}</p>
		<p>Time: ${i.date }</p>
		<p>Date: ${i.timeOfAppt}</p>
		<a href=""> Update</a>
		<a href="">Cancel</a>
		<hr>
	</c:forEach> --%>

</body>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/jquery-3.4.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/assets/js/script.js"></script>

</html>