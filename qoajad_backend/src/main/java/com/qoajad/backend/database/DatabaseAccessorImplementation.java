package com.qoajad.backend.database;

import com.qoajad.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Qualifier(value = "defaultDatabaseAccessor")
public class DatabaseAccessorImplementation implements DatabaseAccessor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseAccessorImplementation(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Objects.requireNonNull(jdbcTemplate, "To instantiate the database accessor the jdbc template must not be null.");
    }

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users;
        try {
            final String query = "SELECT user_id, user_document, user_pw FROM User";
            users = jdbcTemplate.query(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final int userDocument = resultSet.getInt("user_document");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password, userDocument);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return users == null ? Collections.emptyList() : users;
    }

    @Override
    public User findUserByDocument(int document) {
        User user;
        try {
            final String query = "SELECT user_id, user_document, user_pw FROM User where user_document = ?";
            user = jdbcTemplate.queryForObject(query, (resultSet, rowNum) -> {
                final int userId = resultSet.getInt("user_id");
                final int userDocument = resultSet.getInt("user_document");
                final String password = resultSet.getString("user_pw");
                return new User(userId, password, userDocument);
            }, document);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public void createUser(int document, String password) {
        try {
            final String query = "INSERT INTO User(user_document, user_pw) VALUES (?, ?)";
            jdbcTemplate.update(query, document, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateUser(int document, String password) {
        int rowsChanged;
        try {
            final String query = "UPDATE User SET user_pw = ? WHERE user_document = ?";
            rowsChanged = jdbcTemplate.update(query, password, document);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }

    @Override
    public boolean deleteUser(int document) {
        int rowsChanged;
        try {
            final String query = "DELETE FROM User WHERE user_document = ?";
            rowsChanged = jdbcTemplate.update(query, document);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return rowsChanged > 0;
    }
}