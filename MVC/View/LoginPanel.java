package MVC.View;

import MVC.Controller.LoginPanelController;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainWindow mw;
    private JLabel loginText;
    private FormPanel formPanel;
    private ButtonsPanel buttonsPanel;
    private LoginPanelController loginPanelController;

    public LoginPanel(MainWindow mw) {
        setPreferredSize(new Dimension(350, 120));
        this.mw = mw;
        loginPanelController = new LoginPanelController(this, mw);

        this.setLayout(new BorderLayout(0,15));

        loginText = new JLabel("Connect to NexaPC", SwingConstants.CENTER);
        this.add(loginText, BorderLayout.NORTH);

        formPanel = new FormPanel();
        this.add(formPanel, BorderLayout.CENTER);

        buttonsPanel = new ButtonsPanel();
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public class FormPanel extends JPanel {
        private JLabel userText;
        private JTextField userField;
        private JLabel passwordText;
        private JPasswordField passwordField;

        public FormPanel() {
            userText = new JLabel("Username / Email : ", SwingConstants.CENTER);
            userField = new JTextField();

            passwordText = new JLabel("Password : ", SwingConstants.CENTER);
            passwordField = new JPasswordField();

            this.setLayout(new GridLayout(2,2));

            this.add(userText);
            this.add(userField);
            this.add(passwordText);
            this.add(passwordField);
        }

        public JTextField getUserField() {
            return userField;
        }

        public JPasswordField getPasswordField() {
            return passwordField;
        }
    }
    public class ButtonsPanel extends JPanel {
        private JButton resetButton;
        private JButton validateButton;
        private JButton backButton;

        public ButtonsPanel() {
            resetButton = new JButton("Reset");
            validateButton = new JButton("Validate");
            backButton = new JButton("Back");

            this.setLayout(new FlowLayout());
            this.add(resetButton);
            this.add(validateButton);
            this.add(backButton);

            resetButton.addActionListener(loginPanelController);
            validateButton.addActionListener(loginPanelController);
            backButton.addActionListener(loginPanelController);
        }

        public JButton getResetButton() {
            return resetButton;
        }

        public JButton getValidateButton() {
            return validateButton;
        }
    }

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
}
