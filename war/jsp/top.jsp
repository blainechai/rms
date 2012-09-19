<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</a> 
				<a class="brand" href="http://www.mail-remind.com"><h1>Mail reminder</h1></a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="whatsthis">What's this?</a></li>
						<li><a href="contact">Contact</a></li>
					</ul>
					<ul class="nav">
						<c:if test="${requestScope.user != null}">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">${requestScope.email} <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${requestScope.logoutURL}">Sign out</a></li>
							</ul>
						</li>
						</c:if>
						<c:if test="${requestScope.user == null}">
							<li><a href="${requestScope.loginURL}">Sign in</a></li>
						</c:if>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>