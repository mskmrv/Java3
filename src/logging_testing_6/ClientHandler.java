package logging_testing_6;

import logging_testing_6.gui.Message;
import logging_testing_6.history.HistoryKeeper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientHandler {

    private static final Pattern MESSAGE_PATTERN = Pattern.compile("^/w (\\w+) (.+)", Pattern.MULTILINE);
    private static final String MESSAGE_SEND_PATTERN = "/w %s %s";
    private static final Logger log = LogManager.getLogger(ClientHandler.class);


//    private final Thread handleThread;
    private final DataInputStream inp;
    private final DataOutputStream out;
    private final ChatServer server;
    private final String username;
    private final Socket socket;

    public ClientHandler(String username, Socket socket, ChatServer server) throws IOException {
        this.username = username;
        this.socket = socket;
        this.server = server;
        this.inp = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        server.getExecutorService().submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String msg = inp.readUTF();
                    log.info("Message from user {}: {}", username, msg);

                    Matcher matcher = MESSAGE_PATTERN.matcher(msg);
                    if (matcher.matches()) {
                        String userTo = matcher.group(1);
                        String messageText = matcher.group(2);
                        Message message = new Message(username, userTo, messageText);
                        server.sendMessage(message);

                        // Добавляем сообщения в файл истории отправителя
                        HistoryKeeper.saveMassageToHistoryFrom(message);
                        // Добавляем сообщения в файл истории получателя
                        HistoryKeeper.saveMassageToHistoryTo(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                log.info("Client {} disconnected", username);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server.unsubscribeClient(ClientHandler.this);
            }
        });
    }

    public void sendMessage(String userTo, String msg) throws IOException {
        out.writeUTF(String.format(MESSAGE_SEND_PATTERN, userTo, msg));
    }

    public String getUsername() {
        return username;
    }
}
