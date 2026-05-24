package MVC.View;

import Toolkit.UIMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JPanel centerPanel;
    private JMenu appMenu, configuratorMenu, componentMenu, searchMenu;
    private JMenuItem home, quit, processor, openConfigurator, motherBoardRamBrand, configurationSearch;
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
        appMenu.setMnemonic(KeyEvent.VK_1);
        appMenu.setDisplayedMnemonicIndex(0);
        home = new JMenuItem("Home");
        quit = new JMenuItem("Quit");
        quit.addActionListener(e -> {System.exit(0);});
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        appMenu.add(home);
        appMenu.add(quit);
        menuBar.add(appMenu);

        configuratorMenu = new JMenu("Configurator");
        configuratorMenu.setMnemonic(KeyEvent.VK_2);
        configuratorMenu.setDisplayedMnemonicIndex(0);
        openConfigurator = new JMenuItem("Open");
        configuratorMenu.add(openConfigurator);
        openConfigurator.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.getContentPane().add(new ConfiguratorPanel(this));
            UIMode.applyCurrentTheme(this);
            this.revalidate();
            this.repaint();
        });
        menuBar.add(configuratorMenu);

        componentMenu = new JMenu("Components");
        processor = new JMenuItem("Processors");
        componentMenu.setMnemonic(KeyEvent.VK_3);
        componentMenu.setDisplayedMnemonicIndex(0);
        componentMenu.add(processor);
        menuBar.add(componentMenu);

        searchMenu = new JMenu("Search");
        searchMenu.setMnemonic(KeyEvent.VK_4);
        searchMenu.setDisplayedMnemonicIndex(0);
        motherBoardRamBrand = new JMenuItem("Search motherboards");
        motherBoardRamBrand.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.getContentPane().add(new SearchMotherBoardPanel(this));
            UIMode.applyCurrentTheme(this);
            this.revalidate();
            this.repaint();
        });
        searchMenu.add(motherBoardRamBrand);
        configurationSearch = new JMenuItem("search configurations");
        configurationSearch.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.getContentPane().add(new SearchConfigurationPanel(this));
            UIMode.applyCurrentTheme(this);
            this.revalidate();
            this.repaint();
        });
        searchMenu.add(configurationSearch);
        menuBar.add(searchMenu);

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

        mainContainer.add(new HomePanel(), BorderLayout.CENTER);

        processor.addActionListener(e -> {
            this.getContentPane().removeAll();
            this.getContentPane().add(new ReadProcessorsPanel(this));
            UIMode.applyCurrentTheme(this);
            this.revalidate();
            this.repaint();
        });
        home.addActionListener(e -> {
            boolean alreadyHome = Arrays.stream(this.getContentPane().getComponents())
                    .anyMatch(c -> c instanceof HomePanel);
            if (!alreadyHome) {
                this.getContentPane().removeAll();
                this.getContentPane().add(new HomePanel(), BorderLayout.CENTER);
                UIMode.applyCurrentTheme(this);
                this.revalidate();
                this.repaint();
            }
        });

        setVisible(true);
    }
}
