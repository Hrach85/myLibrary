<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.04.2023
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create Book</title>
</head>
<%List<Author> authors = (List<Author>) request.getAttribute("authors"); %>
<body>
<a href="/books"> Back </a>

<h2>Create Book</h2>
<form action="/createBook" method="post">

  Title: <input type="text" name="title"> <br>
  Description: <input type="text" name="description"> <br>
  price: <input type="text" name="price"> <br>
  Author:
  <select name="authorId">
    <% for (Author author : authors) {%>
    <option value="<%=author.getId()%>"><%=author.getName()%></option>
    <% }%>
  </select>
  <input type="submit" value="create">
</form>
</body>
</html>
