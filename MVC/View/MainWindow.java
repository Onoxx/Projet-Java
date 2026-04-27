package MVC.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JPanel centerPanel;
    private JMenu appMenu, configuratorMenu, componentMenu, userMenu;
    private JMenuItem home, quit, processor;

    public MainWindow() {
        super("NexaPC");
        setBounds(0, 0, 1280, 720);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        } );

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/icon.png")));

        //JMenuBar Setup
        JMenuBar menuBar = new JMenuBar();
        appMenu = new JMenu("Menu");
        home = new JMenuItem("Home");
        quit = new JMenuItem("Quit");
        quit.addActionListener(e -> {System.exit(0);});
        appMenu.setMnemonic('M');
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

        setJMenuBar(menuBar);

        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        mainContainer.add(new JLabel("Bienvenue chez NexaPC, le configurateur numéro 1 en Belgique!", SwingConstants.CENTER), BorderLayout.NORTH);

        mainContainer.add(new HomePanel(), BorderLayout.CENTER);

        processor.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.getContentPane().add(new ReadProcessorsPanel(this));
            this.revalidate();
            this.repaint();
        });
        home.addActionListener(e -> {
            boolean alreadyHome = Arrays.stream(this.getContentPane().getComponents())
                    .anyMatch(c -> c instanceof HomePanel);
            if (!alreadyHome) {
                this.getContentPane().removeAll();
                this.getContentPane().add(new HomePanel(), BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            }
        });

        setVisible(true);
    }
}
