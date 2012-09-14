<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
  <head>
    <jsp:include page="jsp/header.jsp"/>
    <script type="text/javascript" language="javascript" src="rms/rms.nocache.js"></script>
  </head>
  <body>
  	<style>body{padding-top: 60px;}</style>    
    <jsp:include page="jsp/top.jsp"/>
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
    <jsp:include page="jsp/footer.jsp"/>
   	<script type="text/javascript">
    	function getClientTimeZone(){
    	    var dateVar = new Date();  
    	    var timezone = dateVar.getTimezoneOffset()/60 * (-1);
        	return timezone;
        }
    </script>
  </body>
</html>