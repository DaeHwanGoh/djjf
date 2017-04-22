<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-2 sidenav"
	style="background-color: #767b7d;  height: 100%; padding: 0;">
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="#section1"
			style="border-radius: 0px; background-color: #a7abad; text-align: center;">M
				O V I E</a></li>
		<div style="text-align: right;">
			<li><a href="/view22?tg=4,7,19"  
				style="border-radius: 0px; color: #f4f6f9;">공포/스릴러/액션&nbsp;</a></li>
			<li><a href="/view22?tg=2,18"
				style="border-radius: 0px; color: #f4f6f9;">SF/판타지&nbsp;</a></li>
			<li><a href="/view22?tg=1,5,11"
				style="border-radius: 0px; color: #f4f6f9;">드라마/로맨스/코미디&nbsp;</a></li>
			<li><a href="/view22?tg=12,15"
				style="border-radius: 0px; color: #f4f6f9;">가족/아동/애니메이션&nbsp;</a></li>
		</div>


		
<%
	String auth = (String) session.getAttribute("auth"); //yes or no yes면 쿠키 갖고 있음
%>
		<c:choose>
			<c:when test="${sessionScope.auth ne null }">
			
			<li class="active"><a href="/recommend/reco_main"
				style="border-radius: 0px; background-color: #a7abad; text-align: center;">추
					천 작</a></li>
			<li class="active"><a href="#section1"
				style="border-radius: 0px; background-color: #a7abad; text-align: center;">관심
					CONTENT</a></li>
	
			<div style="text-align: right;">
				<li><a href="/like/likedata"
					style="border-radius: 0px; color: #f4f6f9;">좋아요 CONTENT 목록&nbsp;</a></li>
				<li><a href="/post/write"
					style="border-radius: 0px; color: #f4f6f9;">포스트 작성&nbsp;</a></li>
				<li><a href="/mypage/main"
					style="border-radius: 0px; color: #f4f6f9;">마이페이지&nbsp;</a></li>
				<li><a href="/post/newsFeed"
					style="border-radius: 0px; color: #f4f6f9;">뉴스피드&nbsp;</a></li>
			</div>
								
			<div style="text-align: center; margin-top: 20%; max-height: 60%;">
				<li><a style="border-radius: 0px; color: #f4f6f9;"><hr/></a></li>
				<li><a href="/auth/show"
					style="border-radius: 0px; color: #f4f6f9;">권한설정</a></li>
				<li><a href="/my/info"
					style="border-radius: 0px; color: #f4f6f9;">개인정보(프로필)</a></li>
				<li><a href="/my/leave"
					style="border-radius: 0px; color: #f4f6f9;">회원탈퇴</a></li>
			</div>
			
			</c:when>

			<c:otherwise>  <!-- 손님 상태이면-->
		
			<li class="active"><a href="/log/login"
				style="border-radius: 0px; background-color: #a7abad; text-align: center;">추
					천 작</a></li>
			<li class="active"><a href="/log/login"
				style="border-radius: 0px; background-color: #a7abad; text-align: center;">관심
					CONTENT</a></li>
	
			<div style="text-align: right;">
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">좋아요 CONTENT 목록&nbsp;</a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">포스트 작성&nbsp;</a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">마이페이지&nbsp;</a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">뉴스피드&nbsp;</a></li>
			</div>
								
			<div style="text-align: center; margin-top: 20%; max-height: 60%;">
				<li><a style="border-radius: 0px; color: #f4f6f9;"><hr/></a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">개인정보(프로필)</a></li>
				<li><a href="/log/login"
					style="border-radius: 0px; color: #f4f6f9;">회원탈퇴</a></li>
			</div>
								
							
			</c:otherwise>		
		</c:choose>
	</ul>
	<br>
</div>


