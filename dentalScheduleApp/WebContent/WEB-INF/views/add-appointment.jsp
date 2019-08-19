<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/styles/bootstrap.min.css">
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.min.css"> -->
	
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.0-alpha14/css/tempusdominus-bootstrap-4.min.css" />
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
 

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
				
			 <div class="input-group date" id="datetimepicker2" data-target-input="nearest">
	             <input type="text" class="form-control datetimepicker-input" name="datePicker" path="dateOfAppt"/>
	             <div class="input-group-append" data-target="#datetimepicker2" data-toggle="datetimepicker">
	                 <div class="input-group-text"><i class="fa fa-calendar"></i></div>
	             </div>
             </div> 
				
			<br>
			<br>
			<label>Pick a time:</label>
			<!-- <input type="text" name="timePicker" class="datePicker"
				path="timeOfAppt" placeholder="YYYY.MM.DD" />
			<br> -->
			
			 <div class="input-group date" data-target-input="nearest" id="datetimepicker3" >
	            <input type="text" class="form-control datetimepicker-input" name="timePicker" path="timeOfAppt"/>
	            <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
	                <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
	            </div>
             </div> 
             
          
              
             <br>
			<button type="submit" value="submit" class="btn btn-success">Submit</button>
		</form:form>

		<!-- <div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<div class="input-group date" id="datetimepicker3"
							data-target-input="nearest">
							<input type="text" class="form-control datetimepicker-input"
								data-target="#datetimepicker3" />
							<div class="input-group-append" data-target="#datetimepicker3"
								data-toggle="datetimepicker">
								<div class="input-group-text">
									<i class="fa fa-clock-o"></i>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div> -->

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