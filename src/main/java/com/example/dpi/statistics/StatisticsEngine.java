package com.example.dpi.statistics;

import com.example.dpi.connection.Connection;
import com.example.dpi.models.Packet;
import com.example.dpi.analyzer.TrafficAnalyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsEngine {

    public static Map<String, Object> generateStatistics(
            List<Packet> packets,
            Map<String, Connection> connections) {

        Map<String, Object> report = new HashMap<>();

        report.put("totalPackets", packets.size());
        report.put("totalConnections", connections.size());
        long totalBytes = 0;

        for (Connection connection : connections.values()) {
            totalBytes += connection.getTotalBytes();
        }

        report.put("totalBytes", totalBytes);
        double averageBytesPerConnection = connections.isEmpty()
                ? 0
                : Math.round(((double) totalBytes / connections.size()) * 100.0) / 100.0;

        report.put("averageBytesPerConnection", averageBytesPerConnection);

        Map<String, Long> sourceTraffic =
                TrafficAnalyzer.sourceTraffic(connections);

        Map<String, Long> destinationTraffic =
                TrafficAnalyzer.destinationTraffic(connections);

        Map<String, Long> applicationTraffic =
                TrafficAnalyzer.applicationTraffic(connections);

        Map.Entry<String, Long> topSource =
                TrafficAnalyzer.findTopEntry(sourceTraffic);

        Map.Entry<String, Long> topDestination =
                TrafficAnalyzer.findTopEntry(destinationTraffic);

        Map.Entry<String, Long> topApplication =
                TrafficAnalyzer.findTopEntry(applicationTraffic);


        report.put("topSourceIP",
                topSource != null ? topSource.getKey() : "N/A");

        report.put("topDestinationIP",
                topDestination != null ? topDestination.getKey() : "N/A");

        report.put("topApplication",
                topApplication != null ? topApplication.getKey() : "N/A");
        Map<String, Integer> protocols = new HashMap<>();

        for (Connection connection : connections.values()) {

            String app = connection.getApplication();

            protocols.put(app,
                    protocols.getOrDefault(app, 0) + 1);
        }

        report.put("protocols", protocols);

        return report;
    }
}