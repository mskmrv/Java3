package lesson3.history;

import lesson3.gui.Message;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryKeeper {
    private static final String HISTORY_DIR = "history";

    public static void saveMassageToHistory(String userName, String message) {
        String fileName = userName + ".txt";
        File file = new File(HISTORY_DIR, fileName);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
            String str = userName + ": " + message + "\r\n";
            out.write(str);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMassageToHistoryFrom(Message message) {
        String userName = message.getUserFrom();
        String messageText = message.getText();
        String fileName = userName + ".txt";
        File file = new File(HISTORY_DIR, fileName);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file, true))) {
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

    public static String getHistory(String fileName, int numberOfRows) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    list.add(line);
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
