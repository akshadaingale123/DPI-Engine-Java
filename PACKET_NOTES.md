\# Packet Model Notes



\## Why the Packet class exists



In a DPI engine, each network packet is like one bag going through security. The program needs a clear way to represent that bag so different modules can inspect it. The Packet class is a simple container that holds all important information about one packet. Instead of passing separate variables everywhere, the parser, rule engine, logger, and statistics modules all work with Packet objects.



\## Fields and what they mean



\- timestamp: when the packet was captured. Used for sorting, measuring time gaps, and understanding the order of events.

\- sourceIP: the IP address of the machine that sent the packet.

\- destinationIP: the IP address of the machine that receives the packet.

\- sourcePort: the port number on the sender side, usually a temporary port chosen by the client.

\- destinationPort: the port number on the receiver side, usually a well-known service port like 80 (HTTP) or 443 (HTTPS).

\- protocol: the transport protocol, like TCP, UDP, or ICMP.

\- payload: the actual data carried by the packet. This is what DPI inspects for patterns or content.

\- size: the total size of the packet in bytes, useful for statistics and detecting unusually large or small packets.



\## Answers to key questions



1\. \*\*Why sourcePort and destinationPort?\*\*



&#x20;  A packet travels between two endpoints. The source port identifies the client-side of the conversation, and the destination port identifies the service being contacted. Keeping both helps us understand direction and which application or service is involved. One "port" field would lose this information.



2\. \*\*Why is timestamp a long?\*\*



&#x20;  The timestamp is used for calculations, not just display. Storing it as a long (for example, milliseconds since epoch) makes it easy to sort packets by time and measure delays between packets. A String would be harder to process, and a Date can be created later when we need a human-readable view.



3\. \*\*How to guess HTTPS traffic?\*\*



&#x20;  Typical HTTPS traffic uses TCP as the protocol and port 443 as the destination port. So we would check that protocol is TCP and destinationPort is 443. This is not perfect for all cases, but it is the standard and most common setup.



4\. \*\*Why have both size and payload?\*\*



&#x20;  Size usually represents the total bytes of the packet, including headers and payload. Payload represents only the data part that DPI cares about. We need size for volume statistics and anomaly detection, and payload for content inspection. They answer different questions, so they are both useful.



5\. \*\*What if protocol is unknown?\*\*



&#x20;  If the parser cannot determine the protocol, a safe choice is to set protocol to a value like "UNKNOWN" instead of pretending it is TCP or leaving it null. This lets rule and statistics modules handle unknown-protocol packets in a controlled way, for example by skipping deep inspection or counting them separately.

