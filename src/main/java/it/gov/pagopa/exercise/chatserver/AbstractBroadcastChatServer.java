package it.gov.pagopa.exercise.chatserver;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractBroadcastChatServer implements ChatServer {

    Set<ChatChannel> channels;

    @Override
    public void connectToChannel(ChatChannel channel) {
        if (channels == null) {
            channels = new HashSet<>();
        }
        channels.add(channel);
    }

    @Override
    public void receiveMessage(ChatChannel sendChannel, String message) {
        broadcastMessage(sendChannel, message);
    }

    private void broadcastMessage(ChatChannel sendChannel, String message) {
        channels.stream()
                .filter(client -> !sendChannel.equals(client))
                .forEach(channel -> sendMessage(channel, message));
    }

    private void sendMessage(ChatChannel channel, String message) {
        channel.writeMessage(message);
    }

    @Override
    public void shutdown() {
        if (channels != null) {
            channels.forEach(ChatChannel::close);
        }
    }
}
