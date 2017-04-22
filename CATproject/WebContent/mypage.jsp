<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<style>
.namnam {
	width: 98%;
	margin-left: 1em;
}
a {
	color: #222222;
}

a:hover {
	text-decoration: none;
}

hr {
	border-color: #dedede;
}

.wrapper {
	height: 100%;
	margin-left: 0;
	margin-right: 0;
}

.wrapper:before, .wrapper:after, .column:before, .column:after {
	content: "";
	display: table;
}

.wrapper:after, .column:after {
	clear: both;
}

.column {
	height: 100%;
	overflow: auto;
	*zoom: 1;
}

.column .padding {
	padding: 20px;
}

.full {
	padding-top: 70px;
}

.box {
	bottom: 0; /* increase for footer use */
	left: 0;
	position: absolute;
	right: 0;
	top: 0;
	background-color: #444444;
	/*
    background-image:url('/assets/example/bg_suburb.jpg');
    background-size:cover;
    background-attachment:fixed;
  */
}

.divider {
	margin-top: 32px;
}

#main {
	background-color: #e9eaed;
	padding-left: 0;
	padding-right: 0;
}

#main .img-circle {
	margin-top: 18px;
	height: 70px;
	width: 70px;
}

.postrep {
	width: 90%;
	margin-left: 5%;
	/* min-height: 100px; */
}

.list-group-item {
	border-radius: 0;
}

.navbar-toggle, .close {
	outline: 0;
}

.navbar-toggle .icon-bar {
	background-color: #fff;
}

.btn-primary, .label-primary, .list-group-item.active, .list-group-item.active:hover,
	.list-group-item.active:focus {
	background-color: #3B5999;
	color: #fffffe;
}

.btn-default {
	color: #666666;
	text-shadow: 0 0 1px rgba(0, 0, 0, .3);
}

.form-control {
	
}

.panel textarea, .well textarea, textarea.form-control {
	resize: none;
}

.badge {
	color: #3B5999;
	background-color: #fff;
}

.badge:hover, .badge-inverse {
	background-color: #3B5999;
	color: #fff;
}

.jumbotron {
	background-color: transparent;
}

.label-default {
	background-color: #dddddd;
}

.page-header {
	margin-top: 55px;
	padding-top: 9px;
	border-top: 1px solid #eeeeee;
	font-weight: 700;
	text-transform: uppercase;
	letter-spacing: 2px;
}

.panel-default .panel-heading {
	background-color: #f9fafb;
	color: #555555;
}

.col-sm-9.full {
	width: 100%;
}

.modal-header, .modal-footer {
	background-color: #f2f2f2;
	font-weight: 800;
	font-size: 12px;
}

.modal-footer i, .well i {
	font-size: 20px;
	color: #c0c0c0;
}

.modal-body {
	padding: 0px;
}

.modal-body textarea.form-control {
	resize: none;
	border: 0;
	box-shadow: 0 0 0;
}

small.text-muted {
	font-family: courier, courier-new, monospace;
}

/* adjust the contents on smaller devices */
@media ( max-width : 768px) {
	.column .padding {
		padding: 7px;
	}
	.full {
		padding-top: 20px;
	}
	.navbar-blue {
		background-color: #3B5999;
		top: 0;
		width: 100%;
		position: relative;
	}
}

/*
 * off canvas sidebar
 * --------------------------------------------------
 */
