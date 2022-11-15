import java.net.*;
import java.io.*;
import java.util.*;

public class UDPClient {
    public static void main(String args[]) {

        // This part creates a DatagramSocket that will be used to send the message.
        DatagramSocket aSocket = null;
        Scanner scan = new Scanner(System.in);

        // This part asks the user for their IP address and port number.
        System.out.println("Please enter IP address: ");

        String ip = scan.nextLine();

        System.out.println("Please enter port number: ");
        int portNum = scan.nextInt();

        try {
            aSocket = new DatagramSocket();

            // This part asks the user to enter their message.
            System.out.println("Please enter your message: ");
            String message = scan.nextLine();
            message = scan.nextLine();

            // This part creates a byte array that will be used to store the message.
            byte[] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName(ip);

            // This part creates a DatagramPacket that will be used to send the message.
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, portNum);

            aSocket.send(request);
            byte[] buffer = new byte[1000];

            // This part creates a DatagramPacket that will be used to store the reply.
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            // This part prints out the reply.
            System.out.println("Reply: " + new String(reply.getData()));
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
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}