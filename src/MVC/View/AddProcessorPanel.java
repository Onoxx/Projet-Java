package MVC.View;

import MVC.Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;

public class AddProcessorPanel extends JPanel {
    private MainWindow mainWindow;

    public AddProcessorPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setLayout(new BorderLayout());

        this.add(new ProcessorFormPanel(mainWindow, null), BorderLayout.CENTER);
    }
}
