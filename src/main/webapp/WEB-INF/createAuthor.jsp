<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 27.04.2023
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Author</title>
</head>

<body>
<a href="/authors"> Back </a>

    <h2>Create Author</h2>

    <form action="/createAuthor" method="post">

        Name: <input type="text" name="name"> <br>
        Surname: <input type="text" name="surname"> <br>
        Email: <input type="text" name="email"> <br>
        Age: <input type="text" name="age"> <br>
        <input type="submit" value="create">
    </form>


</form>
</body>
</html>
