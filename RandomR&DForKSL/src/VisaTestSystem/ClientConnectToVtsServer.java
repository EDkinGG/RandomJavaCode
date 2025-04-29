package VisaTestSystem;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientConnectToVtsServer {

    public void run(){
        String host = "10.88.233.13";  // VTS server IP
        int port = 9999;               // VTS server port

        try (Socket socket = new Socket(host, port)) {
            // Connected to the VTS server
            System.out.println("Connected to server: " + host + ":" + port);

            // Create input stream to read the data from the server (VTS)
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];  // Buffer to hold incoming data
            int bytesRead;

            // Continuously read data from the VTS server
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // Copy the valid data from the buffer
                byte[] receivedData = new byte[bytesRead];
                System.arraycopy(buffer, 0, receivedData, 0, bytesRead);

                // Print the received data as a hexadecimal string (for better readability)
                System.out.println("Received data: " + bytesToHex(receivedData));
            }

        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to convert byte array to a readable hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X ", b));
        }
        return hexString.toString().trim();
    }
}
