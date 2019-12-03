package it.gov.pagopa.exercise.chatserver;

public interface ChatChannel {

    String readMessage();

    void writeMessage(String message);

    void close();
}
