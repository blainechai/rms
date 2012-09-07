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
    
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
  <body>
	Current timezone: <%=Calendar.getInstance().getTimeZone().getDisplayName()%>
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
