<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String auth = (String)session.getAttribute("auth"); //yes or no yes면 쿠키 갖고 있음
%>

<nav class="navbar navbar-inverse" style="margin-bottom: 0;">
	<div class="container-fluid">

		<!--<div style="text-align: right; background-color: #FAF4C0">
  
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
-->

		
		<c:choose>
			<c:when test="${sessionScope.auth eq null}">

				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/log/login"><span
							style="color: brown; font-weight: bold;">손님</span> 환영합니다.</a></li>

				</ul>

				<ul class="nav navbar-nav navbar-left">
					<li><a href="/log/login"><span
							class="glyphicon glyphicon-log-in"></span> LOGIN</a></li>
				</ul>


 			</c:when>




			<c:otherwise>

				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/my/info"><span
							style="color: brown; font-weight: bold;">${sessionScope.auth_id}</span>님
							안녕하세요.</a></li>

				</ul>

				<ul class="nav navbar-nav navbar-left">

					<li><a href="/log/logout"><span
							class="glyphicon glyphicon-log-out"></span> LOGOUT</a></li>
				</ul>
 			</c:otherwise>
		</c:choose>  



		<form class="navbar-form navbar-right">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search">
				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
</nav>


