<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css?family=Lobster|Open+Sans:400,400italic,300italic,300|Raleway:300,400,600"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="resources/assets/styles/font-awesome.min.css">
<link rel="stylesheet" href="resources/assets/styles/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/assets/styles/animate.css">
<link rel="stylesheet" type="text/css"
	href="resources/assets/styles/style.css">
<title>Home</title>
</head>
<body>
	<%
		if (session.getAttribute("username") == null) {
	%>
	<%@ include file="navbar.jsp"%>
	<%
		} else {
	%>
	<%@ include file="loggedInNavBar.jsp"%>
	<%
		}
	%>

	<div class="content">


		<div class="container wow fadeInUp delay-03s">
			<div class="row">
				<div class="logo text-center">
					<h1 class="logo text-center">Flossy <br> The Dental Scheduler</h1>
					<img src="resources/assets/images/toothpaste-4-2.jpg" alt="logo"
						width="150">
					<h2>Boston, we heard you need some help finding your favorite
						hygienists. <br><br>Worry no more, we launch Sept. 1st, 2019!<br><br>👇🏽 </h2>
				</div>

				<div id="countdown" data-wow-delay=".3s"
					data-date="Sept 01, 2019 09:00:00"></div>
				<h2 class="subs-title text-center">Subscribe now to get Recent
					updates!!!</h2>
				<div class="subcription-info text-center">
					<form class="subscribe_form" action="#" method="post">
						<input required="" value="" placeholder="Enter your email..."
							class="email" id="email" name="email" type="email"> <input
							class="subscribe" name="email" value="Subscribe!" type="submit">
					</form>
					<p class="sub-p">We Promise to never span you.</p>
				</div>
			</div>
		</div>
		<section>
			<div class="container">
				<div class="row bort text-center">
					<div class="social">
						<ul>
							<li><a href=""><i class="fa fa-facebook"></i></a></li>
							<li><a href=""><i class="fa fa-twitter"></i></a></li>
							<li><a href=""><i class="fa fa-linkedin"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<section id="about" class="section-padding">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 text-center">
						<div class="about-title">
							<h2>About Us</h2>
							<p>Flossy helps patients find their hygienists at any dental
								office to schedule an appointment</p>
						</div>
						<div class="col-md-4 col-sm-6 col-xs-12 wow fadeInUp delay-02s">
							<div class="img">
								<i class="fa fa-refresh"></i>
							</div>
							<h3 class="abt-hd">Our process</h3>
							<p>Register once and be able to set up a dental appointment
								with any hygienist in the Greater Boston area.</p>
						</div>
						<div class="col-md-4 col-sm-6 col-xs-12 wow fadeInUp delay-04s">
							<div class="img">
								<i class="fa fa-eye"></i>
							</div>
							<h3 class="abt-hd">Our Vision</h3>
							<p>To provide access to all dental hygienist and dental
								offices to every patient in Massachusetts.</p>
						</div>
						<div class="col-md-4 col-sm-6 col-xs-12 wow fadeInUp delay-06s">
							<div class="img">
								<i class="fa fa-cogs"></i>
							</div>
							<h3 class="abt-hd">Our Approach</h3>
							<p>Combining dental office partnerships with the latest
								technology we look forward to bringing dental cleaning bookings to
								the modern era.</p>
						</div>

					</div>
				</div>
			</div>
		</section>
		<%-- <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Log in</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="modal-body mb-1">
							<form action="loginForm" method="POST">
								<div class="md-form form-sm mb-5">

									<i class="fas fa-envelope prefix"></i> <input type="text"
										id="modalLRInput10"
										class="form-control form-control-sm validate" name="username">
									<label data-error="wrong" data-success="right"
										for="modalLRInput10">Your username</label>
								</div>

								<div class="md-form form-sm mb-4">
									<i class="fas fa-lock prefix"></i> <input type="password"
										id="modalLRInput11"
										class="form-control form-control-sm validate" name="password">
									<label data-error="wrong" data-success="right"
										for="modalLRInput11">Your password</label>
								</div>
								<div class="text-center mt-2">
									<button class="btn btn-info">
										Login<i class="fas fa-sign-in ml-1"></i>
									</button>
								</div>
							</form>
						</div> --%>

	<!-- </div>
	</div>
	</div>
	</div> -->


	</div>







</body>
<script src="resources/assets/js/jquery-3.4.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script src="resources/assets/js/bootstrap.min.js"></script>
<script src="resources/assets/js/jquery.countdown.min.js"></script>
<script src="resources/assets/js/wow.js"></script>
<script src="resources/assets/js/custom.js"></script>
</html>