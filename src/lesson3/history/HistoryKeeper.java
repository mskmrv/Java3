package lesson3.history;

import lesson3.ClientHandler;
import lesson3.gui.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryKeeper {
    private String message;
    private String fileName;
    private final ClientHandler clientHandler;

    public HistoryKeeper(String message, String username, ClientHandler clientHandler) {
        this.message = message;
        this.fileName = username + ".txt";
        this.clientHandler = clientHandler;
    }

    public String getMessage() {
        return message;
    }

    public String getFileName() {
        return fileName;
    }

    public void write() {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName, true))) {
            String userName = clientHandler.getUsername();
            String str = userName + ": " + message + "\r\n";
            byte[] array = str.getBytes();
            out.write(array);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHistory(String fileName, int numberOfRows) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    list.add(line);
//                    sb.append(line).append("\r\n");
                }
            }
            if (list.size() <= numberOfRows) {
                for (String s : list) {
                    sb.append(s).append("\r\n");
                }
            } else {
                for (int i = list.size() - numberOfRows; i < list.size(); i++) {
                    sb.append(list.get(i)).append("\r\n");
                }
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
