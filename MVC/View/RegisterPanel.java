package MVC.View;

import MVC.Controller.RegisterPanelController;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private MainWindow mw;
    private JLabel registerText;
    private FormPanel formPanel;
    private ButtonsPanel buttonsPanel;
    private RegisterPanelController registerPanelController;

    public RegisterPanel(MainWindow mw) {
        setPreferredSize(new Dimension(350,120));
        this.mw = mw;
        registerPanelController = new RegisterPanelController(this, mw);

        this.setLayout(new BorderLayout(0,10));

        registerText = new JLabel("Register to NexaPC", SwingConstants.CENTER);
        this.add(registerText, BorderLayout.NORTH);

        formPanel = new FormPanel();
        this.add(formPanel, BorderLayout.CENTER);

        buttonsPanel = new ButtonsPanel();
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    public class FormPanel extends JPanel {
        private JLabel nameText;
        private JTextField nameField;
        private JLabel emailText;
        private JTextField emailField;
        private JLabel passwordText;
        private JPasswordField passwordField;

        public FormPanel() {
            nameText = new JLabel("Username", SwingConstants.CENTER);
            nameField = new JTextField();

            emailText = new JLabel("Email", SwingConstants.CENTER);
            emailField = new JTextField();

            passwordText = new JLabel("Password", SwingConstants.CENTER);
            passwordField = new JPasswordField();

            this.setLayout(new GridLayout(3,2));

            this.add(nameText);
            this.add(nameField);
            this.add(emailText);
            this.add(emailField);
            this.add(passwordText);
            this.add(passwordField);
        }
        public JTextField getNameField() { return nameField; }
        public JTextField getEmailField() { return emailField; }
        public JPasswordField getPasswordField() { return passwordField; }
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

            resetButton.addActionListener(registerPanelController);
            validateButton.addActionListener(registerPanelController);
            backButton.addActionListener(registerPanelController);
        }
        public JButton getResetButton() {return resetButton;}
        public JButton getValidateButton() {return validateButton;}
    }
    public FormPanel getFormPanel() {return formPanel;}
    public ButtonsPanel getButtonsPanel() {return buttonsPanel;}
}
