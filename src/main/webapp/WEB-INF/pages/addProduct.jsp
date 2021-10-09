<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Adauga produs</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    </head>
	<body>
		<jsp:include page="_header.jsp" />				
		<h1>Add product</h1><br/>
			<form:form action="uploadProduct" method="post" modelAttribute="uplProduct" enctype="multipart/form-data">
				<table>
					<tr>
						<th>Tip produs</th>
					       <td>
						       <form:select path="department">	
							       <form:option value="-" label="--Please Select--"/>
							       <form:options items="${listDepartments}"/>
						       </form:select>
					        </td>
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="department" cssClass="error" /></td>
					    </tr>
						<tr>
							<th>Nume</th>
							<td><form:input path="name"/></td>					
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="name" cssClass="error" /></td>
					     </tr>
						<tr>
							<th>Preț</th>
							<td><form:input path="price"/></td>					
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="price" cssClass="error" /></td>
					     </tr>
						<tr>
							<th>Monedă</th>
							<td>
								<form:radiobutton path="currency" value="lei" label="LEU"/>
								<form:radiobutton path="currency" value="eur" label="EUR"/>
							</td>					
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="currency" cssClass="error" /></td>
					     </tr>
						<tr>
							<th>Disponibil</th>
							<td>
								<form:radiobutton path="availability" value="Yes" label="Yes"/>
								<form:radiobutton path="availability" value="No" label="No"/>
							</td>	
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="availability" cssClass="error" /></td>
					     </tr>
						<tr>
							<th>Descriere</th>
							<td><form:textarea path="description" rows="5" cols="40"/></td>				
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="description" cssClass="error" /></td>
					     </tr>
						<tr>
							<th>Specificații</th>
							<td><form:input path="specification1"/></td>		
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="specification1" cssClass="error" /></td>
					     </tr>
						<tr>
							<th></th>					
							<td><form:input path="specification2"/></td>		
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="specification2" cssClass="error" /></td>
					     </tr>
						<tr>
							<th></th>				
							<td><form:input path="specification3"/></td>		
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="specification3" cssClass="error" /></td>
					     </tr>
						<tr>
							<th></th>				
							<td><form:input path="specification4"/></td>		
						</tr>								
						<tr>
							<th></th>
							<td><form:input path="specification5"/></td>		
						</tr>
						<tr>
							<th>Poză</th>
							<td><input type="file" name="image" accept="image/jpeg"/></td>
						</tr>
						<tr>	
					        <th></th>		                    
					        <td><form:errors path="image" cssClass="error" /></td>
					     </tr>
						<tr>  
							<th></th>             
					        <td><form:button>OK</form:button></td>
					    </tr>
				  </table>
			</form:form>
	 </body>
</html>