package logging_testing_6.history;

import logging_testing_6.gui.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryKeeper {
    private static final String HISTORY_DIR = "history";

    public static void saveMassageToHistoryFrom(Message message) {
        String userName = message.getUserFrom();
        String messageText = message.getText();
        String fileName = userName + ".txt";
        File file = new File(HISTORY_DIR, fileName);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))
        ) {
            String str = userName + ": " + messageText + "\r\n";
            out.write(str);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMassageToHistoryTo(Message message) {
        String userName = message.getUserTo();
        String messageText = message.getText();
        String fileName = userName + ".txt";
        File file = new File(HISTORY_DIR, fileName);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
//            String str = userName + ": " + messageText + "\r\n";
            String str = message.getUserFrom() + ": " + messageText + "\r\n";
            out.write(str);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Message> getHistory(String fileName, int numberOfRows) {
        List<String> textMessageList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        File file = new File(HISTORY_DIR, fileName);
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            for (long pointer = file.length() - 1; pointer >= 0 && textMessageList.size() < numberOfRows; pointer--) {
                raf.seek(pointer);
                char aChar = (char) raf.read();
                if (aChar != '\n') {
                    sb.append(aChar);
                } else if (sb.length() > 1) {
                    textMessageList.add(sb.reverse().toString());
                    sb.delete(0, sb.length());
                }
            }
            if (sb.length() > 1) {
                textMessageList.add(sb.reverse().toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Message> messageList = textMessageList.stream()
                .map(record -> {
                    String userFrom = record.substring(0, record.indexOf(" ") - 1);
                    String textMessage = record.substring(record.indexOf(" ") - 1);
                    return new Message(userFrom, null, textMessage);
                }).collect(Collectors.toList());

        Collections.reverse(messageList);
        return messageList;
    }
}
