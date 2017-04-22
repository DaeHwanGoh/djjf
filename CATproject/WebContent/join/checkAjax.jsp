<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="models.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String id = request.getParameter("id");
	MemberDao mdao = new MemberDao();
		
		boolean f= mdao.existOne(id);
		
	request.setAttribute("f", f);
%>
<c:choose>
	<c:when test="${f }">nnnn</c:when>
	<c:otherwise><span style ="color: green; font-weight: bold;"><i>(${param.id } 은/는 사용 가능한 아이디입니다.)</i></span></c:otherwise>

</c:choose>