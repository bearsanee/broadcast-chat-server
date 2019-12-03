package it.gov.pagopa.exercise.chatserver.tcp;

import java.io.IOException;

public class TcpMain {

    public static void main(String[] args) {
        try {
            TcpBroadcastChatServer tcpChatServer = new TcpBroadcastChatServer(10000);
            tcpChatServer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
