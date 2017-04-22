<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
  <div class="media">
  
  <c:forEach var="i" begin="0" end="4" items="${listmap }">
    <div class="media-left">
    <a href="/detail/print?code=${i.link }"><img src="${i.image }" class="media-object" style="width:30px; height: 43px; "></a>
    </div>
    <div class="media-body">
     <h4 class="media-heading"> <a href="/detail/print?code=${i.link }">${i.title }</a></h4>
      <p> <a href="${i.link }">${i.subtitle }</a></p>
    </div>
    <hr>
  </c:forEach>
  </div>
</div>
