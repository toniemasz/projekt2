import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class KantorMainWindow extends JFrame {
    public KantorMainWindow() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setTitle("Kantor");
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        Color lightGreen = new Color(144, 238, 144);
        mainPanel.setBackground(lightGreen);

        JLabel welcomeLabel = new JLabel("KANTOR PLN", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JButton currencyButton = new JButton("Kurs waluty");
        currencyButton.addActionListener(e -> {
            CurrencyCheck();
        });

        JButton exchangeButton = new JButton("Wymień walutę");
        exchangeButton.addActionListener(e -> {
            exchange();
        });

        JButton closeButton = new JButton("Zamknij aplikację");
        Insets margin = new Insets(0, 0, 0, 0);
        closeButton.setMargin(margin);
        closeButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        mainPanel.add(welcomeLabel);
        mainPanel.add(currencyButton);
        mainPanel.add(exchangeButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(closeButton);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void exchange() {
        try {
            // Tutaj tworzymy obiekt CurrencyConverterClient
            CurrencyConverterClient converterClient = new CurrencyConverterClient();

            // Przekazujemy informację o wyborze do klienta
            converterClient.setChoice(2);
            String currencyCode = JOptionPane.showInputDialog(this, "Podaj kod waluty (np. USD, EUR, GBP):");
            converterClient.setCurrencyCode(currencyCode);
            String amount = JOptionPane.showInputDialog(this,"Podaj kwotę: ");
            converterClient.setAmount(Double.parseDouble(amount));
            converterClient.connectToClient();
            double convertedAmount = converterClient.getConvertedAmount();
            String convertedCurrencyCode = converterClient.getCurrencyCode();
            JOptionPane.showMessageDialog(this, convertedAmount + convertedCurrencyCode);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CurrencyCheck() {
        try {
            // Tutaj tworzymy obiekt CurrencyConverterClient
            CurrencyConverterClient converterClient = new CurrencyConverterClient();

            // Przekazujemy informację o wyborze do klienta
            converterClient.setChoice(1);

            // Wywołujemy metodę łączenia z klientem
            converterClient.connectToClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
