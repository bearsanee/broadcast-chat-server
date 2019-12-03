package it.gov.pagopa.exercise.chatserver.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TcpBroadcastChatClient {

    private SocketChannel client;

    TcpBroadcastChatClient(String ipAddress, int port) throws IOException {
        client = SocketChannel.open();
        client.connect(new InetSocketAddress(ipAddress, port));

    }

    public void sendMessage(String message) {
        ByteBuffer buf = ByteBuffer.allocateDirect(message.getBytes().length);
        buf.put(message.getBytes());
        try {
            System.out.println("sending message:" + message);
            client.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMessageReceived() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        try {
            System.out.println("start receiving message");
            int bytesRead = client.read(buf);
            System.out.println("bytes read ["+bytesRead+"]");
            StringBuffer stringBuffer = new StringBuffer(buf.asCharBuffer());
            buf.clear();
            while(bytesRead > 0) {
                bytesRead = client.read(buf);
                System.out.println("bytes read ["+bytesRead+"]");
                stringBuffer.append(buf.asCharBuffer());
                buf.clear();
            }
            if (!stringBuffer.toString().isEmpty()) {
                System.out.println("message received ["+stringBuffer.toString()+"]");
                return stringBuffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("no received message");
        return null;
    }

    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
