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

        List<Server> servers = new ArrayList<>();
//initalize the current available servers (Tomcat and Galssfish), meaning their ports
        servers.add(new Server("localhost", 8082));
        servers.add(new Server("localhost", 8080));

//create an instance of our load balancer class
        LoadB loadBalancer = new LoadB(servers);

        // Example usage of the load balancer instances to round robin 
        loadBalancer.handleRequest(8080, "/another/path");
        loadBalancer.handleRequest(8080, "/yet/another/path");
        loadBalancer.handleRequest(8080, "/yet/another/path/wh");
        loadBalancer.handleRequest(8080, "/yet/another/path/jhsvadjh");
        loadBalancer.handleRequest(8080, "/yet/another/path/jhsvadjh/nbfjkd");
        loadBalancer.handleRequest(8080, "/yet/another/path/jhsvadjh/jknsajkldnla");
        loadBalancer.handleRequest(8080, "/yet/another/path/jhsvadjh/jknsajkldnla/dkjhsda");
        loadBalancer.handleRequest(8080, "/yet/another/path/jhsvadjh/jknsajkldnla/sadjksdsasa");

    }
}
