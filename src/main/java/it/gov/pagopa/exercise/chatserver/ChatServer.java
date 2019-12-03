package it.gov.pagopa.exercise.chatserver;

public interface ChatServer extends Runnable {

    void connectToChannel(ChatChannel channel);

    void receiveMessage(ChatChannel channel, String message);

    void shutdown();
}
