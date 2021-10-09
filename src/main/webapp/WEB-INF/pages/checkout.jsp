<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />
		<h1>Finalizare comandă</h1>
		<div class="form-input">
		<h2>Produs:</h2>
        <c:forEach items="${order.lineItem}" var="lineItemList">
			<ul><li class="specification"><a href="${pageContext.request.contextPath}/buy/details/<c:out value="${lineItemList.product.idProduct}"/>"><c:out value= "${lineItemList.product.name}"/></a> <c:out value= "${lineItemList.quantity}"/> 
				<c:choose>
				<c:when test="${lineItemList.quantity == 1}">produs </c:when>
				<c:otherwise>produse</c:otherwise>
				</c:choose></li>
			</ul>
		</c:forEach>
		<h2>Preț total: <c:out value= "${order.totalPrice}"/><c:out value= "${order.lineItem[0].product.currency}"/></h2>
		<h2>Introduceți detaliile pentru livrare:</h2>
		
			<form:form action="complete" method="post" modelAttribute="order">
				<table>
					<tr>						
						<td><form:textarea path="customerAddress" rows="10" cols="50"/></td>
					</tr>
					<tr>							                    
						<td><form:errors path="customerAddress" cssClass="error" /></td>
					</tr>
					<tr>               
						<td><form:button>OK</form:button></td>
					</tr>
				</table>            
			</form:form>
        </div>
	</body>
</html>