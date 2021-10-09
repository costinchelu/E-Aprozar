<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="q"  value="${0}"/>
<c:if test="${!empty order.lineItem}">
	<c:forEach items="${order.lineItem}" var="lineitem">
		<c:set var="q"  value="${q + lineitem.quantity}"/>
	</c:forEach>
</c:if>
<a href="${pageContext.request.contextPath}/buy/cart" class="header-cart">Cos de cumparaturi ${q}</a>