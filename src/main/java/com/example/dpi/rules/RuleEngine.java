
package com.example.dpi.rules;

import com.example.dpi.models.Packet;

public class RuleEngine {

    public String detectApplication(Packet packet) {

        int sourcePort = packet.getSourcePort();
        int destinationPort = packet.getDestinationPort();

        if (sourcePort == 80 || destinationPort == 80) {
            return "HTTP";
        }

        if (sourcePort == 443 || destinationPort == 443) {
            return "HTTPS";
        }

        if (sourcePort == 53 || destinationPort == 53) {
            return "DNS";
        }

        if (sourcePort == 21 || destinationPort == 21) {
            return "FTP";
        }

        if (sourcePort == 22 || destinationPort == 22) {
            return "SSH";
        }

        if (sourcePort == 25 || destinationPort == 25) {
            return "SMTP";
        }

        return "UNKNOWN";
    }
}
