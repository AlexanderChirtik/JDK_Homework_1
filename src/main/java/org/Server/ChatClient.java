package org.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatClient extends JFrame {
    private static final int WIDTH_WINDOW = 400;
    private static final int HEIGHT_WINDOW = 300;
    private static final int POS_X = 200;
    private static final int POS_Y = 200;

    /**
     * Элементы интерфейса
     */
    private JTextField ipField;
    private JTextField number;
    private JTextField name;
    private JPasswordField password;
    private JButton loginButton;
    private JTextField message;
    private JButton send;
    private JLabel emptyLabel;

    ChatClient() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocation(POS_X, POS_Y);
        setTitle("Chat client");
        setResizable(false);

        /**
         * Инициализация элементов интерфейса
         */
        ipField = new JTextField("Поле для ввода ip адреса получателя");
        number = new JTextField();
        name = new JTextField("Ваше имя");
        password = new JPasswordField();
        message = new JTextField(120);
        loginButton = new JButton("login");
        send = new JButton("send");
        emptyLabel = new JLabel();

        /**
         * Создание верхней панели
         */
        JPanel usersData = new JPanel(new GridLayout(2, 3));
        usersData.add(ipField);
        usersData.add(number);
        usersData.add(emptyLabel);
        usersData.add(name);
        usersData.add(password);
        usersData.add(loginButton);
        usersData.setBorder(BorderFactory.createEmptyBorder(2,0,0,0));

        /**
         * Создание нижней панели
         */
        JPanel messageField = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.ipadx = 315;
        messageField.add(message, constraints);

        constraints.gridx = 1;
        constraints.ipadx = 0;
        messageField.add(send, constraints);

        /**
         * Добавление панелей в окно приложения
         */
        add(usersData, BorderLayout.NORTH);
        add(messageField, BorderLayout.SOUTH);

        setVisible(true);

        /**
         * Создание объектов классов, методы которых будут вызываться при нажатии кнопок start и stop
         */
        ChatServer chatServer = new ChatServer();
        LogHistory logFile = new LogHistory();

        /**
         * При нажатии на кнопку start строка для ввода сообщения проверяется на пустоту и наличие только пробелов.
         * Дальше у объекта logFile вызывается метод writeFile() в который передается строка текста из поля ввода.
         * Таким образом сообщение сначала попадает в файл logHistory.txt.
         * После этого у объекта chatServer вызывается метод messageDisplay(), который выводит в окно сервера все,
         * что сейчас находится в файле.
         */
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkEmpty(message) && checkGap(message)) {
                    if (ChatServer.stopEnable == false) {
                        String rowOfText = name.getText() + ": " + message.getText() + "\n";
                        logFile.writeFile(rowOfText);
                        chatServer.messageDisplay();
                    }
                }
            }
        });

        /**
         * Аналогичный порядок действий, но уже при нажатии на клавишу Enter
         */
        message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (checkEmpty(message) && checkGap(message)) {
                        if (ChatServer.stopEnable == false) {
                            String rowOfText = name.getText() + ": " + message.getText() + "\n";
                            logFile.writeFile(rowOfText);
                            chatServer.messageDisplay();
                        }
                    }
                }
            }
        });
    }


    private boolean checkEmpty(JTextField text) {
        String tempText = text.getText();
        if (tempText.length() > 0) return true;
        else return false;
    }

    private boolean checkGap(JTextField text) {
        String tempText = text.getText();
        if (tempText != " ") return true;
        else return false;
    }
}
