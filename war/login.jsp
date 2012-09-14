<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" type="text/css" media="screen" href="/css/openid.css" />
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.openid.js"></script>
	
		<h4>Login using your OpenId</h4>
		<hr />
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
						<a href="${requestScope.MyOpenId }"><img src="images/myopenidB.png" alt="icon" /></a>
					</li>
					<li class="direct" title="AOL" style="line-height: 0; cursor: pointer;">
						<a href="${requestScope.AOL }"><img src="images/aolB.png" alt="icon" /></a>
					</li>
				</ul>
			</div>
		</form>
	

