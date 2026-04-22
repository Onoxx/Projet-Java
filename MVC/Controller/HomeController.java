package MVC.Controller;

import MVC.View.HomePanel;
import MVC.View.LoginPanel;
import MVC.View.RegisterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController implements ActionListener {
    private HomePanel homePanel;

    public HomeController(HomePanel homePanel) {
        this.homePanel = homePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel centerPanel = homePanel.getMainWindow().getCenterPanel();
        centerPanel.removeAll();
        if (e.getSource() == homePanel.getRegisterButton()) {
            centerPanel.add(new RegisterPanel(homePanel.getMainWindow()));
        } else if (e.getSource() == homePanel.getLoginButton()) {
            centerPanel.add(new LoginPanel(homePanel.getMainWindow()));
        }
        centerPanel.revalidate();
        centerPanel.repaint();
    }
    public void showHomePanel() {
        JPanel centerPanel = homePanel.getMainWindow().getCenterPanel();
        centerPanel.removeAll();
        centerPanel.add(homePanel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
