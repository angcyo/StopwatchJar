package com.angcyo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by KEYABQJHCQUMXSO$ on 16-04-07-007.
 */
public class HelloJar {

    private javax.swing.JPanel JPanel;
    private JCheckBox showTop;
    private JButton start;
    private JButton stop;
    private JLabel time;
    private static JFrame frame;

    public HelloJar() {
        showTop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showTop.isSelected()) {
                    frame.setAlwaysOnTop(true);    //总在最前面
                } else {
                    frame.setAlwaysOnTop(false);
                }
            }
        });
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTime();
                start.setEnabled(false);
            }
        });
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTime();
                start.setEnabled(true);
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("HelloJar");
        frame.setContentPane(new HelloJar().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);    //总在最前面

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = tk.createImage("res/image/logo.png"); /*image.gif是你的图标*/
        frame.setIconImage(image);
    }

    volatile static boolean isExit = false;

    void startTime() {
        isExit = false;
        new TimeThread().start();
    }

    void stopTime() {
        isExit = true;
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (!isExit) {
                long nowTime = System.currentTimeMillis();
                time.setText(format(nowTime - startTime));
            }
        }
    }

    // 将毫秒数格式化
    private String format(long elapsed) {
        int hour, minute, second, milli;

        milli = (int) (elapsed % 1000);
        elapsed = elapsed / 1000;

        second = (int) (elapsed % 60);
        elapsed = elapsed / 60;

        minute = (int) (elapsed % 60);
        elapsed = elapsed / 60;

        hour = (int) (elapsed % 60);

        return String.format("%02d:%02d:%02d.%03d", hour, minute, second, milli);
    }
}
