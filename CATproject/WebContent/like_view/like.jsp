<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="col-md-10" style="max-height: 100%; overflow-y:scroll;">
<div class="row">
<p>
<h3>찜한 게시물</h3>
<b>${id }님</b>이 찜한게시물${map.like_total }개가 검색되었습니다.<br/>
<hr>
	<div class="container">
	  <div class="media">
	  
	 <c:forEach var="i" begin="0" end="${map.like_total }" items="${map.likedata }">
	    <div class="media-left">
	    <a href="/detail/print?code=${i.lmcode }"><img src="${i.lmurl }" class="media-object" style="width:67px; height: 95px; "></a>
	    </div>
	    <div class="media-body">
	     <h4 class="media-heading"> <a href="/detail/print?code=${i.lmcode }">${i.lmname }</a></h4>
	      <p> <a href="/detail/print?code=${i.lmcode }">${i.lmgenre }</a></p>
	    </div>
	    <hr>
	  </c:forEach>
	  </div>
	</div>
	</div>
<div class="row">
<p>
<h3>review단 게시물</h3>
<b>${id }님</b>이 review단 게시물${map.review_total }개가 검색되었습니다.<br/>
<hr>
	<div class="container">
	  <div class="media">
	 <c:forEach var="i" begin="0" end="${map.review_total}" items="${map.reviewdata}">
	    <div class="media-left">
	    <a href="/detail/print?code=${i.code }"><img src="${i.img }" class="media-object" style="width:67px; height: 95px; "></a>
	    </div>
	    <div class="media-body">
	     <h4 class="media-heading"> <a href="/detail/print?code=${i.code }">${i.tn }/${i.date }</a></h4>
	     <hr>
			${i.comment }     
	    </div>
	    <hr>
	 </c:forEach> 
	  </div>
	</div>
	</div>
</div>
