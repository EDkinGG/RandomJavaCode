package VisaTestSystem;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForVtsClientToConnect {

    public void run(){
        String host = "10.88.233.13";  // Listen on all available network interfaces
        int port = 9999;          // The port number to listen for VTS connection

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Listening on port " + port);

            // Accept an incoming connection from VTS (client)
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Connected to VTS client: " + clientSocket.getRemoteSocketAddress());

                // Create input stream to receive data from VTS client
                InputStream inputStream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];  // Buffer to hold incoming data
                int bytesRead;

                // Continuously read data sent by the VTS client
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    // Copy the valid data from the buffer into a new array
                    byte[] receivedData = new byte[bytesRead];
                    System.arraycopy(buffer, 0, receivedData, 0, bytesRead);

                    // Print the received data as a hexadecimal string for better visibility
                    System.out.println("Received data: " + bytesToHex(receivedData));
                }
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
