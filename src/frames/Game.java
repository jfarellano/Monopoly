/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import estructures.Nodo;
import estructures.linkedList.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import machines.Avenue;
import managers.Master;

/**
 *
 * @author JohnBarbosa
 */
public class Game extends javax.swing.JFrame {

    Master m;
    Thread t1;
    boolean stop;
    BufferedImage background;

    /**
     * Creates new form Game
     */
    public Game(Master m) {
        this.m = m;
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                stop = true;
                System.exit(0);
            }
        });

        setUp();

        try {
            background = ImageIO.read(new File("./resources/gfx/monopoly-table.jpg"));
        } catch (Exception e) {
        }

        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    paint();
                }
            }
        });
        t1.setDaemon(false);
        t1.start();
        
        
        Player p;
        for (int i = 0; i < m.getPlayers().length(); i++) {
            p = (Player) m.getPlayers().getCourrent();
            System.out.println("Nombre del jugador: " + p.getPlayerName());
            m.getPlayers().next();
        }
    }

    private void setUp() {
        
        Player p = (Player) m.getPlayers().BuscarConId(1);
        if (p == null) {
            Player1.setText("NONE");
        } else {
            Player1.setText(p.getPlayerName());
        }
        Player1.setBackground(Color.BLUE);
        Player1.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(2);
        if (p == null) {
            Player2.setText("NONE");
        } else {
            Player2.setText(p.getPlayerName());
        }
        Player2.setBackground(Color.YELLOW);
        Player2.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(3);
        if (p == null) {
            Player3.setText("NONE");
        } else {
            Player3.setText(p.getPlayerName());
        }
        Player3.setBackground(Color.GRAY);
        Player3.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(4);
        if (p == null) {
            Player4.setText("NONE");
        } else {
            Player4.setText(p.getPlayerName());
        }
        Player4.setBackground(Color.PINK);
        Player4.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(5);
        if (p == null) {
            Player5.setText("NONE");
        } else {
            Player5.setText(p.getPlayerName());
        }
        Player5.setBackground(Color.WHITE);
        Player5.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(6);
        if (p == null) {
            Player6.setText("NONE");
        } else {
            Player6.setText(p.getPlayerName());
        }
        Player6.setBackground(Color.MAGENTA);
        Player6.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(7);
        if (p == null) {
            Player7.setText("NONE");
        } else {
            Player7.setText(p.getPlayerName());
        }
        Player7.setBackground(Color.ORANGE);
        Player7.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(8);
        if (p == null) {
            Player8.setText("NONE");
        } else {
            Player8.setText(p.getPlayerName());
        }
        Player8.setBackground(Color.CYAN);
        Player8.setOpaque(true);
    }

    private void paint() {
        canvas.createBufferStrategy(2);
        Graphics g = canvas.getBufferStrategy().getDrawGraphics();

        g.drawImage(background, 0, 0, 746, 746, null);

        //proving
        for (int i = 0; i < m.getPlayers().length(); i++) {
            Player p = (Player) m.getPlayers().getCourrent();
            putInCell(g, 1, p.getCourrentCell(), p.getId(), 0, 0);
            m.getPlayers().next();
        }

        for (int i = 0; i < m.getTable().length(); i++) {
            if (m.getTable().getCourrent().getNombre().equals("Avenue")) {
                Avenue a = (Avenue) m.getTable().getCourrent();;
                putInCell(g, 2, a.getId(), 0, a.getHotels(), a.getHouses());
            }
            m.getTable().next();
        }

        canvas.getBufferStrategy().show();
    }

    private void putInCell(Graphics g, int mode, int cell, int playerNumber, int hotel, int house) {
        int pcCell;
        int j;
        if (cell >= 1 && cell <= 10) { //down
            j = 9;
            for (int i = 1; i <= 10; i++) {
                if (i == cell) {
                    pcCell = i;
                    break;
                }
                j--;
            }
            if (mode == 1) {
                drawPlayer(g, playerNumber, j, 1, 0, 1, 1, 0);
            } else if (mode == 2) {
                drawProperties(g, hotel, house, j, 1, 0, 1, 1, 0, true);
            } else if (mode == 3) {
                drawPlayer(g, playerNumber, j, 1, 0, 1, 1, 0);
                drawProperties(g, hotel, house, j, 1, 0, 1, 1, 0, true);
            }
        } else if (cell >= 11 && cell <= 20) { //left
            j = 9;
            for (int i = 11; i <= 20; i++) {
                if (i == cell) {
                    pcCell = i;
                    break;
                }
                j--;
            }
            if (mode == 1) {
                drawPlayer(g, playerNumber, j, 1, 0, 0, 0, 1);
            } else if (mode == 2) {
                drawProperties(g, hotel, house, j, 1, 0, 0, 0, 1, false);
            } else if (mode == 3) {
                drawPlayer(g, playerNumber, j, 1, 0, 0, 0, 1);
                drawProperties(g, hotel, house, j, 1, 0, 0, 0, 1, false);
            }
        } else if (cell >= 22 && cell <= 31) { //up
            j = 0;
            for (int i = 22; i <= 31; i++) {
                if (i == cell) {
                    pcCell = i;
                    break;
                }
                j++;
            }
            if (mode == 1) {
                drawPlayer(g, playerNumber, j, 1, 0, 0, 1, 0);
            } else if (mode == 2) {
                drawProperties(g, hotel, house, j, 1, 0, 0, 1, 0, true);
            } else if (mode == 3) {
                drawPlayer(g, playerNumber, j, 1, 0, 0, 1, 0);
                drawProperties(g, hotel, house, j, 1, 0, 0, 1, 0, true);
            }
        } else if (cell >= 32 && cell <= 40) { //right
            j = 0;
            for (int i = 32; i <= 40; i++) {
                if (i == cell) {
                    pcCell = i;
                    break;
                }
                j++;
            }
            if (mode == 1) {
                drawPlayer(g, playerNumber, j, 1, 1, 0, 0, 1);
            } else if (mode == 2) {
                drawProperties(g, hotel, house, j, 1, 1, 0, 0, 1, false);
            } else if (mode == 3) {
                drawPlayer(g, playerNumber, j, 1, 1, 0, 0, 1);
                drawProperties(g, hotel, house, j, 1, 1, 0, 0, 1, false);
            }
        } else if (cell == 21) { //opposite corner to start
            drawPlayer(g, playerNumber, 0, 0, 0, 0, 0, 1);
        }
    }

    private void drawPlayer(Graphics g, int playerNumber, int j, int i, int k, int k2, int jokerX, int jokerY) {

        int normalCell = 61;
        int cornerCell = 98;

        int side = 647;

        int playerSize = 10;
        int playerSpace = 0;

        int playerX = 5;
        int playerY = 5;

        int cellX = (normalCell * j + cornerCell * i) * jokerX + side * k;
        int cellY = (normalCell * j + cornerCell * i) * jokerY + side * k2;

        switch (playerNumber) {
            case 1:
                g.setColor(Color.BLUE);
                g.fillRect(cellX + playerX + playerSpace * 0 + playerSize * 0, cellY + playerY + playerSize * 0, playerSize, playerSize);
                break;
            case 2:
                g.setColor(Color.YELLOW);
                g.fillRect(cellX + playerX + playerSpace * 1 + playerSize * 1, cellY + playerY + playerSize * 0, playerSize, playerSize);
                break;
            case 3:
                g.setColor(Color.GRAY);
                g.fillRect(cellX + playerX + playerSpace * 2 + playerSize * 2, cellY + playerY + playerSize * 0, playerSize, playerSize);
                break;
            case 4:
                g.setColor(Color.PINK);
                g.fillRect(cellX + playerX + playerSpace * 3 + playerSize * 3, cellY + playerY + playerSize * 0, playerSize, playerSize);
                break;
            case 5:
                g.setColor(Color.WHITE);
                g.fillRect(cellX + playerX + playerSpace * 0 + playerSize * 0, cellY + playerY + playerSize * 1, playerSize, playerSize);
                break;
            case 6:
                g.setColor(Color.MAGENTA);
                g.fillRect(cellX + playerX + playerSpace * 1 + playerSize * 1, cellY + playerY + playerSize * 1, playerSize, playerSize);
                break;
            case 7:
                g.setColor(Color.ORANGE);
                g.fillRect(cellX + playerX + playerSpace * 2 + playerSize * 2, cellY + playerY + playerSize * 1, playerSize, playerSize);
                break;
            case 8:
                g.setColor(Color.CYAN);
                g.fillRect(cellX + playerX + playerSpace * 3 + playerSize * 3, cellY + playerY + playerSize * 1, playerSize, playerSize);
                break;
        }
    }

    private void drawProperties(Graphics g, int hotel, int house, int j, int i, int k, int k2, int jokerX, int jokerY, boolean vertical) {

        int normalCell = 61;
        int cornerCell = 98;

        int side = 647;

        int cellX = (normalCell * j + cornerCell * i) * jokerX + side * k;
        int cellY = (normalCell * j + cornerCell * i) * jokerY + side * k2;

        int playerSize = 10;
        int playerSpace = 0;

        int playerX = 5;
        int playerY = 5;

        for (int x = 1; x < 4; x++) { //Better coordinates are needed...
            for (int y = 0; y < 3; y++) {
                int propertySpaceX;
                int propertySpaceY;
                if (vertical) {
                    propertySpaceX = cellX + playerX * (x) + playerSize * (x - 1);
                    propertySpaceY = cellY + playerY * (y + 2) + playerSpace * (y + 3) + playerSize * (y + 2);
                } else {
                    propertySpaceX = cellX + playerX * (x + 1) + playerSpace * (x + 3) + playerSize * (x + 3);
                    propertySpaceY = cellY + playerY * (y + 1) + playerSize * y;
                }
                if (hotel != 0) {
                    g.setColor(Color.RED);
                    hotel--;
                } else if (house != 0) {
                    g.setColor(Color.GREEN);
                    house--;
                } else if (house == 0 && hotel == 0) {
                    break;
                }
                g.fillRect(propertySpaceX, propertySpaceY, playerSize, playerSize);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas = new java.awt.Canvas();
        elBotonMagico = new javax.swing.JButton();
        playerPane = new javax.swing.JPanel();
        Player1 = new javax.swing.JLabel();
        Player2 = new javax.swing.JLabel();
        Player3 = new javax.swing.JLabel();
        Player4 = new javax.swing.JLabel();
        Player5 = new javax.swing.JLabel();
        Player6 = new javax.swing.JLabel();
        Player7 = new javax.swing.JLabel();
        Player8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Table");
        setResizable(false);

        elBotonMagico.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        elBotonMagico.setText("El Boton magico");
        elBotonMagico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elBotonMagicoActionPerformed(evt);
            }
        });

        Player1.setText("Player1");

        Player2.setText("Player2");

        Player3.setText("Player3");

        Player4.setText("Player4");

        Player5.setText("Player5");

        Player6.setText("Player6");

        Player7.setText("Player7");

        Player8.setText("Player8");

        javax.swing.GroupLayout playerPaneLayout = new javax.swing.GroupLayout(playerPane);
        playerPane.setLayout(playerPaneLayout);
        playerPaneLayout.setHorizontalGroup(
            playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Player2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(Player3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Player4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Player5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Player6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Player7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Player8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Player1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playerPaneLayout.setVerticalGroup(
            playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Player1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Player8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(elBotonMagico, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(playerPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(elBotonMagico)
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void elBotonMagicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elBotonMagicoActionPerformed
        m.nextTurn();
    }//GEN-LAST:event_elBotonMagicoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Player1;
    private javax.swing.JLabel Player2;
    private javax.swing.JLabel Player3;
    private javax.swing.JLabel Player4;
    private javax.swing.JLabel Player5;
    private javax.swing.JLabel Player6;
    private javax.swing.JLabel Player7;
    private javax.swing.JLabel Player8;
    private java.awt.Canvas canvas;
    private javax.swing.JButton elBotonMagico;
    private javax.swing.JPanel playerPane;
    // End of variables declaration//GEN-END:variables
}
