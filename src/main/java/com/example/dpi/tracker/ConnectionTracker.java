package com.example.dpi.tracker;
import com.example.dpi.models.Packet;
import com.example.dpi.connection.Connection;
import java.util.HashMap;
import java.util.Map;

public class ConnectionTracker {

    private Map<String, Connection> connections;

    public ConnectionTracker() {
        this.connections = new HashMap<>();
    }

    // Builds the five-tuple key for a given packet
    public String buildKey(Packet packet) {
        return packet.getSourceIP() + ":" + packet.getSourcePort() + "->" +
               packet.getDestinationIP() + ":" + packet.getDestinationPort() +
               ":" + packet.getProtocol();
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