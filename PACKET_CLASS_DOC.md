\# Class: Packet



\## Purpose



Represents one captured network packet in our DPI engine. It is a model of the packet stored in the PCAP file, not the actual raw bytes. The class holds the key information that other modules need for analysis.



\## Input



Data extracted by PacketParser from a PCAP frame:

\- timestamp from the capture

\- source and destination IP addresses

\- source and destination ports

\- protocol (TCP, UDP, etc.)

\- payload data

\- total size of the packet



\## Output



A Packet object that:

\- can be passed to the connection tracking module

\- can be inspected by the rule engine

\- can be logged or used to compute statistics



\## Used By



\- PacketParser (creates Packet objects)

\- ConnectionTracker (groups Packet objects into connections)

\- RuleEngine (checks Packet objects against rules)

\- StatisticsManager (counts packets, bytes, by protocol and ports)

