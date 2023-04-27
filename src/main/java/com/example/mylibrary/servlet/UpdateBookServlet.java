package com.example.mylibrary.servlet;



import com.example.mylibrary.meneger.AuthorManager;
import com.example.mylibrary.meneger.BookManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();
    AuthorManager authorManager = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookManager.getById(id);
        req.setAttribute("book",book);
        List<Author> all = authorManager.getAll();
        req.setAttribute("authors", all);
        req.getRequestDispatcher("WEB-INF/updateBook.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int priceInt = Integer.parseInt(req.getParameter("price"));
        int author_id = Integer.parseInt(req.getParameter("author_id"));
        Book book = new Book(id,title,description,priceInt,authorManager.getById(author_id));
        bookManager.update(book);
        resp.sendRedirect("/books");
    }
}
