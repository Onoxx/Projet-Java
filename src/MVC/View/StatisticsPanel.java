package MVC.View;

import MVC.Controller.ApplicationController;
import MVC.Model.*;
import Toolkit.UIMode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StatisticsPanel extends JPanel {
    private MainWindow mainWindow;
    private ApplicationController appController;

    private JComboBox<Configuration> configurationComboBox;
    private JTable componentTable;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;

    public StatisticsPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.appController = new ApplicationController();
        setLayout(new BorderLayout(10, 10));

        totalLabel = new JLabel("Total : 0.00 €", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));

        add(getTitlePanel(), BorderLayout.NORTH);
        add(getCenterPanel(), BorderLayout.CENTER);
        add(getBottomPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel getTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("Résumé de configuration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel selectLabel = new JLabel("Configuration : ");
        configurationComboBox = new JComboBox<>();

        try {
            ArrayList<Configuration> configurations = appController.getConfigurations();
            for (Configuration config : configurations) {
                configurationComboBox.addItem(config);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        configurationComboBox.addActionListener(e -> refreshTable());

        titlePanel.add(titleLabel);
        titlePanel.add(Box.createHorizontalStrut(20));
        titlePanel.add(selectLabel);
        titlePanel.add(configurationComboBox);

        return titlePanel;
    }

    private JPanel getCenterPanel() {
        JPanel centerPanel = new JPanel(new BorderLayout());

        String[] columns = {"Composant", "Détail", "Qté", "Prix unitaire", "Sous-total"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        componentTable = new JTable(tableModel);
        componentTable.setRowHeight(25);
        componentTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(componentTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Afficher la première config si elle existe
        if (configurationComboBox.getItemCount() > 0) {
            refreshTable();
        }

        return centerPanel;
    }

    private JPanel getBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            mainWindow.getContentPane().removeAll();
            mainWindow.add(new HomePanel());
            UIMode.applyCurrentTheme(mainWindow);
            mainWindow.revalidate();
            mainWindow.repaint();
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backButton);

        bottomPanel.add(backPanel, BorderLayout.WEST);
        bottomPanel.add(totalLabel, BorderLayout.EAST);

        return bottomPanel;
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        Configuration config = (Configuration) configurationComboBox.getSelectedItem();
        if (config == null) return;

        addComponentRow("Processeur",     config.getProcessor()    != null ? config.getProcessor().getName()    : null,
                config.getProcessor()    != null ? config.getProcessor().getPrice()   : 0, 1);
        addComponentRow("Carte graphique",config.getGraphicCard()  != null ? config.getGraphicCard().getName()  : null,
                config.getGraphicCard()  != null ? config.getGraphicCard().getPrice() : 0, 1);
        addComponentRow("Boitier",        config.getComputerCase() != null ? config.getComputerCase().getName() : null,
                config.getComputerCase() != null ? config.getComputerCase().getPrice(): 0, 1);
        addComponentRow("Carte mère",     config.getMotherBoard()  != null ? config.getMotherBoard().getName()  : null,
                config.getMotherBoard()  != null ? config.getMotherBoard().getPrice() : 0, 1);
        addComponentRow("RAM",            config.getRam()          != null ? config.getRam().getName()          : null,
                config.getRam()          != null ? config.getRam().getPrice()         : 0, 1);

        for (HashMap.Entry<Storage, Integer> entry : config.getStorages().entrySet()) {
            addComponentRow("Stockage", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue());
        }
        for (HashMap.Entry<Cooling, Integer> entry : config.getCoolings().entrySet()) {
            addComponentRow("Refroidissement", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue());
        }

        double total = appController.computeTotalPrice(config);
        totalLabel.setText("Total : " + String.format("%.2f", total) + " €");
    }

    private void addComponentRow(String type, String name, double price, int quantity) {
        if (name == null) return;
        double subtotal = price * quantity;
        tableModel.addRow(new Object[]{
                type,
                name,
                quantity,
                String.format("%.2f €", price),
                String.format("%.2f €", subtotal)
        });
    }
}