
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
        try {
            ServerSocket serverSocket = new ServerSocket(5555);

            while (true) {

                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                        String request = (String) inputStream.readObject();

                        if ("GET_RATE".equals(request)) {
                            // Obsługa żądania pobrania kursu waluty
                            String currencyCode = (String) inputStream.readObject();
                            Double exchangeRate = exchangeRates.getOrDefault(currencyCode, -1.0);
                            outputStream.writeObject(exchangeRate);

                        } else if ("CONVERT".equals(request)) {
                            // Obsługa żądania przeliczenia kwoty
                            String toCurrency = (String) inputStream.readObject();
                            double amount = (Double) inputStream.readObject();

                            Double fromRate = 1.0;
                            Double toRate = exchangeRates.getOrDefault(toCurrency, -1.0);

                            double convertedAmount = amount * (fromRate / toRate);
                            outputStream.writeObject(convertedAmount);
                        }else if ("SHOW".equals(request)){
                            outputStream.writeObject(currencyList.displayShortName());
                        }

                        inputStream.close();
                        outputStream.close();
                        socket.close();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}