package task3;

import task3.draw.DrawPanel;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setSize(500, 500);
        frame1.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        frame1.add(new DrawPanel());
        frame1.setVisible(true);

    }

}
