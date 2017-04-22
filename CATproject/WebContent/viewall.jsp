<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



 
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
		
		
<div align="center"  style="padding : 20px; margin-bottom: 0;" class="jumbotron">
	<h1>고양이 모양과 로고 들어가야됨 <small>Content Allrank findTaste</small></h1>
	<!-- 예쁘게 디자인 나중에 바꾸기 -->
</div>
	</div>
	
	<div class="row">
		
		

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<nav class="navbar navbar-inverse" style="margin-bottom: 0;">
	<div class="container-fluid">

		<!--<div style="text-align: right; background-color: #FAF4C0">
  
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
-->

		
		
			

				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Home</a></li>
					<li><a href="/log/login"><span
							style="color: brown; font-weight: bold;">손님</span> 환영합니다.</a></li>

				</ul>

				<ul class="nav navbar-nav navbar-left">
					<li><a href="/log/login"><span
							class="glyphicon glyphicon-log-in"></span> LOGIN</a></li>
				</ul>


 			




			
		  






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
<div id="autoView"></div>

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
</script>

<!-- ----------------------------------------------------------- -->




		
	</div>

	<div class="row"
		style="min-height: 50%;font-family: Sans-serif; line-height: 2.0em; border: thin; border-left-style: solid; border-left-background-color: #a7abad; padding: 0px 0px;">
	
		


<div class="col-md-2 sidenav"
	style="background-color: #767b7d;  height: 100%; padding: 0;">
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="#section1"
			style="border-radius: 0px; background-color: #a7abad; text-align: center;">M
				O V I E</a></li>
		<div style="text-align: right;">
			<li><a href="#section2"
				style="border-radius: 0px; color: #f4f6f9;">공포/스릴러&nbsp</a></li>
			<li><a href="#section3"
				style="border-radius: 0px; color: #f4f6f9;">SF/판타지&nbsp</a></li>
			<li><a href="#section3"
				style="border-radius: 0px; color: #f4f6f9;">드라마/로맨스/코미디&nbsp</a></li>
			<li><a href="#section3"
				style="border-radius: 0px; color: #f4f6f9;">가족/아동/애니메이션&nbsp</a></li>
		</div>


		

		
			

			  <!-- 손님 상태이면-->
			<li class="active"><a href="/log/login"
				style="border-radius: 0px; background-color: #a7abad; text-align: center;">추
					천 작</a></li>
			<li class="active"><a href="/log/login"
				style="border-radius: 0px; background-color: #a7abad; text-align: center;">관심
					CONTENT</a></li>
	
			<div style="text-align: right;">
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">찜한 CONTENT 목록&nbsp</a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">내 게시글&nbsp</a></li>
			</div>
								
			<div style="text-align: center; margin-top: 20%; max-height: 60%;">
				<li><a style="border-radius: 0px; color: #f4f6f9;"><hr/></a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">개인정보(프로필)</a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">회원탈퇴</a></li>
			</div>			
			
					
		
	</ul>
	<br>
</div>



		

<style>
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
  color:#222222;
}

a:hover {
  text-decoration:none;
}

hr {
  border-color:#dedede;
}

.wrapper {
   height: 100%;
   margin-left:0;
   margin-right:0;
}

.wrapper:before, .wrapper:after,
.column:before, .column:after {
    content: "";
    display: table;
}

.wrapper:after,
.column:after {
    clear: both;
}

.column {
    height: 100%;
    overflow: auto;
    *zoom:1;
}

.column .padding {
    padding: 20px;
}

.full{
	padding-top:70px;
}

.box {
  	bottom: 0; /* increase for footer use */
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
    background-color:#444444;
  /*
    background-image:url('/assets/example/bg_suburb.jpg');
    background-size:cover;
    background-attachment:fixed;
  */
}

.divider {
	margin-top:32px;
}

/* .navbar-blue {
    border-width:0;
	background-color:#3B5999;
    color:#ffffff;
    font-family:arial,sans-serif;
    top:0;
    position:fixed;
    width:inherit;
} 

.navbar-blue li > a,.navbar-toggle  {
   color:#efefef;
}

.navbar-blue .dropdown-menu li a {color:#2A4888;}
.navbar-blue .dropdown-menu li > a {padding-left:30px;}

.navbar-blue li>a:hover, .navbar-blue li>a:focus, .navbar-blue .open, .navbar-blue .open>a, .navbar-blue .open>a:hover, .navbar-blue .open>a:focus {
   background-color:#2A4888;
   color:#fff;
}*/

#main {
   background-color:#e9eaed;
   padding-left:0;
   padding-right:0;
}
#main .img-circle {
   margin-top:18px;
   height:70px;
   width:70px;
}

/* #sidebar {
    padding:0px;
	padding-top:15px;
}

#sidebar, #sidebar a, #sidebar-footer a {
    color:#ffffff;
    background-color:transparent;
	text-shadow:0 0 2px #000000;
    padding-left:5px;
}
#sidebar .nav li>a:hover {
    background-color:#393939;
} */
/* #sidebar-footer {
  background-color:#444;
  position:absolute;
  bottom:5px;
  padding:5px;
} 
#footer {
  margin-bottom:20px;
}*/

