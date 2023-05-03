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

        System.out.println(" ===  === ");
        System.out.println(" === lets use Round Robin algorithm for Load Balancing === ");
        System.out.println(" ===  ===");
        // Example usage of the load balancer instances to round robin 
        loadBalancer.handleRequest("/another/path");
        loadBalancer.handleRequest("/yet/another/path");
        loadBalancer.handleRequest("/yet/another/path/wh");
        loadBalancer.handleRequest("/yet/another/path/jhsvadjh");
        loadBalancer.handleRequest("/yet/another/path/jhsvadjh/nbfjkd");
        loadBalancer.handleRequest("/yet/another/path/jhsvadjh/jknsajkldnla");
        loadBalancer.handleRequest("/yet/another/path/jhsvadjh/jknsajkldnla/dkjhsda");
        loadBalancer.handleRequest("/yet/another/path/jhsvadjh/jknsajkldnla/sadjksdsasa");

        System.out.println("  ===  ===");
        System.out.println("  === end of Round Robin ===");
        System.out.println("  ===  ===");

        RandomAlgo random = new RandomAlgo(servers);
        System.out.println("  ===  ===");
        System.out.println("  === lets try using Random algorithm for Load Balancing === ");
        System.out.println("  ===  ===");

        random.handleRequest("/another/path");
        random.handleRequest("/yet/another/path");
        random.handleRequest("/yet/another/path/wh");
        random.handleRequest("/yet/another/path/jhsvadjh");
        random.handleRequest("/yet/another/path/jhsvadjh/nbfjkd");
        random.handleRequest("/yet/another/path/jhsvadjh/jknsajkldnla");
        random.handleRequest("/yet/another/path/jhsvadjh/jknsajkldnla/dkjhsda");
        random.handleRequest("/yet/another/path/jhsvadjh/jknsajkldnla/sadjksdsasa");

        System.out.println("  ===  ===");
        System.out.println("  === end of Random algorithm ===");
        System.out.println("  ===  ===");

    }
}
