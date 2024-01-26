import javax.swing.*;
import java.awt.*;

public class KantorMainWindow extends JFrame {
    public KantorMainWindow() {
        initializeComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Kantor");
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        Color lightGreen = new Color(144, 238, 144);
        mainPanel.setBackground(lightGreen);

        JLabel welcomeLabel = new JLabel("Wymieniaj waluty!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));

        JButton closeButton = new JButton("Zamknij aplikacje");
        closeButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(closeButton, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KantorMainWindow().setVisible(true));
    }
}
