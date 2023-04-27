<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.04.2023
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<% List<Book> books = (List<Book>) request.getAttribute("books");
    User user = (User) session.getAttribute("user");%>
<body>
<a href="/"> Back </a>
<h2>Book</h2> <a href="/createBook">Create Book</a>
<table border="1">
    <tr>
        <th>id</th>
        <th>Title</th>
        <th>description</th>
        <th>price</th>
        <th>Author name</th>
        <th>action</th>

    </tr>
    <% if(books != null && !books.isEmpty()) {%>
    <% for (Book book : books) { %>
    <tr>
        <td><%=book.getId()%></td>
        <td><%=book.getTitle()%></td>
        <td><%=book.getDescription()%></td>
        <td><%=book.getPrice()%></td>
        <td><%=book.getAuthor_id().getName()%></td>
        </td>
        <td><a href="/removeBook?id=<%=book.getId()%>">delete</a> /
            <a href="updateBook?id=<%=book.getId()%>">Update</a></td>
    </tr>
    <%}%>
    <%}%>
</table>
</body>
</html>
