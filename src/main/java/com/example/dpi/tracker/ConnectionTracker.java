package com.example.dpi.tracker;
import com.example.dpi.models.Packet;
import com.example.dpi.connection.Connection;
import com.example.dpi.rules.RuleEngine;
import java.util.HashMap;
import java.util.Map;

public class ConnectionTracker {

    private Map<String, Connection> connections;
    private RuleEngine ruleEngine;

    public ConnectionTracker() {
        this.connections = new HashMap<>();
        this.ruleEngine = new RuleEngine();
    }

    // Builds a direction-independent five-tuple key for a given packet.
    // A->B and B->A will always produce the SAME key.
    public String buildKey(Packet packet) {
        String endpointA = packet.getSourceIP() + ":" + packet.getSourcePort();
        String endpointB = packet.getDestinationIP() + ":" + packet.getDestinationPort();

        String firstEndpoint;
        String secondEndpoint;

        if (endpointA.compareTo(endpointB) <= 0) {
            firstEndpoint = endpointA;
            secondEndpoint = endpointB;
        } else {
            firstEndpoint = endpointB;
            secondEndpoint = endpointA;
        }

        return firstEndpoint + "<->" + secondEndpoint + ":" + packet.getProtocol();
    }

    // Takes a packet and updates or creates a Connection for it
    public void trackPacket(Packet packet) {
        String key = buildKey(packet);

        if (connections.containsKey(key)) {
            Connection existingConnection = connections.get(key);
            existingConnection.addPacketInfo(packet.getTimestamp(), packet.getSize());
        } else {
            Connection newConnection = new Connection(
                    packet.getSourceIP(),
                    packet.getDestinationIP(),
                    packet.getSourcePort(),
                    packet.getDestinationPort(),
                    packet.getProtocol(),
                    packet.getTimestamp()
            );
            String application = ruleEngine.detectApplication(packet);
            newConnection.setApplication(application);

            newConnection.addPacketInfo(packet.getTimestamp(), packet.getSize());
            connections.put(key, newConnection);
        }
    }

    // Returns all tracked connections
    public Map<String, Connection> getAllConnections() {
        return connections;
    }

    // Returns how many unique connections have been tracked so far
    public int getConnectionCount() {
        return connections.size();
    }

    // Prints all tracked connections (for debugging purposes)
    public void printAllConnections() {
        for (String key : connections.keySet()) {
            System.out.println(key + " => " + connections.get(key).toString());
        }
    }
}