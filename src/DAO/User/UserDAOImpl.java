package DAO.User;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String querry = "SELECT * FROM user";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                User user = new User(userId,name, email);
                users.add(user);
            }
        }catch(SQLException ex){
            throw new AllComponentsException("utilisateurs");
        }
        return users;
    }
    @Override
    public User getUserById(int id) {
        String querry = "SELECT * FROM user WHERE id = ?";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                return new User(userId, name, email);
            }
        }catch (SQLException ex){
            throw new FailedToGetComponentException("utilisateur");
        }
        return null;
    }
}
