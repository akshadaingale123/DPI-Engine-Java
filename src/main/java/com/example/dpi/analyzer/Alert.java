package com.example.dpi.analyzer;
public class Alert {

    private String type;
    private String severity;
    private String sourceIP;
    private String description;

    public Alert(String type, String severity,
                 String sourceIP, String description) {
        this.type = type;
        this.severity = severity;
        this.sourceIP = sourceIP;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getSeverity() {
        return severity;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + severity + "] " + type +
                " | Source: " + sourceIP +
                " | " + description;
    }
}