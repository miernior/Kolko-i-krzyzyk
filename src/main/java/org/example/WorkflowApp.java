package org.example;

public class WorkflowApp implements Runnable {
    private StudentApp studentApp = null;

    public WorkflowApp(StudentApp studentApp) {
        this.studentApp = studentApp;
    }
    @Override
    public void run() {
        menuPanel panel  = new menuPanel();
        studentApp.getContentPane().removeAll();
        studentApp.getContentPane().invalidate();
        studentApp.getContentPane().add(panel);
        studentApp.getContentPane().revalidate();
    }
}
