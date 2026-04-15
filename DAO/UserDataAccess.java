package DAO;

import MVC.Model.User;

import java.util.ArrayList;

public interface UserDataAccess {
    User getUser(User user);
    ArrayList<User> getAllUsers();
    void addUser(User user);
}
