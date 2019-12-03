package it.gov.pagopa.exercise.chatserver;

import java.util.List;

public class BroadcastChatChannel implements ChatChannel {

    private boolean close;
    private String message;
    private List<String> result;

    BroadcastChatChannel(List<String> result) {
        this.result = result;
        close = false;
        message = null;
    }

    public void sendMessage(String message) {
        this.message = message;
    }

    @Override
    public String readMessage() {
        if (!close && message != null) {
            String readed = new String(message);
            message = null;
            return readed;
        } else {
            return null;
        }
    }

    @Override
    public void writeMessage(String message) {
        result.add(message);
    }

    @Override
    public void close() {
        close = true;
    }
}
