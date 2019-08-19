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
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"> -->
	
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">
	
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" /> -->

<title>Insert title here</title>
</head>
<body>
	<%@ include file="loggedInNavBar.jsp"%>
	<h2>Schedule Appointment</h2>
	<hr>
	<div class="container">
		<form:form
			action="${pageContext.request.contextPath}/schedule-appointment/user/${userId }"
			method="POST" modelAttribute="appointmentForm">
			<label>Pick a date:</label>
			<!-- <input type="text" name="datePicker" class="datePicker"
				path="dateOfAppt" placeholder="YYYY.MM.DD" /> -->
			<br>
			<br>
			<label>Pick a time:</label>
			<!-- <input type="text" name="timePicker" class="datePicker"
				path="timeOfAppt" placeholder="YYYY.MM.DD" />
			<br> -->
			
			<div class="input-group date" data-target-input="nearest">
	            <input type="text" id="datetimepicker3"/>
	            <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
	                <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
	            </div>
             </div>
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
<%@ include file="script-files.jsp"%>

</html>