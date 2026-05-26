package MVC.View;

import Exceptions.AllComponentsException;
import Exceptions.EmptyFieldException;
import MVC.Controller.ApplicationController;
import MVC.Model.Storage;
import MVC.Model.StorageCoolingSearch;
import MVC.Model.Cooling;
import MVC.Model.MotherBoard;
import Toolkit.UIMode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchStorageCoolingPanel extends JPanel {
    private JComboBox<String> storageComboBox, coolingComboBox, motherBoardComboBox;
    private JButton backButton, validateButton, resetButton;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private ApplicationController appController;
    private MainWindow mw;
    public SearchStorageCoolingPanel(MainWindow mw) {
        this.appController = new ApplicationController();
        this.mw = mw;

        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        try {
            ArrayList<Storage> storages = appController.getAllStorages();
            storageComboBox = new JComboBox<>();
            storageComboBox.addItem("Sélectionnez un stockage");
            for (Storage storage : storages) {
                storageComboBox.addItem(storage.getName());
            }
            formPanel.add(storageComboBox);

            ArrayList<Cooling> coolings = appController.getAllCoolings();
            coolingComboBox = new JComboBox<>();
            coolingComboBox.addItem("Sélectionnez un refroidissement");
            for (Cooling cooling : coolings) {
                coolingComboBox.addItem(cooling.getName());
            }
            formPanel.add(coolingComboBox);

            ArrayList<MotherBoard> motherBoards = appController.getAllMotherBoards();
            motherBoardComboBox = new JComboBox<>();
            motherBoardComboBox.addItem("Sélectionnez une carte mère");
            for (MotherBoard motherBoard : motherBoards) {
                motherBoardComboBox.addItem(motherBoard.getName());
            }
        } catch (AllComponentsException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        formPanel.add(new JLabel("Stockage :"));
        formPanel.add(storageComboBox);

        formPanel.add(new JLabel("Refroidissement :"));
        formPanel.add(coolingComboBox);

        formPanel.add(new JLabel("Carte Mère :"));
        formPanel.add(motherBoardComboBox);


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
                if (motherBoardComboBox.getSelectedIndex() == 0) {
                    throw new EmptyFieldException("carte mère");
                }

                searchConfiguration();
            } catch (EmptyFieldException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);

            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            storageComboBox.setSelectedIndex(0);
            coolingComboBox.setSelectedIndex(0);
            motherBoardComboBox.setSelectedIndex(0);
        });

        buttonPanel.add(backButton);
        buttonPanel.add(validateButton);
        buttonPanel.add(resetButton);

        formPanel.add(new JLabel()); //Sert a occuper une case vide du grid layout
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Configuration",
                        "Utilisateur",
                        "Date de création",
                        "Capacité stockage",
                        "Vitesse lecture",
                        "Vitesse écriture",
                        "Type refroidissement",
                        "Longueur refroidissement",
                        "Hauteur refroidissment"
                },
                0
        );

        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void searchConfiguration() {
        String storageName = (storageComboBox.getSelectedIndex() == 0 ? "" : (String) storageComboBox.getSelectedItem());
        String coolingName = (coolingComboBox.getSelectedIndex() == 0 ? "" : (String) coolingComboBox.getSelectedItem());
        String motherBoardName = (String) motherBoardComboBox.getSelectedItem();

        tableModel.setRowCount(0);

        ArrayList<StorageCoolingSearch> results =
                appController.searchConfigWithStorageCooling(storageName, coolingName, motherBoardName);

        for (StorageCoolingSearch result : results) {
            tableModel.addRow(new Object[]{
                    result.getConfigurationId(),
                    result.getUserId(),
                    result.getCreationDate(),
                    result.getStorageCapacity(),
                    result.getReadingSpeed(),
                    result.getWritingSpeed(),
                    result.getCoolingType(),
                    result.getCoolingLength(),
                    result.getCoolingHeight()
            });
        }
    }
}
