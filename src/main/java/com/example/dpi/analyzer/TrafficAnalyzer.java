package com.example.dpi.analyzer;

import com.example.dpi.connection.Connection;

import java.util.HashMap;
import java.util.Map;

public class TrafficAnalyzer {

    public static Map<String, Long> sourceTraffic(
            Map<String, Connection> connections) {

        Map<String, Long> traffic = new HashMap<>();

        for (Connection connection : connections.values()) {

            String sourceIP = connection.getSourceIP();
            long bytes = connection.getTotalBytes();

            traffic.put(
                    sourceIP,
                    traffic.getOrDefault(sourceIP, 0L) + bytes
            );
        }

        return traffic;
    }
    public static Map<String, Long> destinationTraffic(
            Map<String, Connection> connections) {

        Map<String, Long> traffic = new HashMap<>();

        for (Connection connection : connections.values()) {

            String destinationIP = connection.getDestinationIP();
            long bytes = connection.getTotalBytes();

            traffic.put(
                    destinationIP,
                    traffic.getOrDefault(destinationIP, 0L) + bytes
            );
        }

        return traffic;
    }

    public static Map<String, Long> applicationTraffic(
            Map<String, Connection> connections) {

        Map<String, Long> traffic = new HashMap<>();

        for (Connection connection : connections.values()) {

            String application = connection.getApplication();
            long bytes = connection.getTotalBytes();

            traffic.put(
                    application,
                    traffic.getOrDefault(application, 0L) + bytes
            );
        }

        return traffic;
    }

    public static Map.Entry<String, Long> findTopEntry(
        Map<String, Long> data) {

        Map.Entry<String, Long> top = null;

        for (Map.Entry<String, Long> entry : data.entrySet()) {

            if (top == null || entry.getValue() > top.getValue()) {
                top = entry;
            }
        }

        return top;
    }
}