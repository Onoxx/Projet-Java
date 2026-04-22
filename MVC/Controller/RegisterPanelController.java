package MVC.Controller;

import DAO.UserDataAccess;
import DAO.UserDataAccessImpl;
import Exceptions.EmptyFieldException;
import Exceptions.FailedToAddUserException;
import Exceptions.StringTooLongException;
import Exceptions.UserAlreadyExistsException;
import MVC.Model.User;
import MVC.View.HomePanel;
import MVC.View.LoginPanel;
import MVC.View.MainWindow;
import MVC.View.RegisterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanelController implements ActionListener {
    private RegisterPanel registerPanel;
    private MainWindow mw;
    private HomePanel homePanel;

    public RegisterPanelController(RegisterPanel registerPanel, MainWindow mainWindow) {
        this.registerPanel = registerPanel;
        this.mw = mainWindow;
        homePanel = mainWindow.getHomePanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField nameField = registerPanel.getFormPanel().getNameField();
        JTextField emailField = registerPanel.getFormPanel().getEmailField();
        JPasswordField passwordField = registerPanel.getFormPanel().getPasswordField();

        if (e.getSource() == registerPanel.getButtonsPanel().getResetButton()) {
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == registerPanel.getButtonsPanel().getValidateButton()) {
            UserDataAccess userDAO = new UserDataAccessImpl();
            try{
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (name.isEmpty() || email.trim().isEmpty() || password.isEmpty()) {
                    throw new EmptyFieldException();
                }
                if (name.length() > 30) {
                    throw new StringTooLongException("Username", 30);
                }
                if (email.length() > 30) {
                    throw new StringTooLongException("Email", 30);
                }
                if (password.length() > 30) {
                    throw new StringTooLongException("Password", 30);
                }

                User user = new User(name, email, password);
                userDAO.addUser(user);

                JOptionPane.showMessageDialog(registerPanel, "Ajout réussi", "Success", JOptionPane.INFORMATION_MESSAGE);

                HomeController homeController = new HomeController(homePanel);
                homeController.showHomePanel();

            }catch (EmptyFieldException ex){
                JOptionPane.showMessageDialog(registerPanel, ex.getMessage(), "Champ nul", JOptionPane.ERROR_MESSAGE);
            }catch (StringTooLongException ex){
                JOptionPane.showMessageDialog(registerPanel, ex.getMessage(), "Champ trop long", JOptionPane.ERROR_MESSAGE);
            }catch(UserAlreadyExistsException ex){
                JOptionPane.showMessageDialog(registerPanel, ex.getMessage(), "Utilisateur existant", JOptionPane.ERROR_MESSAGE);
            }
            catch (FailedToAddUserException ex){
                JOptionPane.showMessageDialog(registerPanel,"Erreur lors de l'inscription", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            HomeController homeController = new HomeController(homePanel);
            homeController.showHomePanel();
        }
    }
}
