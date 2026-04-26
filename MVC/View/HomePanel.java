package MVC.View;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private String text;
    private float offset = 0;

    public HomePanel() {
        this.text = "NexaPC";
        setLayout(new BorderLayout());

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
}
