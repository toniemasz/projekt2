import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CurrencyConverterClient {

    public void connectToClient(){
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Sprawdź kurs waluty");
            System.out.println("2. Przelicz kwotę");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            if (choice == 1) {
                // Sprawdź kurs waluty
                outputStream.writeObject("GET_RATE");

                System.out.println("Podaj kod waluty (np. USD, EUR, GBP):");
                String currencyCode = scanner.nextLine();
                outputStream.writeObject(currencyCode);

                Double exchangeRate = (Double) inputStream.readObject();

                if (exchangeRate != -1.0) {
                    System.out.println("Kurs dla waluty " + currencyCode + ": " + exchangeRate);
                } else {
                    System.out.println("Brak dostępnego kursu dla waluty " + currencyCode);
                }
            } else if (choice == 2) {
                // Przelicz kwotę
                outputStream.writeObject("CONVERT");

                System.out.println("Przeliczanie z PLN na:");

                System.out.println("Podaj kod waluty docelowej (np. USD):");
                String toCurrency = scanner.nextLine();
                outputStream.writeObject(toCurrency);

                System.out.println("Podaj kwotę:");
                double amount = scanner.nextDouble();
                outputStream.writeObject(amount);

                Double convertedAmount = (Double) inputStream.readObject();

                if (convertedAmount != -1.0) {
                    System.out.println("Przeliczona kwota: " + convertedAmount + " " + toCurrency);
                } else {
                    System.out.println("Nie udało się przeliczyć kwoty. Sprawdź dostępność kursów walut.");
                }
            }

            outputStream.close();
            inputStream.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}