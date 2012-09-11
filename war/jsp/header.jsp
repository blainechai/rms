<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</a> 
			<a class="brand" href="/">Mail reminder</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="whatsthis.jsp">What's this?</a></li>
					<li><a href="contact.jsp">Contact</a></li>
					<li>
					<% 
						UserService userService = UserServiceFactory.getUserService();
					 	String thisURL = request.getRequestURI();
						if ( request.getUserPrincipal() != null ){
							out.print("<a href=\"" + userService.createLogoutURL(thisURL) + "\">Sign out</a>");
						}else{
							out.print("<a href=\"" + userService.createLoginURL(thisURL) + "\">Sign in</a>");
						}
					%>	
					</li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>
