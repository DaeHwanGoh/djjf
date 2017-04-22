<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
 
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div style="min-height: 10%; background-color: F6F6F6;">
		
		<tiles:insertAttribute name="header"/>
	</div>
	
	<div class="row">
		
		<tiles:insertAttribute name="nav"/>
		
	</div>

	<div class="row"
		style="min-height: 50%;font-family: Sans-serif; line-height: 2.0em; border: thin; border-left-style: solid; border-left-background-color: #a7abad; padding: 0px 0px;">
	

		<tiles:insertAttribute name="nav-side"/>


		<tiles:insertAttribute name="main"/>

			
		
	</div>

	<div style="min-height: 5%; background-color: FAF4C0;">
		<!-- html색상표 검색해서 사용 -->
		<tiles:insertAttribute name="footer"/>
	</div>

</body>

</html>