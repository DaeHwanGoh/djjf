<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="shortcut icon"
	href="https://assets.nflxext.com/us/ffe/siteui/common/icons/nficon2016.ico">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://codex.nflxext.com/%5E2.0.0/truthBundle/webui/0.0.1-0f9140ec/js/js/mdx2%7Cmdx2.js/2/gd02ga01fMfWfU0d0cfQ050rfSfK4pfRfO0ag9g6f-g04d4m4n25/k/true/none"></script>
<script src="https://www.gstatic.com/cv/js/sender/v1/cast_sender.js"></script>
<script
	src="https://assets.nflxext.com/en_us/ffe/player/html/cadmium-playercore-4.0006.294.011.js"
	id="player-core-js" async=""></script>
<script
	src="chrome-extension://pkedcjkdefgpdelpbcmbmeomcjbeemfm/cast_sender.js"></script>

<style>

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

</style>

<!-- 전체영역 -->
<div class="col-md-10" style="height: 400px;">
	<div id="myCarousel" class="carousel slide" data-ride="carousel"
		style="height: 400px;">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox" style="height: 400px;">
			<div class="item active">
				<img src="https://placehold.it/1200x400?text=IMAGE" alt="Image">
				<div class="carousel-caption">
					<h3>Sell $</h3>
					<p>Money Money.</p>
				</div>
			</div>

			<div class="item">
				<img src="https://placehold.it/1200x400?text=Another Image Maybe"
					alt="Image">
				<div class="carousel-caption">
					<h3>More Sell $</h3>
					<p>Lorem ipsum...</p>
				</div>
			</div>
		</div>

		<!-- Left and right 슬라이드버튼-->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>

	<!--순위리스트영역  -->
	<div class="row">
		<div class="col-md-6" style="background-color: #FBEFF8">
			<h2>hedding</h2>
		</div>
		<div class="col-md-6" style="background-color: #ECF8E0">
			<h2>hedding</h2>
		</div>
	</div>



	<div class="row">
		<div class="col-md-2">
      <div class="thumbnail">
        <a href="/w3images/lights.jpg" target="_blank">
          <img src="/w3images/lights.jpg" alt="Lights" style="width:100%">
          <div class="caption">
            <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
          </div>
        </a>
      </div>
    </div>
    <div class="col-md-2">
      <div class="thumbnail">
        <a href="/w3images/nature.jpg" target="_blank">
          <img src="/w3images/nature.jpg" alt="Nature" style="width:100%">
          <div class="caption">
            <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
          </div>
        </a>
      </div>
    </div>
    <div class="col-md-2">
      <div class="thumbnail">
        <a href="/w3images/fjords.jpg" target="_blank">
          <img src="/w3images/fjords.jpg" alt="Fjords" style="width:100%">
          <div class="caption">
            <p>Lorem ipsum donec id elit non mi porta gravida at eget metus.</p>
          </div>
        </a>
      </div>
    </div>
  </div>
	</div>

</div>