@media screen and (max-width: 768px) {
	.row-offcanvas {
		position: relative;
		-webkit-transition: all 0.25s ease-out;
		-moz-transition: all 0.25s ease-out;
		transition: all 0.25s ease-out;
	}
	.row-offcanvas-left.active {
		left: 33%;
	}
	.row-offcanvas-left.active .sidebar-offcanvas {
		left: 33%;
		position: absolute;
		top: 0;
		width: 33%;
		margin-left: 5px;
	}
	#sidebar, #sidebar a, #sidebar-footer a {
		padding-left: 3px;
	}
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="col-md-10">
<div class="wrapper">
	<div class="box">
		<div class="row row-offcanvas row-offcanvas-left">
			<!-- main right col -->
			<div class="column col-sm-10 col-xs-10 namnam" id="main">
				<div class="padding">
					<div class="full col-sm-9">

						<!-- content -->
						<div class="row">

							<!-- main col left -->
							<div class="col-sm-5">

								<div class="panel panel-default">
								<div class="well" style="margin-bottom: 0;">
									<div class="panel-thumbnail" align="center"
										style="margin-top: 30px;">
										<img src="${pictureUrl }" width="400px"
											height="250px" class="img-responsive">
									</div>
									<div class="panel-body" align="center">
										<p class="lead">${id }님의 마이페이지</p>
										<p><b style="font-size: 25">${fn:length(flist) }</b> Followers, 
										   <b style="font-size: 25">${fn:length(my_postlist) }</b> Posts</p>

										<p>
										</p>
									</div>
									</div>
								</div>


								<div class="panel panel-default" style="margin-bottom: 16px;">
									<div class="fcontainer">
										<div class="well" style="margin-bottom: 0px;">
											<form class="form">
												<h4>친구찾기</h4>
												<div class="input-group text-center">
													<input type="text"
														class="form-control input-lg searchfriend"
														placeholder="친구 ID"> <span class="input-group-btn"><button
															class="btn btn-lg btn-primary" type="button">OK</button></span>
												</div>
											</form>
										</div>
										<div class='panel panel-default findajax' style="postition:absolute; margin-bottom: 0px;">
											<div class='list-group searchflist'>
											<!-- 검색 ajax -->
											</div>
										</div>
									</div>
								</div>
								 <div class="panel panel-default">
                                <div class="panel-heading"><a href="#" class="pull-right">모든 친구 목록</a> <h4>${fn:length(flist) } 명</h4></div>
                                  <div class="panel-body">
                                    <div class="list-group flist">
                                    <c:if test="${fn:length(flist) ne 0}">
                                    <c:forEach var="i" begin="0" end="${fn:length(flist) -1 }">
                                      <div class='list-group-item'>
										<div class='media'> 
											<div class='media-left media-middle'> 
												<img src='${flist[i].URL}' class='media-object' style='width: 50; height: 50;'> 
											</div> 
											<div class='media-body'> 
												<h4 class='media-heading'>${flist[i].ID}</h4> 
												<p>${flist[i].AGE} ${flist[i].GENDER}</p>
											</div> 
										</div>
									  </div>
									 </c:forEach>
									 </c:if>
                                    </div>
                                  </div>
                              </div>

								

								<!--  <div class="panel panel-default">
                                <div class="panel-heading"><h4>What Is Bootstrap?</h4></div>
                               	<div class="panel-body">
                                	Bootstrap is front end frameworkto build custom web applications that are fast, responsive &amp; intuitive. It consist of CSS and HTML for typography, forms, buttons, tables, grids, and navigation along with custom-built jQuery plug-ins and support for responsive layouts. With dozens of reusable components for navigation, pagination, labels, alerts etc..                          </div>
                              </div> -->



							</div>

							<!-- main col right -->
							<div class="col-sm-7"
									style="max-height: 145%; overflow-y: scroll;">
									<c:forEach var="i" items="${my_postlist }">
										<div class='w3-panel' style="margin-top:0px; margin-bottom: 10px">
											<div class='panel panel-default' >
												<div class='panel-heading'>
												
													<a href='#' value="${i._id }" style="left:30; margin-top: 5px;" class="pull-right like" value="${i._id }">
														<div class="likebtn">${i.likebtn }</div>
													</a>
													<h4>내가 쓴 게시글</h4>
												</div>
												<div class='panel-body' style="padding-top: 0;">
												<div class="row well">
												<div class="col-sm-2">
													<img src="${pictureUrl}" class='img-circle pull-left'>
												</div>
												<div class="col-sm-10 pull-left">
													<b><a href='#'>${i.userid }</a></b></br>
													<span style='font-size:9'>작성 날짜: ${i.date }</span><br/>
													<span style='font-size:15'>${i.content }</span>
													<%-- <div class='w3-panel' style='width: 90%; margin-left:0;'>
													${i.content }
													</div> --%>
												</div>
													</div>
													<hr>
													<c:forEach var="i2" items="${i.commentlist }">
														<div class='w3-panel w3-border postrep comment' style="margin-bottom: 5px;">
															<!-- 댓글 -->
															<div class='addcomment'></div>
															<div class='media'>
																<div class='media-left media-top'>
																	<img src='${i2.pictureUrl }' class='media-object'
																		style='width: 60px'>
																</div>
																<div class='media-body'>
																	<h4 class='media-heading'>${i2.id }</h4>
																	<p>${i2.comment }</p>
																</div>
															</div>

														</div>
													</c:forEach>
													<hr>
													<form>
														<div class='input-group'>
															<div class='input-group-btn'>
																<button class='btn btn-default add' value="${i._id }">
																	<i class='glyphicon glyphicon-share'></i>
																</button>
															</div>
															<input type='text' class='form-control'
																placeholder='Add a comment..'>
														</div>
													</form>

												</div>
											</div>
										</div>
									</c:forEach>
								</div>
						</div>
						<!--/row-->
						<hr>

					</div>
					<!-- /col-9 -->
				</div>
				<!-- /padding -->
			</div>
			<!-- /main -->

		</div>
	</div>
