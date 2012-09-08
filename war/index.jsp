<%@page import="java.util.Calendar"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <!--                                                               -->
    <!-- Consider inlining CSS to reduce the number of requested files -->
    <!--                                                               -->
    <link type="text/css" rel="stylesheet" href="RMS.css">

    <!--                                           -->
    <!-- Any title is fine                         -->
    <!--                                           -->
    <title>Remind me this</title>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" language="javascript" src="rms/rms.nocache.js"></script>
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
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
    </div>
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
