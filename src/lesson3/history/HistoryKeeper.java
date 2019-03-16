package lesson3.history;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryKeeper {

    public static void write(String fileName, String userName, String message) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true))) {
            String str = userName + ": " + message + "\r\n";
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
