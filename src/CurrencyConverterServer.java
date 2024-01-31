
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class CurrencyConverterServer {
    private static final HashMap<String, Double> exchangeRates = new HashMap<>();
    static CurrencyList currencyList = new CurrencyList();

    static {
        for (Currency currency : currencyList.currencyList) {
            exchangeRates.put(currency.getShortName(), currency.getCurrency());
        }
    }

    public void connectToServer() {
        try (ServerSocket serverSocket = new ServerSocket(5555)) {

            while (true) {

                Socket socket = serverSocket.accept();

                Thread clientThread = new Thread(() -> handleClient(socket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())
        ) {
            String request = (String) inputStream.readObject();

            if ("GET_RATE".equals(request)) {
                String currencyCode = (String) inputStream.readObject();
                Double exchangeRate = exchangeRates.get(currencyCode);
                outputStream.writeObject(exchangeRate);
            } else if ("CONVERT".equals(request)) {
                String toCurrency = (String) inputStream.readObject();
                double amount = (Double) inputStream.readObject();

                Double toRate = exchangeRates.get(toCurrency);
                double convertedAmount = amount / toRate;
                System.out.println(convertedAmount);
                outputStream.writeObject(convertedAmount);
            } else if ("SHOW".equals(request)) {
                outputStream.writeObject(currencyList.displayShortName());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}