Class:

Connection



Purpose:

Represents one network conversation (connection) between a source IP/port and a destination IP/port using a specific protocol. It groups all related Packet objects and tracks basic stats like time and total bytes.



Input:

Packet objects created by PacketParser that share the same five-tuple (source IP, destination IP, source port, destination port, protocol).



Output:

A Connection object that:

\- Holds all packets for that conversation.

\- Provides summary information like duration and total bytes for later modules.



Used By:

\- ConnectionTracker (will create and update Connection objects).

\- Statistics Engine (will read Connection data to compute metrics).

\- Rule Engine (may evaluate rules per connection instead of per packet).



Fields:

\[List each field from your Connection.java class here: source IP, destination IP, source port, destination port, protocol, list of packets, start time, end time, total bytes, etc.]



Why each field exists:

\- Source IP: identifies the client or sender.

\- Destination IP: identifies the server or receiver.

\- Source Port: separates multiple conversations from the same client IP.

\- Destination Port: indicates which service is being contacted (HTTP, HTTPS, etc.).

\- Protocol: tells us if this is TCP, UDP, etc., which affects how we interpret the connection.

\- List<Packet>: stores all packets that belong to this connection.

\- Start Time: time of the first packet in the connection, for duration calculation.

\- End Time: time of the last packet in the connection.

\- Total Bytes: sum of packet sizes, used for traffic statistics.



Important Methods:

\[List key methods Claude created, e.g. addPacket, getDuration, etc., and a one-line purpose for each.]



One thing I learned:

\[Example: “I learned how the five-tuple uniquely identifies a network connection and why we need all five fields.”]



One thing I still don't understand:

\[Example: “I still need to understand more about how connections are closed in TCP and how that affects end time.”]

