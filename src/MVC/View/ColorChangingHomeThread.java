package MVC.View;

public class ColorChangingHomeThread extends Thread {
    private HomePanel homePanel;

    public ColorChangingHomeThread(HomePanel homePanel) {
        super("ColorChangerLogo");
        this.homePanel = homePanel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(30);

                homePanel.setOffset(homePanel.getOffset() + 5);
                homePanel.repaint();
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.getMessage();
        }
    }
}