/* bootstrap overrides */

/* h1,h2,h3 {
   font-weight:800;
} */

.navbar-toggle, .close {
	outline:0;
}

.navbar-toggle .icon-bar {
	background-color: #fff;
}

.btn-primary,.label-primary,.list-group-item.active, .list-group-item.active:hover, .list-group-item.active:focus  {
	background-color:#3B5999;
    color:#fffffe;
}
.btn-default {
    color:#666666;
    text-shadow:0 0 1px rgba(0,0,0,.3);
}
.form-control {
	
}

.panel textarea, .well textarea, textarea.form-control
{
   resize: none;
}
  
.badge{
   color:#3B5999;
   background-color:#fff;
}
.badge:hover, .badge-inverse{
   background-color:#3B5999;
   color:#fff;
}
    
.jumbotron {
  background-color:transparent;
}
.label-default {
  background-color:#dddddd;
}
.page-header {
  margin-top: 55px;
  padding-top: 9px;
  border-top:1px solid #eeeeee;
  font-weight:700;
  text-transform:uppercase;
  letter-spacing:2px;
}

.panel-default .panel-heading {
  background-color:#f9fafb;
  color:#555555;
}

.col-sm-9.full {
    width: 100%;
}

.modal-header, .modal-footer {
	background-color:#f2f2f2;
    font-weight:800;
    font-size:12px;
}

.modal-footer i, .well i {
    font-size:20px;
    color:#c0c0c0;
}

.modal-body {
	padding:0px;
}

.modal-body textarea.form-control
{
   resize: none;
   border:0;
   box-shadow:0 0 0;
}

small.text-muted {
  font-family:courier,courier-new,monospace;
}

