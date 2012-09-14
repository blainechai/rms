<%@page import="java.util.Set"%>
<%@page import="com.iv.rms.server.PropertyService"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
<html>
  <head>
    <jsp:include page="jsp/header.jsp"/>
  </head>
  <body>
  	<style>body{padding-top: 60px;}</style>    
    <jsp:include page="jsp/top.jsp"/>
    <div class="container">
      <% 
  	Set<String> keys = PropertyService.getInstance().getKeys();
  	for(String key : keys){
  		String value = PropertyService.getInstance().getValue(key);
  		out.println("Key:[" + key + "] Value:[" + value + "] <br />");	
  	}
  %>
    </div>
    <jsp:include page="jsp/footer.jsp"/>
  </body>
</html>