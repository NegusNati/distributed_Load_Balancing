/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.load_balancing;


import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *
 * @author wku-cslab1
 */
public class RandomAlgo {
    private List<Server> servers;

    public RandomAlgo(List<Server> servers) {
        this.servers = servers;
    }

    public Server getNextServer() {
        // implement random algorithm for Load Balancing
        
//        Random random = new Random(servers);
         int randomIndex = new Random().nextInt(servers.size());
         Server server = servers.get(randomIndex);
          return server;
          
          
        

    }

    public void handleRequest( String requestPath) {
        Server server = getNextServer();
        int port = server.getPort();


        try {
            // To open a connection and query the server
//        URL url = new URL("http://" + requestPath + ":" + port  );
            URL url = new URL("http://" + server.getIpAddress() + ":" + port);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println(" new http request ");
            // Check if the server responded with a successful status code
            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the server
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String response = "";
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response += inputLine ;
                    // + "\n"
                }
                in.close();
                System.out.println(" If all goes well with the Server connection");
                System.out.println("Response from server " + server.getIpAddress() + ":" + port + ": " + response);
            } else {
                System.out.println("Server " + server.getIpAddress() + ":" + port + " returned status code " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("Error occurred while connecting to server " + server.getIpAddress() + ":" + port + ": " + e.getMessage());
        }
    }
}