</div>
</div>


<script>


$(".likebtn").parent().click(function(){
	 var post_id= $(this).attr('value');
	 var tmp =$(this);
	$.ajax({
		"url" : "/post/likeBtnClick",
		"method" : "get",
		"data":{
			"post_id" : post_id,
		}
	}).done(function(rst){
		var s="";
		if(rst=='remove'){
			s+="<i class='glyphicon glyphicon-thumbs-up' style='font-size: 25px; color: black;'></i>";
		}else{
			s+="<i class='glyphicon glyphicon-thumbs-up' style='font-size: 25px; color: blue;'></i>";
		}
		tmp.html(s);
	});
}); 


$(".add").click(function(){
	/* alert($(this).val()); */
	/* window.alert($(this).parent().next().val()); */
	var post_id =$(this).val();
	var coment =$(this).parent().next().val();
	$.ajax({
		"url" : "/post/comentAjax",
		"method" : "get",
		"data":{
			"post_id" : post_id,
			"coment": coment
		}
	}).done(function(rst){
		$(".addcomment").html(rst);
	});
}); 

	console.log(${fn:length(flist) }); 
	$(".searchfriend").keyup(function(){
		if($(this).val()==""){
			$(".searchflist").html("");
			return;
		}
		$.ajax({
			"url" : "/friend/search",
			"method" : "post",
			"data" : {
				"fid" : $(".searchfriend").val()
			}
		}).done(function(a) {
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
							s+="<div class='reqfriend1'>";
							s+="<a href='#' class='reqfriend' value='"+a[i].ID+"' style='position: absolute; right: 20; bottom: 10'>"
								s+="<i class='material-icons'>person add</i></a> "
							s+="</div>";
					s+="</div>" 
					s+="</div>";
					s+="</div>";
			}
			$(".searchflist").html(s);
			var tmp;
			$(".reqfriend").click(function(){
				tmp=$(this);
				console.log(tmp.attr("value"));
				$.ajax({
					"url" : "/friend/reqf",
					"method" : "post",
					"data" : {
						"fid" : tmp.attr("value")
					}
				}).done(function() {
					tmp.removeClass("reqfriend");
					tmp.parent().html("<i style='position: absolute; right: 20; bottom: 10'>친구 요청을 보냈습니다. </i> ");
				});
			});
			
		});
	});
	
</script>