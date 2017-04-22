<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.*"%>
<%@ page import="java.util.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-10">

<div>
	<h3>회원정보</h3>
</div>
<div class="well row">	<!-- 12등분  -->
	<div class="col-md-3" align="center">
		<form action="/my/update_picture" method="post" enctype="multipart/form-data">
				<b>PICTURE</b><br />
				<img src="${url }" width="200" height="200" style="border-radius: 100px;"/>
				<hr/>
				<input type="file" class="form-control"	name="pic" />
				<br/>
				<button type="submit" class="btn">사진변경</button>
		</form>
	</div>
	
	<div class="col-md-9" >
		<form action="/my/update_info" method="post">
		<p>
			<b>비밀번호 변경</b><br/>
			<input type="text" class="form-control" name="pass" 
				value="${map.PASS}"/>
		</p>
		
		<p>
			<b>NAME</b><br/>
			<input type="text" class="form-control" name="name" 
				value="${map.NAME }"/>
		</p>
		<p>
			<b>AGE</b><br/>
			<select class="form-control" name="age">
			<c:forEach var="i" begin="14" end="80">
				<option value="${i}" ${i eq map.AGE ? 'selected': '' } >${i }세</option>
			</c:forEach>
				
			</select>
		</p>
		<p>
			<b>GENDER</b><br/>
			<select name="gender" class="form-control">
				<option value="male" ${map.GENDER eq 'male' ? 'selected' : '' }>남</option>
				<option value="female" ${map.GENDER eq 'female' ? 'selected' : '' }>여</option>
			</select> 
		</p>
		<p>
			<b>E-MAIL</b><br/>
			<input type="email" class="form-control" name="email"
				value="${map.EMAIL }"/>
		</p>
		<br/>
		<p>
			<button type="submit" class="btn">변경하기</button>
			<a href="/my/leave"><button type="button" class="btn">탈퇴하기</button></a>
		</p>
	</form>
	</div>
</div>
</div>


