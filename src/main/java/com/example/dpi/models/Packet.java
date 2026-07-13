package com.example.dpi.models;
public class Packet {

    private long timestamp;
    private String sourceIP;
    private String destinationIP;
    private int sourcePort;
    private int destinationPort;
    private String protocol;
    private String payload;
    private int size;

    // Constructor
    public Packet(long timestamp, String sourceIP, String destinationIP,
                  int sourcePort, int destinationPort, String protocol,
                  String payload, int size) {
        this.timestamp = timestamp;
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;
        this.sourcePort = sourcePort;
        this.destinationPort = destinationPort;
        this.protocol = protocol;
        this.payload = payload;
        this.size = size;
    }

    // Getters
    public long getTimestamp() {
        return timestamp;
    }

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

    public String getPayload() {
        return payload;
    }

    public int getSize() {
        return size;
    }

    // Setters
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // toString method
    @Override
    public String toString() {
        return "Packet{" +
                "timestamp=" + timestamp +
                ", sourceIP='" + sourceIP + '\'' +
                ", destinationIP='" + destinationIP + '\'' +
                ", sourcePort=" + sourcePort +
                ", destinationPort=" + destinationPort +
                ", protocol='" + protocol + '\'' +
                ", payload='" + payload + '\'' +
                ", size=" + size +
                '}';
    }
}