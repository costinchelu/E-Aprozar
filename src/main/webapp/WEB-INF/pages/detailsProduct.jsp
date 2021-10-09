<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afacere electronică</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<jsp:include page="_cart.jsp" />
		<jsp:include page="_menu.jsp" />
		<div class="product-container">			
			<ul>
				<li><img class="product-image"  src="${pageContext.request.contextPath}/resources/images/<c:out value="${product.idProduct}"/>.jpg"></li>
				<li class="product-name"><c:out value="${product.name}"/></li>
				<li ><span class="product-price">Preț: </span><span class="product-price-pc"><c:out value="${product.price}"/><c:out value="${product.currency}"/></span></li>
				<li><div class="product-description-container"><span class="product-description">Descriere: </span><c:out value="${product.description}"/></div></li>
				<c:forEach items="${product.specifications}" var="specification">
					<li style="list-style-type: circle"><c:out value= "${specification}"/></li>
				</c:forEach>
				<li><div class="product-cart"><a href="${pageContext.request.contextPath}/buy/addToCart?idProduct=<c:out value="${product.idProduct}"/>"><img src="${pageContext.request.contextPath}/resources/images/cart.png"> Adaugă la coșul de cumpărături</a></div></li>
			</ul>		
		</div>				
	</body>
</html>