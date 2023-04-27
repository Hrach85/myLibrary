<%@ page import="com.example.mylibrary.model.User" %>
<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.04.2023
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");
    User user = (User) session.getAttribute("user");
%>
<body>
<a href="/"> Back </a>
<h2>Authors</h2> <a href="/createAuthor">Create Author</a>
<table border="1">
    <tr>
        <th>id</th>
        <th>name/անուն</th>
        <th>surname/</th>
        <th>email</th>
        <th>age</th>
        <th>action</th>

    </tr>
    <% if (authors != null && !authors.isEmpty()) {%>
    <% for (Author author : authors) { %>
    <tr>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurName()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td><a href="/removeAuthor?id=<%=author.getId()%>">delete</a> /
            <a href="updateAuthor?id=<%=author.getId()%>">Update</a></td>
    </tr>
    <%}%>
    <%}%>
</table>
</body>
</html>