package MVC.View;

import MVC.Controller.HomeController;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private JButton loginButton;
    private JButton registerButton;
    private MainWindow mainWindow;

    public HomePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        setLayout(new GridBagLayout());
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.setPreferredSize(new Dimension(200, 50));
        registerButton.setPreferredSize(new Dimension(200, 50));

        HomeController homeController = new HomeController(this);

        loginButton.addActionListener(homeController);
        registerButton.addActionListener(homeController);

        JPanel panel = new JPanel();
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);
    }

    public JButton getLoginButton() {
        return loginButton;
    }
    public JButton getRegisterButton() {
        return registerButton;
    }
    public MainWindow getMainWindow() {
        return mainWindow;
    }
}
