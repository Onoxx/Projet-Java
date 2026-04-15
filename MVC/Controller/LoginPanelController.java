package MVC.Controller;

import MVC.View.LoginPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanelController implements ActionListener {
    private LoginPanel loginPanel;
    public LoginPanelController(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginPanel.getButtonsPanel().getResetButton()) {
            loginPanel.getFormPanel().getUserField().setText("");
            loginPanel.getFormPanel().getPasswordField().setText("");
        } else {
            if (e.getSource() == loginPanel.getButtonsPanel().getValidateButton()) {
                //verifier les infos rentrees et rentrer dans l app
            }
        }
    }
}
