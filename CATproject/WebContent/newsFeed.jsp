<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<style>
.newspost{

}
.news{
	width: 350px;
  	float: left;
  	margin-left: 2em;
  	margin-right: 10px;
}
.namnam {
	width: 98%;
	margin-left: 1em;
}
/* custom template */
/* html, body {
   height: 100%;
   font-family:verdana,arial,sans-serif;
   color:#555555;
}

.nav {
   font-family:Arial,sans-serif;
   font-size:13px;
} */
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
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>

<div class="col-md-10" style="max-height: 100%; overflow-y: scroll;">
	<div class="wrapper">
		<div class="box">
			<div class="row row-offcanvas row-offcanvas-left">
				<!-- main right col -->
				<div class="column col-sm-10 col-xs-10 namnam" id="main">
					<div class="padding">
					
					<!-- 	<div class="full col-sm-9"> -->
							<!-- content -->
							<div class="row" id="container">
								<!-- main col left -->
								<!-- <div class="col-sm-6"> -->
								<c:forEach var="i" items="${feedlist}" varStatus="vs" >
									<div class="panel panel-default news">
										<div class="panel-heading">
											
											<span><b>${i.alarm }</b></span>
											<span style="position:absolute;right: 5px;"><button type="button" class="btn btn-danger deletepost">삭제</button></span>
										</div>
										<div class="panel-body newspost" style="padding-top: 0px;">
											<div class="row well" style="border-color:#f5f5f5; ">
												<div class="col-sm-2">
													<img src="${i.pictureUrl }" style="height: 50px; width: 50px;"  class="img-circle pull-left"> 
												</div>
												<div class="col-sm-10"  align="left">
													<br/>
													<b>${i.userid}</b><br/>
													${i.content }
												</div>
											</div>
											<div class="row pull-rirgt" >
												<a href='' style="left: 30" class="pull-right like" value="${i._id }">
													<div class="likebtn" style="margin-right: 10px;">${i.likeString }&nbsp;&nbsp;${i.likebtn }</div>
													</a>
												</div>
												<c:forEach var="i2" items="${i.commentlist }" varStatus="vs">
													<div class='row'>
													<hr style="margin-top: 0px; margin-bottom: 0px;" />
													<div class='col-sm-2'>
															<img src='${i2.pictureUrl }' class='img-circle' style="height: 50px; width: 50px;">
														</div>&nbsp;&nbsp;
														<div class='col-sm-10' align="left">
															&nbsp;&nbsp;&nbsp;<b>${i2.id }</b><br/>
															&nbsp;&nbsp;${i2.comment }
														</div>
														</div>
														<c:if test="vs.last">
														<hr style="margin-top: 0px; margin-bottom: 0px; color: white" />
														</c:if>
													</c:forEach>
											<hr>
											<form> 
												<div class="input-group">
													<div class="input-group-btn">
														<button class="btn btn-default add" value="${i._id }">
															<i class="glyphicon glyphicon-share"></i>
														</button>
													</div>
													<input type="text" class="form-control"
														placeholder="댓글작성하기..">
												</div>
											 </form>
										</div>
									</div>
									 </c:forEach> 
								<!-- </div> -->
								
								
								<!-- main col right
								<div class="col-sm-6">
								</div> -->

							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
var container = document.querySelector( '#container' );
var msnry = new Masonry( container, {
  // options
  columnWidth: 100,
  itemSelector: '.news',
} );

$(".like").click(function(){
	 var post_id= $(this).attr('value');
	 var tmp =$(".likebtn").val();
	/* alert(post_id);  */
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
</script>
