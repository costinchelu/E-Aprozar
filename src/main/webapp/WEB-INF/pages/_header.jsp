<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header-container">
	<div class="shop-name"><a href="${pageContext.request.contextPath}/welcome">Afaceri electronice</a></div>
	<div class="header-bar">
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<a href="${pageContext.request.contextPath}/user/accountInfo">${currentUser.name}</a>
			&nbsp;|&nbsp;
			<a href="${pageContext.request.contextPath}/logout">Logout</a>
		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<a href="${pageContext.request.contextPath}/register">Inregistrare</a>
			&nbsp;|&nbsp;
	        <a href="${pageContext.request.contextPath}/login">Login</a>
	    </c:if>
    </div>    
</div>