<!doctype html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta name="Language" content="En" />
<meta name="Keywords" lang="En"
	content="Scheduling,Email Reminder Service,Mail Reminder Service, mail Remind tool,EMail Reminders,Automated Email,Automatic Email" />
<meta name="Description" lang="En" content="Get reminders on email" />
<meta name="Author" content="www.mail-reminder.com" />
<meta name="Copyright" content="www.mail-reminder.com" />
<title>Mail Reminder</title>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/css/rms.css">

<script type="text/javascript" language="javascript"
	src="rms/rms.nocache.js"></script>
</head>
<body>
	<style>
body {
	padding-top: 60px;
}
</style>


	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar">Menu
						1</span> <span class="icon-bar">Menu 2</span> <span class="icon-bar">Menu
						3</span>
				</a> <a class="brand" href="/">Mail reminder</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="whatsthis.jsp">What's this?</a></li>
						<li><a href="contact.jsp">Contact</a></li>
						<li><a href="/_ah/logout?continue=%2Findex.jsp">Sign out</a>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container">
		<table align="center">
			<tr>
				<td colspan="2" style="font-weight: bold;"></td>
			</tr>
			<tr>
				<td id="workspace"></td>
				<td id="sendButtonContainer"></td>
			</tr>
		</table>
	</div>
	<footer style="position: absolute; bottom: 0;">
		<p>&copy; ivEleven 2012</p>
	</footer>
	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
		<div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.</div>
	</noscript>
	<script src="/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function getClientTimeZone() {
			var dateVar = new Date();
			var timezone = dateVar.getTimezoneOffset() / 60 * (-1);
			return timezone;
		}
	</script>
</body>
</html>