<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="Language" content="En" />
<meta name="Keywords" lang="En" content="Scheduling,Email Reminder Service,Mail Reminder Service, mail Remind tool,EMail Reminders,Automated Email,Automatic Email" />
<meta name="Description" lang="En" content="Get reminders on email" />
<meta name="Author" content="www.mail-reminder.com" />
<meta name="Copyright" content="www.mail-reminder.com" />
<title>Mail Reminder</title>
<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/css/rms.css">
<link rel="stylesheet" href="/css/tinybox.css" />
	<script type="text/javascript" src="/js/tinybox.js"></script>
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