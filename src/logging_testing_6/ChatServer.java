package logging_testing_6;


import logging_testing_6.auth.AuthService;
import logging_testing_6.auth.AuthServiceImpl;
import logging_testing_6.gui.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatServer {

    //    private static final String USER_CONNECTED_PATTERN = "/userconn";
//    private static final String USER_DISCONN_PATTERN = "/userdissconn";
    private static final Pattern AUTH_PATTERN = Pattern.compile("^/auth (\\w+) (\\w+)$");

    private AuthService authService = new AuthServiceImpl();

    private Map<String, ClientHandler> clientHandlerMap = Collections.synchronizedMap(new HashMap<>());
    private ExecutorService executorService;

    //    private static final Logger logger = LoggerFactory.getLogger(ChatServer.class);
    private static final Logger log = LogManager.getLogger(ChatServer.class);

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start(7777);
    }

    public void start(int port) {
        executorService = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream inp = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                log.info("New client connected!");

                try {
                    String authMessage = inp.readUTF();
                    Matcher matcher = AUTH_PATTERN.matcher(authMessage);
                    if (matcher.matches()) {
                        String username = matcher.group(1);
                        String password = matcher.group(2);
                        if (authService.authUser(username, password)) {
                            clientHandlerMap.put(username, new ClientHandler(username, socket, this));
                            out.writeUTF("/auth successful");
                            out.flush();
                            broadcastUserConnected();

                            log.info("Authorization for user {} %s successful", username);
                        } else {
                            log.info("Authorization for user {} failed", username);
                            out.writeUTF("/auth fails");
                            out.flush();
                            socket.close();
                        }
                    } else {
                        log.info("Incorrect authorization message {}", authMessage);
                        out.writeUTF("/auth fails");
                        out.flush();
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            authService.close();
            executorService.shutdown();
        }
    }

    public void sendMessage(String userTo, String userFrom, String msg) throws IOException {
        ClientHandler userToClientHandler = clientHandlerMap.get(userTo);
        if (userToClientHandler != null) {
            userToClientHandler.sendMessage(userFrom, msg);
        } else {
            log.debug("User {} not found. Message from {} is lost.", userTo, userFrom);
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void sendMessage(Message message) throws IOException {
        String userTo = message.getUserTo();
        String userFrom = message.getUserFrom();
        String msg = message.getText();
        ClientHandler userToClientHandler = clientHandlerMap.get(userTo);
        if (userToClientHandler != null) {
            userToClientHandler.sendMessage(userFrom, msg);
        } else {
            log.debug("User {} not found. Message from {} is lost.", userTo, userFrom);
        }
    }

    public List<String> getUserList() {
        return new ArrayList<>(clientHandlerMap.keySet());
    }

    public void unsubscribeClient(ClientHandler clientHandler) {
        clientHandlerMap.remove(clientHandler.getUsername());
        broadcastUserDisconnected();
    }

    public void broadcastUserConnected() {
        // TODO сообщать о том, что конкретный пользователь подключился
    }

    public void broadcastUserDisconnected() {
        // TODO сообщать о том, что конкретный пользователь отключился
    }
}
