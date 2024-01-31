import javax.swing.*;

public class Runner{

        public static void main(String[] args) {

            SwingUtilities.invokeLater(() -> {
                KantorGUI kantorGUI = new KantorGUI();
                kantorGUI.setVisible(true);
            });
            


            CurrencyConverterServer server = new CurrencyConverterServer();
            server.connectToServer();
        }
}
