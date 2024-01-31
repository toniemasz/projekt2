import javax.swing.*;

public class Runner{

        public static void main(String[] args) {
            // Create an instance of the KantorGUI to start the application
            SwingUtilities.invokeLater(() -> {
                KantorGUI kantorGUI = new KantorGUI();
                kantorGUI.setVisible(true);
            });

            // Start the server
            CurrencyConverterServer server = new CurrencyConverterServer();
            server.connectToServer();
        }
}
