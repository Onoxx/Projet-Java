package MVC.View;

import Exceptions.AllComponentsException;
import Exceptions.EmptyFieldException;
import Exceptions.FailedToAddProcessorException;
import MVC.Controller.ApplicationController;
import MVC.Model.Brand;
import MVC.Model.Processor;
import MVC.Model.Socket;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AddProcessorPanel extends JPanel {
    private JPanel formPanel, buttonsPanel;
    private JLabel nameLabel, socketLabel, nbCoresLabel, nbThreadsLabel, baseFrequenceLabel, boostFrequenceLabel;
    private JLabel tdpLabel, priceLabel, releaseDateLabel, brandLabel, hasGPULabel;
    private JLabel hasBoostFrequenceLabel, hasNbThreadsLabel;

    private JTextField nameField;
    private JSpinner nbCoresField, nbThreadsField, baseFrequenceField, boostFrequenceField, tdpField,releaseDateField, priceField;
    private JComboBox socketField, brandField;
    private JCheckBox hasGPUField, hasBoostFrequenceField, hasNbThreadsField;

    private JButton resetButton, validateButton, backButton;

    private MainWindow mainWindow;

    private ApplicationController appController;

    public AddProcessorPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.appController = new ApplicationController();

        JPanel formPanel = getFormPanel();
        this.add(formPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = getButtonsPanel();
        this.add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void resetForm(){
        nameField.setText("");
        socketField.setSelectedIndex(0);
        nbCoresField.setValue(4);
        nbThreadsField.setValue(8);
        baseFrequenceField.setValue(3.0);
        boostFrequenceField.setValue(4.5);
        tdpField.setValue(65);
        priceField.setValue(250);
        hasGPUField.setSelected(false);
        releaseDateField.setValue(new Date());
    }

    private JPanel getFormPanel() {
        formPanel = new JPanel();
        this.setLayout(new BorderLayout());
        formPanel.setLayout(new GridLayout(13, 2));

        nameLabel = new JLabel("Nom : ");
        formPanel.add(nameLabel);
        nameField = new JTextField();
        formPanel.add(nameField);

        socketLabel = new JLabel("Socket : ");
        formPanel.add(socketLabel);
        try{
            ArrayList<Socket> sockets = appController.getAllSockets();
            socketField = new JComboBox();

            for(Socket socket : sockets){
                socketField.addItem(socket.getName());
            }

            formPanel.add(socketField);

        }catch(AllComponentsException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        nbCoresLabel = new JLabel("Nombre de coeurs: ");
        formPanel.add(nbCoresLabel);
        nbCoresField = new JSpinner(new SpinnerNumberModel(4, 1, 128, 1));
        formPanel.add(nbCoresField);

        hasNbThreadsLabel = new JLabel("Nombre de threads connu");
        formPanel.add(hasNbThreadsLabel);
        hasNbThreadsField = new JCheckBox();
        formPanel.add(hasNbThreadsField);
        hasNbThreadsField.addActionListener(e ->{
            boolean enabled = hasNbThreadsField.isSelected();
            nbThreadsField.setEnabled(enabled);
            if(!enabled){
                nbThreadsField.setValue(0);
            }
            else{
                nbThreadsField.setValue(8);
            }
        });

        nbThreadsLabel = new JLabel("Nombre de threads: ");
        formPanel.add(nbThreadsLabel);
        nbThreadsField = new JSpinner(new SpinnerNumberModel(8, 1, 256, 1));
        nbThreadsField.setValue(0);
        nbThreadsField.setEnabled(hasNbThreadsField.isSelected());
        formPanel.add(nbThreadsField);

        baseFrequenceLabel = new JLabel("Frequence de base : ");
        formPanel.add(baseFrequenceLabel);
        SpinnerNumberModel modelBaseFrequence = new SpinnerNumberModel(3.0,0.5, 6.0, 0.1);
        baseFrequenceField = new JSpinner(modelBaseFrequence);
        formPanel.add(baseFrequenceField);

        hasBoostFrequenceLabel = new JLabel("Frequence de boost connue ");
        formPanel.add(hasBoostFrequenceLabel);
        hasBoostFrequenceField = new JCheckBox();
        formPanel.add(hasBoostFrequenceField);
        hasBoostFrequenceField.addActionListener(e ->{
            boolean enabled = hasBoostFrequenceField.isSelected();
            boostFrequenceField.setEnabled(enabled);

            if(!enabled){
                boostFrequenceField.setValue(0);
            }
            else{
                boostFrequenceField.setValue(4.5);
            }
        });

        boostFrequenceLabel = new JLabel("Frequence boostée : ");
        formPanel.add(boostFrequenceLabel);
        SpinnerNumberModel modelBoostFrequence = new SpinnerNumberModel(4.5,1.0, 7.0, 0.1);
        boostFrequenceField = new JSpinner(modelBoostFrequence);
        boostFrequenceField.setValue(0);
        boostFrequenceField.setEnabled(hasBoostFrequenceField.isSelected());
        formPanel.add(boostFrequenceField);

        tdpLabel = new JLabel("Consommation électrique : ");
        formPanel.add(tdpLabel);
        tdpField = new JSpinner(new SpinnerNumberModel(65, 1, 500, 1));
        formPanel.add(tdpField);

        hasGPULabel = new JLabel("GPU intégré : ");
        formPanel.add(hasGPULabel);
        hasGPUField = new JCheckBox();
        formPanel.add(hasGPUField);

        priceLabel = new JLabel("Prix : ");
        formPanel.add(priceLabel);
        SpinnerNumberModel modelPrice = new SpinnerNumberModel(250,0.0, 5000, 0.01);
        priceField = new JSpinner(modelPrice);
        formPanel.add(priceField);

        releaseDateLabel = new JLabel("Date de sortie : ");
        formPanel.add(releaseDateLabel);
        SpinnerDateModel modelDate = new SpinnerDateModel(
                new java.util.Date(),
                null,
                null,
                java.util.Calendar.DAY_OF_MONTH
        );
        releaseDateField = new JSpinner(modelDate);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(releaseDateField, "dd/MM/yyyy");
        releaseDateField.setEditor(editor);

        formPanel.add(releaseDateField);

        brandLabel = new JLabel("Marque : ");
        formPanel.add(brandLabel);
        try{
            ArrayList<Brand> brands = appController.getAllBrands();
            brandField = new JComboBox();

            for(Brand brand : brands){
                brandField.addItem(brand.getName());
            }

            formPanel.add(brandField);

        }catch(AllComponentsException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return formPanel;
    }

    private JPanel getButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));

        backButton = new JButton("Retour");
        // Ajouter retour à la fenêtre principale, à voir avec Noa
        validateButton = new JButton("Valider");
        validateButton.addActionListener(e->{
            try{
                if(nameField.getText().trim().equals("")){
                    throw new EmptyFieldException("nom");
                }
                Integer nbThreads = hasNbThreadsField.isSelected() ? (int)nbThreadsField.getValue() : null;
                Double boostFrequence = hasBoostFrequenceField.isSelected() ? (double)boostFrequenceField.getValue() : null;
                Processor processorToAdd = new Processor(
                        nameField.getText(),
                        socketField.getSelectedItem().toString(),
                        brandField.getSelectedItem().toString(),
                        (int)nbCoresField.getValue(),
                        nbThreads,
                        (int)tdpField.getValue(),
                        (double)baseFrequenceField.getValue(),
                        boostFrequence,
                        (double)priceField.getValue(),
                        hasGPUField.isSelected(),
                        (Date)releaseDateField.getValue()
                );
                appController.addProcessor(processorToAdd);
                JOptionPane.showMessageDialog(null, "Ajout du processeur réussi", "Réussite", JOptionPane.INFORMATION_MESSAGE);
                resetForm();
            }catch(FailedToAddProcessorException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }catch(EmptyFieldException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e->{
            resetForm();
        });
        buttonsPanel.add(backButton);
        buttonsPanel.add(validateButton);
        buttonsPanel.add(resetButton);

        return buttonsPanel;
    }
}
