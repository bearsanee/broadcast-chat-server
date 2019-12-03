package it.gov.pagopa.exercise.chatserver;

public class BroadcastChatServer extends AbstractBroadcastChatServer {

    BroadcastChatChannel client1;
    BroadcastChatChannel client2;

    BroadcastChatServer(BroadcastChatChannel client1, BroadcastChatChannel client2) {
        this.client1 = client1;
        this.client2 = client2;
    }

    @Override
    public void run() {
        connectToChannel(client1);
        MessageBroker messageBroker1 = new MessageBroker(this, client1);
        messageBroker1.start();

        connectToChannel(client2);
        MessageBroker messageBroker2 = new MessageBroker(this, client2);
        messageBroker2.start();
    }
}