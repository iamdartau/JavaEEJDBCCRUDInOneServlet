<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sun.xml.internal.txw2.output.DumpSerializer" %>
<%@ page import="models.User" %><%--
  Created by IntelliJ IDEA.
  User: Alexey.Dartau
  Date: 18.01.2020
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Login</th>
        <th scope="col">Password</th>
        <th scope="col">Full Name</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>

    <%

        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        if (users != null) {
            for (User user : users) {
                if (user != null) {
    %>
    <tr>
        <td scope="row"><%=user.getId()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getFullName()%></td>
        <td><a href="/?page=detail?id=<%=user.getId()%>">edit</a></td>
        <td><form action="/" method="post">
            <input type="hidden" name="act" value="delete">
            <input type="hidden" value="<%=user.getId()%>" name="id">
            <button>delete</button>
        </form></td>
        <%
                    }
                }
            }
        %>
    </tr>
    </tbody>
</table>

</body>
</html>
