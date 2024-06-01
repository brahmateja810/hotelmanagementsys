<%@page import="com.jsp.hotelmanagementsystem.entities.Hotel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unapproved Hotels</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: #0066cc;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%
        List<Hotel> hotels = (List<Hotel>) request.getAttribute("unapprovedhotels");
    %>
    <h1>Unapproved Hotels</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Mobile Number</th>
            <th>Status</th>
            <th>Approve</th>
        </tr>
        <%
            for (Hotel h : hotels) {
        %>
        <tr>
            <td><%=h.getId()%></td>
            <td><%=h.getName()%></td>
            <td><%=h.getEmail()%></td>
            <td><%=h.getAddress()%></td>
            <td><%=h.getMobilenumber()%></td>
            <td><%=h.getStatus()%></td>
            <td><a href="approvehotel?id=<%=h.getId()%>">Approve</a></td>
        </tr>
        <%
            }
        %>
    </table>

    <br>
    <a href="adminoptions.jsp">Back to menu</a>
    <br>
    <a href="adminlogout">Logout</a>
</body>
</html>
