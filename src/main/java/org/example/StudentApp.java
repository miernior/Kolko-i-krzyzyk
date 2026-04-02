package org.example;

import javax.swing.*;

public class StudentApp extends JFrame implements  Runnable {
    @Override
    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gra");
        this.setSize(400,500);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        this.setLocation(300,0);
        this.setIconImage(new ImageIcon("ikona.png").getImage());
        Thread workflow = new Thread(new WorkflowApp(this));
        workflow.start();
    }
}
