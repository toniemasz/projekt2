import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class CurrencyConverterClient {
    private int choice;
    private String currencyCode;

    private double exchangeRate;
    private double amount;

    private double convertedAmount;

    private String listOfShortName;



    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getListOfShortName(){
        return listOfShortName;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void connectToClient(){
        try {


            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            if (choice == 1) {
                // Sprawdź kurs waluty
                outputStream.writeObject("GET_RATE");

                System.out.println("Podaj kod waluty (np. USD, EUR, GBP):");
                outputStream.writeObject(currencyCode);

                exchangeRate = (Double) inputStream.readObject();

                if (exchangeRate != -1.0) {
                    System.out.println("Kurs dla waluty " + currencyCode + ": " + exchangeRate);
                } else {
                    System.out.println("Brak dostępnego kursu dla waluty " + currencyCode);
                }
            } else if (choice == 2) {
                // Przelicz kwotę
                outputStream.writeObject("CONVERT");


                outputStream.writeObject(currencyCode);

                System.out.println("Podaj kwotę:");
                outputStream.writeObject(amount);

                convertedAmount = (Double) inputStream.readObject();

                if (convertedAmount != -1.0) {
                    System.out.println("Przeliczona kwota: " + convertedAmount + " " + currencyCode);
                } else {
                    System.out.println("Nie udało się przeliczyć kwoty. Sprawdź dostępność kursów walut.");
                }
                } else if (choice == 3) {
                    outputStream.writeObject("SHOW");
                    listOfShortName = (String) inputStream.readObject();
                }

            outputStream.close();
            inputStream.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}