package MVC.View;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainWindow mw;
    private FormPanel formPanel;
    private ButtonsPanel buttonsPanel;

    public LoginPanel(MainWindow mw) {
        this.mw = mw;

        this.setLayout(new BorderLayout());

        formPanel = new FormPanel();
        this.add(formPanel, BorderLayout.CENTER);

        buttonsPanel = new ButtonsPanel();
        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private class FormPanel extends JPanel {
        private JLabel userText;
        private JTextField userField;
        private JLabel passwordText;
        private JPasswordField passwordField;

        public FormPanel() {
            userText = new JLabel("Username / Email : ", SwingConstants.CENTER);
            userField = new JTextField();

            passwordText = new JLabel("Password : ", SwingConstants.CENTER);
            passwordField = new JPasswordField();

            this.setLayout(new GridLayout(2,2, 5, 5));

            this.add(userText);
            this.add(userField);
            this.add(passwordText);
            this.add(passwordField);
        }
    }

    private class ButtonsPanel extends JPanel {
        private JButton resetButton;
        private JButton validateButton;

        public ButtonsPanel() {
            resetButton = new JButton("Reset");
            validateButton = new JButton("Validate");

            this.setLayout(new FlowLayout());
            this.add(resetButton);
            this.add(validateButton);
        }
    }
}
