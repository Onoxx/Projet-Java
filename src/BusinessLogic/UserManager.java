package BusinessLogic;

import DAO.User.UserDAO;
import DAO.User.UserDAOImpl;
import MVC.Model.User;

import java.util.ArrayList;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() {this.userDAO = new UserDAOImpl();}
    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
}
