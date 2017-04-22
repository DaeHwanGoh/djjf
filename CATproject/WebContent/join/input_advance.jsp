<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
		
	session.setAttribute("jid", id);
	session.setAttribute("jpass", pass);
%>
<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
<div style="padding: 5% 25%;">
<br/>

<div align="center">
	<h2 style="font-family: serif; font-weight: bold;">:&nbsp;&nbsp;:&nbsp;&nbsp;JOIN_STEP02&nbsp;&nbsp;:&nbsp;&nbsp;:</h2>
</div>
<div>
	<form action="/join/result" method="post">
		<p>
			<b>NAME</b><br/>
			<input type="text" class="form-control" name="name" id="name" onkeyup="javascript:namespaceCheck()"/>
		</p>
		<p>
			<b>AGE</b><br/>
			<select class="form-control" name="age" id="age">
				<%for(int i=14; i<=80; i++){ %>
					<option value="<%=i%>"><%=i %>세</option>
				<%} %>
			</select>
		</p>
		<p>
			<b>GENDER</b><br/>
			<select name="gender" class="form-control" id="gender">
				<option value="male">남</option>
				<option value="female">여</option>
			</select> 
		</p>
		<p>
			<b>E-MAIL</b><br/>
			<input type="email" class="form-control" name="email" id="email" onkeyup="javascript:emailspaceCheck()"/>
		</p>
		<p>
			<button type="submit" class="btn" id="sbt" disabled>등록하기</button>
		</p>
	</form>
</div>
</div>
</div>
<script>

	//모든 항목이 다 채워졌으면 등록하기 버튼이 눌려질 수 있게 
	var flag1 =false, flag2 =false; 
	//flag1 = name, flag2=email  age 는 default 가 14세, gender는 default 가 남이여서 필요없음

	document.getElementById("sbt").disabled = true;
	
	function sbtChange() {
		if(flag1==true && flag2==true ) {
			document.getElementById("sbt").disabled = false;
		}else{
			document.getElementById("sbt").disabled = true;
		}
	}	

	function namespaceCheck(){ //name 공백이나 바꼈을 때 체크
		var namemsg = document.getElementById("name").value;   	
		if(namemsg.length > 0 ){ //공백이 아닐 때
			flag1 = true;
			sbtChange();
			
		}else{
			flag1 = false;
			sbtChange();			
		}	
	}

	function emailspaceCheck(){ //name 공백이나 바꼈을 때 체크
		var emailmsg = document.getElementById("email").value;   	
		if(emailmsg.length > 0){ //선택이 됐을 때
			flag2 = true;
			sbtChange();
			
		}else{
			flag2 = false;
			sbtChange();			
		}	
	}
	

</script>