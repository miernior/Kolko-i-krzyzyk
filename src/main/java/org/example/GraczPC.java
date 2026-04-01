package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class GraczPC extends JPanel {
    JButton[][] Przyciski = new JButton[3][3];
    char Gracz = 'X';
    JLabel WynikX;
    JLabel WynikO;
    JPanel Wyniki;
    int pktX = 0;
    int pktO = 0;
    JLabel InfoGracz;
    Boolean KoniecGry = false;

    public GraczPC() {
        this.setLayout(new BorderLayout());
        JPanel Dane = new JPanel(new GridLayout(2,1));

        Wyniki = new JPanel(new BorderLayout());
        Wyniki.setBorder(BorderFactory.createEmptyBorder(5, 40, 3, 40));

        WynikX = new JLabel("Gracz : " + pktX);
        WynikO = new JLabel("Komputer : " + pktO);
        WynikX.setFont(new Font("Arial", Font.BOLD, 20));
        WynikO.setFont(new Font("Arial", Font.BOLD, 20));
        WynikX.setForeground(Color.white);
        WynikO.setForeground(Color.white);

        Wyniki.add(WynikX, BorderLayout.WEST);
        Wyniki.add(WynikO, BorderLayout.EAST);
        Wyniki.setBackground(Color.decode("#1E1E1E"));

        InfoGracz = new JLabel("",  JLabel.CENTER);
        InfoGracz.setFont(new Font("Arial", Font.BOLD, 20));
        InfoGracz.setForeground(Color.white);
        Dane.add(Wyniki, BorderLayout.NORTH);
        Dane.add(InfoGracz, BorderLayout.SOUTH);
        Dane.setBackground(Color.decode("#1E1E1E"));
        this.add(Dane, BorderLayout.NORTH);

        JPanel Plansza = new JPanel();
        Plansza.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Przyciski[i][j] = new JButton("");
                Przyciski[i][j].setFont(new Font("Arial", Font.BOLD, 70));
                Przyciski[i][j].setBackground(Color.decode("#2A2A2A"));
                Przyciski[i][j].setBorder(new LineBorder(Color.decode("#CCCCCC"), 2));
                Plansza.add(Przyciski[i][j]);
                int row = i;
                int col = j;
                Przyciski[i][j].addActionListener(e -> {klik(row,col);});
            }
        }
        this.add(Plansza, BorderLayout.CENTER);
    }
    private void klik(int row , int col) {
        if(KoniecGry) return;
        if(!Przyciski[row][col].getText().equals("")) return;

        Przyciski[row][col].setText(String.valueOf(Gracz));

        if(CzyWygrana(Gracz)) {
            InfoGracz.setText("Wygrał gracz");

            pktX += 1;
            WynikX.setText("Gracz: " + pktX);
            KoniecGry = true;
            Okienko();
            return;
        } else if (CzyRemis()) {
            InfoGracz.setText("Remis!");
            KoniecGry = true;
            Okienko();
            Przyciski[row][col].setForeground(Color.decode("#FF4D4D"));
            return;

        }


        Przyciski[row][col].setForeground(Color.decode("#FF4D4D"));

        RuchKomputera();


        //Ruch komputera

    }
    public void  RuchKomputera(){

        if(Atakuj()){
            if(CzyWygrana('O')) {
                InfoGracz.setText("Wygrał komputer");

                pktO += 1;
                WynikO.setText("Komputer: " + pktO);
                KoniecGry = true;
                Okienko();
            }
            return;
        }

        if(Blokuj()){
            return;
        }


        Random random = new Random();
        int x = random.nextInt(0,3);
        int y = random.nextInt(0,3);
        while (!Przyciski[x][y].getText().equals("")){
            x = random.nextInt(0,3);
            y = random.nextInt(0,3);
        }
        Przyciski[x][y].setText(String.valueOf('O'));
        Przyciski[x][y].setForeground(Color.decode("#4DA6FF"));

    }
    private Boolean Atakuj(){
        for (int i = 0; i < 3; i++) {
            if(Przyciski[i][0].getText().equals("O") && Przyciski[i][1].getText().equals("O") && Przyciski[i][2].getText().equals("")){
                Przyciski[i][2].setText(String.valueOf('O'));
                Przyciski[i][2].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[i][0].getText().equals("O") && Przyciski[i][1].getText().equals("") && Przyciski[i][2].getText().equals("O")) {
                Przyciski[i][1].setText(String.valueOf('O'));
                Przyciski[i][1].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[i][0].getText().equals("") && Przyciski[i][1].getText().equals("O") && Przyciski[i][2].getText().equals("O")) {
                Przyciski[i][0].setText(String.valueOf('O'));
                Przyciski[i][0].setForeground(Color.decode("#4DA6FF"));
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(Przyciski[0][i].getText().equals("O") && Przyciski[1][i].getText().equals("O") && Przyciski[2][i].getText().equals("")){
                Przyciski[2][i].setText(String.valueOf('O'));
                Przyciski[2][i].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[0][i].getText().equals("O") && Przyciski[1][i].getText().equals("") && Przyciski[2][i].getText().equals("O")) {
                Przyciski[1][i].setText(String.valueOf('O'));
                Przyciski[1][i].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[0][i].getText().equals("") && Przyciski[1][i].getText().equals("O") && Przyciski[2][i].getText().equals("O")) {
                Przyciski[0][i].setText(String.valueOf('O'));
                Przyciski[0][i].setForeground(Color.decode("#4DA6FF"));
                return true;
            }
        }

        if(Przyciski[0][0].getText().equals("O") && Przyciski[1][1].getText().equals("O") && Przyciski[2][2].getText().equals("")){
            Przyciski[2][2].setText(String.valueOf('O'));
            Przyciski[2][2].setForeground(Color.decode("#4DA6FF"));
            return true;
        }else if (Przyciski[0][0].getText().equals("O") && Przyciski[1][1].getText().equals("") && Przyciski[2][2].getText().equals("O")) {
            Przyciski[1][1].setText(String.valueOf('O'));
            Przyciski[1][1].setForeground(Color.decode("#4DA6FF"));
            return true;
        } else if (Przyciski[0][0].getText().equals("") && Przyciski[1][1].getText().equals("O") && Przyciski[2][2].getText().equals("0")) {
            Przyciski[0][0].setText(String.valueOf('O'));
            Przyciski[0][0].setForeground(Color.decode("#4DA6FF"));
            return true;
        }

        if(Przyciski[0][2].getText().equals("O") && Przyciski[1][1].getText().equals("O") && Przyciski[2][0].getText().equals("")){
            Przyciski[2][0].setText(String.valueOf('O'));
            Przyciski[2][0].setForeground(Color.decode("#4DA6FF"));
            return true;
        }else if (Przyciski[0][2].getText().equals("O") && Przyciski[1][1].getText().equals("") && Przyciski[2][0].getText().equals("O")) {
            Przyciski[1][1].setText(String.valueOf('O'));
            Przyciski[1][1].setForeground(Color.decode("#4DA6FF"));
            return true;
        } else if (Przyciski[0][2].getText().equals("") && Przyciski[1][1].getText().equals("O") && Przyciski[2][0].getText().equals("O")) {
            Przyciski[0][2].setText(String.valueOf('O'));
            Przyciski[0][2].setForeground(Color.decode("#4DA6FF"));
            return true;
        }


        return false;
    }


    private Boolean Blokuj(){
        for (int i = 0; i < 3; i++) {
            if(Przyciski[i][0].getText().equals("X") && Przyciski[i][1].getText().equals("X") && Przyciski[i][2].getText().equals("")){
                Przyciski[i][2].setText(String.valueOf('O'));
                Przyciski[i][2].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[i][0].getText().equals("X") && Przyciski[i][1].getText().equals("") && Przyciski[i][2].getText().equals("X")) {
                Przyciski[i][1].setText(String.valueOf('O'));
                Przyciski[i][1].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[i][0].getText().equals("") && Przyciski[i][1].getText().equals("X") && Przyciski[i][2].getText().equals("X")) {
                Przyciski[i][0].setText(String.valueOf('O'));
                Przyciski[i][0].setForeground(Color.decode("#4DA6FF"));
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(Przyciski[0][i].getText().equals("X") && Przyciski[1][i].getText().equals("X") && Przyciski[2][i].getText().equals("")){
                Przyciski[2][i].setText(String.valueOf('O'));
                Przyciski[2][i].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[0][i].getText().equals("X") && Przyciski[1][i].getText().equals("") && Przyciski[2][i].getText().equals("X")) {
                Przyciski[1][i].setText(String.valueOf('O'));
                Przyciski[1][i].setForeground(Color.decode("#4DA6FF"));
                return true;
            } else if (Przyciski[0][i].getText().equals("") && Przyciski[1][i].getText().equals("X") && Przyciski[2][i].getText().equals("X")) {
                Przyciski[0][i].setText(String.valueOf('O'));
                Przyciski[0][i].setForeground(Color.decode("#4DA6FF"));
                return true;
            }
        }

        if(Przyciski[0][0].getText().equals("X") && Przyciski[1][1].getText().equals("X") && Przyciski[2][2].getText().equals("")){
            Przyciski[2][2].setText(String.valueOf('O'));
            Przyciski[2][2].setForeground(Color.decode("#4DA6FF"));
            return true;
        }else if (Przyciski[0][0].getText().equals("X") && Przyciski[1][1].getText().equals("") && Przyciski[2][2].getText().equals("X")) {
            Przyciski[1][1].setText(String.valueOf('O'));
            Przyciski[1][1].setForeground(Color.decode("#4DA6FF"));
            return true;
        } else if (Przyciski[0][0].getText().equals("") && Przyciski[1][1].getText().equals("X") && Przyciski[2][2].getText().equals("X")) {
            Przyciski[0][0].setText(String.valueOf('O'));
            Przyciski[0][0].setForeground(Color.decode("#4DA6FF"));
            return true;
        }

        if(Przyciski[0][2].getText().equals("X") && Przyciski[1][1].getText().equals("X") && Przyciski[2][0].getText().equals("")){
            Przyciski[2][0].setText(String.valueOf('O'));
            Przyciski[2][0].setForeground(Color.decode("#4DA6FF"));
            return true;
        }else if (Przyciski[0][2].getText().equals("X") && Przyciski[1][1].getText().equals("") && Przyciski[2][0].getText().equals("X")) {
            Przyciski[1][1].setText(String.valueOf('O'));
            Przyciski[1][1].setForeground(Color.decode("#4DA6FF"));
            return true;
        } else if (Przyciski[0][2].getText().equals("") && Przyciski[1][1].getText().equals("X") && Przyciski[2][0].getText().equals("X")) {
            Przyciski[0][2].setText(String.valueOf('O'));
            Przyciski[0][2].setForeground(Color.decode("#4DA6FF"));
            return true;
        }


        return false;
    }

    private boolean CzyWygrana(char Gracz) {
        String G = String.valueOf(Gracz);
        for (int i = 0; i < 3; i++) {
            if (Przyciski[i][0].getText().equals(G) && Przyciski[i][1].getText().equals(G) && Przyciski[i][2].getText().equals(G)) {
                Przyciski[i][0].setForeground(Color.decode("#2ECC71"));
                Przyciski[i][1].setForeground(Color.decode("#2ECC71"));
                Przyciski[i][2].setForeground(Color.decode("#2ECC71"));

                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (Przyciski[0][i].getText().equals(G) && Przyciski[1][i].getText().equals(G) && Przyciski[2][i].getText().equals(G)) {
                Przyciski[0][i].setForeground(Color.decode("#2ECC71"));
                Przyciski[1][i].setForeground(Color.decode("#2ECC71"));
                Przyciski[2][i].setForeground(Color.decode("#2ECC71"));

                return true;
            }
        }
        if (Przyciski[0][0].getText().equals(G) && Przyciski[1][1].getText().equals(G) && Przyciski[2][2].getText().equals(G)) {
            Przyciski[0][0].setForeground(Color.decode("#2ECC71"));
            Przyciski[1][1].setForeground(Color.decode("#2ECC71"));
            Przyciski[2][2].setForeground(Color.decode("#2ECC71"));

            return true;
        }
        if (Przyciski[0][2].getText().equals(G) && Przyciski[1][1].getText().equals(G) && Przyciski[2][0].getText().equals(G)) {
            Przyciski[0][2].setForeground(Color.decode("#2ECC71"));
            Przyciski[1][1].setForeground(Color.decode("#2ECC71"));
            Przyciski[2][0].setForeground(Color.decode("#2ECC71"));

            return true;
        }

        return false;
    };
    private Boolean CzyRemis() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Przyciski[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void Okienko(){
        JDialog popup = new JDialog(SwingUtilities.getWindowAncestor(this));
        popup.setTitle("Koniec gry");
        popup.setSize(250, 100);
        popup.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        popup.setLocationRelativeTo(this);
        JButton dalej = new JButton("Graj dalej");
        JButton zakoncz = new JButton("Zakończ");
        dalej.setForeground(Color.white);
        zakoncz.setForeground(Color.white);
        dalej.setBackground(Color.decode("#1E1E1E"));
        zakoncz.setBackground(Color.decode("#1E1E1E"));
        popup.getContentPane().setBackground(Color.decode("#2A2A2A"));
        popup.add(dalej);
        popup.add(zakoncz);
        popup.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dalej.addActionListener(e -> {Reset(); popup.dispose();});
        zakoncz.addActionListener(e -> {Powrot(); popup.dispose();});
        popup.setVisible(true);
    }
    private void Reset(){


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Przyciski[i][j].setText("");
            }
        }
        KoniecGry = false;
        Gracz = 'X';
        InfoGracz.setText("");
    }
    private void Powrot(){
        menuPanel panel = new  menuPanel();
        JFrame aktualne = (JFrame) SwingUtilities.getWindowAncestor(this);
        aktualne.getContentPane().removeAll();
        aktualne.getContentPane().invalidate();
        aktualne.getContentPane().add(panel);
        aktualne.getContentPane().revalidate();
    }

}
