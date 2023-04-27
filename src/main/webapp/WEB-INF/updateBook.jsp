<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.04.2023
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Book</title>
</head>

<body>
<%List<Author> authors = (List<Author>) request.getAttribute("authors"); %>
<%Book book = (Book) request.getAttribute("book");%>

<a href="/books"> Back </a>
<h2>Update Book</h2>

<form action="/updateBook" method="post">
    <input name="id" type="hidden" value="<%=book.getId()%>">
    title: <input type="text" name="title" value="<%=book.getTitle()%>"><br>
    description: <input type="text" name="description" value="<%=book.getDescription()%>"><br>
    price: <input type="text" name="price" value="<%=book.getPrice()%>"><br>
    author:
    <select name="author">
        <% for (Author author : authors) { %>
        <option value="<%=author.getId()%>"><%=author.getName()%>
        </option>
        <% }%>
    </select> <br>
    <input type="submit" value="create">

</form>


</body>
</html>