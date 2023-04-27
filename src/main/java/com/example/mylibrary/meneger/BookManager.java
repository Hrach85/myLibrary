package com.example.mylibrary.meneger;


import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private AuthorManager authorManager = new AuthorManager();
    private Book book = new Book();


    public void save(Book book) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO book (title, description, price, author_id) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setInt(3, book.getPrice());
            statement.setInt(4, book.getAuthor_id().getId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            System.out.println("book inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from book where id = " + id);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from book");
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getAllByAuthorId(int authorId) {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from book where author_id=" + authorId);
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(Integer.parseInt(resultSet.getString("price")));
        int authorId = resultSet.getInt("author_id");
        book.setAuthor_id(authorManager.getById(authorId));
        return book;
    }


    public void removeById(int bookId) {
        String sql = "DELETE FROM book WHERE id = " + bookId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Book book) {
        String sql = "UPDATE book SET title = '%s', description = '%s', price = '%s', author_id = '%s' WHERE id = %d";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(sql, book.getTitle(), book.getDescription(), book.getPrice(), book.getAuthor_id(), book.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
