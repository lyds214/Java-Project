import java.net.*;
import java.io.*;
import java.util.*;

// This file is the server side of the UDP client-server application. 
// The server will wait for a client to send a message, and then it will
// send a reply back to the client.

public class UDPServer {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;

        Scanner input = new Scanner(System.in);

        try {
            aSocket = new DatagramSocket(6789);

            // This part inputs the port number that you want to input.
            System.out.println("Please enter your port number: ");

            int port = input.nextInt();

            // This part inputs the IP address that you want to input.
            aSocket = new DatagramSocket(port);

            while (true) {
                // This part creates a byte array that will be used to store the message.
                byte[] buffer = new byte[1000];

                // This part creates a DatagramPacket that will be used to store the message.
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                // This part prints out the message that was received.
                String message = new String(request.getData(), 0, request.getLength());

                System.out.println("Client Message: " + message);
                message = message.toUpperCase();
                buffer = message.getBytes();

                // This part creates a DatagramPacket that will be used to send the reply.
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
            }
        }

        // This part catches any SocketException errors.
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }

        // This part catches any IOException errors.
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

        // This part closes the socket.
        finally {
            if (aSocket != null)
                aSocket.close();
        }
    }

}