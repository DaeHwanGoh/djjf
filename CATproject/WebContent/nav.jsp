<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
				<ul class="nav navbar-nav" style="position:absolute;right:270; bottom:0">				
					<li class="factive aa"><a href="#" ><i class="material-icons" style="font-size:20px">notifications</i></a></li>
				</ul>
 			</c:otherwise>
		</c:choose>  


        


<!-- ----------------------검색창-------------------------------- -->
		<form class="navbar-form navbar-right" action="/search/01"
			method="get">

			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search"
					name="title" id="autocomplete">

				<div class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<i class="glyphicon glyphicon-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
</nav>
<div>
<div id="autoView" style="position:absolute;  right:0px; z-index:10; width:300px; display:block; 
background-color:FFFFFF; ">
</div>
<div class='panel panel-default friendpanel' style="position:absolute;  right:0px; z-index:10; width:300px; display:none; 
background-color:FFFFFF; ">
		<!-- 검색 ajax -->
			<div class='list-group req-list'>
			</div>
			<div class='list-group post-list'>
			</div>
			
</div>
</div>

<!-- ----------------------검색결과-------------------------------- -->
<script>
	$("#autocomplete").on('keyup', function() {
		var t = $(this).val();
		if (t.length == 0) {
			$("#autoView").html("");
			return;
		}
		$.ajax({
			"url" : "/search/searchAjax",
			"method" : "get",
			"data" : {
				"title" : t
			}
		}).done(function(rst) {
			$("#autoView").html(rst);

		}); //ajax
	}); //srch함수
	$("#autocomplete").focusout(function() {
		$("#autoView").css("display","none");
	});
	$("#autocomplete").focusin(function() {
		$("#autoView").css("display","block");
	});
	$(".factive a").click(function(){
		$(".friendpanel").animate({
            height: 'toggle'
        });
	});
	$(".friendpanel").mouseleave(function(){
		$(".friendpanel").animate({
            height: 'toggle'
        });
	});
// 	$(".factive").focusout(function() {
// 		$(".friendpanel").css("display","none");
// 	});
// 	$(".friendpanel").focusout(function(){
// 		$(".friendpanel").css("display","none");
// 	});
	$(document).ready(function() {
		$.ajax({
			"url" : "/post/nav"
		}).done(function(a) {
			if(a.length!=0){
				$(".material-icons").css("color","green");
				var s="";
				for(var i=0; i<a.length; i++){
					s+="<div class='list-group-item'>"
						s+="<div class='media'> ";
						s+="<div class='media-left media-middle'> "
							s+="<img src='"+a[i].pictureUrl+"' class='media-object' "
							s+="style='width: 50; height: 50;'> "
						s+="</div> "
							s+="<div class='media-body'> "
								s+="<h4 class='media-heading'>"+a[i].fid+"</h4> "
								s+="<p>"+a[i].alarm+"</p> "
							s+="</div>" 
						s+="</div>";
					s+="</div>";
				} //<button class='w3-button w3-khaki w3-round-large w3-hover-blue accept' value='"+a[i].ID+"'
												//style='position: absolute; right: 25; bottom: 10'>수락</button>
				//<button class='w3-button w3-khaki w3-round-large w3-hover-blue accept' value='"+a[i].ID+"'
												//style='position: absolute; right: 15; bottom: 10'>거절</button>
				$(".post-list").html(s);
				var tmp;			
			} 
		});
		//==================================================================================================
		$.ajax({
			"url" : "/friend/nav"
		}).done(function(a) {
			if(a.length!=0){
				$(".material-icons").css("color","green");
				var s="";
				for(var i=0; i<a.length; i++){
					s+="<div class='list-group-item'>"
						s+="<div class='media'> ";
						s+="<div class='media-left media-middle'> "
							s+="<img src='"+a[i].URL+"' class='media-object' "
							s+="style='width: 50; height: 50;'> "
						s+="</div> "
							s+="<div class='media-body'> "
								s+="<h4 class='media-heading'>"+a[i].ID+"</h4> "
								s+="<p>"+a[i].AGE+" "+a[i].GENDER+"</p> "
								s+="<div class='reqfriend1' value='"+a[i].ID+"'>";
// 								s+="<a href='#' class='reqfriend' value='"+a[i].ID+"' style='position: absolute; right: 20; bottom: 10'>"
// 									s+="<i class='material-icons'>person add</i></a> "
								s+="<button class='w3-button w3-khaki w3-round-large w3-hover-blue w3-tiny accept' style='position: absolute; right: 70; bottom: 10'>수락</button>";
								s+="<button class='w3-button w3-khaki w3-round-large w3-hover-blue w3-tiny refuse' style='position: absolute; right: 15; bottom: 10'>거절</button>";
								s+="</div>";
						s+="</div>" 
						s+="</div>";
						s+="</div>";
				} //<button class='w3-button w3-khaki w3-round-large w3-hover-blue accept' value='"+a[i].ID+"'
												//style='position: absolute; right: 25; bottom: 10'>수락</button>
				//<button class='w3-button w3-khaki w3-round-large w3-hover-blue accept' value='"+a[i].ID+"'
												//style='position: absolute; right: 15; bottom: 10'>거절</button>
				$(".req-list").html(s);
				var tmp;
				$(".accept").click(function(){
					tmp=$(this);
					console.log(tmp.parent().attr("value"));
					$.ajax({
						"url" : "/friend/accept",
						"method" : "post",
						"data" : {
							"fid" : tmp.parent().attr("value")
						}
					}).done(function() {
						tmp.parent().html("<i style='position: absolute; right: 20; bottom: 10; color: blue; font-size: 10;'>친구 요청을 수락하셨습니다. </i> ");
					});
				});
				$(".refuse").click(function(){
					tmp=$(this);
					console.log(tmp.parent().attr("value"));
					$.ajax({
						"url" : "/friend/refuse",
						"method" : "post",
						"data" : {
							"fid" : tmp.parent().attr("value")
						}
					}).done(function() {
						tmp.parent().html("<i style='position: absolute; right: 20; bottom: 10; color: red; font-size: 10;'>친구 요청을 거절하였습니다. </i> ");
					});
				});
			}
		}); //ajax
	}); 
	
</script>

<!-- ----------------------------------------------------------- -->



