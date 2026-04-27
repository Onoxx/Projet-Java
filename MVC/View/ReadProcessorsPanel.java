package MVC.View;

import Exceptions.FailedToRemoveComponentException;
import MVC.Controller.ApplicationController;
import MVC.Model.Processor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ReadProcessorsPanel extends JPanel {
    private MainWindow mainWindow;
    private ApplicationController appController;
    private JButton addButton;

    public ReadProcessorsPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        this.setLayout(new BorderLayout());
        appController = new ApplicationController();

        addButton = new JButton("Ajouter");
        addButton.addActionListener(e -> {
            mainWindow.getContentPane().removeAll();
            mainWindow.getContentPane().add(new AddProcessorPanel(mainWindow));
            mainWindow.getContentPane().revalidate();
            mainWindow.getContentPane().repaint();
        });
        this.add(addButton, BorderLayout.NORTH);

        String[] columnNames = {"Nom","Socket","Nombre de coeur","Nombre de threads","Fréquence de base","Fréquence boostée","TDP","GPU intégré","Date de sortie","Marque","Prix", "Modifier", "Supprimer"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        ArrayList<Processor> processors = appController.getAllProcessors();

        for(Processor processor : processors) {
            tableModel.addRow(new Object[] {
                processor.getName(),
                processor.getSocket(),
                processor.getNbCores(),
                processor.getNbThreads(),
                processor.getBaseFrequence(),
                processor.getBoostFrequence(),
                processor.getTdp(),
                processor.isHasGPU(),
                processor.getReleaseDate(),
                processor.getBrand(),
                processor.getPrice(),
                "Modifier",
                "Supprimer"
            });
        }

        JTable table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 11 || column == 12;
            }
            @Override
            public Object getValueAt(int row, int column) {
                Object value = super.getValueAt(row, column);
                if(value instanceof Boolean) {
                    return (Boolean) value ? "✅" : "❌";
                }
                return value == null ? "Inconnu" : value;
            }
        };

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        JButton modifyBtn = new JButton("✏️ Modifier");
        JButton deleteBtn = new JButton("🗑️ Supprimer");

        table.getColumn("Modifier").setCellRenderer((_, _, _, _, _, _) -> modifyBtn);
        table.getColumn("Supprimer").setCellRenderer((_, _, _, _, _, _) -> deleteBtn);

        table.getColumn("Modifier").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton btn = new JButton("✏️ Modifier");
                btn.addActionListener(e -> {
                    fireEditingStopped();
                    String processorName = (String) table.getValueAt(row, 0);
                    mainWindow.getContentPane().removeAll();
                    mainWindow.getContentPane().add(new EditProcessorPanel(mainWindow, processorName));
                    mainWindow.getContentPane().revalidate();
                    mainWindow.getContentPane().repaint();
                });
                return btn;
            }
            @Override public Object getCellEditorValue() { return ""; }
        });

        table.getColumn("Supprimer").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                JButton btn = new JButton("🗑️ Supprimer");
                btn.addActionListener(e -> {
                    fireEditingStopped();
                    String processorName = (String) table.getValueAt(row, 0);
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Supprimer " + processorName + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try{
                            appController.removeProcessor(processorName);
                            ((DefaultTableModel) table.getModel()).removeRow(row);
                        }
                        catch (FailedToRemoveComponentException ex){
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                return btn;
            }
            @Override public Object getCellEditorValue() { return ""; }
        });

        table.setRowHeight(35);

        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
