package it.gov.pagopa.exercise.chatserver;

import java.util.Optional;

public class MessageBroker extends Thread {

    private ChatServer chatServer;
    private ChatChannel chatChannel;

    public MessageBroker(ChatServer chatServer, ChatChannel chatChannel) {
        this.chatServer = chatServer;
        this.chatChannel = chatChannel;
    }

    @Override
    public void run() {
        while(true) {
            Optional.ofNullable(chatChannel.readMessage())
                    .ifPresent(msg -> chatServer.receiveMessage(chatChannel, msg));
            sleep();
        }
    }

    private void sleep() {
        try {
            sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
