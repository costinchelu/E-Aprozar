<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${pages>1}">
	<div class="page-nav">
		<c:if test="${page!=1}">
			<a href="${pageContext.request.contextPath}/buy/${department}?page=${page-1}">←Inapoi</a>
			<c:if test="${page>2}">
				<a href="${pageContext.request.contextPath}/buy/${department}?page=1">1</a>
				...
			</c:if>
			<a href="${pageContext.request.contextPath}/buy/${department}?page=${page-1}">${page-1}</a>
		</c:if>
		<a href="${pageContext.request.contextPath}/buy/${department}?page=${page}"><span class="page-nav-current">${page}</span></a>
		<c:if test="${page!=pages}">
			<a href="${pageContext.request.contextPath}/buy/${department}?page=${page+1}">${page+1}</a>
			<c:if test="${page<pages-1}">
				...
				<a href="${pageContext.request.contextPath}/buy/${department}?page=${pages}">${pages}</a>
			</c:if>
			<a href="${pageContext.request.contextPath}/buy/${department}?page=${page+1}">Inainte→</a>
		</c:if>
	</div>	
</c:if>	