import javax.swing.SwingUtilities;
import ui.MainFrame;
import util.FileHandler;

/**
 * Main launcher - Fast start.
 */
public class Main {
    public static void main(String[] args) {
        FileHandler.initializeFiles();
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}