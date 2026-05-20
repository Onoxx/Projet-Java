package Toolkit;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UIMode {
    public static Theme currentTheme = Theme.LIGHT;

    /**
     * apply automatically the right theme based on the current theme (used to keep track of current one).
     * @param container
     */

    public static void applyCurrentTheme(Container container) {
        if (currentTheme == Theme.DARK) {
            applyDarkToComponents(container);
        } else {
            applyWhiteToComponents(container);
        }
    }

    /**
     * apply dark theme using UIManager and custom values of looks.
     * @param frame : the operating frame needed to change.
     */
    public static void applyDarkMode(JFrame frame) {
        currentTheme = Theme.DARK;
        refresh(frame);
        applyDarkToComponents(frame);
    }

    /**
     * Revert the ui theme to classic theme (light) using the UIManager and custom values
     * (similar to default values).
     * @param frame : the operating frame needed to change.
     */
    public static void applyClassicMode(JFrame frame) {
        currentTheme = Theme.LIGHT;
        refresh(frame);
        applyWhiteToComponents(frame);
    }

    private static void applyDarkToComponents(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JPanel) {
                c.setBackground(new Color(43, 43, 43));
            }

            if (c instanceof JScrollPane) {
                ((JScrollPane) c).getViewport().setBackground(new Color(60, 63, 65));
            }

            if (c instanceof JLabel) {
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JButton) {
                c.setBackground(new Color(60, 63, 65));
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JCheckBox) {
                c.setBackground(new Color(43, 43, 43));
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JTextField) {
                c.setBackground(new Color(60, 63, 65));
                c.setForeground(Color.WHITE);
            }

            if (c instanceof JTable) {
                JTableHeader header = ((JTable) c).getTableHeader();
                ((JTable) c).setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {{
                    setBackground(new Color(60,63,65));
                    setForeground(Color.WHITE);
                    header.setBackground(new Color(60,63,65));
                    header.setForeground(Color.WHITE);
                }});
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

            if (c instanceof JScrollPane) {
                ((JScrollPane) c).getViewport().setBackground(new Color(240, 240, 240));
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

            if (c instanceof JTable) {
                JTableHeader header = ((JTable) c).getTableHeader();
                ((JTable) c).setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {{
                    setBackground(new Color(240,240,240));
                    setForeground(Color.BLACK);
                    header.setBackground(new Color(240,240,240));
                    header.setForeground(Color.BLACK);
                }});
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

    public static enum Theme {
        DARK,
        LIGHT
    }
}

