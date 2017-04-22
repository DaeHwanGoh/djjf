<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- jQuery library (served from Google) -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<!-- bxSlider Javascript file -->
<script src="/js/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.carousel-inner>.item>.container, .carousel-inner>.item>a>img {
   width: 70%;
   margin: auto;
}

.thumbnail {
   margin-bottom: 0;
}
</style>
<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
	<h3 style="margin-top: 10px; margin-bottom: 0px; color: gray; font-family: 나눔고딕;"><span style="color: brown; font-weight: bold;">${id }</span>님께 추천드리는 영화</h3>
	<h5><span style="color: red;  font-weight: bold;">${age-age%10}</span>대 <span style="color: red;  font-weight: bold;">${gender eq 'male'?'남성':'여성'}</span>이 가장 많이 조회한 장르별 작품</h5>

	<div class="row" style="height:">
		<div class="col-md-1">
			<br /> <br /> <br /> <br /> <span id="slider-prev"
				style="font-size: 2em;"></span>
		</div>
		<div class="col-md-10" align="center">
			
			<ul class="bxslider">
				<!-- 몽고 DB 에서 정렬먹여서 순서대로 가져오기, 이미지 소스도 가져오기  -->

<!-- 공포/스릴러  -->
				<li>
					<div>
					<h4>공포/스릴러/액션</h4>
					<!-- 반복문  -->
					<!-- /detail/print?code=영화 코드-->
						<c:forEach var="i" begin="0" end="4">
							<a href="/detail/print?code=${li1.get(i).get("vmcode")}">
								<img
								src=${li1.get(i).get("vmurl")}
								width=150px height=214.8px title="1위~5위" />
							</a>
							

						
						</c:forEach>
						<!-- 	원래 방식						<a href="http://movie.naver.com/movie/bi/mi/basic.nhn?code=144927">
								<img
								src="http://movie.phinf.naver.net/20170207_225/1486428081012r6FQr_JPEG/movie_image.jpg"
								width=150px height=214.8px title="1위~5위" />
							</a> -->
							
						
					</div>
				</li>
				
<!--SF/판타지( 2페이지 )  -->		
				<li>
					<div>
					<h4>SF/판타지</h4>
					<!-- 반복문  -->
					<!-- /detail/print?code=영화 코드-->
						<c:forEach var="i" begin="0" end="4">
							<a href="/detail/print?code=${li2.get(i).get("vmcode")}">
								<img src=${li2.get(i).get("vmurl")}	width=150px height=214.8px title="1위~5위" />
							</a>
						</c:forEach>
					</div>
					
				</li>
<!--드라마/로맨스/코미디( 3페이지 )  -->					
				<li>
					<div>
					<h4>드라마/로맨스/코미디</h4>
					<!-- 반복문  -->
					<!-- /detail/print?code=영화 코드-->
						<c:forEach var="i" begin="0" end="4">
							<a href="/detail/print?code=${li3.get(i).get("vmcode")}">
								<img src=${li3.get(i).get("vmurl")}	width=150px height=214.8px title="1위~5위" />
							</a>
						</c:forEach>
					</div>
					
				</li>
<!--가족/아동/애니메이션( 4페이지 )  -->					
				<li>
					<div>
					<h4>가족/아동/애니메이션</h4>
					<!-- 반복문  -->
					<!-- /detail/print?code=영화 코드-->
						<c:forEach var="i" begin="0" end="4">
							<a href="/detail/print?code=${li4.get(i).get("vmcode")}">
								<img src=${li4.get(i).get("vmurl")}	width=150px height=214.8px title="1위~5위" />
							</a>
						</c:forEach>
					</div>
					
				</li>				

<!-- 페이지 끝 -->				
			</ul>
		</div>
		<div class="col-md-1">
			<br /> <br /> <br /> <br /> <span id="slider-next"
				style="font-size: 2em;"></span>
		</div>

	</div>
	<!-- row  -->



	<div id="bx-pager">
		<a data-slide-index="0" href=""></a> 
		<a data-slide-index="1" href=""></a>
		<a data-slide-index="2" href=""></a>
	</div>
	
<!-- 구분선 -->
	<div class="row">
		<hr style="margin-top: 5px; margin-bottom: 5px;"/>
	</div>

	<!--  콤보박스   -->
	<div class="row">
		<div class="col-md-4">
		<form action="/recommend/reco_main" method="get">  <!-- 조회버튼 누르면 바뀌게  -->
		<b>조회할 연령대/성별 선택</b><br/>

			<div class="col-md-4">		
				<select class="form-control" name="sage">
				<c:forEach var="i" begin="10" end="60" step="10">
					<option value="${i}" ${i eq sage-sage%10? 'selected': '' } >${i }대</option>					
				</c:forEach>
					
				</select>
			</div> 
			
			<div class="col-md-4">	
				<select name="sgender" class="form-control">
					<option value="male" ${sgender eq 'male' ? 'selected' : '' }>남</option>
					<option value="female" ${sgender eq 'female' ? 'selected' : '' }>여</option>
				</select>
			</div> 
		
			<div class="col-md-4">	
				<button type="submit" class="btn">조회</button> <!--ajax로 처리  -->
			</div> 	
			
		</form>		
		</div>	

	</div>

