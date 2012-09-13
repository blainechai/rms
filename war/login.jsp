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
<title>mailRemind</title>
<link type="text/css" rel="stylesheet" href="RMS.css">
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen" href="/css/openid.css" />
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.openid.js"></script>
<c:if test="${requestScope.GAE_MODE }">
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-34714345-1' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
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
	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
		<div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.</div>
	</noscript>
	<jsp:include page="jsp/header.jsp" />
	<div class="container">
		<h2>Login using your OpenId</h2>
		<form class="openid" method="post" action="/Login.xhtml?ReturnUrl=">
			<div>
				<ul class="providers">
					<li class="direct" title="Google" style="line-height: 0; cursor: pointer;">
						<a href="${requestScope.Google }"><img src="images/googleB.png" alt="icon" /></a>
					</li>
					<li class="direct" title="Yahoo" style="line-height: 0; cursor: pointer;">
						<a href="${requestScope.Yahoo }"><img src="images/yahooB.png" alt="icon" /></a>
					</li>
					<li class="direct" title="MyOpenId" style="line-height: 0; cursor: pointer;">
						<a href="${requestScope.MyOpenId.com }"><img src="images/myopenidB.png" alt="icon" /></a>
					</li>
					<li class="direct" title="AOL" style="line-height: 0; cursor: pointer;">
						<a href="${requestScope.AOL }"><img src="images/aolB.png" alt="icon" /></a>
					</li>
				</ul>
			</div>
		</form>
	</div>
	<footer>
		<p>&copy; ivEleven 2012</p>
	</footer>
</body>
</html>
