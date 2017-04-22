<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- {date=1490955619455, comment=알라알라코알랑, id=namnami, pictureUrl=/picture/profile.png}
 --><!-- 댓글 -->
<c:forEach var="i" items="${comentlist }">
	<div class='media'>
		<div class='media-left media-top'>
			<img src='${i.pictureUrl}' class='media-object' style='width: 60px'>
		</div>
		<div class='media-body'>
			<h4 class='media-heading'>${i.id }</h4>
			<!-- 아이디 -->
			<p>${i.comment }</p>
			<!-- 시간 -->
		</div>
	</div>
</c:forEach>

