<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link type="text/css" rel="stylesheet" href="RMS.css">
    <title>Remind me this</title>
    <script type="text/javascript" language="javascript" src="rms/rms.nocache.js"></script>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
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
  </head>
  <body>
  <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
	<script src="/bootstrap/js/bootstrap.min.js"></script>    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>

    <h1></h1>
    <jsp:include page="jsp/header.jsp"/>
    <div class="container">
      <table align="center">
      <tr>
        <td colspan="2" style="font-weight:bold;"></td>        
      </tr>
      <tr>
        <td id="workspace"></td>
        <td id="sendButtonContainer"></td>
      </tr>
    </table>
    </div>
   	<script type="text/javascript">

    	function getClientTimeZone(){
    	    var dateVar = new Date();  
    	    var timezone = dateVar.getTimezoneOffset()/60 * (-1);
        	return timezone;
        }
    </script>
  </body>
</html>
