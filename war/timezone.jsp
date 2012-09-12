<%@page import="com.iv.rms.server.PropertyService"%>
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
    <link type="text/css" rel="stylesheet" href="RMS.css">
    <title>Remind me this</title>    
    <link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="/js/jquery-1.8.1.min.js"></script>
  </head>
  <body>
  <%
  	out.print(PropertyService.getInstance().getValue("senderEmailAddress"));
  out.print(PropertyService.getInstance().getValue("senderEmailName"));
  %>
  </body>
</html>
