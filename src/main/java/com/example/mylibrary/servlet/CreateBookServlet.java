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

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {

    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        req.setAttribute("authors", all);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        int authorId = Integer.parseInt(req.getParameter("author_id"));
        int price1 = 0;
        if(price != null) {
            price1 = Integer.parseInt(price);
        }
        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price1)
                .author_id(authorManager.getById(authorId))
                .build();
        bookManager.save(book);
        resp.sendRedirect("/book");
    }
}