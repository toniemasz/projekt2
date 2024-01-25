public class Runner {

    public static void main(String[] args) throws InterruptedException {
        // Create instances of the server and client
        CurrencyConverterServer server = new CurrencyConverterServer();
        CurrencyConverterClient client = new CurrencyConverterClient();

        // Create and start threads for server and client
        Thread serverThread = new Thread(() -> server.connectToServer());
        Thread clientThread = new Thread(() -> client.connectToClient());

        serverThread.start();
        clientThread.start();
    }
}

