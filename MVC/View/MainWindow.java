package MVC.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Toolkit.UIMode;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JPanel centerPanel;
    private JMenu appMenu, configuratorMenu, componentMenu, userMenu;
    private JMenuItem home, quit, processor;
    private JToggleButton uiMode;

    public MainWindow() {
        super("NexaPC");
        setBounds(0, 0, 1280, 720);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        } );

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));
        UIMode.applyClassicMode(this);

        //JMenuBar Setup
        JMenuBar menuBar = new JMenuBar();

        appMenu = new JMenu("Menu");
        appMenu.setMnemonic('M');
        home = new JMenuItem("Home");
        quit = new JMenuItem("Quit");
        quit.addActionListener(e -> {System.exit(0);});
        appMenu.add(home);
        appMenu.add(quit);
        menuBar.add(appMenu);

        configuratorMenu = new JMenu("Configurator");
        configuratorMenu.setMnemonic('C');
        menuBar.add(configuratorMenu);

        componentMenu = new JMenu("Components");
        processor = new JMenuItem("Processors");
        componentMenu.setMnemonic('C');
        componentMenu.add(processor);
        menuBar.add(componentMenu);

        userMenu = new JMenu("Users");
        userMenu.setMnemonic('U');
        menuBar.add(userMenu);

        menuBar.add(Box.createHorizontalGlue()); //colle les éléments a droite de la menuBar
        uiMode = new JToggleButton("DarkMode");
        uiMode.addActionListener(e -> {
            if (uiMode.isSelected()) {
                uiMode.setText("LightMode");
                UIMode.applyDarkMode(this);
            } else {
                uiMode.setText("DarkMode");
                UIMode.applyClassicMode(this);
            }
        });
        uiMode.setFocusPainted(false); //enlève la séléction
        uiMode.setFocusable(false); // et la bloque

        menuBar.add(uiMode);

        setJMenuBar(menuBar);

        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        mainContainer.add(new JLabel("Bienvenue chez NexaPC, le configurateur numéro 1 en Belgique!", SwingConstants.CENTER), BorderLayout.NORTH);

        centerPanel = new JPanel();
        mainContainer.add(new HomePanel(), BorderLayout.CENTER);
        //mainContainer.add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public JPanel getCenterPanel(){
        return centerPanel;
    }
}
