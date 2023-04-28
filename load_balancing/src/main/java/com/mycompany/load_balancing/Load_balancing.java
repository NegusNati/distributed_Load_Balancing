/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.load_balancing;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wku-cslab1
 */
public class Load_balancing {

    public static void main(String[] args) {
//        List<InetSocketAddress> serverAddresses = new ArrayList<>();
//        serverAddresses.add(new InetSocketAddress("localhost", 8001));
//        serverAddresses.add(new InetSocketAddress("localhost", 8002));
//        serverAddresses.add(new InetSocketAddress("localhost", 8003));
//
//        LoadBalancer loadBalancer = new LoadBalancer(serverAddresses);
//        loadBalancer.start();


   List<Server> servers = new ArrayList<>();
//        servers.add(new Server("192.168.1.1", 8080));
//        servers.add(new Server("192.168.1.2", 8080));
//        servers.add(new Server("192.168.1.3", 8080));        
        servers.add(new Server("localhost", 8080));
        servers.add(new Server("localhost", 8080));

        

        LoadB loadBalancer = new LoadB(servers);

//        for (int i = 0; i < 100; i++) {
//            loadBalancer.handleRequest(servera, "/path/to/request");
//        }
          for (Server server : servers) {
            int port = server.getPort();
            String request = server.getIpAddress();

            loadBalancer.handleRequest(port, request);
        }
    }
}
