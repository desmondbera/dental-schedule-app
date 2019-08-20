<%-- <nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
		<button class="navbar-toggler ml-auto" type="button"
			data-toggle="collapse" data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/loginForm">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	 --%>


<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/loginForm">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath}/edit-profile/user/${username}/${userId}"><span class=""></span>Edit Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/logout"><span class=""></span>Log out</a></li>
				<!-- <li><a href="login"><span class="glyphicon glyphicon-log-in"></span>
						Login</a></li> -->	
			</ul>
			
		</div>
	</div>
</nav>