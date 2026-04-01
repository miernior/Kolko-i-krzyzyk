package org.example;

import javax.swing.*;
import java.awt.*;

public class menuPanel extends JPanel {

    Image tlo;
    JButton GraczVsGracz;
    JButton GraczVsPC;
    JButton Koniec;
    public menuPanel(){
        this.setLayout(null);
        GraczVsGracz = new JButton("Gracz VS Gracz");
        GraczVsGracz.setBounds(100, 100, 200, 50);
        GraczVsGracz.setFont(new Font("Arial", Font.BOLD, 20));
        GraczVsGracz.setForeground(Color.white);
        GraczVsGracz.setBackground(Color.decode("#1E1E1E"));
        this.add(GraczVsGracz);

        GraczVsPC = new JButton("Gracz VS Bot");
        GraczVsPC.setBounds(100, 200, 200, 50);
        GraczVsPC.setFont(new Font("Arial", Font.BOLD, 20));
        GraczVsPC.setForeground(Color.white);
        GraczVsPC.setBackground(Color.decode("#1E1E1E"));
        this.add(GraczVsPC);

        Koniec = new JButton("Zakończ grę");
        Koniec.setBounds(100, 300, 200, 50);
        Koniec.setFont(new Font("Arial", Font.BOLD, 20));
        Koniec.setForeground(Color.white);
        Koniec.setBackground(Color.decode("#1E1E1E"));
        this.add(Koniec);

        tlo = new ImageIcon("tlo.png").getImage();

        Koniec.addActionListener(e -> {
            System.exit(0);
        });

        GraczVsGracz.addActionListener(e -> {
            GraczVsGracz panel = new GraczVsGracz();
            JFrame aktualnie = (JFrame) SwingUtilities.getWindowAncestor(this);
            aktualnie.getContentPane().removeAll();
            aktualnie.getContentPane().invalidate();
            aktualnie.getContentPane().add(panel);
            aktualnie.getContentPane().revalidate();
        });

        GraczVsPC.addActionListener(e -> {
            GraczPC panel = new GraczPC();
            JFrame aktualnie = (JFrame) SwingUtilities.getWindowAncestor(this);
            aktualnie.getContentPane().removeAll();
            aktualnie.getContentPane().invalidate();
            aktualnie.getContentPane().add(panel);
            aktualnie.getContentPane().revalidate();
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tlo, 0, 0, getWidth(), getHeight(), this);
    }

}
