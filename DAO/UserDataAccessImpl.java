package DAO;

import Exceptions.*;
import MVC.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

public class UserDataAccessImpl implements UserDataAccess {
    private Connection connection;
    public UserDataAccessImpl() {
        connection = SingletonConnection.getInstance();
    }

    @Override
    public User getUser(User user) {
        try {
            String query = "SELECT * FROM user WHERE name = ? OR email = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());

                try (ResultSet data = ps.executeQuery()) {
                    if (!data.next()) {
                        throw new UserNotFoundException();
                    }

                    String name = data.getString("name");
                    String email = data.getString("email");
                    String hashedPassword = data.getString("password");

                    if (!BCrypt.checkpw(user.getPassword(), hashedPassword)) {
                        throw new UserNotFoundException();
                    }

                    return new User(name, email, hashedPassword);
                }
            }
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new FailedToLogin();
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        try {
            String query = "SELECT * FROM user";
            try (PreparedStatement ps = connection.prepareStatement(query);
                 ResultSet users = ps.executeQuery()) {

                ArrayList<User> usersList = new ArrayList<>();
                while (users.next()) {
                    String name = users.getString("name");
                    String email = users.getString("email");
                    String hashedPassword = users.getString("password");
                    usersList.add(new User(name, email, hashedPassword));
                }
                return usersList;
            }
        } catch (Exception e) {
            throw new FailedToGetUsers();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            String checkQuery = "SELECT name FROM user WHERE name = ? OR email = ?";
            try (PreparedStatement checkPs = connection.prepareStatement(checkQuery)) {
                checkPs.setString(1, user.getName());
                checkPs.setString(2, user.getEmail());

                ResultSet data = checkPs.executeQuery();

                if (data.next()) {
                    throw new UserAlreadyExistsException();
                }
            }
            String query = "INSERT INTO user(name, email, password) VALUES (?, ?, ?)";

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, hashedPassword);
                ps.executeUpdate();
            }
        } catch (UserAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            throw new FailedToAddUserException(user);
        }
    }
}
