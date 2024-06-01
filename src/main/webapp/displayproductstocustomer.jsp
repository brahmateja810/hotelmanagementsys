<%@page import="com.jsp.hotelmanagementsystem.entities.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	List<Product> products = (List) request.getAttribute("productsList");
	%>

	<table cellPadding="20px" border="1">
		<th>name</th>
		<th>type</th>
		<th>cost</th>
		<th>add</th>

		<%
		for (Product product : products) {
		%>
		<tr>
          <td> <%= product.getName() %>  </td>
          <td> <%= product.getType() %> </td>
          <td> <%= product.getCost() %> </td>
          <td> <a href="additem?id=<%=product.getId()%>">Add</a> </td>
		</tr>
		<%
		}
		%>
		
		
	</table>
	
	<button><a href="viewaddeditemstocustomer.jsp">Proceed to buy</a></button>
</body>
</html>