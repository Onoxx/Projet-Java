package MVC.View;

import Exceptions.FailedToGetComponentException;
import Exceptions.ValueASmallerThanBException;
import MVC.Controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class StatisticsPanel extends JPanel {

    private JPanel formPanel;
    private JPanel contentPanel;

    private JLabel minValueLabel, maxValueLabel;
    private JSpinner minValueSpinner, maxValueSpinner;
    private JButton searchButton;

    private ApplicationController applicationController;

    public StatisticsPanel() {
        this.applicationController = new ApplicationController();

        setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        add(getFormPanel(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel getFormPanel() {

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Nombre de configurations entre deux dates");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        minValueLabel = new JLabel("Date minimum :");
        maxValueLabel = new JLabel("Date maximum :");

        minValueSpinner = new JSpinner(new SpinnerDateModel(
                new Date(), null, null, Calendar.DAY_OF_MONTH));

        maxValueSpinner = new JSpinner(new SpinnerDateModel(
                new Date(), null, null, Calendar.DAY_OF_MONTH));

        minValueSpinner.setPreferredSize(new Dimension(120, 25));
        maxValueSpinner.setPreferredSize(new Dimension(120, 25));

        minValueSpinner.setEditor(new JSpinner.DateEditor(minValueSpinner, "dd/MM/yyyy"));
        maxValueSpinner.setEditor(new JSpinner.DateEditor(maxValueSpinner, "dd/MM/yyyy"));

        formPanel.add(minValueLabel);
        formPanel.add(minValueSpinner);
        formPanel.add(maxValueLabel);
        formPanel.add(maxValueSpinner);

        searchButton = new JButton("Rechercher");

        searchButton.addActionListener(
                e -> updateStats()
        );

        formPanel.add(searchButton);

        containerPanel.add(Box.createVerticalStrut(10));
        containerPanel.add(titleLabel);
        containerPanel.add(Box.createVerticalStrut(10));
        containerPanel.add(formPanel);

        return containerPanel;
    }

    private void updateStats() {
        try {
            contentPanel.removeAll();

            Date date1 = (Date) minValueSpinner.getValue();
            Date date2 = (Date) maxValueSpinner.getValue();

            if(date1.after(date2)) {
                throw new ValueASmallerThanBException("date minimale","date maximale");
            }
            int count = applicationController.countConfiguration(date1, date2);

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
            card.setBackground(new Color(245, 245, 245));

            JLabel title = new JLabel("Résultat");
            title.setFont(new Font("Arial", Font.BOLD, 16));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel value = new JLabel(String.valueOf(count));
            value.setFont(new Font("Arial", Font.BOLD, 42));
            value.setForeground(new Color(0, 120, 215));
            value.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel subtitle = new JLabel("configurations trouvées");
            subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
            subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            card.add(title);
            card.add(Box.createVerticalStrut(10));
            card.add(value);
            card.add(Box.createVerticalStrut(10));
            card.add(subtitle);

            contentPanel.add(card, BorderLayout.CENTER);

            contentPanel.revalidate();
            contentPanel.repaint();

        } catch (FailedToGetComponentException | ValueASmallerThanBException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}