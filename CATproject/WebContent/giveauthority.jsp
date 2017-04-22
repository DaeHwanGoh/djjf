<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<div class="col-md-10" style="max-height: 100%; z-index:1; overflow-y: scroll;">
	<div class='list-group searchflist' style="margin-top: 10px;width:80%;margin-left:10%;">
		<c:forEach var="i" items="${userList}" varStatus="vs">
			<div class='list-group-item'>
				<div class='media'>
					<div class='media-left media-middle'>
						<img src='${i.img }' class='media-object'
							style='width: 50; height: 50;'>
					</div>
					<div class='media-body'>
						<h4 class='media-heading'>${i.userid }</h4>
						<p>${i.userauth }</p>
						<div class='authdiv'>
							<button type="button" class="btn btn-primary authchange"
								value="${i.userid }"
								style='position: absolute; right: 20; top: 20'>권한 변경</button>
						</div>
					</div>
				</div>
			<div class='w3-panel w3-border ${i.userid } aaa' value="${i.userid }" target="${i.userid }" >
				
			</div>

			</div>
		</c:forEach>
	</div>
</div>
<script>
$(".authchange").click(function(){
	tmp=$(this);
	$.ajax({
		"url" : "/auth/authority"
	}).done(function(a) {
		if(a=='master'){
			var s="";
			s+="<table class='table' >";
			s+="<tbody>";
			s+="<tr>";
			s+=" <td>GM</td>";
			s+="<td style='width:40px;'><button type='button' class='btn btn-primary authconfirm' value='gm'>변경</button></td>";
			s+="</tr>";
			s+="<tr>";
			s+="<td>VIP</td>";
			s+="<td style='width:40px;'><button type='button' class='btn btn-primary authconfirm' value='vip'>변경</button></td>";
			s+="</tr>";
			s+="<tr>";
			s+="<td>USER</td>";
			s+="<td style='width:40px;'><button type='button' class='btn btn-primary authconfirm' value='user'>변경</button></td>";
			s+="</tr>";
			s+="</tbody>";
			s+="</table>";
			$("."+tmp.attr("value")).html(s);
			$(".table button").click(function(){
				$.ajax({
					"url" : "/auth/authchange",
					"method" : "post",
					"data" : {
						"userid" : tmp.attr("value"),
						"authority": $(this).attr("value")
					}
				}).done(function(a) {
					if(a=='yyyy'){
						window.alert("권한 변경 완료");
						location.reload();
					} else {
						window.alert("권한 변경 실패");
						location.reload();
					}
				});
			});
		} else {
			var s="";
			s+="<table class='table' >";
			s+="<tbody>";
			s+="<tr>";
			s+="<td>VIP</td>";
			s+="<td style='width:40px;'><button type='button' class='btn btn-primary authconfirm' value='vip'>변경</button></td>";
			s+="</tr>";
			s+="<tr>";
			s+="<td>USER</td>";
			s+="<td style='width:40px;'><button type='button' class='btn btn-primary authconfirm' value='user'>변경</button></td>";
			s+="</tr>";
			s+="</tbody>";
			s+="</table>";
			$("."+tmp.attr("value")).html(s);
			$(".table button").click(function(){
				$.ajax({
					"url" : "/auth/authchange",
					"method" : "post",
					"data" : {
						"userid" : tmp.attr("value"),
						"authority": $(this).attr("value")
					}
				}).done(function(a) {
					if(a=='yyyy'){
						window.alert("권한 변경 완료");
						location.reload();
					} else {
						window.alert("권한 변경 실패");
						location.reload();
					}
				});
			});
		}
	});
});
</script>