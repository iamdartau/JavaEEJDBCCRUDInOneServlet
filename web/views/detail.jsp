<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container mt-3">
    <div class="row">
        <div class="col-6 offset3">
            <%
                String success =request.getParameter("success");

                User user = (User) request.getAttribute("user");

                if(success!=null){
                    if (success.equals("1")){
            %>
            <div class="alert alert-primary" role="alert">
                User added
            </div>
            <%
                    }
                }
            %>
            <form action="/" method="post">
                <input type="hidden" name="act" value="edituser">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <div class="row mt-2">
                    <div class="col-4">
                        <label>Login</label>
                    </div>
                    <div class="col-8">
                        <input type="text" class="form-control" name="login" value="<%=user.getLogin()%>">
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-4">
                        <label>Password</label>
                    </div>
                    <div class="col-8">
                        <input type="password" class="form-control" name="password" value="<%=user.getPassword()%>">
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-4">
                        <label>Full Name</label>
                    </div>
                    <div class="col-8">
                        <input type="text" class="form-control" name="fullName" value="<%=user.getFullName()%>">
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">

                        <button class="btn btn-success float-right">insert</button>

                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
