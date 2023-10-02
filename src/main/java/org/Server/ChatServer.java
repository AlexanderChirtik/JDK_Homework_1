package org.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Данное окно вызывается из конструктора класса ChatClient
 */
public class ChatServer extends JFrame {

    private static final int WIDTH_WINDOW = 400;
    private static final int HEIGHT_WINDOW = 300;
    private static final int POS_X = 600;
    private static final int POS_Y = 200;

    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");

    /**
     * Переменная для отслеживания состояния кнопки stop. Если она не нажата, то сообщения могут выводиться на экран
     */
    public static boolean stopEnable = true;

    private JTextArea messages = new JTextArea();

    ChatServer() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocation(POS_X, POS_Y);
        setTitle("Chat server");
        setResizable(false);

        JPanel btms = new JPanel(new GridLayout(1,2));
        btms.add(start);
        btms.add(stop);

        add(btms, BorderLayout.SOUTH);
        add(messages, BorderLayout.CENTER);

        /**
         * При нажатии кнопки start значение переменной stopEnable автоматически становится false,
         * так как start подразумевает, что выводить информацию в центр экрана можно.
         */
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopEnable = false;
                messageDisplay();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopEnable =true;
            }
        });

        setVisible(true);
    }

    /**
     * Метод берет всю информацию из файла logHistory.txt благодаря методу readFile() класса LogHistory
     * и передает этот текст в поле messages, находящееся в центре экрана
     */
    public void messageDisplay() {
        LogHistory logHistory = new LogHistory();
        messages.setText(logHistory.readFile());
    }

}
