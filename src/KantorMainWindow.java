import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;

public class KantorMainWindow extends JFrame {
    CurrencyConverterClient client = new CurrencyConverterClient();
    public KantorMainWindow() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Kantor");
        pack();
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
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

        gbc.gridy = 0;
        mainPanel.add(Box.createVerticalStrut(20), gbc);
        gbc.gridy++;
        mainPanel.add(welcomeLabel, gbc);
        gbc.gridy++;
        mainPanel.add(Box.createVerticalStrut(20), gbc);
        gbc.gridy++;
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




    private void CurrencyCheck() {
            //przypisujemy wybór który jest zależny od przycisku
            client.setChoice(1);
            String currencyCode = JOptionPane.showInputDialog(this, "Podaj kod waluty (np. USD, EUR, GBP):");

            //ustawiamy walutę którą wpisaliśmy w obiekcie client
            client.setCurrencyCode(currencyCode);

           //wywołujemy metodę główną clienta
            client.connectToClient();

           //pobieramy z klienta parametr exchangeRate
            Double exchangeRate = client.getExchangeRate();

            JOptionPane.showMessageDialog(this, "Kurs dla waluty " + currencyCode + ": " + exchangeRate);


    }
    private void exchange() {
        client.setChoice(2);

        String currencyCode = JOptionPane.showInputDialog(this, "Podaj kod waluty (np. USD, EUR):");
        client.setCurrencyCode(currencyCode);

        String amount = JOptionPane.showInputDialog(this,"Podaj kwotę: ");
        client.setAmount(Double.parseDouble(amount));

        client.connectToClient();

        double convertedAmount = client.getConvertedAmount();
        double roundedAmount = Math.round(convertedAmount * 100.0) / 100.0;
        JOptionPane.showMessageDialog(this, roundedAmount +" "+currencyCode);
    }
    private void showAllCurrencies() {
        client.setChoice(3);

        client.connectToClient();

        String shortNames = client.getListOfShortName();
        JOptionPane.showMessageDialog(this, shortNames);
    }

}
