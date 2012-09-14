<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
	<head>
		<jsp:include page="jsp/header.jsp"/>
	</head>
	<body>
		<style>body{padding-top: 60px;}</style>
		<jsp:include page="jsp/top.jsp" />
		<h2>What's this ?</h2>
		<div class="container">
			<p>Mail reminder is a simple application that helps you keep track
				of the things and events that are important to you.</p>
			<p>So you need to remember to brush your teeth thoroughly because
				tomorrow you have a dentist appointment, or you need to remember to
				water the succulets every two weeks, or you need to remember that two
				months ago you've made a bet and a free beer is coming your way, or
				that you'd better start chucking down that glass of water or you
				won't meet your daily quota. If e-mail is something you're
				comfortable with and you keep what's important in your inbox, then
				mailRemaind is what you need.</p>
		</div>
		<h2>How does it work?</h2>
		<div class="container">
			<p>
			<p>After you logged in using your open id account(Google, Yahoo, AOl, MyOpenId) you can get down to business.</p>
			<p>
				Using a simple interface you can choose when, what and how* to be notified. Because you've signed in, the application already knows your e-mail address so at the date and time chosen by you an e-mail reminder will be delivered in your inbox containing all the precious information you so much need
			</p>
			* notification methods may change, we're constantly trying to improve 
	    </div>
	    <jsp:include page="jsp/footer.jsp"/>
	</body>
</html>
