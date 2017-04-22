<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="jquery-1.10.2.js"></script>
<!-- 사이즈 설정-->
<!-- 사이즈 설정-->

<script type="text/javascript">
	state = 0;
	function changeimage() {
		if (state == 0) {
			state = 1;
			document.getElementById('image').src = "/img/like2.jpg";
		} else {
			state = 0;
			document.getElementById('image').src = "/img/like1.jpg";
		}
	}
</script>





	<div class="row" style="margin-top: 20px; min-height:400px;">
		<div class="col-md-1"></div>
		<div class="col-md-4">
			<p></p>
			<!-- //영화정보(wide) -->
			<!-- 영화정보(basic) -->
			<div class="mv_info_area">
				<div class="mv_info">
					<h1 class="h_movie">
						<strong> ${map.tn } </strong>
					</h1>
					<h3>
						<strong class="h_movie2">${map.tn2 }</strong><br />
					</h3>

					<h5 style="line-height: 200%;">

						<c:forEach items="${map.all}" var="list">
							${list } <br />
						</c:forEach>

					</h5>
<!-- 					<p> -->
<!-- 						좋아요 : <img id="image" onclick="changeimage()" border="0" -->
<!-- 							src="/like1.jpg" style="width: 15px; height: 15px;" /> -->
<!-- 						 <a href="#"><img src="/like1.jpg" id=""></a>
<!-- 							<a href="#"><img src="/like2.jpg"></a>  --> 
<!-- 					</p> -->

				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
		<div class="col-md-2">
			<div class="poster" style="position:absolute;left: -30; top: 10;">
				<img src="${map.mpos }" height="350px" width="300px" />
			</div>
		</div>
	</div>





	<div class="row" role="pillpanel"
		style="border-style: outset; margin-top: 40px;">

		<ul class="nav nav-pills" style="margin: auto; width: 500px;"
			role="pilllist">
			<li role="presentation" class="active" style="display: inline-block;"><a
				href="#all" aria-controls="pill1" role="pill" data-toggle="pill"><em>주요
						정보</em></a></li>
			<li role="presentation"><a href="#acts" aria-controls="pill2"
				role="pill" data-toggle="pill"><em>배우 / 제작진</em></a></li>
			<li role="presentation"><a href="#photo" aria-controls="pill3"
				role="pill" data-toggle="pill"><em>포토</em></a></li>
			<li role="presentation"><a href="#review" aria-controls="pill4"
				role="pill" data-toggle="pill"><em>평점 / 리뷰</em></a></li>
		</ul>


		<div class="col-md-1"></div>
		<div class="col-md-10" style="margin-bottom: 50px;">
			<hr style="border: solid 3px;" />
			<div class="tab-content">
				<div role="pillpanel" class="tab-pane fade in active" id="all">
					<h2>
						<strong>줄거리</strong>
					</h2>
					<h4>
						<strong>${map.mainstory }</strong>
					</h4>
					<hr />
					<p>${map.detailstory }</p>

				</div>
				<div role="pillpanel" class="tab-pane fade " id="acts" >
					<h2>
						<strong>배우/제작진</strong>
					</h2>
						<hr/>
					<div align="center">
						<c:forEach items="${map.peos}" var="list">
							<div
								style="display: inline-block; border: 1px solid silver; height: auto; width: auto;">
								<p>
									<img src="${list.photo }" height="139px" width="111px" />
								</p>
								<div>
									<strong>${list.name }</strong> <em></em>
									<hr style="margin: 0;"/>
									<p>
										<em>${list.part }</em>|<span>${list.role }</span>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				
				
				<div role="pillpanel" class="tab-pane fade" id="photo">
						<h2>
							<strong>포토</strong>
						</h2>
					<div>
						<ul style="position: relative; height: auto;">
							<c:forEach items="${map.photos}" var="list" >
								
									<li style="display: inline-block; border: 1px solid silver; height: auto; width: auto;">
										<p>
											<img src="${list }" width="221" height="147" />
										</p>
									</li>
								
							</c:forEach>
						</ul>
						
					</div>
				
				
				</div>
					
				<div role="pillpanel" class="tab-pane fade" id="review">리뷰</div>
			</div>
			<hr style="border: solid 3px;" />
		</div>
		<div class="col-md-1"></div>

	</div>



