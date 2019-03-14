package lesson3.gui;

import lesson3.gui.elements_for_changing_name.DialogWindow;
import lesson3.history.HistoryKeeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements MessageSender {

    private JTextField textField;
    private JButton button;
    private JScrollPane scrollPane;
    private JList<Message> messageList;
    private DefaultListModel<Message> messageListModel;
    private JList<String> userList;
    private JPanel panel;

    private Network network;

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 500);

        // Добавим контекстное меню
        JPopupMenu popupMenu = createPopupMenu();
        ((JComponent) getContentPane()).setComponentPopupMenu(popupMenu);

        setLayout(new BorderLayout());

        messageListModel = new DefaultListModel<>();
//        messageListModel.addElement();
        messageList = new JList<>(messageListModel);
        messageList.setCellRenderer(new MessageCellRenderer());

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(messageList, BorderLayout.SOUTH);
        panel.setBackground(messageList.getBackground());
        scrollPane = new JScrollPane(panel);
        scrollPane.setInheritsPopupMenu(true); // Добавим контекстное меню
        add(scrollPane, BorderLayout.CENTER);

        userList = new JList<>();
        // TODO добавить класс Model для userList по аналогии с messageListModel
        userList.setListData(new String[]{"ivan", "petr", "julia"}); // Для простоты, пока фиксированный список имен пользователей
        userList.setPreferredSize(new Dimension(100, 0));
        add(userList, BorderLayout.WEST);
        userList.setInheritsPopupMenu(true); // Добавим контекстное меню

        textField = new JTextField();
        button = new JButton("Отправить");
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userTo = userList.getSelectedValue();
                if (userTo == null) {
                    JOptionPane.showMessageDialog(MainWindow.this,
                            "Не указан получатель",
                            "Отправка сообщения",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String text = textField.getText();
                if (text == null || text.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(MainWindow.this,
                            "Нельзя отправить пустое сообщение",
                            "Отправка сообщения",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Message msg = new Message(network.getUsername(), userTo, text.trim());
                submitMessage(msg);
                textField.setText(null);
                textField.requestFocus();

                network.sendMessageToUser(msg);
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                messageList.ensureIndexIsVisible(messageListModel.size() - 1);
            }
        });

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.EAST);
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (network != null) {
                    network.close();
                }
                super.windowClosing(e);
            }
        });


        setVisible(true);

        network = new Network("localhost", 7777, this);

        LoginDialog loginDialog = new LoginDialog(this, network);
        loginDialog.setVisible(true);

        if (!loginDialog.isConnected()) {
            System.exit(0);
        }

        setTitle("Сетевой чат. Пользователь " + network.getUsername());
////
        System.out.println("getNetwork().getUsername(): " + getNetwork().getUsername());
        Message[] messages = getMessageArray(HistoryKeeper.getHistory(getNetwork().getUsername() + ".txt", 10));
        for (int i = 0; i < messages.length; i++) {
            System.out.println(messages[i].getText());
        }

        System.out.println(HistoryKeeper.getHistory(getNetwork().getUsername() + ".txt", 10));
        for (Message message : messages) {
            messageListModel.addElement(message);
        }
//        messageListModel.addElement(new Message(null, null, HistoryKeeper.getHistory(getNetwork().getUsername() + ".txt", 10)));
    }

    private Message[] getMessageArray(String history) {
        String[] strMessages = history.split("\r\n");
        Message[] messages = new Message[strMessages.length];

        for (int i = 0; i < messages.length; i++) {
            messages[i] = new Message(network.getUsername(), null, strMessages[i]);
        }
        return null;
    }

    @Override
    public void submitMessage(Message msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                messageListModel.add(messageListModel.size(), msg);
                messageList.ensureIndexIsVisible(messageListModel.size() - 1);
            }
        });
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem changeNameItem = new JMenuItem("Сменить ник");
        changeNameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWindow dialogWindow = new DialogWindow(getNetwork());
            }
        });
        popupMenu.add(changeNameItem);
        return popupMenu;
    }

    public Network getNetwork() {
        return network;
    }
}
