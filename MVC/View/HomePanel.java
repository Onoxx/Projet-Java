package MVC.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    private JToggleButton enButton;
    private JToggleButton frButton;
    private ButtonGroup langGroup;
    private JLabel welcome;
    private JLabel getStarted;
    private JLabel tuto;
    private JLabel contact;
    private String text;
    private float offset = 0;

    public HomePanel() {
        JPanel infos = new JPanel();
        infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS));

        welcome = new JLabel("", SwingConstants.CENTER);
        getStarted = new JLabel("", SwingConstants.CENTER);
        tuto = new JLabel("", SwingConstants.CENTER
        );
        tuto.setFont(new Font("Arial", Font.PLAIN, 14));

        infos.add(welcome);
        infos.add(getStarted);
        infos.add(tuto);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contact = new JLabel("App created by Leo Clarinval and Noa Forseille.");
        leftPanel.add(contact);

        langGroup = new ButtonGroup();
        LangButtonListener listener = new LangButtonListener();
        enButton = new JToggleButton("EN");
        enButton.addActionListener(listener);
        frButton = new JToggleButton("FR");
        frButton.addActionListener(listener);

        langGroup.add(enButton);
        langGroup.add(frButton);

        enButton.setSelected(true);

        enButton.setFocusPainted(false);
        frButton.setFocusPainted(false);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(enButton);
        rightPanel.add(frButton);

        textEditor();

        this.text = "NexaPC";

        setLayout(new BorderLayout());

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.add(infos);

        JPanel contactPanel = new JPanel(new BorderLayout());
        contactPanel.add(leftPanel, BorderLayout.WEST);
        contactPanel.add(rightPanel, BorderLayout.EAST);

        this.add(wrapper, BorderLayout.NORTH);
        this.add(contactPanel, BorderLayout.SOUTH);

        ColorChangingHomeThread changingHomeThread = new ColorChangingHomeThread(this);
        changingHomeThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setFont(new Font("Arial", Font.BOLD, 100));

        GradientPaint gp = new GradientPaint(
                offset, 0, Color.CYAN,
                offset + 200, 0, Color.MAGENTA,
                true
        );

        g2.setPaint(gp);

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = getHeight() / 2;

        g2.drawString(text, x, y);
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public float getOffset() {
        return offset;
    }

    public void textEditor() {
        welcome.setText("<html><h1>Welcome to NexaPC, the number one pc builder in Belgium!</h1></html>\"");
        getStarted.setText("<html><h2>Getting Started</h2></html>");
        tuto.setText(
                "<html>" +
                        "<ol>" +
                        "<li>Add components to the app (if not already done).</li>" +
                        "<li>Generate a configuration of a computer.</li>" +
                        "<li>Check if everything is done correctly before sending to customer.</li>" +
                        "</ol>" +
                        "</html>"
        );
        contact.setText("App created by Leo Clarinval and Noa Forseille.");
    }

    public void textEditor(ActionEvent e) {
        if (e.getSource() == enButton) {
            textEditor();
        } else {
            welcome.setText("<html><h1>Bienvenue chez NexaPc, le configurateur numero 1 en Belgique!</h1></html>\"");
            getStarted.setText("<html><h2>Pour débuter</h2></html>");
            tuto.setText(
                    "<html>" +
                            "<ol>" +
                            "<li>Ajoutez des pièces a l'application (si pas déjà fait).</li>" +
                            "<li>Générez une configuration d'un pc.</li>" +
                            "<li>Regardez si tout est bon avant d'envoyer la commande au client.</li>" +
                            "</ol>" +
                            "</html>"
            );
            contact.setText("Application créée par Leo Clarinval et Noa Forseille.");
        }
    }

    private class LangButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textEditor(e);
        }
    }
}
