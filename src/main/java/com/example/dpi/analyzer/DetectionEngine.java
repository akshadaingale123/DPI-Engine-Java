package com.example.dpi.analyzer;

import com.example.dpi.connection.Connection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DetectionEngine {

    private static final int PORT_SCAN_THRESHOLD = 10;

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
}
