<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Afacere electronică</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_cart.jsp"/>
<jsp:include page="_menu.jsp"/>
<div class="product-preview-container-page">
    <h1 class="title-buy-page"><c:out value="${department}"></c:out>.</h1>
    <c:forEach items="${products}" var="product">
        <div class="product-preview-container">
            <ul>
                <li><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${product.idProduct}"/>">
                    <img
                        class="product-preview-image"
                        src="${pageContext.request.contextPath}/resources/images/<c:out value="${product.idProduct}"/>.jpg"></a>
                </li>
                <li class="product-preview-name"><a
                        href="${pageContext.request.contextPath}/buy/details/<c:out value="${product.idProduct}"/>"><c:out
                        value="${product.name}"/></a></li>
                <li><span class="product-preview-price">Preț: </span><c:out value="${product.price}"/><c:out
                        value="${product.currency}"/></li>
                <li>
                    <div class="product-preview-cart"><a
                            href="${pageContext.request.contextPath}/buy/addToCart?idProduct=<c:out value="${product.idProduct}"/>"><img
                            src="${pageContext.request.contextPath}/resources/images/cart.png"> Adaugă la comandă</a>
                    </div>
                </li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li>
                        <div class="product-preview-cart"><a
                                href="${pageContext.request.contextPath}/managementProduct/update/<c:out value="${product.idProduct}"/>">Actualizează
                            informații</a></div>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </c:forEach>
</div>
<br/>
<jsp:include page="_page.jsp"/>
</body>
</html>