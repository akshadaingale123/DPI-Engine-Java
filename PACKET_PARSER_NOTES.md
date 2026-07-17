Class:

PacketParser



Purpose:

Reads a PCAP file using Pcap4J, extracts important packet fields (timestamp, IPs, ports, protocol, size), and creates Packet objects so other modules can analyze them.



Input:

\- Path to a PCAP file.

\- Raw Packet objects from Pcap4J (one per captured packet).



Output:

\- Packet objects in my own Packet model.

\- Printed information for each packet (for now, just to verify the parser works).



Used By:

\- Connection tracking (later: will group these Packet objects into connections).

\- Rule engine (later: will check Packet objects against rules).

\- Statistics module (later: will count packets, bytes, etc.).



Important Methods:

\- \[Write the main method name Claude gave you, e.g. `parse(String pcapPath)`] – Opens the PCAP file, loops over packets, and builds Packet objects.

\- \[List any helper methods, e.g. `createPacket(...)`, `extractIpInfo(...)`] – briefly explain what each does in one sentence.



One thing I learned:

\[Example] “I learned how Pcap4J’s PcapHandle and Packet classes help me read PCAP files without manually parsing raw bytes.”



One thing I still don't understand:

\[Example] “I still don’t fully understand all the TCP header fields, like sequence numbers and flags, but I know they are there for reliability.”

