package MVC.Controller;

import DAO.UserDataAccess;
import DAO.UserDataAccessImpl;
import Exceptions.EmptyFieldException;
import Exceptions.StringTooLongException;
import Exceptions.UserNotFoundException;
import MVC.Model.User;
import MVC.View.HomePanel;
import MVC.View.LoginPanel;
import MVC.View.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanelController implements ActionListener {
    private LoginPanel loginPanel;
    private HomePanel homePanel;

    public LoginPanelController(LoginPanel loginPanel, MainWindow mainWindow) {
        this.loginPanel = loginPanel;
        this.homePanel = mainWindow.getHomePanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField usernameField = loginPanel.getFormPanel().getUserField();
        JPasswordField passwordField = loginPanel.getFormPanel().getPasswordField();

        if (e.getSource() == loginPanel.getButtonsPanel().getResetButton()) {
            usernameField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == loginPanel.getButtonsPanel().getValidateButton()){
            if (e.getSource() == loginPanel.getButtonsPanel().getValidateButton()) {
                UserDataAccess userDAO = new UserDataAccessImpl();
                try{
                    String login = usernameField.getText().trim();
                    String password = new String(passwordField.getText()).trim();

                    if(login.isEmpty() || password.isEmpty()){
                        throw new EmptyFieldException();
                    }
                    if (login.length() > 30) {
                        throw new StringTooLongException("Username", 30);
                    }
                    if (password.length() > 30) {
                        throw new StringTooLongException("Password", 30);
                    }
                    userDAO.getUser(new User(login, login, password));

                    JOptionPane.showMessageDialog(loginPanel, "Conexion réussie !", "Connexion validée", JOptionPane.INFORMATION_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");

                    HomeController homeController = new HomeController(homePanel);
                    homeController.showHomePanel();
                }catch(EmptyFieldException ex){
                    JOptionPane.showMessageDialog(loginPanel, ex.getMessage(), "Champ nul", JOptionPane.ERROR_MESSAGE);
                }catch (StringTooLongException ex){
                    JOptionPane.showMessageDialog(loginPanel, ex.getMessage(), "Champ trop long", JOptionPane.ERROR_MESSAGE);
                }catch(UserNotFoundException ex){
                    JOptionPane.showMessageDialog(loginPanel, ex.getMessage(), "Utilisateur introuvable", JOptionPane.ERROR_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(loginPanel, "Error lors de la connexion", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else{
            HomeController homeController = new HomeController(homePanel);
            homeController.showHomePanel();
        }
    }
}
