package it.gov.pagopa.exercise.chatserver.tcp;

import it.gov.pagopa.exercise.chatserver.ChatChannel;

import java.io.*;
import java.net.Socket;

public class TcpChatChannel implements ChatChannel {

    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    public TcpChatChannel(Socket socket) throws IOException {
        this.socket = socket;

        OutputStream outputStream = socket.getOutputStream();
        writer = new PrintWriter(outputStream, true);

        InputStream inputStream = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public String readMessage() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeMessage(String message) {
        writer.println(message);
    }

    @Override
    public void close() {
        writer.close();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