/* adjust the contents on smaller devices */
@media (max-width: 768px) {

  .column .padding {
    padding: 7px;
  }

  .full{
	padding-top:20px;
  }

  .navbar-blue {
	background-color:#3B5999;
    top:0;
    width:100%;
    position:relative;
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
    padding-left:3px;
  }
}
</style>


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
                                <div class="panel-thumbnail" align="center" style="margin-top: 30px;"><img src="/img/share/profile.png" width="250px" height="250px class="img-responsive"></div>
                                <div class="panel-body">
                                  <p class="lead">Urbanization</p>
                                  <p>45 Followers, 13 Posts</p>
                                  
                                  <p>
                                    <img src="https://lh3.googleusercontent.com/uFp_tsTJboUY7kue5XAsGA=s28" width="28px" height="28px">
                                  </p>
                                </div>
                              </div>

                           
                              <div class="panel panel-default">
                                <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Bootstrap Examples</h4></div>
                                  <div class="panel-body">
                                    <div class="list-group">
                                      <a href="http://bootply.com/tagged/modal" class="list-group-item">Modal / Dialog</a>
                                      <a href="http://bootply.com/tagged/datetime" class="list-group-item">Datetime Examples</a>
                                      <a href="http://bootply.com/tagged/datatable" class="list-group-item">Data Grids</a>
                                    </div>
                                  </div>
                              </div>
                           
                              <div class="well"> 
                                   <form class="form-horizontal" role="form">
                                    <h4>What's New</h4>
                                     <div class="form-group" style="padding:14px;">
                                      <textarea class="form-control" placeholder="Update your status"></textarea>
                                    </div>
                                    <button class="btn btn-primary pull-right" type="button">Post</button><ul class="list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
                                  </form>
                              </div>
                           
                              <div class="panel panel-default">
                                 <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>More Templates</h4></div>
                                  <div class="panel-body">
                                    <img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">Free @Bootply</a>
                                    <div class="clearfix"></div>
                                    There a load of new free Bootstrap 3 ready templates at Bootply. All of these templates are free and don't require extensive customization to the Bootstrap baseline.
                                    <hr>
                                    <ul class="list-unstyled"><li><a href="http://www.bootply.com/templates">Dashboard</a></li><li><a href="http://www.bootply.com/templates">Darkside</a></li><li><a href="http://www.bootply.com/templates">Greenfield</a></li></ul>
                                  </div>
                              </div>
                           
                             <!--  <div class="panel panel-default">
                                <div class="panel-heading"><h4>What Is Bootstrap?</h4></div>
                               	<div class="panel-body">
                                	Bootstrap is front end frameworkto build custom web applications that are fast, responsive &amp; intuitive. It consist of CSS and HTML for typography, forms, buttons, tables, grids, and navigation along with custom-built jQuery plug-ins and support for responsive layouts. With dozens of reusable components for navigation, pagination, labels, alerts etc..                          </div>
                              </div> -->

                           		
                           
                          </div>
                          
                          <!-- main col right -->
                          <div class="col-sm-7">
                               
                                <div class="well"> 
                                   <form class="form">
                                    <h4>친구찾기</h4>
                                    <div class="input-group text-center">
                                    <input type="text" class="form-control input-lg" placeholder="친구 ID">
                                      <span class="input-group-btn"><button class="btn btn-lg btn-primary" type="button">OK</button></span>
                                    </div>
                                  </form>
                                </div>
                      
                               <div class="panel panel-default">
                                 <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Bootply Editor &amp; Code Library</h4></div>
                                  <div class="panel-body">
                                    <p><img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">The Bootstrap Playground</a></p>
                                    <div class="clearfix"></div>
                                    <hr>
                                    Design, build, test, and prototype using Bootstrap in real-time from your Web browser. Bootply combines the power of hand-coded HTML, CSS and JavaScript with the benefits of responsive design using Bootstrap. Find and showcase Bootstrap-ready snippets in the 100% free Bootply.com code repository.
                                  </div>
                               </div>
                            
                               <div class="panel panel-default">
                                 <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Stackoverflow</h4></div>
                                  <div class="panel-body">
                                    <img src="//placehold.it/150x150" class="img-circle pull-right"> <a href="#">Keyword: Bootstrap</a>
                                    <div class="clearfix"></div>
                                    <hr>
                                    
                                    <p>If you're looking for help with Bootstrap code, the <code>twitter-bootstrap</code> tag at <a href="http://stackoverflow.com/questions/tagged/twitter-bootstrap">Stackoverflow</a> is a good place to find answers.</p>
                                    
                                    <hr>
                                    <form>
                                    <div class="input-group">
                                      <div class="input-group-btn">
                                      <button class="btn btn-default">+1</button><button class="btn btn-default"><i class="glyphicon glyphicon-share"></i></button>
                                      </div>
                                      <input type="text" class="form-control" placeholder="Add a comment..">
                                    </div>
                                    </form>
                                    
                                  </div>
                               </div>

                               <div class="panel panel-default">
                                 <div class="panel-heading"><a href="#" class="pull-right">View all</a> <h4>Portlet Heading</h4></div>
                                  <div class="panel-body">
                                    <ul class="list-group">
                                    <li class="list-group-item">Modals</li>
                                    <li class="list-group-item">Sliders / Carousel</li>
                                    <li class="list-group-item">Thumbnails</li>
                                    </ul>
                                  </div>
                               </div>
                            
                               <div class="panel panel-default">
                                <div class="panel-thumbnail"><img src="/assets/example/bg_4.jpg" class="img-responsive"></div>
                                <div class="panel-body">
                                  <p class="lead">Social Good</p>
                                  <p>1,200 Followers, 83 Posts</p>
                                  
                                  <p>
                                    <img src="https://lh6.googleusercontent.com/-5cTTMHjjnzs/AAAAAAAAAAI/AAAAAAAAAFk/vgza68M4p2s/s28-c-k-no/photo.jpg" width="28px" height="28px">
                                    <img src="https://lh4.googleusercontent.com/-6aFMDiaLg5M/AAAAAAAAAAI/AAAAAAAABdM/XjnG8z60Ug0/s28-c-k-no/photo.jpg" width="28px" height="28px">
                                    <img src="https://lh4.googleusercontent.com/-9Yw2jNffJlE/AAAAAAAAAAI/AAAAAAAAAAA/u3WcFXvK-g8/s28-c-k-no/photo.jpg" width="28px" height="28px">
                                  </p>
                                </div>
                              </div>
                            
                          </div>
                       </div><!--/row-->
                      
                        <div class="row">
                          <div class="col-sm-6">
                            <a href="#">Twitter</a> <small class="text-muted">|</small> <a href="#">Facebook</a> <small class="text-muted">|</small> <a href="#">Google+</a>
                          </div>
                        </div>
                      
                        <div class="row" id="footer">    
                          <div class="col-sm-6">
                            
                          </div>
                          <div class="col-sm-6">
                            <p>
                            <a href="#" class="pull-right">©Copyright 2013</a>
                            </p>
                          </div>
                        </div>
                      
                      <hr>
                      
                      <h4 class="text-center">
                      <a href="http://bootply.com/96266" target="ext">Download this Template @Bootply</a>
                      </h4>
                        
                      <hr>
                        
                      
                    </div><!-- /col-9 -->
                </div><!-- /padding -->
            </div>
            <!-- /main -->
          
        </div>
    </div>
</div>


<!--post modal-->
<div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			Update Status
      </div>
      <div class="modal-body">
          <form class="form center-block">
            <div class="form-group">
              <textarea class="form-control input-lg" autofocus="" placeholder="What do you want to share?"></textarea>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div>
          <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
            <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
		  </div>	
      </div>
  </div>
  </div>
</div>
</div>


		
	</div>

	<div style="min-height: 5%; background-color: FAF4C0;">
		<!-- html색상표 검색해서 사용 -->
		
<div align="center">
	<b>Copyright. Content Allrank find Taste&copy;</b>All Rights reserved.<br/>
</div>
	</div>

</body>

</html>