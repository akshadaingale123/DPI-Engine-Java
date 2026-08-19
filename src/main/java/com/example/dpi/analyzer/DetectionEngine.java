package com.example.dpi.analyzer;

import com.example.dpi.connection.Connection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetectionEngine {

    private static final int PORT_SCAN_THRESHOLD = 10;
    private static final int FAN_OUT_THRESHOLD = 10;
    private static final long LARGE_TRANSFER_THRESHOLD = 1000;

    public static List<Alert> detectPortScans(
            Map<String, Connection> connections) {

        Map<String, Set<Integer>> sourcePorts = new HashMap<>();

        for (Connection connection : connections.values()) {

            String sourceIP = connection.getSourceIP();
            int destinationPort = connection.getDestinationPort();

            sourcePorts
                    .computeIfAbsent(sourceIP, k -> new HashSet<>())
                    .add(destinationPort);
        }

        List<Alert> alerts = new java.util.ArrayList<>();

        for (Map.Entry<String, Set<Integer>> entry : sourcePorts.entrySet()) {

            if (entry.getValue().size() >= PORT_SCAN_THRESHOLD) {

                String description =
                        "Contacted " + entry.getValue().size()
                        + " unique destination ports";

                alerts.add(
                        new Alert(
                                "Possible Port Scan",
                                "HIGH",
                                entry.getKey(),
                                description
                        )
                );
            }
        }

        return alerts;
    }
    public static List<Alert> detectFanOut(
            Map<String, Connection> connections) {

        Map<String, Set<String>> sourceDestinations = new HashMap<>();

        for (Connection connection : connections.values()) {

            String sourceIP = connection.getSourceIP();
            String destinationIP = connection.getDestinationIP();

            sourceDestinations
                    .computeIfAbsent(sourceIP, k -> new HashSet<>())
                    .add(destinationIP);
        }

        List<Alert> alerts = new java.util.ArrayList<>();

        for (Map.Entry<String, Set<String>> entry
                : sourceDestinations.entrySet()) {

            if (entry.getValue().size() >= FAN_OUT_THRESHOLD) {

                String description =
                        "Contacted " + entry.getValue().size()
                        + " unique destination IPs";

                alerts.add(
                        new Alert(
                                "Possible High Fan-Out",
                                "MEDIUM",
                                entry.getKey(),
                                description
                        )
                );
            }
        }

        return alerts;
    }
    public static List<Alert> detectLargeTransfers(
            Map<String, Connection> connections) {

        Map<String, Long> outboundBytes = new HashMap<>();

        for (Connection connection : connections.values()) {

            String sourceIP = connection.getSourceIP();
            long bytes = connection.getForwardBytes();

            outboundBytes.put(
                    sourceIP,
                    outboundBytes.getOrDefault(sourceIP, 0L) + bytes
            );
        }

        List<Alert> alerts = new java.util.ArrayList<>();

        for (Map.Entry<String, Long> entry : outboundBytes.entrySet()) {

            if (entry.getValue() >= LARGE_TRANSFER_THRESHOLD) {

                String description =
                        "Sent " + entry.getValue()
                        + " bytes in the forward direction";

                alerts.add(
                        new Alert(
                                "Possible Large Outbound Transfer",
                                "MEDIUM",
                                entry.getKey(),
                                description
                        )
                );
            }
        }

        return alerts;
    }
    public static List<Alert> analyze(
            Map<String, Connection> connections) {

        List<Alert> alerts = new java.util.ArrayList<>();

        alerts.addAll(
                detectPortScans(connections)
        );

        alerts.addAll(
                detectFanOut(connections)
        );

        alerts.addAll(
                detectLargeTransfers(connections)
        );

        return alerts;
    }
    
}
