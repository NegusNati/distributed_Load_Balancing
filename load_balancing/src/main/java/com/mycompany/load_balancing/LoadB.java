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
        currentIndex = (currentIndex + 1) % servers.size(); //impleamenting round robin 
        return server;
    }

    public void handleRequest( String requestPath) {
        Server server = getNextServer();
        int port = 80;
        int serverCount = servers.size();

        try {
            for (int i = 0; i < serverCount; i++) {
                port = server.getPort();
            }
            // To open a connection and query the server
//        URL url = new URL("http://" + requestPath + ":" + port  );
            URL url = new URL("http://" + server.getIpAddress() + ":" + port);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(" after connection is created");
            // Check if the server responded with a successful status code
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = "";
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine + "\n";
                }
                in.close();
                System.out.println(" If all goes well");
                System.out.println("Response from server " + server.getIpAddress() + ":" + port + ": " + response);
            } else {
                System.out.println("Server " + server.getIpAddress() + ":" + port + " returned status code " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while connecting to server " + server.getIpAddress() + ":" + port + ": " + e.getMessage());
        }
    }
}
