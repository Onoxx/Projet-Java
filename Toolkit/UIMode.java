package Toolkit;

import javax.swing.*;
import java.awt.*;

public class UIMode {

    /**
     * apply dark theme using UIManager and custom values of looks.
     * @param frame : the operating frame needed to change.
     */
    public static void applyDarkMode(JFrame frame) {
        refresh(frame);
        applyDarkToComponents(frame);
    }

    /**
     * Revert the ui theme to classic theme (light) using the UIManager and custom values
     * (similar to default values).
     * @param frame : the operating frame needed to change.
     */
    public static void applyClassicMode(JFrame frame) {
        refresh(frame);
        applyWhiteToComponents(frame);
    }

    private static void applyDarkToComponents(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JPanel) {
                c.setBackground(new Color(43, 43, 43));
            }

            if (c instanceof JLabel) {
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JButton) {
                c.setBackground(new Color(60, 63, 65));
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JTextField) {
                c.setBackground(new Color(60, 63, 65));
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JMenuBar) {
                c.setBackground(new Color(43, 43, 43));
            }

            if (c instanceof JMenu) {
                c.setForeground(Color.WHITE);
            }

            if (c instanceof Container) {
                applyDarkToComponents((Container) c);
            }
        }
    }

    private static void applyWhiteToComponents(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JPanel) {
                c.setBackground(new Color(240, 240, 240));
            }

            if (c instanceof JLabel) {
                c.setForeground(Color.BLACK);
            }

            if (c instanceof JButton) {
                c.setBackground(new Color(240, 240, 240));
                c.setForeground(Color.BLACK);
                ((JButton) c).setFocusPainted(false);
            }

            if (c instanceof JTextField) {
                c.setBackground(new Color(240, 240, 240));
                c.setForeground(Color.BLACK);
            }

            if (c instanceof JMenuBar) {
                c.setBackground(new Color(240, 240, 240));
            }

            if (c instanceof JMenu) {
                c.setForeground(Color.BLACK);
            }

            if (c instanceof Container) {
                applyWhiteToComponents((Container) c);
            }
        }
    }

    private static void refresh(JFrame frame) {
        SwingUtilities.updateComponentTreeUI(frame);
        frame.revalidate();
        frame.repaint();
    }
}

