<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="Language" content="En" />
<meta name="Keywords" lang="En"
	content="Scheduling,Email Reminder Service,Mail Reminder Service, mail Remind tool,EMail Reminders,Automated Email,Automatic Email" />
<meta name="Description" lang="En" content="Get reminders on email" />
<meta name="Author" content="www.mail-reminder.com" />
<meta name="Copyright" content="www.mail-reminder.com" />
<title>What's this ?</title>
<link type="text/css" rel="stylesheet" href="RMS.css">
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">

<c:if test="${requestScope.GAE_MODE }">
	<script type="text/javascript">
	
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-34714345-1']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	
	</script>
</c:if>
</head>
<body>
	<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
	<jsp:include page="jsp/header.jsp" />
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
</p>
		

	</div>
<h2>How does it work?</h2>
<div class="container">
<p>After you logged in using your google account you can get down to business.</p>
<p>Using a simple interface you can choose when, what and how* to be notified.
Because you've signed in, the application already knows your e-mail address so at the date and time chosen by you an e-mail reminder will be delivered in your inbox containing all the precious information you so much need
</p>
* notification methods may change, we're constantly trying to improve
</p> 
    </div>
    <footer style="position:absolute;bottom:0;">
        <p>&copy; ivEleven  2012</p>
    </footer>
  </body>
</html>
