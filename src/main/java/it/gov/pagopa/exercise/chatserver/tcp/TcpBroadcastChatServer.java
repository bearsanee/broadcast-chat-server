package it.gov.pagopa.exercise.chatserver.tcp;

import it.gov.pagopa.exercise.chatserver.AbstractBroadcastChatServer;
import it.gov.pagopa.exercise.chatserver.MessageBroker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpBroadcastChatServer extends AbstractBroadcastChatServer {

    private ServerSocket server;
    private boolean running;

    public TcpBroadcastChatServer(int port) throws IOException {
        server = new ServerSocket(port);
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Socket socketClient = server.accept();
                TcpChatChannel tcpChatChannel = new TcpChatChannel(socketClient);
                connectToChannel(tcpChatChannel);
                MessageBroker messageBroker = new MessageBroker(this, tcpChatChannel);
                messageBroker.start();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void shutdown() {
        running = false;
        super.shutdown();
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
