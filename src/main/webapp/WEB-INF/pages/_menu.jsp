<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:import var="list_department" url="/resources/xml/department.xml"/>
<x:parse doc="${list_department}" var="list"/>
<ul class="menu">
	<x:forEach select="$list/departments/department" var="department">	
	<li><a href="${pageContext.request.contextPath}/buy/<x:out select="$department"/>"><x:out select="$department"/></a></li>	
	</x:forEach>
</ul>