package MVC.View;

import Exceptions.AllComponentsException;
import Exceptions.EmptyFieldException;
import MVC.Controller.ApplicationController;
import MVC.Model.MotherBoardRamBrand;
import MVC.Model.Ram;
import Toolkit.UIMode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchMotherBoardPanel extends JPanel {
    private JComboBox<String> formatComboBox, ramComboBox;
    private JSpinner maxPriceSpinner;
    private JButton backButton, validateButton, resetButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ApplicationController appController;
    private MainWindow mw;
    public SearchMotherBoardPanel(MainWindow mw) {
        this.appController = new ApplicationController();
        this.mw = mw;

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formatComboBox = new JComboBox<>(new String[]{
                "Sélectionnez un format de carte mère",
                "ATX",
                "Micro-ATX",
                "Mini-ITX",
                "E-ATX"
        });

        try {
            ArrayList<Ram> rams = appController.getallRams();
            ramComboBox = new JComboBox<>();
            ramComboBox.addItem("Sélectionnez une mémoire vive");
            for (Ram ram : rams) {
                ramComboBox.addItem(ram.getName());
            }
            formPanel.add(ramComboBox);
        } catch (AllComponentsException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        maxPriceSpinner = new JSpinner(
                new SpinnerNumberModel(100.0, 0.0, 2000.0, 10.0)
        );

        formPanel.add(new JLabel("Format carte mère :"));
        formPanel.add(formatComboBox);

        formPanel.add(new JLabel("RAM :"));
        formPanel.add(ramComboBox);

        formPanel.add(new JLabel("Prix maximum :"));
        formPanel.add(maxPriceSpinner);


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
                if (formatComboBox.getSelectedIndex() == 0) {
                    throw new EmptyFieldException("format");
                }

                if (ramComboBox.getSelectedIndex() == 0) {
                    throw new EmptyFieldException("ram");
                }

                searchMotherBoards();
            } catch (EmptyFieldException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);

            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            formatComboBox.setSelectedIndex(0);
            ramComboBox.setSelectedIndex(0);
            maxPriceSpinner.setValue(100);
        });

        buttonPanel.add(backButton);
        buttonPanel.add(validateButton);
        buttonPanel.add(resetButton);

        formPanel.add(new JLabel());
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Carte mère",
                        "Modèle",
                        "Slots RAM",
                        "Type RAM",
                        "Capacité RAM",
                        "Marque",
                        "Site web",
                        "Pays"
                },
                0
        );

        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void searchMotherBoards() {
        String format = (String) formatComboBox.getSelectedItem();
        String ramName = (String) ramComboBox.getSelectedItem();
        double maxPrice = (double) maxPriceSpinner.getValue();

        tableModel.setRowCount(0);

        ArrayList<MotherBoardRamBrand> results =
                appController.searchByFormatRamMaxPrice(format, ramName, maxPrice);

        for (MotherBoardRamBrand result : results) {
            tableModel.addRow(new Object[]{
                    result.getMotherBoardName(),
                    result.getMotherBoardmodel(),
                    result.getNbRamSlots(),
                    result.getRamType(),
                    result.getRamCapacity(),
                    result.getBrandName(),
                    result.getWebsite(),
                    result.getCountry()
            });
        }
    }
}
