package com.example.dpi.demo;

import com.example.dpi.analyzer.Alert;
import com.example.dpi.analyzer.DetectionEngine;
import com.example.dpi.connection.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectionDemo {

    public static void main(String[] args) {

        Map<String, Connection> testConnections = new HashMap<>();

        String sourceIP = "192.168.1.50";

        for (int port = 20; port < 30; port++) {

            Connection connection = new Connection(
                    sourceIP,
                    "10.0.0.5",
                    50000 + port,
                    port,
                    "TCP",
                    1000
            );

            testConnections.put(
                    sourceIP + ":" + port,
                    connection
            );
        }

        List<Alert> alerts =
                DetectionEngine.detectPortScans(testConnections);

        System.out.println("Port Scan Detection Demo");
        System.out.println("------------------------");

        for (Alert alert : alerts) {
            System.out.println(alert);
        }
    }
}