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
	<script type="text/javascript" language="javascript" src="rms/rms.nocache.js"></script>
	<script type="text/javascript" language="javascript" src="/js/jquery-1.8.1.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/themes/base/jquery-ui.css" type="text/css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/css/style.css" />
	<script type="text/javascript" src="/js/tinybox.js"></script>
</head>
<body>
	<style>body {padding-top: 60px;}</style>
	<script type="text/javascript">
    $(function (){
        $('a.ajax').click(function() {
            var url = this.href;
            // show a spinner or something via css
            var dialog = $('<div style="display:none" class="loading"></div>').appendTo('body');
            // open the dialog
            dialog.dialog({
                // add a close listener to prevent adding multiple divs to the document
                close: function(event, ui) {
                    // remove div with all data and events
                    dialog.remove();
                },
                modal: false
            });
            // load remote content
            dialog.load(
                url, 
                {}, // omit this param object to issue a GET request instead a POST request, otherwise you may provide post parameters within the object
                function (responseText, textStatus, XMLHttpRequest) {
                    // remove the loading class
                    dialog.removeClass('loading');
                }
            );
            //prevent the browser to follow the link
            return false;
        });
    });
    </script>
	
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
						<li onclick="TINY.box.show({url:'loginOpenId',width:300,height:150})"><a>Login</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container">
	</div>
	<footer style="position: absolute; bottom: 0;">
		<p>&copy; ivEleven 2012</p>
	</footer>
	<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
	<noscript>
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