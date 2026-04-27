package MVC.View;
import MVC.Controller.ApplicationController;
import javax.swing.*;
import java.awt.*;

public class AddProcessorPanel extends JPanel {
    private MainWindow mainWindow;
    private ApplicationController appController;

    public AddProcessorPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.appController = new ApplicationController();

        this.setLayout(new BorderLayout());

        this.add(new ProcessorFormPanel(mainWindow, null), BorderLayout.CENTER);
    }
}
