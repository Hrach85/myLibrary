<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Author</title>
</head>
<body>
<% Author author = (Author) request.getAttribute("author"); %>
<a href="/authors"> Back </a>

<h2>Update Author</h2>
<form action="/updateAuthor" method="post">
    <input name="id" type="hidden" value="<%=author.getId()%>">
    name: <input type="text" name="name" value="<%=author.getName()%>"><br>
    surname: <input type="text" name="surname" value="<%=author.getSurName()%>"> <br>
    email: <input type="text" name="email" value="<%=author.getEmail()%>"> <br>
    age: <input type="text" name="age" value="<%=author.getAge()%>"> <br>
    <input type="submit" value="update">
</form>
</body>
</html>
