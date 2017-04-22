<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
<div style="padding: 5% 25%;">
<br/>
<div align="center">
	<h2 style="font-family: serif; font-weight: bold;">:&nbsp;&nbsp;:&nbsp;&nbsp;JOIN_STEP01&nbsp;&nbsp;:&nbsp;&nbsp;:</h2>
</div>

<div>
	<c:if test="${param.mode ne null }">
		<p>
			<i style="color: red">비밀번호가 일치하지 않았습니다.</i>
		</p>
	</c:if>
	<form action="/join/step02" method="post">
		<p>
			<b>ID</b><span id="chkResult"></span><br /> <input type="text"
				class="form-control" name="id" id="id" onblur="javascript:idCheck()"/>
		</p>
		<p>
			<b>PASS</b><br /> <input type="password" class="form-control"
				name="pass" id="pass" onkeyup="javascript:passCompare()" />
		</p>
		<p>
			<b>PASS CONFIRM</b><span id="cmpResult"></span><br /> <input
				type="password" class="form-control" name="rpass" id="rpass"
				onkeyup="javascript:passCompare()" />

		</p>
		<p>
			<button type="submit" id="sbt" class="btn" disabled>다음단계로</button>
		</p>
	</form>
</div>
</div>
</div>
<script>
	var flag1 =false, flag2 =false;
	document.getElementById("sbt").disabled = true;
	
	function sbtChange() {
		if(flag1==true && flag2==true ) {
			document.getElementById("sbt").disabled = false;
		}else{
			document.getElementById("sbt").disabled = true;
		}
	}

	
	function passCompare() { //keyup일때 작동
		pmsg=document.getElementById("pass").value;
		rpmsg=document.getElementById("rpass").value;
		
		var flag = (document.getElementById("pass").value == document.getElementById("rpass").value);
		if(pmsg.length>0&&rpmsg.length>0){ //공백이 아닐 때
			if (flag) {
				flag2 =true;
				sbtChange();
				document.getElementById("cmpResult").style.color = "green";
				document.getElementById("cmpResult").innerHTML = "<i>일치</i>";
				
			} else {
				flag2 =false;
				sbtChange();
				document.getElementById("cmpResult").style.color = "red";
				document.getElementById("cmpResult").innerHTML = "<i>비밀번호가 일치하지 않습니다.</i>";
				
			}
		}else{
			flag2 =false;
			sbtChange();
			document.getElementById("cmpResult").style.color = "red";
			document.getElementById("cmpResult").innerHTML = "<i>비밀번호를 입력해주세요.</i>";
			
		}
		sbtChange();
	}

	function idCheck(){ //id 중복체크
		var msg = document.getElementById("id").value;   //this.value 로 해도됨
		if (msg.length > 0) { //공백이 아닐 때
			var xhr = new XMLHttpRequest();
			var url = "/join/checkAjax?id=" + msg; //아이디 넣어서 넘김

			xhr.open("post", url, true); // 
			xhr.send();

			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var txt = xhr.responseText;
					if(txt == 'YYYYY'){
						flag1=false; //이미 사용 중일 때는 다음 단계로 버튼이 안눌리게
						sbtChange();
						document.getElementById("chkResult").innerHTML = '<span style ="color: red; font-weight: bold;"><i>(이미 사용 중인 아이디입니다.)</i></span>';
						
					}else{
						//사용가능한 아이디일 때
						flag1=true;
						sbtChange();
						document.getElementById("chkResult").innerHTML = '<span style ="color: green; font-weight: bold;"><i>('+msg+' 은/는 사용 가능한 아이디입니다.)</i></span>';
					}
				}
			};
		} else {
			flag1=false;
			sbtChange();
			document.getElementById("chkResult").innerHTML ="<span style ='color: red; font-weight: bold;'><i>(아이디를 입력해주세요.)</i></span>";
			
		}
		sbtChange();
	}
</script>