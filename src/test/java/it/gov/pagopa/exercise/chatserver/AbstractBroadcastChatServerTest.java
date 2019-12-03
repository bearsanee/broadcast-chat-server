package it.gov.pagopa.exercise.chatserver;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class AbstractBroadcastChatServerTest {

    @Test
    public void shouldBroadcast() {

        List<String> messageRicevivedByClient1 = new ArrayList<>();
        BroadcastChatChannel broadcastChatChannel1 = new BroadcastChatChannel(messageRicevivedByClient1);
        List<String> messageRicevivedByClient2 = new ArrayList<>();
        BroadcastChatChannel broadcastChatChannel2 = new BroadcastChatChannel(messageRicevivedByClient2);

        ChatServer server = new BroadcastChatServer(broadcastChatChannel1, broadcastChatChannel2);
        server.run();

        waitBroker();
        broadcastChatChannel1.sendMessage("uno");
        waitBroker();
        broadcastChatChannel2.sendMessage("due");
        waitBroker();
        broadcastChatChannel1.sendMessage("tre");
        waitBroker();
        broadcastChatChannel2.sendMessage("quattro");
        waitBroker();

        server.shutdown();

        assertTrue(messageRicevivedByClient1.equals(Arrays.asList("due", "quattro")));
        assertTrue(messageRicevivedByClient2.equals(Arrays.asList("uno", "tre")));
    }

    private void waitBroker() {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}