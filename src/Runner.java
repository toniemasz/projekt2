import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        // Create an instance of the KantorGUI to start the application
        SwingUtilities.invokeLater(() -> {
            KantorGUI kantorGUI = new KantorGUI();
            kantorGUI.setVisible(true);
        });

        // Optionally, you can start the server in the background
        startServerInBackground();
    }

    private static void startServerInBackground() {
        // You may want to run the server in a separate thread or as a background task
        // to allow the GUI to remain responsive.
        new Thread(() -> {
            CurrencyConverterServer server = new CurrencyConverterServer();
            server.connectToServer();
        }).start();
    }
}
