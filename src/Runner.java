public class Runner {

    public static void main(String[] args) throws InterruptedException {

        CurrencyConverterServer server = new CurrencyConverterServer();
        CurrencyConverterClient client = new CurrencyConverterClient();

        Thread serverThread = new Thread(() -> server.connectToServer());
        Thread clientThread = new Thread(() -> client.connectToClient());

        serverThread.start();
        clientThread.start();
    }
}

