package com.example.dpi.parser;
import com.example.dpi.models.Packet;

import org.pcap4j.core.*;
import org.pcap4j.packet.*;
import org.pcap4j.packet.namednumber.IpNumber;

import java.util.ArrayList;
import java.util.List;

public class PacketParser {

    public List<Packet> parse(String pcapFilePath) {
        List<Packet> parsedPackets = new ArrayList<>();

        try {
            PcapHandle handle = Pcaps.openOffline(pcapFilePath);

            org.pcap4j.packet.Packet rawPacket;
            while ((rawPacket = handle.getNextPacketEx()) != null) {

                long timestamp = handle.getTimestamp().getTime();

                IpV4Packet ipV4Packet = rawPacket.get(IpV4Packet.class);
                if (ipV4Packet == null) {
                    continue;
                }

                String sourceIP = ipV4Packet.getHeader().getSrcAddr().getHostAddress();
                String destinationIP = ipV4Packet.getHeader().getDstAddr().getHostAddress();
                IpNumber ipProtocol = ipV4Packet.getHeader().getProtocol();

                int sourcePort = 0;
                int destinationPort = 0;
                String protocol = ipProtocol.name();
                String payload = "";
                int size = rawPacket.length();

                TcpPacket tcpPacket = rawPacket.get(TcpPacket.class);
                UdpPacket udpPacket = rawPacket.get(UdpPacket.class);

                if (tcpPacket != null) {
                    sourcePort = tcpPacket.getHeader().getSrcPort().valueAsInt();
                    destinationPort = tcpPacket.getHeader().getDstPort().valueAsInt();
                    protocol = "TCP";
                    if (tcpPacket.getPayload() != null) {
                        payload = new String(tcpPacket.getPayload().getRawData());
                    }
                } else if (udpPacket != null) {
                    sourcePort = udpPacket.getHeader().getSrcPort().valueAsInt();
                    destinationPort = udpPacket.getHeader().getDstPort().valueAsInt();
                    protocol = "UDP";
                    if (udpPacket.getPayload() != null) {
                        payload = new String(udpPacket.getPayload().getRawData());
                    }
                }

                Packet packet = new Packet(
                        timestamp,
                        sourceIP,
                        destinationIP,
                        sourcePort,
                        destinationPort,
                        protocol,
                        payload,
                        size
                );

                parsedPackets.add(packet);
            }

            handle.close();

        } catch (PcapNativeException | NotOpenException | java.io.EOFException e) {
            e.printStackTrace();
        }

        return parsedPackets;
    }
}