<!-- 구분선2 -->
	<div class="row">
		<hr style="margin-top: 5px; margin-bottom: 5px;"/>
	</div>
	
	
<!--  연령과, 성별로 조회한 조회수 많은 순으로 랭킹   -->
	 
	<div class="row">
		<div class="col-md-6" style="border-right-style: solid; height: ">
			<h3 style="margin-top: 0px; margin-bottom: 5px; color: gray; font-family: 나눔고딕;">
				<span style="color: pink; font-weight: bold;  font-size: large; ">${sage-sage%10 }</span> <span style="font-size: large;">대 </span>
				<span style="color: pink; font-weight: bold; font-size: large;">${sgender eq 'male'?'남성':'여성'}</span>&nbsp;조회수 랭킹</h3>
			
			<!-- 슬라이더 들어가기  -->
				<div class="row">

				
				<div align="center">
					
					<ul class="bxslider2">
						<li>
							<div>
							<!-- 1 페이지. 총 6개 svlist1 -->
							
								<div class="row" >
								<!-- 3개 반복 1위~3위 -->
								<c:forEach var="i" begin="0" end="2">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="color: red; font-weight: bold; font-size: 2em;">&nbsp;&nbsp;${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>									
								
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 4위~6위 -->
								<c:forEach var="i" begin="3" end="5">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">&nbsp;&nbsp;${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>								
																									
								</div>
								
							</div>
						</li>
						
						<!-- 2페이지 총 6개 svlist1 -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 7위~9위 -->
								<c:forEach var="i" begin="6" end="8">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">&nbsp;&nbsp;${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>									
								
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 10위~12위 -->
								<c:forEach var="i" begin="9" end="11">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>	
																	
								</div>
								
							</div>
							
						</li>
						
						<!-- 3페이지 총 6개 svlist1  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 13위~15위 -->								
								<c:forEach var="i" begin="12" end="14">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>	
																
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 16위~18위 -->		
								<c:forEach var="i" begin="15" end="17">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>																		
									
								</div>
								
							</div>
							
						</li>
												
						<!-- 4페이지 총 6개 svlist1  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 19위~21위 -->		
								<c:forEach var="i" begin="18" end="20">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>
																
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 22위~24위 -->			
								<c:forEach var="i" begin="21" end="23">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>									
									
								</div>
								
							</div>
							
						</li>						
						
						
						<!-- 5페이지 총 6개 svlist1  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 25위~27위 -->	
								<c:forEach var="i" begin="24" end="26">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>																	
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 28위~30위 -->	
								<c:forEach var="i" begin="27" end="29">
									<a href="/detail/print?code=${svlist1.get(i).get("vmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${svlist1.get(i).get("vmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>	
								</div>
								
							</div>
							
						</li>
						
						<!--  -->												
						
					</ul>
				</div>
			</div>				
				<div class ="row" align="center">
					<div class="col-md-5"  align="right">
						<span id="slider-prev2"
							style="font-size: 1em;"></span>
					</div>
					
					<div id="bx-pager2" class="col-md-2" >
						<a data-slide-index="0" href="">1</a> 
						<a data-slide-index="1" href="">2</a>
						<a data-slide-index="2" href="">3</a>
						<a data-slide-index="3" href="">4</a>
						<a data-slide-index="4" href="">5</a>								
					</div>
				
				
					<div class="col-md-5"  align="left">
						<span id="slider-next2"
							style="font-size: 1em;"></span>
					</div>
				
				</div>	<!-- row  -->						
		</div> <!-- col-md-6의 /div -->	
		
		
<!--  연령과, 성별로 조회한 즐겨찾기 많은 순으로 랭킹   -->		
	

				
		<div class="col-md-6">
			<h3 style="margin-top: 0px; margin-bottom: 5px; color: gray; font-family: 나눔고딕;">
				<span style="color: pink; font-weight: bold;  font-size: large; ">${sage-sage%10 }</span> <span style="font-size: large;">대 </span>
				<span style="color: pink; font-weight: bold; font-size: large;">${sgender eq 'male'?'남성':'여성'}</span>&nbsp;좋아요 랭킹</h3>
							
			<!-- 슬라이더 들어가기  -->
					
			
			<!-- 슬라이더 들어가기  -->
				<div class="row">

				
				<div align="center">
					
					<ul class="bxslider3">
					<!-- 1페이지 총 6개 sllist2  -->
						<li>	
							<div>
								<div class="row">
								<!-- 3개 반복 1위~3위 -->	
								
								<c:forEach var="i" begin="0" end="2">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="color: red; font-weight: bold; font-size: 2em;">&nbsp;&nbsp;${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>	
								
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 4위~6위 -->
								<c:forEach var="i" begin="3" end="5">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">&nbsp;&nbsp;${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>
														
								</div>
								
							</div>
						</li>
						
						<!-- 2페이지 총 6개 sllist2  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 7위~9위 -->
								<c:forEach var="i" begin="6" end="8">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">&nbsp;&nbsp;${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>								
								
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 10위~12위 -->
								
								<c:forEach var="i" begin="9" end="11">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>								
																	
								</div>
								
							</div>
							
						</li>
						
						<!-- 3페이지 총 6개 sllist2  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 13위~15위 -->
								<c:forEach var="i" begin="12" end="14">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>	
																
																
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 16위~18위 -->
								<c:forEach var="i" begin="15" end="17">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>																	
									
								</div>
								
							</div>
							
						</li>
												
						<!-- 4페이지 총 6개 sllist2  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 19위~21위 -->
								<c:forEach var="i" begin="18" end="20">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>	
																
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 22위~24위 -->
								<c:forEach var="i" begin="21" end="23">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>			
									
								</div>
								
							</div>
							
						</li>						
						
						
						<!-- 5페이지 총 6개 sllist2  -->
						<li>
							<div>
								<div class="row">
								<!-- 3개 반복 25위~27위 -->
								<c:forEach var="i" begin="24" end="26">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>
																
								</div>
								
								<div class="row" style="margin-top: 5px">
								<!-- 3개 반복 28위~30위 -->
								<c:forEach var="i" begin="27" end="29">
									<a href="/detail/print?code=${sllist2.get(i).get("lmcode")}">
										<span style ="font-weight: bold; font-size: 2em;">${i+1}</span>위
										<img src=${sllist2.get(i).get("lmurl")}	width=112.5px height=161.1px />
									</a>															  
								</c:forEach>									
									
								</div>
								
							</div>
							
						</li>
						
						<!--  -->												
						
					</ul>
				</div>
			</div>				
				<div class ="row" align="center">
					<div class="col-md-5"  align="right">
						<span id="slider-prev3"
							style="font-size: 1em;"></span>
					</div>
					
					<div id="bx-pager3" class="col-md-2" >
						<a data-slide-index="0" href="">1</a> 
						<a data-slide-index="1" href="">2</a>
						<a data-slide-index="2" href="">3</a>
						<a data-slide-index="3" href="">4</a>
						<a data-slide-index="4" href="">5</a>								
					</div>
				
				
					<div class="col-md-5"  align="left">
						<span id="slider-next3"
							style="font-size: 1em;"></span>
					</div>
				
				</div>	<!-- row  -->						
		</div> <!-- col-md-6의 /div -->	
		

	</div> <!--  row -->
	<!--  연령과, 성별로 조회한 즐겨찾기 많은 순으로 랭킹   -->

</div>
<!-- 전체 main 부 col-md-10 한 것 -->


<script>
//전체슬라이더
	$(document).ready(function() {
		$('.bxslider').bxSlider({

			nextSelector : '#slider-next',
			prevSelector : '#slider-prev',
			nextText : '>',
			prevText : '<',

			moveSlides : 1,
			minSlides : 1,
			maxSlides : 1,
			slideWidth : 1000,
			slideMargin : 10,
			mode : 'horizontal',
			captions : true,
			pagerCustom : '#bx-pager'
		});

	});

//조회순 랭킹 슬라이더
	$(document).ready(function() {
		$('.bxslider2').bxSlider({

			nextSelector : '#slider-next2',
			prevSelector : '#slider-prev2',
			nextText : '>',
			prevText : '<',

			moveSlides : 1,
			minSlides : 1,
			maxSlides : 1,
			slideWidth : 2000,
			slideMargin : 200,
			mode : 'horizontal',
			captions : true,
			pagerCustom : '#bx-pager2'
		});

	});	
	
//즐겨찾기순 랭킹 슬라이더	
	$(document).ready(function() {
		$('.bxslider3').bxSlider({

			nextSelector : '#slider-next3',
			prevSelector : '#slider-prev3',
			nextText : '>',
			prevText : '<',

			moveSlides : 1,
			minSlides : 1,
			maxSlides : 1,
			slideWidth : 2000,
			slideMargin : 200,
			mode : 'horizontal',
			captions : true,
			pagerCustom : '#bx-pager3'
		});

	});	
</script>
