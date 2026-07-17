package com.example.dpi.connection;

import com.example.dpi.models.Packet;

public class Connection {

    private String sourceIP;
    private String destinationIP;
    private int sourcePort;
    private int destinationPort;
    private String protocol;

    private long startTime;
    private long lastSeenTime;
    private long totalBytes;
    private int packetCount;

    // Constructor
    public Connection(String sourceIP, String destinationIP,
                       int sourcePort, int destinationPort,
                       String protocol, long startTime) {
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.protocol = protocol;
        this.startTime = startTime;
        this.lastSeenTime = startTime;
        this.totalBytes = 0;
        this.packetCount = 0;
    }

    // Getters
    public String getSourceIP() {
        return sourceIP;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getLastSeenTime() {
        return lastSeenTime;
    }

    public long getTotalBytes() {
        return totalBytes;
    }

    public int getPacketCount() {
        return packetCount;
    }

    // Setters
    public void setLastSeenTime(long lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public void setPacketCount(int packetCount) {
        this.packetCount = packetCount;
    }

    // Helper method to record that a new packet belongs to this connection
    public void addPacketInfo(long packetTime, int packetSize) {
        this.lastSeenTime = packetTime;
        this.totalBytes += packetSize;
        this.packetCount += 1;
    }

    // toString method
    @Override
    public String toString() {
        return "Connection{" +
                "sourceIP='" + sourceIP + '\'' +
                ", destinationIP='" + destinationIP + '\'' +
                ", sourcePort=" + sourcePort +
                ", destinationPort=" + destinationPort +
                ", protocol='" + protocol + '\'' +
                ", startTime=" + startTime +
                ", lastSeenTime=" + lastSeenTime +
                ", totalBytes=" + totalBytes +
                ", packetCount=" + packetCount +
                '}';
    }
}