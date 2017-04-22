<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
<div style="padding: 5% 25%;">
<br/>

<div align="center">
	<h2 style="font-family: serif; font-weight: bold;">:&nbsp;&nbsp;:&nbsp;&nbsp;LOGIN&nbsp;&nbsp;:&nbsp;&nbsp;:</h2>
</div>

<div>
	<form action="/log/login/result" method="post">
		<p>
			<b >ID</b>
			<br/>
			<input type="text" class="form-control" name="id"/>
		</p>
		<p>
			<b>PASS</b><br/>
			<input type="password" class="form-control" name="pass"/>
		</p>
		<div style="text-align:center;">
			<p>
				<input type="checkbox" name="keep" />로그인 유지&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn">로그인</button>
			</p>
		</div>	
		</form>
		<div style="text-align:center;">
		<i>아직 회원이 아닌가요?<a href="/join/step01"> <span style="color:red">회원가입하기</span></a></i><br/>
		</div>	
	
</div>
</div>
</div>