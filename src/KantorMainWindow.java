import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class KantorMainWindow extends JFrame {
    public KantorMainWindow() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Kantor");
        pack();
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        Color lightGreen = new Color(144, 238, 144);
        mainPanel.setBackground(lightGreen);

        JLabel welcomeLabel = new JLabel("KANTOR PLN", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));

        JButton currencyButton = new JButton("Kurs waluty");
        currencyButton.addActionListener(e -> CurrencyCheck());

        JButton exchangeButton = new JButton("Wymień walutę");
        exchangeButton.addActionListener(e -> exchange());

        JButton allCurrencyButton = new JButton("Wyświetl wszystkie dostępne waluty");
        allCurrencyButton.addActionListener(e -> showAllCurrencies());

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.add(Box.createVerticalStrut(50));
        buttons.add(currencyButton, gbc);
        buttons.add(Box.createVerticalStrut(50));
        buttons.add(exchangeButton, gbc);
        buttons.add(allCurrencyButton, gbc);

        gbc.gridy = 0; // Zerujemy wartość przed dodaniem komponentów

        mainPanel.add(Box.createVerticalStrut(20), gbc);
        gbc.gridy++; // Przesuwamy się o jeden wiersz
        mainPanel.add(welcomeLabel, gbc);
        gbc.gridy++; // Przesuwamy się o jeden wiersz
        mainPanel.add(Box.createVerticalStrut(20), gbc);
        gbc.gridy++; // Przesuwamy się o jeden wiersz
        mainPanel.add(buttons, gbc);
        buttons.setBackground(lightGreen);

        Font buttonFont = new Font("Arial", Font.PLAIN, 24); // Możesz dostosować wielkość czcionki przycisków
        currencyButton.setFont(buttonFont);
        exchangeButton.setFont(buttonFont);
        allCurrencyButton.setFont(buttonFont);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton closeButton = new JButton("Zamknij aplikację");
        closeButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
        bottomPanel.add(closeButton);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void showAllCurrencies() {
        CurrencyConverterClient converterClient = new CurrencyConverterClient();
        converterClient.setChoice(3);
        converterClient.connectToClient();
        String shortNames = converterClient.getListOfShortName();
        JOptionPane.showMessageDialog(this, shortNames);
    }

    private void exchange() {
        try {
            // Tutaj tworzymy obiekt CurrencyConverterClient
            CurrencyConverterClient converterClient = new CurrencyConverterClient();

            // Przekazujemy informację o wyborze do klienta
            converterClient.setChoice(2);
            String currencyCode = JOptionPane.showInputDialog(this, "Podaj kod waluty (np. USD, EUR):");
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

            // Pobieramy kod waluty od użytkownika
            String userCurrencyCode = JOptionPane.showInputDialog(this, "Podaj kod waluty (np. USD, EUR, GBP):");

            // Ustawiamy kod waluty w obiekcie klienta
            converterClient.setCurrencyCode(userCurrencyCode);

            // Wywołujemy metodę łączenia z klientem
            converterClient.connectToClient();

            // Pobieramy kurs i walutę z klienta
            Double exchangeRate = converterClient.getExchangeRate();

            // Pobieramy kod waluty z klienta (ponowne pobranie, gdyż zmienił się w obiekcie klienta)
            String retrievedCurrencyCode = converterClient.getCurrencyCode();

            // Wyświetlamy kurs w oknie dialogowym
            if (exchangeRate != -1.0) {
                JOptionPane.showMessageDialog(this, "Kurs dla waluty " + retrievedCurrencyCode + ": " + exchangeRate);
            } else {
                JOptionPane.showMessageDialog(this, "Brak dostępnego kursu dla waluty " + retrievedCurrencyCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
