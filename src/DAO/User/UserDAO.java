package DAO.User;

import MVC.Model.User;

import java.util.ArrayList;

public interface UserDAO {
    ArrayList<User> getAllUsers();
    User getUserById(int id);
}
