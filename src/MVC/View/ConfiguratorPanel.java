package MVC.View;

import Exceptions.*;
import MVC.Controller.ApplicationController;
import MVC.Model.*;
import Toolkit.UIMode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class ConfiguratorPanel extends JPanel {
    private JPanel formPanel;
    private JLabel processorLabel, graphicCardLabel, caseLabel, motherBoardLabel,
            ramLabel,userLabel, storageLabel, coolingLabel;
    private JComboBox processorField, graphicCardField, caseField, motherBoardField,
            ramField, userField, storageField, coolingField;
    private JSpinner storageQuantityField, coolingQuantityField;
    private JButton addStorageButton, removeStorageButton, addCoolingButton, removeCoolingButton,
            backButton, resetButton, validateButton,
            increaseStorageQuantity, diminishStorageQuantity, increaseCoolingQuantity, decreaseCoolingQuantity;
    private JPanel contentStoragePanel, contentCoolingPanel;
    private HashMap<Storage, Integer> addedStorages = new HashMap<>();
    private HashMap<Cooling, Integer> addedCoolings = new HashMap<>();

    private MainWindow mainWindow;
    private ApplicationController appController;

    public ConfiguratorPanel(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        this.appController = new ApplicationController();
        setLayout(new BorderLayout());
        JPanel formPanel = getFormPanel();
        this.add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    private JPanel getFormPanel(){
        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,10,10,10);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridy = 0;

        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.CENTER;
        JPanel leftContainer = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new GridLayout(0,2,10,10));
        leftContainer.add(leftPanel);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));

        formPanel.add(leftContainer, constraints);
        try {
            processorLabel = new JLabel("Processeur");
            leftPanel.add(processorLabel);
            processorField = new JComboBox();
            leftPanel.add(processorField);

            processorField.addItem("Sélectionnez un processeur : ");
            ArrayList<Processor> processors = appController.getAllProcessors();
            for(Processor processor : processors){
                processorField.addItem(processor);
            }
            mainWindow.getContentPane().revalidate();
            mainWindow.getContentPane().repaint();


            graphicCardLabel = new JLabel("Carte graphique");
            leftPanel.add(graphicCardLabel);
            graphicCardField = new JComboBox();
            leftPanel.add(graphicCardField);

            graphicCardField.addItem("Sélectionnez une carte graphique");
            ArrayList<GraphicCard> graphicCards = appController.getAllGraphicCards();
            for(GraphicCard graphicCard : graphicCards){
                graphicCardField.addItem(graphicCard);
            }

            caseLabel = new JLabel("Boitier");
            leftPanel.add(caseLabel);
            caseField = new JComboBox();
            leftPanel.add(caseField);

            caseField.addItem("Sélectionnez un boitier");
            ArrayList<Case> cases = appController.getAllCases();
            for(Case currentCase : cases){
                caseField.addItem(currentCase);
            }

            motherBoardLabel = new JLabel("Carte mère");
            leftPanel.add(motherBoardLabel);
            motherBoardField = new JComboBox();
            leftPanel.add(motherBoardField);

            motherBoardField.addItem("Sélectionnez une carte mère");
            ArrayList<MotherBoard> motherBoards= appController.getAllMotherBoards();
            for(MotherBoard motherBoard : motherBoards){
                motherBoardField.addItem(motherBoard);
            }

            ramLabel = new JLabel("RAM");
            leftPanel.add(ramLabel);
            ramField = new JComboBox();
            leftPanel.add(ramField);

            ramField.addItem("Sélectionnez une mémoire vive");
            ArrayList<Ram> rams = appController.getallRams();
            for(Ram currentRam : rams){
                ramField.addItem(currentRam);
            }

            userLabel = new JLabel("Utilisateur");
            leftPanel.add(userLabel);
            userField = new JComboBox();
            leftPanel.add(userField);

            userField.addItem("Sélectionnez un utilisateur");
            ArrayList<User> users = appController.getAllUsers();
            for(User user : users){
                userField.addItem(user);
            }

            JPanel storagePanel = new JPanel();
            storagePanel.setLayout(new BoxLayout(storagePanel, BoxLayout.Y_AXIS));

            JPanel storageTopRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            storageLabel = new JLabel("Stockage");
            storageField = new JComboBox();

            storageField.addItem("Sélectionnez un stockage");
            ArrayList<Storage> storage = appController.getAllStorages();
            for(Storage currentStorage : storage){
                storageField.addItem(currentStorage);
            }
            SpinnerNumberModel modelStorage = new SpinnerNumberModel(1, 1, 5, 1);
            storageQuantityField = new JSpinner(modelStorage);
            addStorageButton = new JButton("Ajouter");

            storageTopRow.add(storageLabel);
            storageTopRow.add(storageField);
            storageTopRow.add(storageQuantityField);
            storageTopRow.add(addStorageButton);

            contentStoragePanel = new JPanel();
            contentStoragePanel.setLayout(new BoxLayout(contentStoragePanel, BoxLayout.Y_AXIS));
            JScrollPane storageScroll = new JScrollPane(contentStoragePanel);
            storageScroll.setPreferredSize(new Dimension(300, 150));

            addStorageButton.addActionListener(e -> {
                if(storageField.getSelectedItem() != null
                        && storageField.getSelectedIndex() != 0
                        && !addedStorages.containsKey(storageField.getSelectedItem())){
                    Storage selectedStorage = (Storage) storageField.getSelectedItem();
                    int quantity = (Integer) storageQuantityField.getValue();
                    addedStorages.put(selectedStorage, quantity);
                    JPanel storageAddedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    storageAddedPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

                    JLabel storageAddedLabel = new JLabel(storageField.getSelectedItem().toString());
                    storageAddedLabel.setPreferredSize(new Dimension(130,25));
                    JLabel quantityAddedLabel = new JLabel(storageQuantityField.getValue().toString());
                    quantityAddedLabel.setPreferredSize(new Dimension(20,25));

                    Dimension btnSize = new Dimension(50, 25);
                    increaseStorageQuantity = new JButton("+");
                    increaseStorageQuantity.setPreferredSize(btnSize);
                    increaseStorageQuantity.addActionListener(ev -> {
                        int currentValue = Integer.parseInt(quantityAddedLabel.getText());
                        if(currentValue < 5) currentValue++;
                        quantityAddedLabel.setText(String.valueOf(currentValue));
                    });
                    diminishStorageQuantity = new JButton("-");
                    diminishStorageQuantity.setPreferredSize(btnSize);
                    diminishStorageQuantity.addActionListener(ev -> {
                        int currentValue = Integer.parseInt(quantityAddedLabel.getText());
                        if(currentValue > 1) currentValue--;
                        quantityAddedLabel.setText(String.valueOf(currentValue));
                    });
                    removeStorageButton = new JButton("🗑️");
                    removeStorageButton.setPreferredSize(btnSize);
                    removeStorageButton.addActionListener(ev -> {
                        contentStoragePanel.remove(storageAddedPanel);
                        contentStoragePanel.revalidate();
                        contentStoragePanel.repaint();
                        addedStorages.remove(storageField.getSelectedItem());
                    });

                    storageAddedPanel.add(storageAddedLabel);
                    storageAddedPanel.add(quantityAddedLabel);
                    storageAddedPanel.add(increaseStorageQuantity);
                    storageAddedPanel.add(diminishStorageQuantity);
                    storageAddedPanel.add(removeStorageButton);

                    contentStoragePanel.add(storageAddedPanel);
                    contentStoragePanel.revalidate();
                    contentStoragePanel.repaint();
                }
            });

            constraints.gridx = 1;

            storagePanel.add(storageTopRow);
            storagePanel.add(storageScroll);
            rightPanel.add(storagePanel);

            JPanel coolingPanel = new JPanel();
            coolingPanel.setLayout(new BoxLayout(coolingPanel, BoxLayout.Y_AXIS));

            JPanel coolingTopRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            coolingLabel = new JLabel("Refroidissement");
            coolingField = new JComboBox();

            coolingField.addItem("Sélectionnez un refroidissement");
            ArrayList<Cooling> coolings = appController.getAllCoolings();
            for(Cooling cooling : coolings){
                coolingField.addItem(cooling);
            }
            SpinnerNumberModel modelCooling = new SpinnerNumberModel(1, 1, 5, 1);
            coolingQuantityField = new JSpinner(modelCooling);
            addCoolingButton = new JButton("Ajouter");

            coolingTopRow.add(coolingLabel);
            coolingTopRow.add(coolingField);
            coolingTopRow.add(coolingQuantityField);
            coolingTopRow.add(addCoolingButton);

            contentCoolingPanel = new JPanel();
            contentCoolingPanel.setLayout(new BoxLayout(contentCoolingPanel, BoxLayout.Y_AXIS));
            JScrollPane coolingScroll = new JScrollPane(contentCoolingPanel);
            coolingScroll.setPreferredSize(new Dimension(300, 150));

            addCoolingButton.addActionListener(e -> {
                if(coolingField.getSelectedItem() != null
                        && coolingField.getSelectedIndex() != 0
                        &&!addedCoolings.containsKey(coolingField.getSelectedItem())){

                    Cooling selectedCooling = (Cooling) coolingField.getSelectedItem();
                    Processor processor = (Processor) processorField.getSelectedItem();
                    Case computerCase = (Case) caseField.getSelectedItem();

                    if(processor != null && !Objects.equals(selectedCooling.getComptabileSocket(), processor.getSocket())){
                        throw new IncompatibleComponentException("socket","refroidissement","processeur");
                    }
                    if(computerCase != null && selectedCooling.getHeight() > computerCase.getMaxHeightVentirad()){
                        throw new IncompatibleComponentException("hauteur","refroidissement","boitier");
                    }

                    int quantity = (Integer) coolingQuantityField.getValue();
                    addedCoolings.put(selectedCooling, quantity);

                    JPanel coolingAddedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    coolingAddedPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

                    JLabel coolingAddedLabel = new JLabel(coolingField.getSelectedItem().toString());
                    coolingAddedLabel.setPreferredSize(new Dimension(130,25));
                    JLabel quantityAddedLabel = new JLabel(coolingQuantityField.getValue().toString());
                    quantityAddedLabel.setPreferredSize(new Dimension(20,25));

                    Dimension btnSize = new Dimension(50, 25);
                    increaseCoolingQuantity = new JButton("+");
                    increaseCoolingQuantity.setPreferredSize(btnSize);
                    increaseCoolingQuantity.addActionListener(ev -> {
                        int currentValue = Integer.parseInt(quantityAddedLabel.getText());
                        if(currentValue < 5) currentValue++;
                        quantityAddedLabel.setText(String.valueOf(currentValue));
                    });
                    decreaseCoolingQuantity = new JButton("-");
                    decreaseCoolingQuantity.setPreferredSize(btnSize);
                    decreaseCoolingQuantity.addActionListener(ev -> {
                        int currentValue = Integer.parseInt(quantityAddedLabel.getText());
                        if(currentValue > 1) currentValue--;
                        quantityAddedLabel.setText(String.valueOf(currentValue));
                    });
                    removeCoolingButton = new JButton("🗑️");
                    removeCoolingButton.setPreferredSize(btnSize);
                    removeCoolingButton.addActionListener(ev -> {
                        contentCoolingPanel.remove(coolingAddedPanel);
                        contentCoolingPanel.revalidate();
                        contentCoolingPanel.repaint();
                        addedCoolings.remove(coolingField.getSelectedItem());
                    });

                    coolingAddedPanel.add(coolingAddedLabel);
                    coolingAddedPanel.add(quantityAddedLabel);
                    coolingAddedPanel.add(increaseCoolingQuantity);
                    coolingAddedPanel.add(decreaseCoolingQuantity);
                    coolingAddedPanel.add(removeCoolingButton);

                    contentCoolingPanel.add(coolingAddedPanel);
                    contentCoolingPanel.revalidate();
                    contentCoolingPanel.repaint();
                }
            });

            coolingPanel.add(coolingTopRow);
            coolingPanel.add(coolingScroll);
            rightPanel.add(coolingPanel);

            constraints.gridx = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weighty = 0;
            constraints.anchor = GridBagConstraints.CENTER;
            formPanel.add(rightPanel, constraints);

            GridBagConstraints btnConstraints = new GridBagConstraints();
            btnConstraints.gridx = 0;
            btnConstraints.gridy = 1;
            btnConstraints.gridwidth = 2;
            btnConstraints.anchor = GridBagConstraints.CENTER;
            btnConstraints.fill = GridBagConstraints.NONE;
            btnConstraints.weighty = 0;
            btnConstraints.insets = new Insets(10,10,10,10);
            formPanel.add(getButtonsPanel(), btnConstraints);
        }catch(AllComponentsException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
        }
        return formPanel;
    }
    private JPanel getButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,3));
        buttonsPanel.setPreferredSize(new Dimension(500,40));

        backButton = new JButton("Retour");
        backButton.addActionListener(e -> {
            mainWindow.getContentPane().removeAll();
            mainWindow.add(new HomePanel());
            UIMode.applyCurrentTheme(mainWindow);
            mainWindow.revalidate();
            mainWindow.repaint();
        });
        validateButton = new JButton("Valider");
        validateButton.addActionListener(e -> {
            try{
                if(userField.getSelectedIndex() == 0){
                    throw new EmptyFieldException("utilisateur");
                }
                Processor processor = null;
                GraphicCard graphicCard = null;
                Case computerCase = null;
                MotherBoard motherBoard = null;
                Ram ram = null;

                if(processorField.getSelectedIndex() != 0){
                    processor = (Processor) processorField.getSelectedItem();
                }
                if(graphicCardField.getSelectedIndex() != 0){
                    graphicCard = (GraphicCard) graphicCardField.getSelectedItem();
                }
                if(caseField.getSelectedIndex() != 0){
                    computerCase = (Case) caseField.getSelectedItem();
                }
                if(motherBoardField.getSelectedIndex() != 0){
                    motherBoard = (MotherBoard) motherBoardField.getSelectedItem();
                }
                if(ramField.getSelectedIndex() != 0){
                    ram = (Ram) ramField.getSelectedItem();
                }
                User user = (User) userField.getSelectedItem();

                Configuration configuration = new Configuration(
                        user,
                        processor,
                        graphicCard,
                        computerCase,
                        motherBoard,
                        ram,
                        new Date()
                );
                verifyConfiguration(configuration, addedStorages, addedCoolings);

                appController.addConfiguration(configuration, addedStorages, addedCoolings);
                JOptionPane.showMessageDialog(null, "Ajout réussi !", "Validation",JOptionPane.INFORMATION_MESSAGE);
                resetForm();
            }catch(EmptyFieldException | FailedToAddComponentException | IncompatibleComponentException | InvalidQuantityException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            resetForm();
        });
        buttonsPanel.add(backButton);
        buttonsPanel.add(validateButton);
        buttonsPanel.add(resetButton);

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(buttonsPanel);
        return wrapperPanel;
    }
    private void resetForm(){
        processorField.setSelectedIndex(0);
        graphicCardField.setSelectedIndex(0);
        caseField.setSelectedIndex(0);
        motherBoardField.setSelectedIndex(0);
        ramField.setSelectedIndex(0);
        userField.setSelectedIndex(0);
        storageField.setSelectedIndex(0);
        storageQuantityField.setValue(1);
        coolingField.setSelectedIndex(0);
        coolingQuantityField.setValue(1);
        addedStorages.clear();
        contentStoragePanel.removeAll();
        contentStoragePanel.revalidate();
        contentStoragePanel.repaint();
        addedCoolings.clear();
        contentCoolingPanel.removeAll();
        contentCoolingPanel.revalidate();
        contentCoolingPanel.repaint();
    }
    private void verifyConfiguration(Configuration configuration, HashMap<Storage, Integer> storages, HashMap<Cooling, Integer> coolings) {
        Processor currentProcessor = configuration.getProcessor();
        GraphicCard currentGraphicCard = configuration.getGraphicCard();
        Case computerCase = configuration.getComputerCase();
        MotherBoard currentMotherBoard = configuration.getMotherBoard();
        Ram currentRam = configuration.getRam();
        if(currentProcessor != null && currentMotherBoard != null
                && !Objects.equals(currentProcessor.getSocket(), currentMotherBoard.getSocket())){
            throw new IncompatibleComponentException("socket","processeur","carte mère");
        }
        if(currentGraphicCard != null && computerCase != null
                && currentGraphicCard.getLength() > computerCase.getMaxLengthGPU()){
            throw new IncompatibleComponentException("longueur","carte graphique","boitier");
        }
        if(computerCase != null && currentMotherBoard != null
                && !Objects.equals(computerCase.getFomat(), currentMotherBoard.getFormat())){
            throw new IncompatibleComponentException("fomat","boitier","carte mère");
        }
        if(currentMotherBoard != null && currentRam != null
                && !Objects.equals(currentMotherBoard.getRamType(), currentRam.getType())){
            throw new IncompatibleComponentException("type de ram","carte mère","ram");
        }
        for(HashMap.Entry<Cooling, Integer> entry : coolings.entrySet()) {
            Cooling cooling = entry.getKey();
            int quantity = entry.getValue();
            if(quantity < 1){
                throw new InvalidQuantityException(quantity);
            }
            if(currentProcessor != null &&
                    !Objects.equals(
                            cooling.getComptabileSocket(),
                            currentProcessor.getSocket())){
                throw new IncompatibleComponentException(
                        "socket",
                        "refroidissement",
                        "processeur"
                );
            }
            if(computerCase != null &&
                    cooling.getHeight() > computerCase.getMaxHeightVentirad()){
                throw new IncompatibleComponentException("taille","refroidissement","boitier");
            }
        }
        for(HashMap.Entry<Storage, Integer> entry : storages.entrySet()) {
            int quantity = entry.getValue();
            if(quantity < 1){
                throw new InvalidQuantityException(quantity);
            }
        }
    }
}