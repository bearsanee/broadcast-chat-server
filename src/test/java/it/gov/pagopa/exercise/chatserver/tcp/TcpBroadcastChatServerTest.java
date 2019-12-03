package it.gov.pagopa.exercise.chatserver.tcp;

import it.gov.pagopa.exercise.chatserver.ChatServer;
import org.junit.Test;

import java.io.IOException;

public class TcpBroadcastChatServerTest {

    @Test
    public void shouldTcpBroadcast() throws IOException {
        int port = 10000;
        ChatServer server = new TcpBroadcastChatServer(port);
        new Thread(server).start();

        TcpBroadcastChatClient client1 = new TcpBroadcastChatClient("127.0.0.1", port);
        TcpBroadcastChatClient client2 = new TcpBroadcastChatClient("127.0.0.1", port);

        client1.close();
        client2.close();
        server.shutdown();
    }

}