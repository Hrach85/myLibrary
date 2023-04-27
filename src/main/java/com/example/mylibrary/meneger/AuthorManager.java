package com.example.mylibrary.meneger;


import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AuthorManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void save(Author author) {
        String sql = "INSERT INTO author(name,surname,email,age) VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurName());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }
            System.out.println("author inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from author where id =" + id);
            if (resultSet.next()) {
                return getCompanyFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAll() {
        List<Author> authorList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from author");
            while (resultSet.next()) {
                authorList.add(getCompanyFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public List<Author> getByAuthorName(String authorName) {
        List<Author> authorList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from author where name = ?");
            ps.setString(1, authorName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                authorList.add(getCompanyFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    private Author getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("name"));
        author.setSurName(resultSet.getString("surName"));
        author.setEmail(resultSet.getString("email"));
        author.setAge(resultSet.getInt("age"));
        return author;
    }

    public void removeById(int authorId) {
        String sql = "DELETE FROM author WHERE id = " + authorId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(Author author) {
        String sql = "UPDATE author SET name = '%s', surname = '%s',email = '%s',age = '%s' WHERE id = %d";
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(String.format(sql, author.getName(), author.getSurName(),author.getEmail(),author.getAge(), author.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}