/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.load_balancing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 *
 * @author wku-cslab1
 */
public class LoadB {

    private List<Server> servers;
    private int currentIndex;

    public LoadB(List<Server> servers) {
        this.servers = servers;
        this.currentIndex = 0;
    }

    public Server getNextServer() {
        Server server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size();
        return server;
    }

//    public void handleRequest(String request) {
//        Server server = getNextServer();
//        System.out.println("Sending request " + request + " to server " + server.getIpAddress() + ":" + server.getPort());
//    }
    ///
//    public void handleRequest(String requestUrl) {
//        Server server = getNextServer();
//
//        try {
//            // Open a connection to the server
//            URL url = new URL("http://" + server.getIpAddress() + ":" + server.getPort() + requestUrl);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // Check if the server responded with a successful status code
//            int statusCode = connection.getResponseCode();
//            if (statusCode == HttpURLConnection.HTTP_OK) {
//                // Read the response from the server
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String response = "";
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    response += inputLine;
//                }
//                in.close();
//
//                System.out.println("Response from server " + server.getIpAddress() + ":" + server.getPort() + ": " + response);
//            } else {
//                System.out.println("Server " + server.getIpAddress() + ":" + server.getPort() + " returned status code " + statusCode);
//            }
//        } catch (Exception e) {
//            System.out.println("Error occurred while connecting to server " + server.getIpAddress() + ":" + server.getPort() + ": " + e.getMessage());
//        }
//    }
   public void handleRequest(int port, String requestPath) {
    Server server = getNextServer();

    try {
        // Open a connection to the server
        URL url = new URL("http://" + requestPath + ":" + port);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Check if the server responded with a successful status code
        int statusCode = connection.getResponseCode();
        if (statusCode == HttpURLConnection.HTTP_OK) {
            // Read the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();

            System.out.println("Response from server " + server.getIpAddress() + ":" + port + ": " + response);
        } else {
            System.out.println("Server " + server.getIpAddress() + ":" + port + " returned status code " + statusCode);
        }
    } catch (Exception e) {
        System.out.println("Error occurred while connecting to server " + server.getIpAddress() + ":" + port + ": " + e.getMessage());
    }
}
}
