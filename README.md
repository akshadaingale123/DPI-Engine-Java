# DPI-Engine-Java

A Java-based **Deep Packet Inspection (DPI) Engine** that analyzes network traffic from PCAP files and generates detailed traffic statistics in both **JSON** and **HTML** formats.

The project is built using a modular architecture to separate packet parsing, connection tracking, traffic analysis, statistics generation, and report creation.

---

## Features

- Parse PCAP files using Pcap4J
- Track network connections
- Detect application protocols
  - HTTP
  - HTTPS
  - DNS
- Generate traffic statistics
  - Total Packets
  - Total Connections
  - Total Bytes
  - Average Bytes per Connection
  - Top Source IP
  - Top Destination IP
  - Top Application Protocol
- Export reports in:
  - JSON
  - HTML

---

## Tech Stack

- Java 21
- Maven
- Pcap4J
- Jackson Databind

---

## Project Structure

```text
src
└── main
    └── java
        └── com.example.dpi
            ├── analyzer
            ├── connection
            ├── models
            ├── parser
            ├── report
            ├── rules
            ├── statistics
            ├── tracker
            └── DpiMain.java
```

---

## Project Workflow

```text
PCAP File
     │
     ▼
PacketParser
     │
     ▼
ConnectionTracker
     │
     ▼
RuleEngine
     │
     ▼
TrafficAnalyzer
     │
     ▼
StatisticsEngine
     │
     ▼
ReportGenerator
     │
     ├── report.json
     └── report.html
```

---

## Sample HTML Report

(Add a screenshot of `report.html` here)

---

## Sample JSON Report

(Add a screenshot of `report.json` here)

---

## Future Improvements

- Support additional protocols (FTP, SMTP, SSH, DHCP, ICMP)
- Command-line input for PCAP files
- Interactive dashboard
- CSV export
- Unit testing
- Improved protocol detection

---

## Author

**Akshada Ingale**

