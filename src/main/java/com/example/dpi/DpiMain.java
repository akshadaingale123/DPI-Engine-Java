package com.example.dpi;

import com.example.dpi.models.Packet;
import com.example.dpi.connection.Connection;
import com.example.dpi.parser.PacketParser;
import com.example.dpi.tracker.ConnectionTracker;

import java.util.List;
import java.util.Map;

public class DpiMain {

    public static void main(String[] args) {
        // 1. Set the path to your PCAP file
        // TODO: change this to the actual path of a .pcap file on your system
        String pcapPath = "C:\\Users\\aksha\\OneDrive\\Desktop\\Packet_analyzer\\test_dpi.pcap";

        // 2. Parse packets from the PCAP file
        PacketParser parser = new PacketParser();
        List<Packet> packets = parser.parse(pcapPath);

        System.out.println("Total packets parsed: " + packets.size());
        System.out.println("---------------------");

        // 3. Track connections using the parsed packets
        ConnectionTracker tracker = new ConnectionTracker();

        for (Packet packet : packets) {
            tracker.trackPacket(packet);
        }

        // 4. Get all tracked connections
        Map<String, Connection> connections = tracker.getAllConnections();

        // 5. Print summary of each connection
        int index = 1;
        for (Map.Entry<String, Connection> entry : connections.entrySet()) {
            String key = entry.getKey();
            Connection connection = entry.getValue();

            System.out.println("Connection " + index++);
            System.out.println("Key (five-tuple): " + key);
            System.out.println("Source IP: " + connection.getSourceIP());
            System.out.println("Destination IP: " + connection.getDestinationIP());
            System.out.println("Source Port: " + connection.getSourcePort());
            System.out.println("Destination Port: " + connection.getDestinationPort());
            System.out.println("Protocol: " + connection.getProtocol());
            System.out.println("Packet Count: " + connection.getPacketCount());
            System.out.println("Total Bytes: " + connection.getTotalBytes());
            System.out.println("Start Time: " + connection.getStartTime());
            System.out.println("Last Seen Time: " + connection.getLastSeenTime());
            System.out.println("---------------------");
        }

        System.out.println("Total unique connections: " + tracker.getConnectionCount());
    }
}