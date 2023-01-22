package de.voyagedev.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class UDPSender {

    public static void UDPFlood(String IP, int Port) {
        try {
            // Ziel-IP-Adresse und -Port
            InetAddress address = InetAddress.getByName(IP);

            // Erstelle eine Liste von Proxy-IP-Adressen und -Ports
            List<InetSocketAddress> proxies = new ArrayList<>();
            proxies.add(new InetSocketAddress("192.168.1.1", 8080));
            proxies.add(new InetSocketAddress("192.168.1.2", 8080));
            proxies.add(new InetSocketAddress("192.168.1.3", 8080));

            // Erstelle einen DatagramSocket
            DatagramSocket socket = new DatagramSocket();

            // Erstelle ein DatagramPacket mit dem zu sendenden Nachrichteninhalt
            byte[] data = "Hello World!".getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, Port);

            // Iterieren Sie durch die Liste der Proxies
            for (InetSocketAddress proxy : proxies) {
                // Set the socket address for the packet
                packet.setSocketAddress(proxy);
                // Sende das Paket
                socket.send(packet);
            }

            // Schließe den DatagramSocket
            socket.close();

        } catch (IOException ex)  {
            ex.printStackTrace();
        }
    }
}

