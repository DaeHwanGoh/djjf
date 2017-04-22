<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- jQuery library (served from Google) -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<!-- bxSlider Javascript file -->
<script src="/js/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">

	<h2>
		<strong>조회순</strong>
	</h2>
	<hr/>

	<div class="row" style="height:">
		<div class="col-md-1">
			<br /> <br /> <br /> <br /> <span id="slider-prev"
				style="font-size: 2em;"></span>
		</div>
		<div class="col-md-10" align="center">

			<ul class="bxslider">
				<!-- 몽고 DB 에서 정렬먹여서 순서대로 가져오기, 이미지 소스도 가져오기  -->

				<!-- 		   <header> 공포/ 스릴러</header> -->
				<li>
					<div class="rank1" align="center">
						
					</div>
				</li>
				<!-- 2페이지 -->
				<li>
					<div class="rank2">
					</div>

				</li>
			</ul>
		</div>
		<div class="col-md-1" align="right">
			<br /> <br /> <br /> <br /> <span id="slider-next"
				style="font-size: 2em;" ></span>
		</div>

	</div>
	<!-- row  -->



	<div id="bx-pager">
		<a data-slide-index="0" href=""></a> <a data-slide-index="1" href=""></a>
		<a data-slide-index="2" href=""></a>
	</div>

	<div class="row">
		<hr style="margin-top: 5px; margin-bottom: 5px;" />
	</div>



	
	<h2>
		<strong>평점순</strong>
	</h2>
	<hr/>
	<!--  연령과, 성별로 조회한 즐겨찾기 많은 순으로 랭킹   -->
	<div class="container" style="position:relative; width:100%; min-height:300px; overflow-y:scroll; ">
	<div role="pillpanel" class="tab-pane" id="photo">
					<div>
						<ul style=" position:absolute;height: auto; left:4%;">
							<c:forEach var="i" begin="0" end="19">
								<a href='/detail/print?code=${data1[i].link }'>
									<li style="display: inline-block; border: 1px solid silver; height: auto; width: auto;">
										<p>
											<img src="${data1[i].image }" width="221" height="147" />
										</p>
									</li>
								</a>
							</c:forEach>
						</ul>
						
					</div>
				
				
				</div>
		</div>
	

</div>
<!-- 전체 col-md-10 한 것 -->

<script>	
	
	$(document).ready(function() {
		getData();
		$('.bxslider').bxSlider({

			nextSelector : '#slider-next',
			prevSelector : '#slider-prev',
			nextText : '>',
			prevText : '<',

			moveSlides : 1,
			minSlides : 1,
			maxSlides : 1,
			slideWidth : 1100,
			slideMargin : 10,
			mode : 'horizontal',
			captions : true,
			pagerCustom : '#bx-pager'
		});

	});
	function getData(){
			<c:forEach var="i" begin="0" end="7">
			var s="<a href='/detail/print?code=${data[i].link }'> ";
				s+="<img src='${data[i].image }' width=130px height=214.8px/>";
				s+="</a>";
			$(".rank1").append(s);
			</c:forEach>
			<c:forEach var="i" begin="8" end="15">
			var s="<a href='/detail/print?code=${data[i].link }'> ";
				s+="<img src='${data[i].image }' width=130px height=214.8px/>";
				s+="</a>";
			$(".rank2").append(s);
			</c:forEach>
		
	}
</script>
