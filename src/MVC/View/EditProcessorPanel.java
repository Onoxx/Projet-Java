package MVC.View;

import Exceptions.FailedToGetComponentException;
import MVC.Controller.ApplicationController;
import MVC.Model.Processor;

import javax.swing.*;
import java.awt.*;

public class EditProcessorPanel extends JPanel {
    private MainWindow mainWindow;

    private ApplicationController appController;

    public EditProcessorPanel(MainWindow mainWindow, String processorName) {
        this.mainWindow = mainWindow;
        this.appController = new ApplicationController();
        this.setLayout(new BorderLayout());
        try {
            Processor processor = appController.getProcessor(processorName);
            this.add(new ProcessorFormPanel(mainWindow, processor), BorderLayout.CENTER);
        }catch(FailedToGetComponentException e){
            JOptionPane.showMessageDialog(mainWindow, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }
}
