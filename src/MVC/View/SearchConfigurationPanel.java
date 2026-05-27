package MVC.View;

import Exceptions.AllComponentsException;
import Exceptions.EmptyFieldException;
import MVC.Controller.ApplicationController;
import MVC.Model.*;
import Toolkit.UIMode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchConfigurationPanel extends JPanel {
    private JComboBox<String> rgbComboBox;
    private JComboBox userComboBox;
    private JSpinner monthsSpinner;
    private JButton backButton, validateButton, resetButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ApplicationController appController;
    private MainWindow mw;

    public SearchConfigurationPanel(MainWindow mw) {
        this.appController = new ApplicationController();
        this.mw = mw;

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        try {
            ArrayList<User> users = appController.getAllUsers();
            userComboBox = new JComboBox<>();
            userComboBox.addItem("Sélectionnez un utilisateur");

            for (User user : users) {
                userComboBox.addItem(user);
            }
        } catch (AllComponentsException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        rgbComboBox = new JComboBox<>(new String[]{
                "Sélectionnez RGB",
                "Oui",
                "Non"
        });

        monthsSpinner = new JSpinner(
                new SpinnerNumberModel(1, 0, 120, 1)
        );

        formPanel.add(new JLabel("Utilisateur :"));
        formPanel.add(userComboBox);

        formPanel.add(new JLabel("Ancienneté en mois :"));
        formPanel.add(monthsSpinner);

        formPanel.add(new JLabel("Boîtier RGB :"));
        formPanel.add(rgbComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            mw.getContentPane().removeAll();
            mw.add(new HomePanel());
            UIMode.applyCurrentTheme(mw);
            mw.repaint();
            mw.revalidate();
        });

        validateButton = new JButton("Valider");
        validateButton.addActionListener(e -> {
            try {
                if (userComboBox.getSelectedIndex() == 0) {
                    throw new EmptyFieldException("utilisateur");
                }

                if (rgbComboBox.getSelectedIndex() == 0) {
                    throw new EmptyFieldException("rgb");
                }

                searchConfigurations();

            } catch (EmptyFieldException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            userComboBox.setSelectedIndex(0);
            rgbComboBox.setSelectedIndex(0);
            monthsSpinner.setValue(0);
        });

        buttonPanel.add(backButton);
        buttonPanel.add(validateButton);
        buttonPanel.add(resetButton);

        formPanel.add(new JLabel()); //Sert a occuper une cellule vide du grid layout
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Configuration",
                        "Processeur",
                        "Cœurs",
                        "Fréquence base",
                        "Fréquence boost",
                        "Carte graphique",
                        "Chipset",
                        "VRAM",
                        "Type VRAM",
                        "RAM",
                        "Capacité RAM",
                        "Nombre barrettes",
                        "Type RAM"
                },
                0
        );

        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void searchConfigurations() {
        User user = (User) userComboBox.getSelectedItem();
        int months = (int) monthsSpinner.getValue();
        boolean hasRGB = rgbComboBox.getSelectedItem().equals("Oui");

        tableModel.setRowCount(0);

        ArrayList<ConfigurationSearch> results =
                appController.searchConfigByUserDateRGB(user, months, hasRGB);

        for (ConfigurationSearch result : results) {
            tableModel.addRow(new Object[]{
                    result.getConfigurationId(),
                    result.getProcessorName(),
                    result.getNbCores(),
                    result.getBaseFrequence(),
                    result.getBoostFrequence(),
                    result.getGraphicCardName(),
                    result.getChipset(),
                    result.getvRamCapacity(),
                    result.getvRamType(),
                    result.getRamName(),
                    result.getCapacity(),
                    result.getNbRamSticks(),
                    result.getRamType()
            });
        }
    }
}