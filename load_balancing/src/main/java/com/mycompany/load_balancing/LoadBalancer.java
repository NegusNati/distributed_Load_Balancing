package com.mycompany.load_balancing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class LoadBalancer {

    private final List<InetSocketAddress> serverAddresses;
    private int currentIndex;

    public LoadBalancer(List<InetSocketAddress> serverAddresses) {
        this.serverAddresses = serverAddresses;
        this.currentIndex = 0;
    }

    // Implement load balancer logic here
    //Round Robin algo 
    private synchronized InetSocketAddress getNextServerAddress() {
        if (currentIndex >= serverAddresses.size()) {
            currentIndex = 0;
        }
        return serverAddresses.get(currentIndex++);
    }

    //forwarding logic 
    private void forwardRequest(Socket clientSocket, InetSocketAddress serverAddress) throws IOException {
        Socket serverSocket = new Socket(serverAddress.getAddress(), serverAddress.getPort());

        InputStream clientInput = clientSocket.getInputStream();
        OutputStream clientOutput = clientSocket.getOutputStream();

        InputStream serverInput = serverSocket.getInputStream();
        OutputStream serverOutput = serverSocket.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = clientInput.read(buffer)) != -1) {
            serverOutput.write(buffer, 0, bytesRead);
            serverOutput.flush();
        }

        while ((bytesRead = serverInput.read(buffer)) != -1) {
            clientOutput.write(buffer, 0, bytesRead);
            clientOutput.flush();
        }

        clientSocket.close();
        serverSocket.close();
    }

    //start method 
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(80)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                InetSocketAddress serverAddress = getNextServerAddress();
                forwardRequest(clientSocket, serverAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
