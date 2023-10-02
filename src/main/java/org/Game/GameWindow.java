package org.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private static final int WIDTH_WINDOW = 500;
    private static final int HEIGHT_WINDOW = 500;

    JButton btnStart, btnExit;
    SettingWindow settingWindow;
    Map map;

    GameWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setTitle("TicTacToe");
        setResizable(false);

        btnStart = new JButton("Новая игра");
        btnExit = new JButton("Выход");
        settingWindow = new SettingWindow(this);
        map = new Map();

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingWindow.setVisible(true);
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);

        add(panBottom, BorderLayout.SOUTH);
        add(map);

        setVisible(true);
    }

    void startNewGame(int mode, int sizeX, int sizeY, int winLen){
        map.startNewGame(mode, sizeX, sizeY, winLen);
    }
}
