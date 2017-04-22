<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
	<h3>검색결과</h3>
	${map.total } 개가 검색됬습니다
	<c:forEach var="i" begin="${start }" end="${end }">
		<div class="well row">
			<div class="col-md-3" align="center">
				<a href="/detail/print?code=${map.items[i].link }"> <c:choose>
						<c:when test="${map.items[i].image eq '' }">
							<img src="/img/dft_img.png" height="95" width="67" />
						</c:when>
						<c:otherwise>
							<img src="${map.items[i].image }" height="95" width="67" />
						</c:otherwise>
					</c:choose>
				</a> <br />
			</div>
			<div class="col-md-9" align="left">
				<a href="/detail/print?code=${map.items[i].link }"><b>제목:</b> ${map.items[i].title }&nbsp;</a> <b>서브제목:</b>
				${map.items[i].subtitle } <br /> <b>별점:</b>
				${map.items[i].userRating } &nbsp; <b>제작년도:</b>
				${map.items[i].pubDate } <br /> <b>감독:</b>
				<c:forEach var="director" items="${map.items[i].director }"
					varStatus="vs">
							${director }
							<c:if test="${!vs.last}">,</c:if>
				</c:forEach>
				<br /> <b>배우:</b>
				<c:forEach var="actor" items="${map.items[i].actor }" varStatus="vs">
							${actor }
							<c:if test="${!vs.last}">,</c:if>
				</c:forEach>
				<br />
			</div>
		</div>
	</c:forEach>
	<!-- 페이지처리 -->
<div align="center">
	<c:if test="${page ne 1 }">
			<a href="/search/01?title=${param.title}&page=${page -1 }">이전</a>
	</c:if>
	<c:forEach var="p" begin="1" end="${size }" varStatus="vs">
		<c:choose>
			<c:when test="${p eq page }">
				<b style="color: red;">${p }</b>
			</c:when>
			<c:otherwise>
				<a href="/search/01?title=${param.title}&page=${p }">${p }</a>
			</c:otherwise>
		</c:choose>
		<c:if test="${vs.last eq false }">|</c:if>
	</c:forEach>
	<c:if test="${page ne size }">
			<a href="/search/01?title=${param.title}&page=${page +1 }">다음</a>
	</c:if>
</div>
</div>




