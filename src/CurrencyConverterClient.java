import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CurrencyConverterClient {
    private double amount;
    private String currencyCode;
    private int choice;
    private double convertedAmount;
    private double exchangeRate;
    private String listOfShortName;

    public String getCurrencyCode() {
        return currencyCode;
    }
    public String getListOfShortName(){
        return listOfShortName;
    }
    public double getConvertedAmount() {
        return convertedAmount;
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



    public void connectToClient(){

        try {

            Socket socket = new Socket("localhost", 5555);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            if (choice == 1) {
                outputStream.writeObject("GET_RATE");

                outputStream.writeObject(currencyCode);

                exchangeRate = (Double) inputStream.readObject();

            } else if (choice == 2) {

                outputStream.writeObject("CONVERT");
                outputStream.writeObject(currencyCode);
                outputStream.writeObject(amount);

                convertedAmount = (Double) inputStream.readObject();


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