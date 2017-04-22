<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 사이즈 설정-->
<!-- 사이즈 설정-->



<!--  
<style type="text/css">

</style>
-->
<!--  
<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
-->
<!--  
<div class="col-md-10" style="margin-top: 50px;">-->
<div class="col-md-10" style="max-height: 100%; overflow-y:scroll; ">
	<div class="row" style="margin-top: 50px;">
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
	
					<hr style="border:black 1px solid;"/>

					<h5 style="line-height: 200%;">

						<c:forEach items="${map.all}" var="list">
						<h5>
							${list } <br />
						</h5>
						</c:forEach>

					</h5>
					<p>
						<c:choose>
									<c:when test="${sessionScope.auth eq null}">
										좋아요 : <img id="image1" onclick="gotoLogin()" border="0"
										src="/like1.jpg" style="width: 15px; height: 15px;" />
									</c:when>
									
									<c:otherwise> 
										<c:choose>
											<c:when test="${like eq false}">
												좋아요 : <img id="image" onclick="changeimage()" border="0"
												src="/like1.jpg" style="width: 50px; height: 50px;" />
											</c:when>
											<c:otherwise>
												좋아요 : <img id="image2"  border="0"
												src="/like2.jpg" style="width: 50px; height: 50px;" />
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
						<!--  
								좋아요 : <img id="image" onclick="changeimage()" border="0"
								src="/like1.jpg" style="width: 15px; height: 15px;" />
									  <a href="#"><img src="/like1.jpg" id=""></a>
									<a href="#"><img src="/like2.jpg"></a>  
							-->
						
					</p>
					
					
					<script type="text/javascript">
						state = 0;
						function changeimage() {
							if (state == 0) {
								state = 1;
								document.getElementById('image').src = "/like2.jpg";
								$.ajax({
									"url" : "/detail/like",
									"method" : "post",
									"data" : {
										"code" : ${code },
										"tn": "${map.tn}",
										"img": "${map.mpos}",
										"genre" : "${map3.genre}"
									}
								});
							}/* else {
								state = 0;
								document.getElementById('image').src = "/like1.jpg";
							}*/
						}
					</script>
					
					
				</div>
			</div>
		</div>
		<div class="col-md-2"></div>
		<div class="col-md-2">
			<div class="poster">
				<img src="${map.mpos }" height="350px" width="300px" />
			</div>
		</div>
	</div>





	<div class="row" role="pillpanel"
		style="border-style: outset; margin-top: 40px;">

		<ul class="nav nav-pills" style="margin: auto; width: 500px;"
			role="pilllist">
			<li role="presentation" class="active" style="display: inline-block;"><a
				href="#all" aria-controls="pill1" role="pill" data-toggle="pill"><em>스토리</em></a></li>
			<li role="presentation"><a href="#acts" aria-controls="pill2"
				role="pill" data-toggle="pill"><em>배우 / 제작진</em></a></li>
			<li role="presentation"><a href="#photo" aria-controls="pill3"
				role="pill" data-toggle="pill"><em>포토</em></a></li>
			<li role="presentation"><a href="#review" aria-controls="pill4" id="pill4"
				role="pill" data-toggle="pill"><em>리뷰</em></a></li>
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
					<hr style="border:black 1px dashed"/>
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
										<strong><em>${list.part }</em></strong> | <span>${list.role }</span>
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
					
					
					
				<div role="pillpanel" class="tab-pane fade" id="review">
					<h2>
						<strong>리뷰</strong>
					</h2>
					
					<div>
						<div align="center">
							
								<c:choose>
									<c:when test="${sessionScope.auth eq null}">
										댓글 : <input type="text" id="cmt1" style="width: 50%" /> 
										<input type="button" value="입력" id="createbt1" onclick="gotoLogin()" />
									
									</c:when>
									<c:otherwise>
										댓글 : <input type="text" id="cmt" style="width: 50%" /> 
										<input type="button" value="입력" id="createbt" onclick=""/>
									</c:otherwise>
								
								</c:choose>
								<script>
									function gotoLogin(){
										if(confirm('로그인이 필요합니다. 로그인페이지로 이동하시겠습니까?')){
											location.href="/log/login";
										}
											
									}
								</script>
							<!--  
								댓글 : <input type="text" id="cmt" style="width: 50%" /> 
								<input type="button" value="입력" id="createbt" onclick=""/>
							 -->
						</div>
						<hr style="border:black 1px solid;"/>
						<div>
							<c:forEach items="${li }" var="list" >
									<div class="well">
										<div class="media-left">
											<img src="${list.picurl }" height="139px" width="111px" />
										</div>
										<div class="media-body">
    										<h4 class="media-heading" align="left">작성자 : ${list.id }  |  ${list.date }</h4>  
    										<hr style="border:black 1px dashed"/>
										    <h4>${list.comment }</h4>
  										</div>
									</div>
								<hr/>
							</c:forEach>
							
							<!-- 페이지처리 -->
							<div align="center">
								<c:if test="${page ne 1 }">
									<a href="/detail/print?code=${code}&mode=review&page=${page -1 }">이전</a>
								</c:if>
								<c:forEach var="p" begin="1" end="${size }" varStatus="vs">
									<c:choose>
										<c:when test="${p eq page }">
											<b style="color: red;">${p }</b>
										</c:when>
										<c:otherwise>
											<a href="/detail/print?code=${code}&mode=review&page=${p }">${p }</a>
										</c:otherwise>
									</c:choose>
									<c:if test="${vs.last eq false }">|</c:if>
								</c:forEach>
								<c:if test="${page ne size }">
									<a href="/detail/print?code=${code}&mode=review&page=${page +1 }">다음</a>
								</c:if>
							</div>


						</div>
					</div>
						<div id="cmtTarget">
					</div>
					
					
					
					<script>
					/*
					$("#cmt").on('click', function() {
						if(${list.id }==null){
							
						}else
							
					});
					*/
										
					$("#createbt").on('click', function() {
						var t = $("#cmt").val();
						/* if (t.length == 0) {
							$("#autoView").html("");
							return;
						} */
						
						$.ajax({
							"url" : "/detail/print/review",
							"method" : "post",
							"data" : {
								"comment" : $("#cmt").val(),
								"code": ${code},
								"tn": "${map.tn}",
								"img": "${map.mpos}"
							}
						}).done(function(rst) {
							if(rst=="YYYYY") {
								location.href="/detail/print?code=${code}&mode=review";
							}else {
								
							}

						}); //ajax
					}); //srch함수
					<c:if test="${param.mode eq 'review'}">
					   $("#pill4").trigger("click");
					</c:if>
					
					</script>
					
					<!--  
					<div>
						<div>
							<form action="/detail/addNew" method="get">
								
								<p class="form-inline">
									<input type="text" class="form-control" name="writer"
										placeholder="작성자" />
								</p>
								<p>
									<textarea rows="3" cols="83" name="content"
										class="form-control" placeholder="남길 내용"></textarea>
								</p>
								<p style="text-align: right; font-size: 14px;">

									<button type="submit" id="send" class="btn">작성하기</button>
								</p>
							</form>
						</div>

					</div>
					-->
					
			





					<!--  
					 <div align="center" id="replyDiv">
						<div id="formWrapper">
							<form id="writeReplyForm">
								<input type="hidden" id="articleId" name="articleId"
									value="${article.articleId}" /> <input type="hidden"
									id="depth" name="depth" value="0" /> <input type="hidden"
									id="parentReplyId" name="parentReplyId" value="0" /> <input
									type="hidden" id="groupId" name="groupId" value="0" /> <input
									type="hidden" id="orderNo" name="orderNo" value="0" /> <input
									type="hidden" id="replyId" name="replyId" value="0" />

								<textarea id="descrption" name="description"
									style="width: 800px;"></textarea>
								<input type="button" id="writeReplyBtn" value="등록" />
							</form>
						</div>

					-->



						<!--  
					<div class="reviewfull">
						<div class="reviewsub">
							<form id="reviewarea" style="height: 63px; width: 703px; margin-bottom: 0px;">
								<fieldset>
									<ul class="review_text" style="height: 63px; width: 703px; padding-left: 0px; margin-bottom: 0px;">
										<li class="input_textarea" style="height: 63px;width: 636px;padding-top: 1px;padding-bottom: 1px;">
											<textarea id="ment" name="ment" row="1" cols="1" rows="1"  class="input_tx" maxlength="150" style="height: 61px; width: 636px;"></textarea>
											<p class="tx_length">
												<em id="ment_cnt">0</em>
												"/150"
											</p>
										</li>
										<li style="width:66px;">
											 <img id="commit"  
												src="/commit.jpg" style="width: 66px; height: 63px;" />
										</li>
									</ul>		
								</fieldset>
							</form>
						</div>
					</div>
				-->


				
			</div>
			<hr style="border: solid 3px;" />
		</div>
		<div class="col-md-1"></div>

	</div>


</div>
</div>


<!-- 사이즈 설정-->

<!-- 사이즈 설정-->

