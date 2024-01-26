import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KantorGUI extends JFrame {
    private JButton startButton;
    private JPanel mainPanel;
    private JLabel titleLabel;

    public KantorGUI() {
        initializeComponents();
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Kantor");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new KantorMainWindow().setVisible(true);
            }
        });
    }

    private void initializeComponents() {
        titleLabel = new JLabel("Witaj w Kantorze!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        mainPanel = new JPanel();
        startButton = new JButton("Start");
        mainPanel.add(startButton);
    }
}




