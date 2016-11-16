/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import estructures.Nodo;
import estructures.linkedList.Card;
import estructures.linkedList.Chance;
import estructures.linkedList.Chest;
import estructures.linkedList.Player;
import estructures.linkedList.Railroad;
import estructures.linkedList.Tax;
import estructures.linkedList.Utility;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import machines.Avenue;
import machines.Corner;
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

    HashMap componentMap;

    Player playerOnTurn;
    Nodo cellInTurn;
    boolean onAcquirable;

    Component propertySelected;

    /**
     * Creates new form Game
     */
    public Game(Master m) {
        this.m = m;
        stop = false;
        onAcquirable = false;

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
        createComponentMap();
        //justProving();

        setPlayerOnTurn();
        Player p;
        for (int i = 0; i < m.getPlayers().length(); i++) {
            p = (Player) m.getPlayers().getCourrent();
            setPropertiesClickable(p);
            m.getPlayers().next();
        }

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

        p = null;
        for (int i = 0; i < m.getPlayers().length(); i++) {
            p = (Player) m.getPlayers().getCourrent();
            System.out.println("Nombre del jugador: " + p.getPlayerName());
            m.getPlayers().next();
        }
    }

    private Nodo getCellByComponent(Component component) {
        String componentName = component.getName().substring(2);
        int number;
        if (componentName.length() == 3) {
            number = Integer.valueOf(componentName.substring(2));
            switch (number) {
                case 1:
                    return m.getTable().BuscarConId(6);
                case 2:
                    return m.getTable().BuscarConId(16);
                case 3:
                    return m.getTable().BuscarConId(26);
                case 4:
                    return m.getTable().BuscarConId(36);
                default:
                    break;
            }
        } else {
            number = Integer.valueOf(componentName.substring(1));
            String initial = componentName.substring(0, 1);
            String color = "";
            if (initial.equals("M")) {
                color = "Brown";
            } else if (initial.equals("C")) {
                color = "Light Blue";
            } else if (initial.equals("P")) {
                color = "Pink";
            } else if (initial.equals("O")) {
                color = "Orange";
            } else if (initial.equals("R")) {
                color = "Red";
            } else if (initial.equals("Y")) {
                color = "Yellow";
            } else if (initial.equals("G")) {
                color = "Green";
            } else if (initial.equals("B")) {
                color = "Dark Blue";
            } else if (initial.equals("U")) {
                if (number == 1) {
                    return m.getTable().BuscarConId(13);
                } else if (number == 2) {
                    return m.getTable().BuscarConId(29);
                }
            }

            m.getTable().BuscarConId(1); //i dont know...
            int cont = 0;
            Avenue p;
            for (int i = 0; i < m.getTable().length(); i++) {
                try {
                    p = (Avenue) m.getTable().getCourrent();
                    if (p.getAvenueColor().equals(color)) {
                        cont++;
                        if (cont == number) {
                            return m.getTable().getCourrent();
                        }
                    }
                } catch (Exception e) {
                }
                m.getTable().next();
            }
        }
        return null;
    }

    private Nodo getCellByComponent(String componentName) {
        componentName = componentName.substring(2);
        int number;
        if (componentName.length() == 3) {
            number = Integer.valueOf(componentName.substring(2));
            switch (number) {
                case 1:
                    return m.getTable().BuscarConId(6);
                case 2:
                    return m.getTable().BuscarConId(16);
                case 3:
                    return m.getTable().BuscarConId(26);
                case 4:
                    return m.getTable().BuscarConId(36);
                default:
                    break;
            }
        } else {
            number = Integer.valueOf(componentName.substring(1));
            String initial = componentName.substring(0, 1);
            String color = "";
            if (initial.equals("M")) {
                color = "Brown";
            } else if (initial.equals("C")) {
                color = "Light Blue";
            } else if (initial.equals("P")) {
                color = "Pink";
            } else if (initial.equals("O")) {
                color = "Orange";
            } else if (initial.equals("R")) {
                color = "Red";
            } else if (initial.equals("Y")) {
                color = "Yellow";
            } else if (initial.equals("G")) {
                color = "Green";
            } else if (initial.equals("B")) {
                color = "Dark Blue";
            } else if (initial.equals("U")) {
                if (number == 1) {
                    return m.getTable().BuscarConId(13);
                } else if (number == 2) {
                    return m.getTable().BuscarConId(29);
                }
            }

            m.getTable().BuscarConId(1); //i dont know...
            int cont = 0;
            Avenue p;
            for (int i = 0; i < m.getTable().length(); i++) {
                try {
                    p = (Avenue) m.getTable().getCourrent();
                    if (p.getAvenueColor().equals(color)) {
                        cont++;
                        if (cont == number) {
                            return m.getTable().getCourrent();
                        }
                    }
                } catch (Exception e) {
                }
                m.getTable().next();
            }
        }
        return null;
    }

    private void setPropertiesClickable(Player p) {
        String playerNumber = "P" + Integer.toString(p.getId());

        addPopupMenus(playerNumber, "M", 3);

        addPopupMenus(playerNumber, "C", 4);

        addPopupMenus(playerNumber, "P", 4);

        addPopupMenus(playerNumber, "O", 4);

        addPopupMenus(playerNumber, "R", 4);

        addPopupMenus(playerNumber, "Y", 4);

        addPopupMenus(playerNumber, "G", 4);

        addPopupMenus(playerNumber, "B", 3);

        addPopupMenus(playerNumber, "U", 3);

        addPopupMenus(playerNumber, "RA", 5);
    }

    private void addPopupMenus(String playerNumber, String initial, int j) {
        String componentName = playerNumber + initial;
        for (int i = 1; i < j; i++) {
            Component component = getComponentByName(componentName + i);
            //component.add(PopupProperties);
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!PopupProperties.isPopupTrigger(e)) {
                        Color color = e.getComponent().getBackground();
                        if (color != UIManager.getColor("Panel.background") && playerOnTurn.getId() == Integer.valueOf(e.getComponent().getName().substring(1, 2))) {
                            PopupProperties.show(e.getComponent(), e.getX(), e.getY());
                            propertySelected = e.getComponent();
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    Color color = e.getComponent().getBackground();
                    if (color != UIManager.getColor("Panel.background") && playerOnTurn.getId() == Integer.valueOf(e.getComponent().getName().substring(1, 2))) {
                        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
                        setCursor(cursor);
                    }
                }

                public void mouseExited(MouseEvent e) {
                    Color color = e.getComponent().getBackground();
                    if (color != UIManager.getColor("Panel.background") && playerOnTurn.getId() == Integer.valueOf(e.getComponent().getName().substring(1, 2))) {
                        Cursor cursor = Cursor.getDefaultCursor();
                        setCursor(cursor);
                    }
                }
            });
        }

    }

    private void setPlayerOnTurn() {
        playerOnTurn = (Player) m.getPlayers().getCourrent();
        int playerNumber = playerOnTurn.getId();

        String componentName = "lPlayer" + Integer.toString(playerNumber);
        JLabel a = (JLabel) getComponentByName(componentName);
        lPlayerOnTurn.setText(a.getText());
        lPlayerOnTurn.setBackground(a.getBackground());
        lPlayerOnTurn.setOpaque(true);

        String money = Integer.toString(playerOnTurn.getMoney()) + " M";
        lMoney.setText(money);

        loadCell(playerOnTurn.getCourrentCell());
        
        if(playerOnTurn.isInJail()){
            lRentToPay.setText("50 M");
        }

    }

    private void loadCell(int numberCell) {
        String pathName = "./resources/gfx/" + Integer.toString(numberCell) + ".jpg";
        try {
            Icon icon = new ImageIcon(new javax.swing.ImageIcon(pathName).getImage());
            lCell.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void justProving() {
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 3; j++) {
                showAvenues(i, "Brown", j);
                unShowAvenues(i, "Brown", j);
            }
            for (int j = 1; j < 4; j++) {
                showAvenues(i, "Light Blue", j);
                unShowAvenues(i, "Light Blue", j);
            }
            for (int j = 1; j < 4; j++) {
                showAvenues(i, "Pink", j);
                unShowAvenues(i, "Pink", j);
            }
            for (int j = 1; j < 4; j++) {
                showAvenues(i, "Orange", j);
                unShowAvenues(i, "Orange", j);
            }
            for (int j = 1; j < 4; j++) {
                showAvenues(i, "Red", j);
                unShowAvenues(i, "Red", j);
            }
            for (int j = 1; j < 4; j++) {
                showAvenues(i, "Yellow", j);
                unShowAvenues(i, "Yellow", j);
            }
            for (int j = 1; j < 4; j++) {
                showAvenues(i, "Green", j);
                unShowAvenues(i, "Green", j);
            }
            for (int j = 1; j < 3; j++) {
                showAvenues(i, "Dark Blue", j);
                unShowAvenues(i, "Dark Blue", j);
            }

            for (int j = 1; j < 3; j++) {
                showUtilityOrRailroad(true, i, j);
                unShowUtilityOrRailroad(true, i, j);
            }
            for (int j = 1; j < 5; j++) {
                showUtilityOrRailroad(false, i, j);
                unShowUtilityOrRailroad(false, i, j);
            }
        }
    }

    private void createComponentMap() {
        componentMap = new HashMap<String, Component>();
        Component[] components = playerPane.getComponents();
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
    }

    private Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        } else {
            return null;
        }
    }

    private void showAvenues(int playerNumber, String propertyColor, int propertyNumber) {
        String initial;
        if (propertyColor.equals("Brown")) {
            initial = "M";
        } else if (propertyColor.equals("Light Blue")) {
            initial = "C";
        } else if (propertyColor.equals("Dark Blue")) {
            initial = "B";
        } else {
            initial = propertyColor.substring(0, 1);
        }
        String component = "P" + playerNumber + initial + propertyNumber;
        JLabel a = (JLabel) getComponentByName(component);
        if (a != null) {
            switch (initial) {
                case "M":
                    a.setForeground(Color.WHITE);
                    a.setBackground(Color.DARK_GRAY);
                    break;
                case "C":
                    a.setBackground(Color.CYAN);
                    break;
                case "P":
                    a.setBackground(Color.PINK);
                    break;
                case "O":
                    a.setBackground(Color.ORANGE);
                    break;
                case "R":
                    a.setForeground(Color.WHITE);
                    a.setBackground(Color.RED);
                    break;
                case "Y":
                    a.setBackground(Color.YELLOW);
                    break;
                case "G":
                    a.setBackground(Color.GREEN);
                    break;
                case "B":
                    a.setForeground(Color.WHITE);
                    a.setBackground(Color.BLUE);
                    break;
            }
            a.setOpaque(true);
        } else {
            System.out.println("component not found!");
        }
    }

    private void unShowAvenues(int playerNumber, String propertyColor, int propertyNumber) {
        String initial;
        if (propertyColor.equals("Brown")) {
            initial = "M";
        } else if (propertyColor.equals("Light Blue")) {
            initial = "C";
        } else if (propertyColor.equals("Dark Blue")) {
            initial = "B";
        } else {
            initial = propertyColor.substring(0, 1);
        }
        String component = "P" + playerNumber + initial + propertyNumber;
        JLabel a = (JLabel) getComponentByName(component);
        if (a != null) {
            a.setBackground(UIManager.getColor("Panel.background"));
            a.setForeground(Color.BLACK);
            a.setOpaque(true);
        } else {
            System.out.println("component not found!");
        }

    }

    private void showUtilityOrRailroad(boolean utility, int playerNumber, int propertyNumber) {
        String component;
        JLabel a;
        if (utility) {
            component = "P" + playerNumber + "U" + propertyNumber;
            a = (JLabel) getComponentByName(component);
            if (a != null) {
                a.setBackground(Color.WHITE);
                a.setOpaque(true);
            } else {
                System.out.println("component not found!");
            }
        } else {
            component = "P" + playerNumber + "RA" + propertyNumber;
            a = (JLabel) getComponentByName(component);
            if (a != null) {
                a.setForeground(Color.WHITE);
                a.setBackground(Color.BLACK);
                a.setOpaque(true);
            } else {
                System.out.println("component not found!");
            }
        }
    }

    private void unShowUtilityOrRailroad(boolean utility, int playerNumber, int propertyNumber) {
        String component;
        JLabel a;
        if (utility) {
            component = "P" + playerNumber + "U" + propertyNumber;
        } else {
            component = "P" + playerNumber + "RA" + propertyNumber;
        }
        a = (JLabel) getComponentByName(component);
        if (a != null) {
            a.setBackground(UIManager.getColor("Panel.background"));
            a.setForeground(Color.BLACK);
            a.setOpaque(true);
        } else {
            System.out.println("component not found!");
        }
    }

    private void setUp() {

        playerPane.setBorder(BorderFactory.createLineBorder(Color.black));

        Player p = (Player) m.getPlayers().BuscarConId(1);
        if (p == null) {
            lPlayer1.setText("NONE");
        } else {
            lPlayer1.setText(p.getPlayerName());
        }
        lPlayer1.setBackground(Color.BLUE);
        lPlayer1.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(2);
        if (p == null) {
            lPlayer2.setText("NONE");
        } else {
            lPlayer2.setText(p.getPlayerName());
        }
        lPlayer2.setBackground(Color.YELLOW);
        lPlayer2.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(3);
        if (p == null) {
            lPlayer3.setText("NONE");
        } else {
            lPlayer3.setText(p.getPlayerName());
        }
        lPlayer3.setBackground(Color.GRAY);
        lPlayer3.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(4);
        if (p == null) {
            lPlayer4.setText("NONE");
        } else {
            lPlayer4.setText(p.getPlayerName());
        }
        lPlayer4.setBackground(Color.PINK);
        lPlayer4.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(5);
        if (p == null) {
            lPlayer5.setText("NONE");
        } else {
            lPlayer5.setText(p.getPlayerName());
        }
        lPlayer5.setBackground(Color.WHITE);
        lPlayer5.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(6);
        if (p == null) {
            lPlayer6.setText("NONE");
        } else {
            lPlayer6.setText(p.getPlayerName());
        }
        lPlayer6.setBackground(Color.MAGENTA);
        lPlayer6.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(7);
        if (p == null) {
            lPlayer7.setText("NONE");
        } else {
            lPlayer7.setText(p.getPlayerName());
        }
        lPlayer7.setBackground(Color.ORANGE);
        lPlayer7.setOpaque(true);

        p = (Player) m.getPlayers().BuscarConId(8);
        if (p == null) {
            lPlayer8.setText("NONE");
        } else {
            lPlayer8.setText(p.getPlayerName());
        }
        lPlayer8.setBackground(Color.CYAN);
        lPlayer8.setOpaque(true);
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

    private Player playerWithMoreProperties() {
        Player bestPlayer = null;
        int bestNumber = -999;

        for (int i = 1; i <= m.getPlayers().length(); i++) {
            Player p = (Player) m.getPlayers().BuscarConId(i);
            if (!p.isBankruptcy()) {
                String playerNumber = "P" + Integer.toString(i);
                int cont = 0;

                cont = cont + propertiesCounter(playerNumber, "M", 3);

                cont = cont + propertiesCounter(playerNumber, "C", 4);

                cont = cont + propertiesCounter(playerNumber, "P", 4);

                cont = cont + propertiesCounter(playerNumber, "O", 4);

                cont = cont + propertiesCounter(playerNumber, "R", 4);

                cont = cont + propertiesCounter(playerNumber, "Y", 4);

                cont = cont + propertiesCounter(playerNumber, "G", 4);

                cont = cont + propertiesCounter(playerNumber, "B", 3);

                cont = cont + propertiesCounter(playerNumber, "U", 3);

                cont = cont + propertiesCounter(playerNumber, "RA", 5);

                if (cont == 28) {
                    bestPlayer = (Player) m.getPlayers().BuscarConId(i);
                    JOptionPane.showMessageDialog(null, "el Jugador " + bestPlayer.getPlayerName() + " ha ganado el juego.");
                    System.exit(0);
                } else if (cont > 0 && cont > bestNumber) {
                    bestNumber = cont;
                    bestPlayer = p;
                }
            }
        }

        return bestPlayer;
    }

    private int propertiesCounter(String playerNumber, String initial, int j) {
        int cont = 0;
        Color defaul = UIManager.getColor("Panel.background");
        for (int i = 1; i < j; i++) {
            if (getComponentByName(playerNumber + initial + Integer.toString(i)).getBackground() != defaul) {
                cont++;
            }
        }
        return cont;
    }

    public void passPropertiesOfLoser(Player owner) {
        Player receiver = playerWithMoreProperties();

        Nodo p;
        for (int i = 1; i <= m.getTable().length(); i++) {
            p = m.getTable().BuscarConId(i);
            if (p.getNombre().equals("Avenue")) {
                Avenue a = (Avenue) p;
                if (a.getOwner() == owner) {
                    a.setOwner(receiver);
                    int number = m.getAvenueNumber(p);
                    showAvenues(receiver.getId(), a.getAvenueColor(), number);
                    unShowAvenues(owner.getId(), a.getAvenueColor(), number);
                }
            } else if (p.getNombre().equals("Railroad")) {
                Railroad r = (Railroad) p;
                if (r.getOwner() == owner) {
                    r.setOwner(receiver);
                    Railroad rCopy = (Railroad) m.getRail().BuscarConId(p.getId());
                    rCopy.setOwner(receiver);
                    int number = m.getRailNumber(p);
                    showUtilityOrRailroad(false, receiver.getId(), number);
                    unShowUtilityOrRailroad(false, owner.getId(), number);
                }
            } else if (p.getNombre().equals("Utility")) {
                Utility u = (Utility) p;
                if (u.getOwner() == owner) {
                    u.setOwner(receiver);
                    Utility uCopy = (Utility) m.getUtility().BuscarConId(p.getId());
                    uCopy.setOwner(receiver);
                    int number = m.getUtilityNumber(p);
                    showUtilityOrRailroad(true, receiver.getId(), number);
                    unShowUtilityOrRailroad(true, owner.getId(), number);
                    m.enableUtilityRentPrice(receiver);
                }
            }
        }
        playersPlaying();
    }

    private int playersPlaying() {
        int cont = 0;
        Player p /*= m.getPlayerOnTurn()*/;
        Player winner = null;
        for (int i = 1; i <= m.getPlayers().length(); i++) {
            p = (Player) m.getPlayers().BuscarConId(i);
            if (!p.isBankruptcy()) {
                cont++;
                winner = p;
            }
            //m.getPlayers().next();
        }
        m.getPlayers().BuscarConId(playerOnTurn.getId());
        if (cont == 1) {
            JOptionPane.showMessageDialog(null, "el Jugador " + winner.getPlayerName() + " ha ganado el juego.");
            System.exit(0);
        }
        return cont;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PopupProperties = new javax.swing.JPopupMenu();
        MenuItemSell = new javax.swing.JMenuItem();
        MenuItemHotels = new javax.swing.JMenuItem();
        MenuItemHouses = new javax.swing.JMenuItem();
        MenuItemSellHouses = new javax.swing.JMenuItem();
        MenuItemSellHotels = new javax.swing.JMenuItem();
        canvas = new java.awt.Canvas();
        playerPane = new javax.swing.JPanel();
        lPlayer1 = new javax.swing.JLabel();
        lPlayer2 = new javax.swing.JLabel();
        lPlayer3 = new javax.swing.JLabel();
        lPlayer4 = new javax.swing.JLabel();
        lPlayer5 = new javax.swing.JLabel();
        lPlayer6 = new javax.swing.JLabel();
        lPlayer7 = new javax.swing.JLabel();
        lPlayer8 = new javax.swing.JLabel();
        lPlayers = new javax.swing.JLabel();
        P1M1 = new javax.swing.JLabel();
        P1M2 = new javax.swing.JLabel();
        P1C1 = new javax.swing.JLabel();
        P1C2 = new javax.swing.JLabel();
        P1C3 = new javax.swing.JLabel();
        P1P1 = new javax.swing.JLabel();
        P1P2 = new javax.swing.JLabel();
        P1P3 = new javax.swing.JLabel();
        P1O1 = new javax.swing.JLabel();
        P1O2 = new javax.swing.JLabel();
        P1O3 = new javax.swing.JLabel();
        lProperties = new javax.swing.JLabel();
        P1R3 = new javax.swing.JLabel();
        P1R2 = new javax.swing.JLabel();
        P1R1 = new javax.swing.JLabel();
        P1Y3 = new javax.swing.JLabel();
        P1Y2 = new javax.swing.JLabel();
        P1Y1 = new javax.swing.JLabel();
        P1G3 = new javax.swing.JLabel();
        P1G2 = new javax.swing.JLabel();
        P1G1 = new javax.swing.JLabel();
        P1B1 = new javax.swing.JLabel();
        P1B2 = new javax.swing.JLabel();
        P2M1 = new javax.swing.JLabel();
        P2M2 = new javax.swing.JLabel();
        P2C1 = new javax.swing.JLabel();
        P2C2 = new javax.swing.JLabel();
        P2C3 = new javax.swing.JLabel();
        P2P1 = new javax.swing.JLabel();
        P2P2 = new javax.swing.JLabel();
        P2P3 = new javax.swing.JLabel();
        P2O1 = new javax.swing.JLabel();
        P2O2 = new javax.swing.JLabel();
        P2O3 = new javax.swing.JLabel();
        P2R1 = new javax.swing.JLabel();
        P2R2 = new javax.swing.JLabel();
        P2R3 = new javax.swing.JLabel();
        P2Y1 = new javax.swing.JLabel();
        P2Y2 = new javax.swing.JLabel();
        P2Y3 = new javax.swing.JLabel();
        P2G1 = new javax.swing.JLabel();
        P2G2 = new javax.swing.JLabel();
        P2G3 = new javax.swing.JLabel();
        P2B1 = new javax.swing.JLabel();
        P2B2 = new javax.swing.JLabel();
        P3M1 = new javax.swing.JLabel();
        P3M2 = new javax.swing.JLabel();
        P3C1 = new javax.swing.JLabel();
        P3C2 = new javax.swing.JLabel();
        P3C3 = new javax.swing.JLabel();
        P3P1 = new javax.swing.JLabel();
        P3P2 = new javax.swing.JLabel();
        P3P3 = new javax.swing.JLabel();
        P3O1 = new javax.swing.JLabel();
        P3O2 = new javax.swing.JLabel();
        P3O3 = new javax.swing.JLabel();
        P3R1 = new javax.swing.JLabel();
        P3R2 = new javax.swing.JLabel();
        P3R3 = new javax.swing.JLabel();
        P3Y1 = new javax.swing.JLabel();
        P3Y2 = new javax.swing.JLabel();
        P3Y3 = new javax.swing.JLabel();
        P3G1 = new javax.swing.JLabel();
        P3G2 = new javax.swing.JLabel();
        P3G3 = new javax.swing.JLabel();
        P3B1 = new javax.swing.JLabel();
        P3B2 = new javax.swing.JLabel();
        P4P3 = new javax.swing.JLabel();
        P4Y3 = new javax.swing.JLabel();
        P4R2 = new javax.swing.JLabel();
        P4Y1 = new javax.swing.JLabel();
        P4R3 = new javax.swing.JLabel();
        P4G2 = new javax.swing.JLabel();
        P4P1 = new javax.swing.JLabel();
        P4O3 = new javax.swing.JLabel();
        P4G3 = new javax.swing.JLabel();
        P4R1 = new javax.swing.JLabel();
        P4M1 = new javax.swing.JLabel();
        P4M2 = new javax.swing.JLabel();
        P4O2 = new javax.swing.JLabel();
        P4B2 = new javax.swing.JLabel();
        P4P2 = new javax.swing.JLabel();
        P4C1 = new javax.swing.JLabel();
        P4O1 = new javax.swing.JLabel();
        P4G1 = new javax.swing.JLabel();
        P4B1 = new javax.swing.JLabel();
        P4C2 = new javax.swing.JLabel();
        P4C3 = new javax.swing.JLabel();
        P4Y2 = new javax.swing.JLabel();
        P5C1 = new javax.swing.JLabel();
        P5C3 = new javax.swing.JLabel();
        P5Y1 = new javax.swing.JLabel();
        P5R3 = new javax.swing.JLabel();
        P5M1 = new javax.swing.JLabel();
        P5G1 = new javax.swing.JLabel();
        P5G3 = new javax.swing.JLabel();
        P5P2 = new javax.swing.JLabel();
        P5R2 = new javax.swing.JLabel();
        P5O3 = new javax.swing.JLabel();
        P5O1 = new javax.swing.JLabel();
        P5B1 = new javax.swing.JLabel();
        P5G2 = new javax.swing.JLabel();
        P5P1 = new javax.swing.JLabel();
        P5B2 = new javax.swing.JLabel();
        P5Y3 = new javax.swing.JLabel();
        P5O2 = new javax.swing.JLabel();
        P5C2 = new javax.swing.JLabel();
        P5R1 = new javax.swing.JLabel();
        P5M2 = new javax.swing.JLabel();
        P5P3 = new javax.swing.JLabel();
        P5Y2 = new javax.swing.JLabel();
        P6G3 = new javax.swing.JLabel();
        P6C1 = new javax.swing.JLabel();
        P6Y1 = new javax.swing.JLabel();
        P6C2 = new javax.swing.JLabel();
        P6G2 = new javax.swing.JLabel();
        P6Y2 = new javax.swing.JLabel();
        P6G1 = new javax.swing.JLabel();
        P6P1 = new javax.swing.JLabel();
        P6M2 = new javax.swing.JLabel();
        P6B2 = new javax.swing.JLabel();
        P6B1 = new javax.swing.JLabel();
        P6Y3 = new javax.swing.JLabel();
        P6R1 = new javax.swing.JLabel();
        P6O3 = new javax.swing.JLabel();
        P6R2 = new javax.swing.JLabel();
        P6P2 = new javax.swing.JLabel();
        P6C3 = new javax.swing.JLabel();
        P6P3 = new javax.swing.JLabel();
        P6M1 = new javax.swing.JLabel();
        P6O1 = new javax.swing.JLabel();
        P6R3 = new javax.swing.JLabel();
        P6O2 = new javax.swing.JLabel();
        P7R1 = new javax.swing.JLabel();
        P7M2 = new javax.swing.JLabel();
        P7P2 = new javax.swing.JLabel();
        P7R2 = new javax.swing.JLabel();
        P7B2 = new javax.swing.JLabel();
        P7O3 = new javax.swing.JLabel();
        P7O2 = new javax.swing.JLabel();
        P7P3 = new javax.swing.JLabel();
        P7P1 = new javax.swing.JLabel();
        P7G3 = new javax.swing.JLabel();
        P7B1 = new javax.swing.JLabel();
        P7O1 = new javax.swing.JLabel();
        P7Y2 = new javax.swing.JLabel();
        P7G1 = new javax.swing.JLabel();
        P7Y1 = new javax.swing.JLabel();
        P7Y3 = new javax.swing.JLabel();
        P7R3 = new javax.swing.JLabel();
        P7C3 = new javax.swing.JLabel();
        P7C1 = new javax.swing.JLabel();
        P7C2 = new javax.swing.JLabel();
        P7G2 = new javax.swing.JLabel();
        P7M1 = new javax.swing.JLabel();
        P8O3 = new javax.swing.JLabel();
        P8M1 = new javax.swing.JLabel();
        P8P3 = new javax.swing.JLabel();
        P8P1 = new javax.swing.JLabel();
        P8B1 = new javax.swing.JLabel();
        P8R3 = new javax.swing.JLabel();
        P8Y2 = new javax.swing.JLabel();
        P8O1 = new javax.swing.JLabel();
        P8P2 = new javax.swing.JLabel();
        P8C2 = new javax.swing.JLabel();
        P8R1 = new javax.swing.JLabel();
        P8M2 = new javax.swing.JLabel();
        P8Y1 = new javax.swing.JLabel();
        P8B2 = new javax.swing.JLabel();
        P8R2 = new javax.swing.JLabel();
        P8G3 = new javax.swing.JLabel();
        P8Y3 = new javax.swing.JLabel();
        P8G2 = new javax.swing.JLabel();
        P8C1 = new javax.swing.JLabel();
        P8O2 = new javax.swing.JLabel();
        P8G1 = new javax.swing.JLabel();
        P8C3 = new javax.swing.JLabel();
        P1U1 = new javax.swing.JLabel();
        P1U2 = new javax.swing.JLabel();
        P1RA1 = new javax.swing.JLabel();
        P1RA2 = new javax.swing.JLabel();
        P1RA3 = new javax.swing.JLabel();
        P1RA4 = new javax.swing.JLabel();
        P2RA4 = new javax.swing.JLabel();
        P2U1 = new javax.swing.JLabel();
        P2U2 = new javax.swing.JLabel();
        P2RA1 = new javax.swing.JLabel();
        P2RA2 = new javax.swing.JLabel();
        P2RA3 = new javax.swing.JLabel();
        P3RA4 = new javax.swing.JLabel();
        P3U1 = new javax.swing.JLabel();
        P3U2 = new javax.swing.JLabel();
        P3RA1 = new javax.swing.JLabel();
        P3RA2 = new javax.swing.JLabel();
        P3RA3 = new javax.swing.JLabel();
        P4RA4 = new javax.swing.JLabel();
        P4U1 = new javax.swing.JLabel();
        P4U2 = new javax.swing.JLabel();
        P4RA1 = new javax.swing.JLabel();
        P4RA2 = new javax.swing.JLabel();
        P4RA3 = new javax.swing.JLabel();
        P5RA4 = new javax.swing.JLabel();
        P5U1 = new javax.swing.JLabel();
        P5U2 = new javax.swing.JLabel();
        P5RA1 = new javax.swing.JLabel();
        P5RA2 = new javax.swing.JLabel();
        P5RA3 = new javax.swing.JLabel();
        P6RA4 = new javax.swing.JLabel();
        P6U1 = new javax.swing.JLabel();
        P6U2 = new javax.swing.JLabel();
        P6RA1 = new javax.swing.JLabel();
        P6RA2 = new javax.swing.JLabel();
        P6RA3 = new javax.swing.JLabel();
        P7RA4 = new javax.swing.JLabel();
        P7U1 = new javax.swing.JLabel();
        P7U2 = new javax.swing.JLabel();
        P7RA1 = new javax.swing.JLabel();
        P7RA2 = new javax.swing.JLabel();
        P7RA3 = new javax.swing.JLabel();
        P8RA4 = new javax.swing.JLabel();
        P8U1 = new javax.swing.JLabel();
        P8U2 = new javax.swing.JLabel();
        P8RA1 = new javax.swing.JLabel();
        P8RA2 = new javax.swing.JLabel();
        P8RA3 = new javax.swing.JLabel();
        dicePane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lCell = new javax.swing.JLabel();
        bDice = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lDice1 = new javax.swing.JLabel();
        lDice2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lTotalDice = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lPlayerOnTurn = new javax.swing.JLabel();
        bBankrupt = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        bBuyProperty = new javax.swing.JButton();
        bAuctionProperty = new javax.swing.JButton();
        bPropertyHandle = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lMoney = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lHousePrice = new javax.swing.JLabel();
        lHotelPrice = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lRentToPay = new javax.swing.JLabel();
        bPayRent = new javax.swing.JButton();
        bNextTurn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        MenuItemSell.setText("Vender");
        MenuItemSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSellActionPerformed(evt);
            }
        });
        PopupProperties.add(MenuItemSell);

        MenuItemHotels.setText("Poner Hotel");
        MenuItemHotels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemHotelsActionPerformed(evt);
            }
        });
        PopupProperties.add(MenuItemHotels);

        MenuItemHouses.setText("Poner Casa");
        MenuItemHouses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemHousesActionPerformed(evt);
            }
        });
        PopupProperties.add(MenuItemHouses);

        MenuItemSellHouses.setText("Vender Casa");
        MenuItemSellHouses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSellHousesActionPerformed(evt);
            }
        });
        PopupProperties.add(MenuItemSellHouses);

        MenuItemSellHotels.setText("Vender Hotel");
        MenuItemSellHotels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemSellHotelsActionPerformed(evt);
            }
        });
        PopupProperties.add(MenuItemSellHotels);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Table");
        setResizable(false);

        canvas.setName("p1,m1"); // NOI18N

        lPlayer1.setText("Player1");
        lPlayer1.setName("lPlayer1"); // NOI18N

        lPlayer2.setText("Player2");
        lPlayer2.setName("lPlayer2"); // NOI18N

        lPlayer3.setText("Player3");
        lPlayer3.setName("lPlayer3"); // NOI18N

        lPlayer4.setText("Player4");
        lPlayer4.setName("lPlayer4"); // NOI18N

        lPlayer5.setText("Player5");
        lPlayer5.setName("lPlayer5"); // NOI18N

        lPlayer6.setText("Player6");
        lPlayer6.setName("lPlayer6"); // NOI18N

        lPlayer7.setText("Player7");
        lPlayer7.setName("lPlayer7"); // NOI18N

        lPlayer8.setText("Player8");
        lPlayer8.setName("lPlayer8"); // NOI18N

        lPlayers.setText("JUGADORES");

        P1M1.setText("1");
        P1M1.setName("P1M1"); // NOI18N

        P1M2.setText("2");
        P1M2.setName("P1M2"); // NOI18N

        P1C1.setText("1");
        P1C1.setName("P1C1"); // NOI18N

        P1C2.setText("2");
        P1C2.setName("P1C2"); // NOI18N

        P1C3.setText("3");
        P1C3.setName("P1C3"); // NOI18N

        P1P1.setText("1");
        P1P1.setName("P1P1"); // NOI18N

        P1P2.setText("2");
        P1P2.setName("P1P2"); // NOI18N

        P1P3.setText("3");
        P1P3.setName("P1P3"); // NOI18N

        P1O1.setText("1");
        P1O1.setName("P1O1"); // NOI18N

        P1O2.setText("2");
        P1O2.setName("P1O2"); // NOI18N

        P1O3.setText("3");
        P1O3.setName("P1O3"); // NOI18N

        lProperties.setText("PROPIEDADES");

        P1R3.setText("3");
        P1R3.setName("P1R3"); // NOI18N

        P1R2.setText("2");
        P1R2.setName("P1R2"); // NOI18N

        P1R1.setText("1");
        P1R1.setName("P1R1"); // NOI18N

        P1Y3.setText("3");
        P1Y3.setName("P1Y3"); // NOI18N

        P1Y2.setText("2");
        P1Y2.setName("P1Y2"); // NOI18N

        P1Y1.setText("1");
        P1Y1.setName("P1Y1"); // NOI18N

        P1G3.setText("3");
        P1G3.setName("P1G3"); // NOI18N

        P1G2.setText("2");
        P1G2.setName("P1G2"); // NOI18N

        P1G1.setText("1");
        P1G1.setName("P1G1"); // NOI18N

        P1B1.setText("1");
        P1B1.setName("P1B1"); // NOI18N

        P1B2.setText("2");
        P1B2.setName("P1B2"); // NOI18N

        P2M1.setText("1");
        P2M1.setName("P2M1"); // NOI18N

        P2M2.setText("2");
        P2M2.setName("P2M2"); // NOI18N

        P2C1.setText("1");
        P2C1.setName("P2C1"); // NOI18N

        P2C2.setText("2");
        P2C2.setName("P2C2"); // NOI18N

        P2C3.setText("3");
        P2C3.setName("P2C3"); // NOI18N

        P2P1.setText("1");
        P2P1.setName("P2P1"); // NOI18N

        P2P2.setText("2");
        P2P2.setName("P2P2"); // NOI18N

        P2P3.setText("3");
        P2P3.setName("P2P3"); // NOI18N

        P2O1.setText("1");
        P2O1.setName("P2O1"); // NOI18N

        P2O2.setText("2");
        P2O2.setName("P2O2"); // NOI18N

        P2O3.setText("3");
        P2O3.setName("P2O3"); // NOI18N

        P2R1.setText("1");
        P2R1.setName("P2R1"); // NOI18N

        P2R2.setText("2");
        P2R2.setName("P2R2"); // NOI18N

        P2R3.setText("3");
        P2R3.setName("P2R3"); // NOI18N

        P2Y1.setText("1");
        P2Y1.setName("P2Y1"); // NOI18N

        P2Y2.setText("2");
        P2Y2.setName("P2Y2"); // NOI18N

        P2Y3.setText("3");
        P2Y3.setName("P2Y3"); // NOI18N

        P2G1.setText("1");
        P2G1.setName("P2G1"); // NOI18N

        P2G2.setText("2");
        P2G2.setName("P2G2"); // NOI18N

        P2G3.setText("3");
        P2G3.setName("P2G3"); // NOI18N

        P2B1.setText("1");
        P2B1.setName("P2B1"); // NOI18N

        P2B2.setText("2");
        P2B2.setName("P2B2"); // NOI18N

        P3M1.setText("1");
        P3M1.setName("P3M1"); // NOI18N

        P3M2.setText("2");
        P3M2.setName("P3M2"); // NOI18N

        P3C1.setText("1");
        P3C1.setName("P3C1"); // NOI18N

        P3C2.setText("2");
        P3C2.setName("P3C2"); // NOI18N

        P3C3.setText("3");
        P3C3.setName("P3C3"); // NOI18N

        P3P1.setText("1");
        P3P1.setName("P3P1"); // NOI18N

        P3P2.setText("2");
        P3P2.setName("P3P2"); // NOI18N

        P3P3.setText("3");
        P3P3.setName("P3P3"); // NOI18N

        P3O1.setText("1");
        P3O1.setName("P3O1"); // NOI18N

        P3O2.setText("2");
        P3O2.setName("P3O2"); // NOI18N

        P3O3.setText("3");
        P3O3.setName("P3O3"); // NOI18N

        P3R1.setText("1");
        P3R1.setName("P3R1"); // NOI18N

        P3R2.setText("2");
        P3R2.setName("P3R2"); // NOI18N

        P3R3.setText("3");
        P3R3.setName("P3R3"); // NOI18N

        P3Y1.setText("1");
        P3Y1.setName("P3Y1"); // NOI18N

        P3Y2.setText("2");
        P3Y2.setName("P3Y2"); // NOI18N

        P3Y3.setText("3");
        P3Y3.setName("P3Y3"); // NOI18N

        P3G1.setText("1");
        P3G1.setName("P3G1"); // NOI18N

        P3G2.setText("2");
        P3G2.setName("P3G2"); // NOI18N

        P3G3.setText("3");
        P3G3.setName("P3G3"); // NOI18N

        P3B1.setText("1");
        P3B1.setName("P3B1"); // NOI18N

        P3B2.setText("2");
        P3B2.setName("P3B2"); // NOI18N

        P4P3.setText("3");
        P4P3.setName("P4P3"); // NOI18N

        P4Y3.setText("3");
        P4Y3.setName("P4Y3"); // NOI18N

        P4R2.setText("2");
        P4R2.setName("P4R2"); // NOI18N

        P4Y1.setText("1");
        P4Y1.setName("P4Y1"); // NOI18N

        P4R3.setText("3");
        P4R3.setName("P4R3"); // NOI18N

        P4G2.setText("2");
        P4G2.setName("P4G2"); // NOI18N

        P4P1.setText("1");
        P4P1.setName("P4P1"); // NOI18N

        P4O3.setText("3");
        P4O3.setName("P4O3"); // NOI18N

        P4G3.setText("3");
        P4G3.setName("P4G3"); // NOI18N

        P4R1.setText("1");
        P4R1.setName("P4R1"); // NOI18N

        P4M1.setText("1");
        P4M1.setName("P4M1"); // NOI18N

        P4M2.setText("2");
        P4M2.setName("P4M2"); // NOI18N

        P4O2.setText("2");
        P4O2.setName("P4O2"); // NOI18N

        P4B2.setText("2");
        P4B2.setName("P4B2"); // NOI18N

        P4P2.setText("2");
        P4P2.setName("P4P2"); // NOI18N

        P4C1.setText("1");
        P4C1.setName("P4C1"); // NOI18N

        P4O1.setText("1");
        P4O1.setName("P4O1"); // NOI18N

        P4G1.setText("1");
        P4G1.setName("P4G1"); // NOI18N

        P4B1.setText("1");
        P4B1.setName("P4B1"); // NOI18N

        P4C2.setText("2");
        P4C2.setName("P4C2"); // NOI18N

        P4C3.setText("3");
        P4C3.setName("P4C3"); // NOI18N

        P4Y2.setText("2");
        P4Y2.setName("P4Y2"); // NOI18N

        P5C1.setText("1");
        P5C1.setName("P5C1"); // NOI18N

        P5C3.setText("3");
        P5C3.setName("P5C3"); // NOI18N

        P5Y1.setText("1");
        P5Y1.setName("P5Y1"); // NOI18N

        P5R3.setText("3");
        P5R3.setName("P5R3"); // NOI18N

        P5M1.setText("1");
        P5M1.setName("P5M1"); // NOI18N

        P5G1.setText("1");
        P5G1.setName("P5G1"); // NOI18N

        P5G3.setText("3");
        P5G3.setName("P5G3"); // NOI18N

        P5P2.setText("2");
        P5P2.setName("P5P2"); // NOI18N

        P5R2.setText("2");
        P5R2.setName("P5R2"); // NOI18N

        P5O3.setText("3");
        P5O3.setName("P5O3"); // NOI18N

        P5O1.setText("1");
        P5O1.setName("P5O1"); // NOI18N

        P5B1.setText("1");
        P5B1.setName("P5B1"); // NOI18N

        P5G2.setText("2");
        P5G2.setName("P5G2"); // NOI18N

        P5P1.setText("1");
        P5P1.setName("P5P1"); // NOI18N

        P5B2.setText("2");
        P5B2.setName("P5B2"); // NOI18N

        P5Y3.setText("3");
        P5Y3.setName("P5Y3"); // NOI18N

        P5O2.setText("2");
        P5O2.setName("P5O2"); // NOI18N

        P5C2.setText("2");
        P5C2.setName("P5C2"); // NOI18N

        P5R1.setText("1");
        P5R1.setName("P5R1"); // NOI18N

        P5M2.setText("2");
        P5M2.setName("P5M2"); // NOI18N

        P5P3.setText("3");
        P5P3.setName("P5P3"); // NOI18N

        P5Y2.setText("2");
        P5Y2.setName("P5Y2"); // NOI18N

        P6G3.setText("3");
        P6G3.setName("P6G3"); // NOI18N

        P6C1.setText("1");
        P6C1.setName("P6C1"); // NOI18N

        P6Y1.setText("1");
        P6Y1.setName("P6Y1"); // NOI18N

        P6C2.setText("2");
        P6C2.setName("P6C2"); // NOI18N

        P6G2.setText("2");
        P6G2.setName("P6G2"); // NOI18N

        P6Y2.setText("2");
        P6Y2.setName("P6Y2"); // NOI18N

        P6G1.setText("1");
        P6G1.setName("P6G1"); // NOI18N

        P6P1.setText("1");
        P6P1.setName("P6P1"); // NOI18N

        P6M2.setText("2");
        P6M2.setName("P6M2"); // NOI18N

        P6B2.setText("2");
        P6B2.setName("P6B2"); // NOI18N

        P6B1.setText("1");
        P6B1.setName("P6B1"); // NOI18N

        P6Y3.setText("3");
        P6Y3.setName("P6Y3"); // NOI18N

        P6R1.setText("1");
        P6R1.setName("P6R1"); // NOI18N

        P6O3.setText("3");
        P6O3.setName("P6O3"); // NOI18N

        P6R2.setText("2");
        P6R2.setName("P6R2"); // NOI18N

        P6P2.setText("2");
        P6P2.setName("P6P2"); // NOI18N

        P6C3.setText("3");
        P6C3.setName("P6C3"); // NOI18N

        P6P3.setText("3");
        P6P3.setName("P6P3"); // NOI18N

        P6M1.setText("1");
        P6M1.setName("P6M1"); // NOI18N

        P6O1.setText("1");
        P6O1.setName("P6O1"); // NOI18N

        P6R3.setText("3");
        P6R3.setName("P6R3"); // NOI18N

        P6O2.setText("2");
        P6O2.setName("P6O2"); // NOI18N

        P7R1.setText("1");
        P7R1.setName("P7R1"); // NOI18N

        P7M2.setText("2");
        P7M2.setName("P7M2"); // NOI18N

        P7P2.setText("2");
        P7P2.setName("P7P2"); // NOI18N

        P7R2.setText("2");
        P7R2.setName("P7R2"); // NOI18N

        P7B2.setText("2");
        P7B2.setName("P7B2"); // NOI18N

        P7O3.setText("3");
        P7O3.setName("P7O3"); // NOI18N

        P7O2.setText("2");
        P7O2.setName("P7O2"); // NOI18N

        P7P3.setText("3");
        P7P3.setName("P7P3"); // NOI18N

        P7P1.setText("1");
        P7P1.setName("P7P1"); // NOI18N

        P7G3.setText("3");
        P7G3.setName("P7G3"); // NOI18N

        P7B1.setText("1");
        P7B1.setName("P7B1"); // NOI18N

        P7O1.setText("1");
        P7O1.setName("P7O1"); // NOI18N

        P7Y2.setText("2");
        P7Y2.setName("P7Y2"); // NOI18N

        P7G1.setText("1");
        P7G1.setName("P7G1"); // NOI18N

        P7Y1.setText("1");
        P7Y1.setName("P7Y1"); // NOI18N

        P7Y3.setText("3");
        P7Y3.setName("P7Y3"); // NOI18N

        P7R3.setText("3");
        P7R3.setName("P7R3"); // NOI18N

        P7C3.setText("3");
        P7C3.setName("P7C3"); // NOI18N

        P7C1.setText("1");
        P7C1.setName("P7C1"); // NOI18N

        P7C2.setText("2");
        P7C2.setName("P7C2"); // NOI18N

        P7G2.setText("2");
        P7G2.setName("P7G2"); // NOI18N

        P7M1.setText("1");
        P7M1.setName("P7M1"); // NOI18N

        P8O3.setText("3");
        P8O3.setName("P8O3"); // NOI18N

        P8M1.setText("1");
        P8M1.setName("P8M1"); // NOI18N

        P8P3.setText("3");
        P8P3.setName("P8P3"); // NOI18N

        P8P1.setText("1");
        P8P1.setName("P8P1"); // NOI18N

        P8B1.setText("1");
        P8B1.setName("P8B1"); // NOI18N

        P8R3.setText("3");
        P8R3.setName("P8R3"); // NOI18N

        P8Y2.setText("2");
        P8Y2.setName("P8Y2"); // NOI18N

        P8O1.setText("1");
        P8O1.setName("P8O1"); // NOI18N

        P8P2.setText("2");
        P8P2.setName("P8P2"); // NOI18N

        P8C2.setText("2");
        P8C2.setName("P8C2"); // NOI18N

        P8R1.setText("1");
        P8R1.setName("P8R1"); // NOI18N

        P8M2.setText("2");
        P8M2.setName("P8M2"); // NOI18N

        P8Y1.setText("1");
        P8Y1.setName("P8Y1"); // NOI18N

        P8B2.setText("2");
        P8B2.setName("P8B2"); // NOI18N

        P8R2.setText("2");
        P8R2.setName("P8R2"); // NOI18N

        P8G3.setText("3");
        P8G3.setName("P8G3"); // NOI18N

        P8Y3.setText("3");
        P8Y3.setName("P8Y3"); // NOI18N

        P8G2.setText("2");
        P8G2.setName("P8G2"); // NOI18N

        P8C1.setText("1");
        P8C1.setName("P8C1"); // NOI18N

        P8O2.setText("2");
        P8O2.setName("P8O2"); // NOI18N

        P8G1.setText("1");
        P8G1.setName("P8G1"); // NOI18N

        P8C3.setText("3");
        P8C3.setName("P8C3"); // NOI18N

        P1U1.setText("1");
        P1U1.setName("P1U1"); // NOI18N

        P1U2.setText("2");
        P1U2.setName("P1U2"); // NOI18N

        P1RA1.setText("1");
        P1RA1.setName("P1RA1"); // NOI18N

        P1RA2.setText("2");
        P1RA2.setName("P1RA2"); // NOI18N

        P1RA3.setText("3");
        P1RA3.setName("P1RA3"); // NOI18N

        P1RA4.setText("4");
        P1RA4.setName("P1RA4"); // NOI18N

        P2RA4.setText("4");
        P2RA4.setName("P2RA4"); // NOI18N

        P2U1.setText("1");
        P2U1.setName("P2U1"); // NOI18N

        P2U2.setText("2");
        P2U2.setName("P2U2"); // NOI18N

        P2RA1.setText("1");
        P2RA1.setName("P2RA1"); // NOI18N

        P2RA2.setText("2");
        P2RA2.setName("P2RA2"); // NOI18N

        P2RA3.setText("3");
        P2RA3.setName("P2RA3"); // NOI18N

        P3RA4.setText("4");
        P3RA4.setName("P3RA4"); // NOI18N

        P3U1.setText("1");
        P3U1.setName("P3U1"); // NOI18N

        P3U2.setText("2");
        P3U2.setName("P3U2"); // NOI18N

        P3RA1.setText("1");
        P3RA1.setName("P3RA1"); // NOI18N

        P3RA2.setText("2");
        P3RA2.setName("P3RA2"); // NOI18N

        P3RA3.setText("3");
        P3RA3.setName("P3RA3"); // NOI18N

        P4RA4.setText("4");
        P4RA4.setName("P4RA4"); // NOI18N

        P4U1.setText("1");
        P4U1.setName("P4U1"); // NOI18N

        P4U2.setText("2");
        P4U2.setName("P4U2"); // NOI18N

        P4RA1.setText("1");
        P4RA1.setName("P4RA1"); // NOI18N

        P4RA2.setText("2");
        P4RA2.setName("P4RA2"); // NOI18N

        P4RA3.setText("3");
        P4RA3.setName("P4RA3"); // NOI18N

        P5RA4.setText("4");
        P5RA4.setName("P5RA4"); // NOI18N

        P5U1.setText("1");
        P5U1.setName("P5U1"); // NOI18N

        P5U2.setText("2");
        P5U2.setName("P5U2"); // NOI18N

        P5RA1.setText("1");
        P5RA1.setName("P5RA1"); // NOI18N

        P5RA2.setText("2");
        P5RA2.setName("P5RA2"); // NOI18N

        P5RA3.setText("3");
        P5RA3.setName("P5RA3"); // NOI18N

        P6RA4.setText("4");
        P6RA4.setName("P6RA4"); // NOI18N

        P6U1.setText("1");
        P6U1.setName("P6U1"); // NOI18N

        P6U2.setText("2");
        P6U2.setName("P6U2"); // NOI18N

        P6RA1.setText("1");
        P6RA1.setName("P6RA1"); // NOI18N

        P6RA2.setText("2");
        P6RA2.setName("P6RA2"); // NOI18N

        P6RA3.setText("3");
        P6RA3.setName("P6RA3"); // NOI18N

        P7RA4.setText("4");
        P7RA4.setName("P7RA4"); // NOI18N

        P7U1.setText("1");
        P7U1.setName("P7U1"); // NOI18N

        P7U2.setText("2");
        P7U2.setName("P7U2"); // NOI18N

        P7RA1.setText("1");
        P7RA1.setName("P7RA1"); // NOI18N

        P7RA2.setText("2");
        P7RA2.setName("P7RA2"); // NOI18N

        P7RA3.setText("3");
        P7RA3.setName("P7RA3"); // NOI18N

        P8RA4.setText("4");
        P8RA4.setName("P8RA4"); // NOI18N

        P8U1.setText("1");
        P8U1.setName("P8U1"); // NOI18N

        P8U2.setText("2");
        P8U2.setName("P8U2"); // NOI18N

        P8RA1.setText("1");
        P8RA1.setName("P8RA1"); // NOI18N

        P8RA2.setText("2");
        P8RA2.setName("P8RA2"); // NOI18N

        P8RA3.setText("3");
        P8RA3.setName("P8RA3"); // NOI18N

        javax.swing.GroupLayout playerPaneLayout = new javax.swing.GroupLayout(playerPane);
        playerPane.setLayout(playerPaneLayout);
        playerPaneLayout.setHorizontalGroup(
            playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayer8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lProperties)
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P1M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P1RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P1RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P2M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P2RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P2RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P3M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P3RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P3RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P4M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P4RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P4RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P5M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P5RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P5RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P6M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P6RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P6RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P7M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P7RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P7RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerPaneLayout.createSequentialGroup()
                        .addComponent(P8M1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8M2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8C1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8C2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8C3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8P1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8P2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8P3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8O1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8O2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8O3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8R1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8R2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8R3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8Y1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8Y2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8Y3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8G1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8G2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8G3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8B1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8B2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8U1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8U2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(P8RA1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8RA2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8RA3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(P8RA4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        playerPaneLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {P1B1, P1B2, P1C1, P1C2, P1C3, P1G1, P1G2, P1G3, P1M1, P1M2, P1O1, P1O2, P1O3, P1P1, P1P2, P1P3, P1R1, P1R2, P1R3, P1RA1, P1RA2, P1RA3, P1RA4, P1U1, P1U2, P1Y1, P1Y2, P1Y3, P2B1, P2B2, P2C1, P2C2, P2C3, P2G1, P2G2, P2G3, P2M1, P2M2, P2O1, P2O2, P2O3, P2P1, P2P2, P2P3, P2R1, P2R2, P2R3, P2RA1, P2RA2, P2RA3, P2RA4, P2U1, P2U2, P2Y1, P2Y2, P2Y3, P3B1, P3B2, P3C1, P3C2, P3C3, P3G1, P3G2, P3G3, P3M1, P3M2, P3O1, P3O2, P3O3, P3P1, P3P2, P3P3, P3R1, P3R2, P3R3, P3RA1, P3RA2, P3RA3, P3RA4, P3U1, P3U2, P3Y1, P3Y2, P3Y3, P4B1, P4B2, P4C1, P4C2, P4C3, P4G1, P4G2, P4G3, P4M1, P4M2, P4O1, P4O2, P4O3, P4P1, P4P2, P4P3, P4R1, P4R2, P4R3, P4RA1, P4RA2, P4RA3, P4RA4, P4U1, P4U2, P4Y1, P4Y2, P4Y3, P5B1, P5B2, P5C1, P5C2, P5C3, P5G1, P5G2, P5G3, P5M1, P5M2, P5O1, P5O2, P5O3, P5P1, P5P2, P5P3, P5R1, P5R2, P5R3, P5RA1, P5RA2, P5RA3, P5RA4, P5U1, P5U2, P5Y1, P5Y2, P5Y3, P6B1, P6B2, P6C1, P6C2, P6C3, P6G1, P6G2, P6G3, P6M1, P6M2, P6O1, P6O2, P6O3, P6P1, P6P2, P6P3, P6R1, P6R2, P6R3, P6RA1, P6RA2, P6RA3, P6RA4, P6U1, P6U2, P6Y1, P6Y2, P6Y3, P7B1, P7B2, P7C1, P7C2, P7C3, P7G1, P7G2, P7G3, P7M1, P7M2, P7O1, P7O2, P7O3, P7P1, P7P2, P7P3, P7R1, P7R2, P7R3, P7RA1, P7RA2, P7RA3, P7RA4, P7U1, P7U2, P7Y1, P7Y2, P7Y3, P8B1, P8B2, P8C1, P8C2, P8C3, P8G1, P8G2, P8G3, P8M1, P8M2, P8O1, P8O2, P8O3, P8P1, P8P2, P8P3, P8R1, P8R2, P8R3, P8RA1, P8RA2, P8RA3, P8RA4, P8U1, P8U2, P8Y1, P8Y2, P8Y3});

        playerPaneLayout.setVerticalGroup(
            playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPlayers)
                    .addComponent(lProperties))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(P1M1)
                    .addComponent(P1M2)
                    .addComponent(P1C1)
                    .addComponent(P1C2)
                    .addComponent(P1C3)
                    .addComponent(P1P1)
                    .addComponent(P1P2)
                    .addComponent(P1P3)
                    .addComponent(P1O1)
                    .addComponent(P1O2)
                    .addComponent(P1O3)
                    .addComponent(P1R1)
                    .addComponent(P1R2)
                    .addComponent(P1R3)
                    .addComponent(P1Y1)
                    .addComponent(P1Y2)
                    .addComponent(P1Y3)
                    .addComponent(P1G1)
                    .addComponent(P1G2)
                    .addComponent(P1G3)
                    .addComponent(P1B1)
                    .addComponent(P1B2)
                    .addComponent(P1U1)
                    .addComponent(P1U2)
                    .addComponent(P1RA1)
                    .addComponent(P1RA2)
                    .addComponent(P1RA3)
                    .addComponent(P1RA4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P2U1)
                        .addComponent(P2U2)
                        .addComponent(P2RA1)
                        .addComponent(P2RA2)
                        .addComponent(P2RA3)
                        .addComponent(P2RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P2M1)
                        .addComponent(P2M2)
                        .addComponent(P2C1)
                        .addComponent(P2C2)
                        .addComponent(P2C3)
                        .addComponent(P2P1)
                        .addComponent(P2P2)
                        .addComponent(P2P3)
                        .addComponent(P2O1)
                        .addComponent(P2O2)
                        .addComponent(P2O3)
                        .addComponent(P2R1)
                        .addComponent(P2R2)
                        .addComponent(P2R3)
                        .addComponent(P2Y1)
                        .addComponent(P2Y2)
                        .addComponent(P2Y3)
                        .addComponent(P2G1)
                        .addComponent(P2G2)
                        .addComponent(P2G3)
                        .addComponent(P2B1)
                        .addComponent(P2B2))
                    .addComponent(lPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P3U1)
                        .addComponent(P3U2)
                        .addComponent(P3RA1)
                        .addComponent(P3RA2)
                        .addComponent(P3RA3)
                        .addComponent(P3RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P3M1)
                        .addComponent(P3M2)
                        .addComponent(P3C1)
                        .addComponent(P3C2)
                        .addComponent(P3C3)
                        .addComponent(P3P1)
                        .addComponent(P3P2)
                        .addComponent(P3P3)
                        .addComponent(P3O1)
                        .addComponent(P3O2)
                        .addComponent(P3O3)
                        .addComponent(P3R1)
                        .addComponent(P3R2)
                        .addComponent(P3R3)
                        .addComponent(P3Y1)
                        .addComponent(P3Y2)
                        .addComponent(P3Y3)
                        .addComponent(P3G1)
                        .addComponent(P3G2)
                        .addComponent(P3G3)
                        .addComponent(P3B1)
                        .addComponent(P3B2))
                    .addComponent(lPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P4U1)
                        .addComponent(P4U2)
                        .addComponent(P4RA1)
                        .addComponent(P4RA2)
                        .addComponent(P4RA3)
                        .addComponent(P4RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P4M1)
                        .addComponent(P4M2)
                        .addComponent(P4C1)
                        .addComponent(P4C2)
                        .addComponent(P4C3)
                        .addComponent(P4P1)
                        .addComponent(P4P2)
                        .addComponent(P4P3)
                        .addComponent(P4O1)
                        .addComponent(P4O2)
                        .addComponent(P4O3)
                        .addComponent(P4R1)
                        .addComponent(P4R2)
                        .addComponent(P4R3)
                        .addComponent(P4Y1)
                        .addComponent(P4Y2)
                        .addComponent(P4Y3)
                        .addComponent(P4G1)
                        .addComponent(P4G2)
                        .addComponent(P4G3)
                        .addComponent(P4B1)
                        .addComponent(P4B2))
                    .addComponent(lPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P5U1)
                        .addComponent(P5U2)
                        .addComponent(P5RA1)
                        .addComponent(P5RA2)
                        .addComponent(P5RA3)
                        .addComponent(P5RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P5M1)
                        .addComponent(P5M2)
                        .addComponent(P5C1)
                        .addComponent(P5C2)
                        .addComponent(P5C3)
                        .addComponent(P5P1)
                        .addComponent(P5P2)
                        .addComponent(P5P3)
                        .addComponent(P5O1)
                        .addComponent(P5O2)
                        .addComponent(P5O3)
                        .addComponent(P5R1)
                        .addComponent(P5R2)
                        .addComponent(P5R3)
                        .addComponent(P5Y1)
                        .addComponent(P5Y2)
                        .addComponent(P5Y3)
                        .addComponent(P5G1)
                        .addComponent(P5G2)
                        .addComponent(P5G3)
                        .addComponent(P5B1)
                        .addComponent(P5B2))
                    .addComponent(lPlayer5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P6U1)
                        .addComponent(P6U2)
                        .addComponent(P6RA1)
                        .addComponent(P6RA2)
                        .addComponent(P6RA3)
                        .addComponent(P6RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P6M1)
                        .addComponent(P6M2)
                        .addComponent(P6C1)
                        .addComponent(P6C2)
                        .addComponent(P6C3)
                        .addComponent(P6P1)
                        .addComponent(P6P2)
                        .addComponent(P6P3)
                        .addComponent(P6O1)
                        .addComponent(P6O2)
                        .addComponent(P6O3)
                        .addComponent(P6R1)
                        .addComponent(P6R2)
                        .addComponent(P6R3)
                        .addComponent(P6Y1)
                        .addComponent(P6Y2)
                        .addComponent(P6Y3)
                        .addComponent(P6G1)
                        .addComponent(P6G2)
                        .addComponent(P6G3)
                        .addComponent(P6B1)
                        .addComponent(P6B2))
                    .addComponent(lPlayer6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P7U1)
                        .addComponent(P7U2)
                        .addComponent(P7RA1)
                        .addComponent(P7RA2)
                        .addComponent(P7RA3)
                        .addComponent(P7RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P7M1)
                        .addComponent(P7M2)
                        .addComponent(P7C1)
                        .addComponent(P7C2)
                        .addComponent(P7C3)
                        .addComponent(P7P1)
                        .addComponent(P7P2)
                        .addComponent(P7P3)
                        .addComponent(P7O1)
                        .addComponent(P7O2)
                        .addComponent(P7O3)
                        .addComponent(P7R1)
                        .addComponent(P7R2)
                        .addComponent(P7R3)
                        .addComponent(P7Y1)
                        .addComponent(P7Y2)
                        .addComponent(P7Y3)
                        .addComponent(P7G1)
                        .addComponent(P7G2)
                        .addComponent(P7G3)
                        .addComponent(P7B1)
                        .addComponent(P7B2))
                    .addComponent(lPlayer7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P8U1)
                        .addComponent(P8U2)
                        .addComponent(P8RA1)
                        .addComponent(P8RA2)
                        .addComponent(P8RA3)
                        .addComponent(P8RA4))
                    .addGroup(playerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(P8M1)
                        .addComponent(P8M2)
                        .addComponent(P8C1)
                        .addComponent(P8C2)
                        .addComponent(P8C3)
                        .addComponent(P8P1)
                        .addComponent(P8P2)
                        .addComponent(P8P3)
                        .addComponent(P8O1)
                        .addComponent(P8O2)
                        .addComponent(P8O3)
                        .addComponent(P8R1)
                        .addComponent(P8R2)
                        .addComponent(P8R3)
                        .addComponent(P8Y1)
                        .addComponent(P8Y2)
                        .addComponent(P8Y3)
                        .addComponent(P8G1)
                        .addComponent(P8G2)
                        .addComponent(P8G3)
                        .addComponent(P8B1)
                        .addComponent(P8B2))
                    .addComponent(lPlayer8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        playerPaneLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {P1B1, P1B2, P1C1, P1C2, P1C3, P1G1, P1G2, P1G3, P1M1, P1M2, P1O1, P1O2, P1O3, P1P1, P1P2, P1P3, P1R1, P1R2, P1R3, P1RA1, P1RA2, P1RA3, P1RA4, P1U1, P1U2, P1Y1, P1Y2, P1Y3, P2B1, P2B2, P2C1, P2C2, P2C3, P2G1, P2G2, P2G3, P2M1, P2M2, P2O1, P2O2, P2O3, P2P1, P2P2, P2P3, P2R1, P2R2, P2R3, P2RA1, P2RA2, P2RA3, P2RA4, P2U1, P2U2, P2Y1, P2Y2, P2Y3, P3B1, P3B2, P3C1, P3C2, P3C3, P3G1, P3G2, P3G3, P3M1, P3M2, P3O1, P3O2, P3O3, P3P1, P3P2, P3P3, P3R1, P3R2, P3R3, P3RA1, P3RA2, P3RA3, P3RA4, P3U1, P3U2, P3Y1, P3Y2, P3Y3, P4B1, P4B2, P4C1, P4C2, P4C3, P4G1, P4G2, P4G3, P4M1, P4M2, P4O1, P4O2, P4O3, P4P1, P4P2, P4P3, P4R1, P4R2, P4R3, P4RA1, P4RA2, P4RA3, P4RA4, P4U1, P4U2, P4Y1, P4Y2, P4Y3, P5B1, P5B2, P5C1, P5C2, P5C3, P5G1, P5G2, P5G3, P5M1, P5M2, P5O1, P5O2, P5O3, P5P1, P5P2, P5P3, P5R1, P5R2, P5R3, P5RA1, P5RA2, P5RA3, P5RA4, P5U1, P5U2, P5Y1, P5Y2, P5Y3, P6B1, P6B2, P6C1, P6C2, P6C3, P6G1, P6G2, P6G3, P6M1, P6M2, P6O1, P6O2, P6O3, P6P1, P6P2, P6P3, P6R1, P6R2, P6R3, P6RA1, P6RA2, P6RA3, P6RA4, P6U1, P6U2, P6Y1, P6Y2, P6Y3, P7B1, P7B2, P7C1, P7C2, P7C3, P7G1, P7G2, P7G3, P7M1, P7M2, P7O1, P7O2, P7O3, P7P1, P7P2, P7P3, P7R1, P7R2, P7R3, P7RA1, P7RA2, P7RA3, P7RA4, P7U1, P7U2, P7Y1, P7Y2, P7Y3, P8B1, P8B2, P8C1, P8C2, P8C3, P8G1, P8G2, P8G3, P8M1, P8M2, P8O1, P8O2, P8O3, P8P1, P8P2, P8P3, P8R1, P8R2, P8R3, P8RA1, P8RA2, P8RA3, P8RA4, P8U1, P8U2, P8Y1, P8Y2, P8Y3});

        jLabel1.setText("TARJETA EN LA QUE ESTAS");

        bDice.setText("Tirar Dados");
        bDice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDiceActionPerformed(evt);
            }
        });

        jLabel2.setText("Dado 1:");

        lDice1.setText("0");

        lDice2.setText("0");

        jLabel5.setText("Dado 2:");

        jLabel3.setText("Te mueves:");

        lTotalDice.setText("0");

        jLabel4.setText("Turno de:");

        lPlayerOnTurn.setText("PlayerOnTurn");

        bBankrupt.setText("Declararme en Bancarota");
        bBankrupt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBankruptActionPerformed(evt);
            }
        });

        jButton1.setText("Tradear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bBuyProperty.setText("Comprar propiedad");
        bBuyProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuyPropertyActionPerformed(evt);
            }
        });

        bAuctionProperty.setText("Subastar propiedad");
        bAuctionProperty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAuctionPropertyActionPerformed(evt);
            }
        });

        bPropertyHandle.setText("Manejar Mis Propiedades");
        bPropertyHandle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPropertyHandleActionPerformed(evt);
            }
        });

        jLabel6.setText("Dinero:");

        lMoney.setText("0 M");

        jLabel8.setText("Precio de Casas:");

        lHousePrice.setText("0 M");

        lHotelPrice.setText("0 M");

        jLabel11.setText("Precio de Hoteles:");

        jLabel7.setText("Renta a pagar:");

        lRentToPay.setText("0 M");

        bPayRent.setText("Pagar Renta");
        bPayRent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPayRentActionPerformed(evt);
            }
        });

        bNextTurn.setText("Finalizar Turno");
        bNextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextTurnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dicePaneLayout = new javax.swing.GroupLayout(dicePane);
        dicePane.setLayout(dicePaneLayout);
        dicePaneLayout.setHorizontalGroup(
            dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dicePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dicePaneLayout.createSequentialGroup()
                        .addComponent(bBankrupt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bNextTurn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(dicePaneLayout.createSequentialGroup()
                        .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lCell, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bPayRent)
                                    .addGroup(dicePaneLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lPlayerOnTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dicePaneLayout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lRentToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dicePaneLayout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lHotelPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dicePaneLayout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lHousePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bBuyProperty)
                                    .addComponent(bAuctionProperty))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lTotalDice, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lDice1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lDice2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bDice)
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bPropertyHandle)
                            .addComponent(jButton1))
                        .addGap(81, 81, 81))))
        );
        dicePaneLayout.setVerticalGroup(
            dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dicePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dicePaneLayout.createSequentialGroup()
                        .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lPlayerOnTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(lMoney))
                        .addGap(12, 12, 12)
                        .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addComponent(bBuyProperty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAuctionProperty)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(lHousePrice)))
                            .addComponent(bDice)
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(lDice1)
                                    .addComponent(jLabel5)
                                    .addComponent(lDice2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(lTotalDice))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(lHotelPrice))
                                .addGap(16, 16, 16)
                                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lRentToPay)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bPayRent))
                            .addGroup(dicePaneLayout.createSequentialGroup()
                                .addComponent(bPropertyHandle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))))
                    .addComponent(lCell, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(dicePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bBankrupt)
                    .addComponent(bNextTurn))
                .addContainerGap())
        );

        jButton2.setText("Pa la carcel Papi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dicePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(playerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dicePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jButton2)
                        .addGap(96, 96, 96))
                    .addComponent(canvas, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bDiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDiceActionPerformed
        if (lTotalDice.getText().equals("0")) {
            playerOnTurn = (Player) m.getPlayers().getCourrent();

            int totalDice = m.throwDice();
            lTotalDice.setText(Integer.toString(totalDice));
            lDice1.setText(Integer.toString(m.getDice1()));
            lDice2.setText(Integer.toString(m.getDice2()));
            if(playerOnTurn.isInJail()){
                
                if(m.getDice1() != m.getDice2()){
                    playerOnTurn.setTurnsInJail(playerOnTurn.getTurnsInJail() + 1);
                    if(playerOnTurn.getTurnsInJail() >= 5){
                        JOptionPane.showMessageDialog(null, "Tienes que salir de la carcel ya han pasado 4 turnos, paga la fianza de 50 M y vuelve a lanzar los dados");
                        onAcquirable = true;
                    }
                    return;
                }
                playerOnTurn.setInJail(false);
            }
            int toCell = playerOnTurn.getCourrentCell() + totalDice;
            if (toCell > 40) {
                toCell = toCell % 40;
                playerOnTurn.setMoney(playerOnTurn.getMoney() + 200);
            }
            playerOnTurn.setCourrentCell(toCell);
            setPlayerOnTurn();

            cellInTurn = m.getTable().BuscarConId(toCell);
            if (cellInTurn.getNombre().equals("Avenue")) {
                onAcquirable = true;
                Avenue courrentCell = (Avenue) cellInTurn;
                if (courrentCell.getOwner() != null) {
                    String rent = Integer.toString(courrentCell.getTotalRentPrice()) + " M";
                    lRentToPay.setText(rent);
                }
                lHousePrice.setText(Integer.toString(courrentCell.getHousesPrice()) + " M");
                lHotelPrice.setText(Integer.toString(courrentCell.getHotelsPrice()) + " M");
            } else if (cellInTurn.getNombre().equals("Corner")) { //fix..
                Corner courrentCell = (Corner) cellInTurn;
                playerOnTurn.setMoney(playerOnTurn.getMoney() + courrentCell.getGift());
                System.out.println("ID " + courrentCell.getId());
                System.out.println("Nombre " + courrentCell.getName());
                playerOnTurn.setCourrentCell(courrentCell.getId());
                if(courrentCell.getId() == 11 || courrentCell.getId() == 31){
                    playerOnTurn.setCourrentCell(11);
                    playerOnTurn.setInJail(true);
                }
            } else if (cellInTurn.getNombre().equals("Railroad")) {
                onAcquirable = true;
                Railroad courrentCell = (Railroad) cellInTurn;
                if (courrentCell.getOwner() != null) {
                    String rent = Integer.toString(m.getRailRentPrice(courrentCell.getOwner())) + " M";
                    lRentToPay.setText(rent);
                }
            } else if (cellInTurn.getNombre().equals("Tax")) {
                onAcquirable = true; //Not exactly but ok...
                Tax courrentCell = (Tax) cellInTurn;
                lRentToPay.setText(Integer.toString(courrentCell.getTax()));
            } else if (cellInTurn.getNombre().equals("Utility")) {
                onAcquirable = true;
                Utility courrentCell = (Utility) cellInTurn;
                if (courrentCell.getOwner() != null) {
                    String rent = Integer.toString(courrentCell.getRentPrice(totalDice)) + " M";
                    lRentToPay.setText(rent);
                }
            } else if (cellInTurn.getNombre().equals("Chance")) { //fix...
                Chance courrentCell = (Chance) cellInTurn;
                Card c = m.drawRandomCard(1);
                playerOnTurn.setCourrentCell(courrentCell.getId());
                c.showMessageToPlayer();
                c.accion();
            } else if (cellInTurn.getNombre().equals("Chest")) { //fix...
                Chest courrentCell = (Chest) cellInTurn;
                Card c = (Card) m.drawRandomCard(2);
                playerOnTurn.setCourrentCell(courrentCell.getId());
                c.showMessageToPlayer();
                c.accion();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya tiraste los dados.");
        }
    }//GEN-LAST:event_bDiceActionPerformed

    private void bBankruptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBankruptActionPerformed
        if (!playerOnTurn.isBankruptcy()) {
            JLabel jl = (JLabel) getComponentByName("lPlayer" + Integer.toString(playerOnTurn.getId()));
            jl.setText("BANCARROTA");
            playerOnTurn.setBankruptcy(true);
            passPropertiesOfLoser(playerOnTurn);
            JOptionPane.showMessageDialog(null, "Estas en bancarrota. Tus propiedades seran dadas a la persona que tenga mas propiedades");
        }
    }//GEN-LAST:event_bBankruptActionPerformed

    private void bBuyPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuyPropertyActionPerformed
        if (onAcquirable) {
            String name = cellInTurn.getNombre();
            if (name.equals("Avenue")) {
                Avenue courrentCell = (Avenue) cellInTurn;
                if (courrentCell.getOwner() == null) {
                    if (m.compra(courrentCell, playerOnTurn, courrentCell.getPrice())) {
                        setPlayerOnTurn();
                        onAcquirable = false;
                        showAvenues(playerOnTurn.getId(), courrentCell.getAvenueColor(), m.getAvenueNumber(cellInTurn));
                    } else {
                        JOptionPane.showMessageDialog(null, "Dinero insuficiente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya tiene dueño.");
                }
            } else if (name.equals("Railroad")) {
                Railroad courrentCell = (Railroad) cellInTurn;
                if (courrentCell.getOwner() == null) {
                    if (m.compra(courrentCell, playerOnTurn, courrentCell.getPrice())) {
                        setPlayerOnTurn();
                        onAcquirable = false;
                        showUtilityOrRailroad(false, playerOnTurn.getId(), m.getRailNumber(cellInTurn));
                    } else {
                        JOptionPane.showMessageDialog(null, "Dinero insuficiente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya tiene dueño.");
                }
            } else if (name.equals("Utility")) {
                Utility courrentCell = (Utility) cellInTurn;
                if (courrentCell.getOwner() == null) {
                    if (m.compra(courrentCell, playerOnTurn, courrentCell.getPrice())) {
                        setPlayerOnTurn();
                        onAcquirable = false;
                        showUtilityOrRailroad(true, playerOnTurn.getId(), m.getUtilityNumber(cellInTurn));
                    } else {
                        JOptionPane.showMessageDialog(null, "Dinero insuficiente.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya tiene dueño.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No puedes comprar esto.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes comprar esto.");
        }
    }//GEN-LAST:event_bBuyPropertyActionPerformed

    private void bAuctionPropertyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAuctionPropertyActionPerformed
        Avenue courrentCell = (Avenue) cellInTurn;
        if (courrentCell.getOwner() == null) {
            //to do...            
            setPlayerOnTurn();
        } else {
            JOptionPane.showMessageDialog(null, "Ya tiene dueño.");
        }
    }//GEN-LAST:event_bAuctionPropertyActionPerformed

    private void bPayRentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPayRentActionPerformed
        if (onAcquirable || playerOnTurn.isInJail()) {
            String rentToPay = lRentToPay.getText();
            if (!rentToPay.equals("0 M")) {
                int value = Integer.valueOf(rentToPay.substring(0, rentToPay.length() - 2));
                int finalMoney = playerOnTurn.getMoney() - value;
                if (finalMoney > 0) {
                    playerOnTurn.setMoney(finalMoney);
                    lRentToPay.setText("0 M");
                    setPlayerOnTurn();
                    onAcquirable = false;
                    if(playerOnTurn.isInJail()){
                        playerOnTurn.setInJail(false);
                        lTotalDice.setText("0");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay renta que pagar.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay renta que pagar.");
        }
    }//GEN-LAST:event_bPayRentActionPerformed

    private void bNextTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextTurnActionPerformed
        if (!playerOnTurn.isBankruptcy()) {
            if (!onAcquirable) {
                if (!lTotalDice.getText().equals("0")) {
                    lTotalDice.setText("0");
                    lDice1.setText("0");
                    lDice2.setText("0");

                    lHousePrice.setText("0 M");
                    lHotelPrice.setText("0 M");
                    lRentToPay.setText("0 M");

                    playerWithMoreProperties();
                    playersPlaying();

                    Player p;
                    do {
                        p = (Player) m.getPlayers().next();
                    } while (p.isBankruptcy());
                    setPlayerOnTurn();
                } else {
                    JOptionPane.showMessageDialog(null, "Tira los dados.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tienes algo pendiente por hacer. Puede ser pagar renta o que caiste en una propiedad sin dueño. Al caer en una propiedad si dueño debes obligatoriamente comprarla o subastarla, no se puede quedar asi.");
            }
        } else {
            lTotalDice.setText("0");
            lDice1.setText("0");
            lDice2.setText("0");

            lHousePrice.setText("0 M");
            lHotelPrice.setText("0 M");
            lRentToPay.setText("0 M");

            Player p;
            do {
                p = (Player) m.getPlayers().next();
            } while (p.isBankruptcy());
            setPlayerOnTurn();
        }
    }//GEN-LAST:event_bNextTurnActionPerformed

    private void MenuItemSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSellActionPerformed
        JLabel jl = (JLabel) propertySelected;
        Color color = jl.getBackground();
        if (color != UIManager.getColor("Panel.background")) {
            Nodo p = getCellByComponent(jl);
            String name = p.getNombre();
            if (name.equals("Avenue")) {
                Avenue a = (Avenue) p;
                int buildings = a.getHouses() + a.getHotels();
                if (buildings == 0) {
                    String componentName = propertySelected.getName();
                    int cont = 0, j;
                    if (componentName.contains("M") || componentName.contains("B")) {
                        j = 3;
                    } else {
                        j = 4;
                    }
                    for (int i = 1; i < j; i++) {
                        Avenue avenue = (Avenue) getCellByComponent(componentName.substring(0, 3) + Integer.toString(i));
                        if (avenue.getHouses() == 0 && avenue.getHotels() == 0) {
                            cont++;
                        }
                    }
                    if (cont == j - 1) {
                        a.setOwner(null);
                        playerOnTurn.setMoney(playerOnTurn.getMoney() + a.getMortgage());
                        jl.setBackground(UIManager.getColor("Panel.background"));
                        jl.setForeground(Color.BLACK);
                        jl.setOpaque(true);
                        setPlayerOnTurn();
                    } else {
                        JOptionPane.showMessageDialog(null, "No puedes vender una propiedad si tienes construcciones en las otras propiedades del mismo color.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No puedes vender una propiedad con construciones.");
                }
            } else if (name.equals("Railroad")) {
                Railroad r = (Railroad) p;
                Railroad rCopy = (Railroad) m.getRail().BuscarConId(p.getId());
                r.setOwner(null);
                rCopy.setOwner(null);
                playerOnTurn.setMoney(playerOnTurn.getMoney() + r.getMortgage());
                jl.setBackground(UIManager.getColor("Panel.background"));
                jl.setForeground(Color.BLACK);
                jl.setOpaque(true);
                setPlayerOnTurn();
            } else if (name.equals("Utility")) {
                Utility u = (Utility) p;
                Utility uCopy = (Utility) m.getUtility().BuscarConId(p.getId());
                Player owner = u.getOwner();
                u.setOwner(null);
                uCopy.setOwner(null);

                u.setRentDicex10(false);
                uCopy.setRentDicex10(false);
                u.setRentDicex4(false);
                uCopy.setRentDicex4(false);

                m.enableUtilityRentPrice(owner);
                playerOnTurn.setMoney(playerOnTurn.getMoney() + u.getMortgage());
                jl.setBackground(UIManager.getColor("Panel.background"));
                jl.setForeground(Color.BLACK);
                jl.setOpaque(true);
                setPlayerOnTurn();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede vender.");
        }
    }//GEN-LAST:event_MenuItemSellActionPerformed

    private void MenuItemHotelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemHotelsActionPerformed
        String componentName = propertySelected.getName();
        if (!componentName.contains("RA") && !componentName.contains("U")) {
            Avenue a = (Avenue) getCellByComponent(propertySelected);
            int houses = a.getHouses();
            int value = Integer.valueOf(a.getHotelsPrice());
            int finalMoney = playerOnTurn.getMoney() - value;
            if (finalMoney > 0) {
                if (houses >= 4) {
                    int cont = 0, j;
                    if (componentName.contains("M") || componentName.contains("B")) {
                        j = 3;
                    } else {
                        j = 4;
                    }
                    for (int i = 1; i < j; i++) {
                        Avenue p = (Avenue) getCellByComponent(componentName.substring(0, 3) + Integer.toString(i));
                        int buildings = p.getHouses() + p.getHotels();
                        if (buildings > 0) {
                            cont++;
                        }
                    }
                    if (cont == j - 1) {
                        a.setHotels(a.getHotels() + 1);
                        a.setHouses(a.getHouses() - 4);
                        playerOnTurn.setMoney(finalMoney);
                        setPlayerOnTurn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes tener todas las propiedades de un mismo color con construciones para construir un hotel.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes tener 4 casas para construir un hotel.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Dinero insuficiente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes poner hoteles aqui.");
        }
    }//GEN-LAST:event_MenuItemHotelsActionPerformed

    private void MenuItemHousesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemHousesActionPerformed
        String componentName = propertySelected.getName();
        if (!componentName.contains("RA") && !componentName.contains("U")) {
            Avenue a = (Avenue) getCellByComponent(propertySelected);
            int buildings = a.getHouses() + a.getHotels();
            int value = Integer.valueOf(a.getHousesPrice());
            int finalMoney = playerOnTurn.getMoney() - value;
            if (finalMoney > 0) {
                if (buildings == 0) {
                    int cont = 0, j;
                    if (componentName.contains("M") || componentName.contains("B")) {
                        j = 3;
                    } else {
                        j = 4;
                    }
                    for (int i = 1; i < j; i++) {
                        if (getComponentByName(componentName.substring(0, 3) + Integer.toString(i)).getBackground() != UIManager.getColor("Panel.background")) {
                            cont++;
                        }
                    }
                    if (cont == j - 1) {
                        a.setHouses(a.getHouses() + 1);
                        playerOnTurn.setMoney(finalMoney);
                        setPlayerOnTurn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes tener todas las propiedades de un mismo color para poder consturir casas o hoteles en ellas.");
                    }
                } else if (buildings > 0 && buildings < 9) {
                    a.setHouses(a.getHouses() + 1);
                    playerOnTurn.setMoney(finalMoney);
                    setPlayerOnTurn();
                } else {
                    System.out.println("Tienes muchas construcciones.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Dinero insuficiente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes poner casas aqui.");
        }
    }//GEN-LAST:event_MenuItemHousesActionPerformed

    private void bPropertyHandleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPropertyHandleActionPerformed
        JOptionPane.showMessageDialog(null, "Para manejar tus propiedades debes hacer click en ellas, estando en tu turno. Recuerda que seras capas de manejar solo las propiedades que sean tuyas, osea que hayas comprado.");
    }//GEN-LAST:event_bPropertyHandleActionPerformed

    private void MenuItemSellHousesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSellHousesActionPerformed
        String componentName = propertySelected.getName();
        if (!componentName.contains("RA") && !componentName.contains("U")) {
            Avenue a = (Avenue) getCellByComponent(propertySelected);
            int houses = a.getHouses();
            if (houses > 0) {
                if (houses == 1) {
                    int cont = 0, j;
                    if (componentName.contains("M") || componentName.contains("B")) {
                        j = 3;
                    } else {
                        j = 4;
                    }
                    for (int i = 1; i < j; i++) {
                        Avenue p = (Avenue) getCellByComponent(componentName.substring(0, 3) + Integer.toString(i));
                        if (p.getHotels() > 0) {
                            cont++;
                            break;
                        }
                    }
                    if (cont == 0) {
                        a.setHouses(a.getHouses() - 1);
                        playerOnTurn.setMoney(playerOnTurn.getMoney() + a.getHousesPrice() / 2);
                        setPlayerOnTurn();
                    } else {
                        JOptionPane.showMessageDialog(null, "No puede vender la casa de una propiedad si las propiedades del mismo color tienen hoteles.");
                    }
                } else {
                    a.setHouses(houses - 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No tienes casas para vender.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes vender.");
        }
    }//GEN-LAST:event_MenuItemSellHousesActionPerformed

    private void MenuItemSellHotelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemSellHotelsActionPerformed
        String componentName = propertySelected.getName();
        if (!componentName.contains("RA") && !componentName.contains("U")) {
            Avenue a = (Avenue) getCellByComponent(propertySelected);
            int hotels = a.getHotels();
            if (hotels > 0) {
                a.setHotels(hotels - 1);
                a.setHouses(a.getHouses() + 4);
                playerOnTurn.setMoney(playerOnTurn.getMoney() + a.getHotelsPrice() / 2);
                setPlayerOnTurn();
            } else {
                JOptionPane.showMessageDialog(null, "No tienes hoteles para vender.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puedes vender.");
        }
    }//GEN-LAST:event_MenuItemSellHotelsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // PAPI HACE FALTA TRADEO
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        playerOnTurn.courrentCell = 11;
        playerOnTurn.setInJail(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    //GETTER SETTER
    //<editor-fold defaultstate="collapsed" desc="comment">
    
    public Master getM() {
        return m;
    }
    
    public void setM(Master m) {
        this.m = m;
    }
    
    public Thread getT1() {
        return t1;
    }
    
    public void setT1(Thread t1) {
        this.t1 = t1;
    }
    
    public boolean isStop() {
        return stop;
    }
    
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    
    public void setBackground(BufferedImage background) {
        this.background = background;
    }
    
    public HashMap getComponentMap() {
        return componentMap;
    }
    
    public void setComponentMap(HashMap componentMap) {
        this.componentMap = componentMap;
    }
    
    public Player getPlayerOnTurn() {
        return playerOnTurn;
    }
    
    public void setPlayerOnTurn(Player playerOnTurn) {
        this.playerOnTurn = playerOnTurn;
    }
    
    public Nodo getCellInTurn() {
        return cellInTurn;
    }
    
    public void setCellInTurn(Nodo cellInTurn) {
        this.cellInTurn = cellInTurn;
    }
    
    public boolean isOnAcquirable() {
        return onAcquirable;
    }
    
    public void setOnAcquirable(boolean onAcquirable) {
        this.onAcquirable = onAcquirable;
    }
    
    public Component getPropertySelected() {
        return propertySelected;
    }
    
    public void setPropertySelected(Component propertySelected) {
        this.propertySelected = propertySelected;
    }
    
    public JMenuItem getMenuItemHotels() {
        return MenuItemHotels;
    }
    
    public void setMenuItemHotels(JMenuItem MenuItemHotels) {
        this.MenuItemHotels = MenuItemHotels;
    }
    
    public JMenuItem getMenuItemHouses() {
        return MenuItemHouses;
    }
    
    public void setMenuItemHouses(JMenuItem MenuItemHouses) {
        this.MenuItemHouses = MenuItemHouses;
    }
    
    public JMenuItem getMenuItemSell() {
        return MenuItemSell;
    }
    
    public void setMenuItemSell(JMenuItem MenuItemSell) {
        this.MenuItemSell = MenuItemSell;
    }
    
    public JMenuItem getMenuItemSellHotels() {
        return MenuItemSellHotels;
    }
    
    public void setMenuItemSellHotels(JMenuItem MenuItemSellHotels) {
        this.MenuItemSellHotels = MenuItemSellHotels;
    }
    
    public JMenuItem getMenuItemSellHouses() {
        return MenuItemSellHouses;
    }
    
    public void setMenuItemSellHouses(JMenuItem MenuItemSellHouses) {
        this.MenuItemSellHouses = MenuItemSellHouses;
    }
    
    public JLabel getP1B1() {
        return P1B1;
    }
    
    public void setP1B1(JLabel P1B1) {
        this.P1B1 = P1B1;
    }
    
    public JLabel getP1B2() {
        return P1B2;
    }
    
    public void setP1B2(JLabel P1B2) {
        this.P1B2 = P1B2;
    }
    
    public JLabel getP1C1() {
        return P1C1;
    }
    
    public void setP1C1(JLabel P1C1) {
        this.P1C1 = P1C1;
    }
    
    public JLabel getP1C2() {
        return P1C2;
    }
    
    public void setP1C2(JLabel P1C2) {
        this.P1C2 = P1C2;
    }
    
    public JLabel getP1C3() {
        return P1C3;
    }
    
    public void setP1C3(JLabel P1C3) {
        this.P1C3 = P1C3;
    }
    
    public JLabel getP1G1() {
        return P1G1;
    }
    
    public void setP1G1(JLabel P1G1) {
        this.P1G1 = P1G1;
    }
    
    public JLabel getP1G2() {
        return P1G2;
    }
    
    public void setP1G2(JLabel P1G2) {
        this.P1G2 = P1G2;
    }
    
    public JLabel getP1G3() {
        return P1G3;
    }
    
    public void setP1G3(JLabel P1G3) {
        this.P1G3 = P1G3;
    }
    
    public JLabel getP1M1() {
        return P1M1;
    }
    
    public void setP1M1(JLabel P1M1) {
        this.P1M1 = P1M1;
    }
    
    public JLabel getP1M2() {
        return P1M2;
    }
    
    public void setP1M2(JLabel P1M2) {
        this.P1M2 = P1M2;
    }
    
    public JLabel getP1O1() {
        return P1O1;
    }
    
    public void setP1O1(JLabel P1O1) {
        this.P1O1 = P1O1;
    }
    
    public JLabel getP1O2() {
        return P1O2;
    }
    
    public void setP1O2(JLabel P1O2) {
        this.P1O2 = P1O2;
    }
    
    public JLabel getP1O3() {
        return P1O3;
    }
    
    public void setP1O3(JLabel P1O3) {
        this.P1O3 = P1O3;
    }
    
    public JLabel getP1P1() {
        return P1P1;
    }
    
    public void setP1P1(JLabel P1P1) {
        this.P1P1 = P1P1;
    }
    
    public JLabel getP1P2() {
        return P1P2;
    }
    
    public void setP1P2(JLabel P1P2) {
        this.P1P2 = P1P2;
    }
    
    public JLabel getP1P3() {
        return P1P3;
    }
    
    public void setP1P3(JLabel P1P3) {
        this.P1P3 = P1P3;
    }
    
    public JLabel getP1R1() {
        return P1R1;
    }
    
    public void setP1R1(JLabel P1R1) {
        this.P1R1 = P1R1;
    }
    
    public JLabel getP1R2() {
        return P1R2;
    }
    
    public void setP1R2(JLabel P1R2) {
        this.P1R2 = P1R2;
    }
    
    public JLabel getP1R3() {
        return P1R3;
    }
    
    public void setP1R3(JLabel P1R3) {
        this.P1R3 = P1R3;
    }
    
    public JLabel getP1RA1() {
        return P1RA1;
    }
    
    public void setP1RA1(JLabel P1RA1) {
        this.P1RA1 = P1RA1;
    }
    
    public JLabel getP1RA2() {
        return P1RA2;
    }
    
    public void setP1RA2(JLabel P1RA2) {
        this.P1RA2 = P1RA2;
    }
    
    public JLabel getP1RA3() {
        return P1RA3;
    }
    
    public void setP1RA3(JLabel P1RA3) {
        this.P1RA3 = P1RA3;
    }
    
    public JLabel getP1RA4() {
        return P1RA4;
    }
    
    public void setP1RA4(JLabel P1RA4) {
        this.P1RA4 = P1RA4;
    }
    
    public JLabel getP1U1() {
        return P1U1;
    }
    
    public void setP1U1(JLabel P1U1) {
        this.P1U1 = P1U1;
    }
    
    public JLabel getP1U2() {
        return P1U2;
    }
    
    public void setP1U2(JLabel P1U2) {
        this.P1U2 = P1U2;
    }
    
    public JLabel getP1Y1() {
        return P1Y1;
    }
    
    public void setP1Y1(JLabel P1Y1) {
        this.P1Y1 = P1Y1;
    }
    
    public JLabel getP1Y2() {
        return P1Y2;
    }
    
    public void setP1Y2(JLabel P1Y2) {
        this.P1Y2 = P1Y2;
    }
    
    public JLabel getP1Y3() {
        return P1Y3;
    }
    
    public void setP1Y3(JLabel P1Y3) {
        this.P1Y3 = P1Y3;
    }
    
    public JLabel getP2B1() {
        return P2B1;
    }
    
    public void setP2B1(JLabel P2B1) {
        this.P2B1 = P2B1;
    }
    
    public JLabel getP2B2() {
        return P2B2;
    }
    
    public void setP2B2(JLabel P2B2) {
        this.P2B2 = P2B2;
    }
    
    public JLabel getP2C1() {
        return P2C1;
    }
    
    public void setP2C1(JLabel P2C1) {
        this.P2C1 = P2C1;
    }
    
    public JLabel getP2C2() {
        return P2C2;
    }
    
    public void setP2C2(JLabel P2C2) {
        this.P2C2 = P2C2;
    }
    
    public JLabel getP2C3() {
        return P2C3;
    }
    
    public void setP2C3(JLabel P2C3) {
        this.P2C3 = P2C3;
    }
    
    public JLabel getP2G1() {
        return P2G1;
    }
    
    public void setP2G1(JLabel P2G1) {
        this.P2G1 = P2G1;
    }
    
    public JLabel getP2G2() {
        return P2G2;
    }
    
    public void setP2G2(JLabel P2G2) {
        this.P2G2 = P2G2;
    }
    
    public JLabel getP2G3() {
        return P2G3;
    }
    
    public void setP2G3(JLabel P2G3) {
        this.P2G3 = P2G3;
    }
    
    public JLabel getP2M1() {
        return P2M1;
    }
    
    public void setP2M1(JLabel P2M1) {
        this.P2M1 = P2M1;
    }
    
    public JLabel getP2M2() {
        return P2M2;
    }
    
    public void setP2M2(JLabel P2M2) {
        this.P2M2 = P2M2;
    }
    
    public JLabel getP2O1() {
        return P2O1;
    }
    
    public void setP2O1(JLabel P2O1) {
        this.P2O1 = P2O1;
    }
    
    public JLabel getP2O2() {
        return P2O2;
    }
    
    public void setP2O2(JLabel P2O2) {
        this.P2O2 = P2O2;
    }
    
    public JLabel getP2O3() {
        return P2O3;
    }
    
    public void setP2O3(JLabel P2O3) {
        this.P2O3 = P2O3;
    }
    
    public JLabel getP2P1() {
        return P2P1;
    }
    
    public void setP2P1(JLabel P2P1) {
        this.P2P1 = P2P1;
    }
    
    public JLabel getP2P2() {
        return P2P2;
    }
    
    public void setP2P2(JLabel P2P2) {
        this.P2P2 = P2P2;
    }
    
    public JLabel getP2P3() {
        return P2P3;
    }
    
    public void setP2P3(JLabel P2P3) {
        this.P2P3 = P2P3;
    }
    
    public JLabel getP2R1() {
        return P2R1;
    }
    
    public void setP2R1(JLabel P2R1) {
        this.P2R1 = P2R1;
    }
    
    public JLabel getP2R2() {
        return P2R2;
    }
    
    public void setP2R2(JLabel P2R2) {
        this.P2R2 = P2R2;
    }
    
    public JLabel getP2R3() {
        return P2R3;
    }
    
    public void setP2R3(JLabel P2R3) {
        this.P2R3 = P2R3;
    }
    
    public JLabel getP2RA1() {
        return P2RA1;
    }
    
    public void setP2RA1(JLabel P2RA1) {
        this.P2RA1 = P2RA1;
    }
    
    public JLabel getP2RA2() {
        return P2RA2;
    }
    
    public void setP2RA2(JLabel P2RA2) {
        this.P2RA2 = P2RA2;
    }
    
    public JLabel getP2RA3() {
        return P2RA3;
    }
    
    public void setP2RA3(JLabel P2RA3) {
        this.P2RA3 = P2RA3;
    }
    
    public JLabel getP2RA4() {
        return P2RA4;
    }
    
    public void setP2RA4(JLabel P2RA4) {
        this.P2RA4 = P2RA4;
    }
    
    public JLabel getP2U1() {
        return P2U1;
    }
    
    public void setP2U1(JLabel P2U1) {
        this.P2U1 = P2U1;
    }
    
    public JLabel getP2U2() {
        return P2U2;
    }
    
    public void setP2U2(JLabel P2U2) {
        this.P2U2 = P2U2;
    }
    
    public JLabel getP2Y1() {
        return P2Y1;
    }
    
    public void setP2Y1(JLabel P2Y1) {
        this.P2Y1 = P2Y1;
    }
    
    public JLabel getP2Y2() {
        return P2Y2;
    }
    
    public void setP2Y2(JLabel P2Y2) {
        this.P2Y2 = P2Y2;
    }
    
    public JLabel getP2Y3() {
        return P2Y3;
    }
    
    public void setP2Y3(JLabel P2Y3) {
        this.P2Y3 = P2Y3;
    }
    
    public JLabel getP3B1() {
        return P3B1;
    }
    
    public void setP3B1(JLabel P3B1) {
        this.P3B1 = P3B1;
    }
    
    public JLabel getP3B2() {
        return P3B2;
    }
    
    public void setP3B2(JLabel P3B2) {
        this.P3B2 = P3B2;
    }
    
    public JLabel getP3C1() {
        return P3C1;
    }
    
    public void setP3C1(JLabel P3C1) {
        this.P3C1 = P3C1;
    }
    
    public JLabel getP3C2() {
        return P3C2;
    }
    
    public void setP3C2(JLabel P3C2) {
        this.P3C2 = P3C2;
    }
    
    public JLabel getP3C3() {
        return P3C3;
    }
    
    public void setP3C3(JLabel P3C3) {
        this.P3C3 = P3C3;
    }
    
    public JLabel getP3G1() {
        return P3G1;
    }
    
    public void setP3G1(JLabel P3G1) {
        this.P3G1 = P3G1;
    }
    
    public JLabel getP3G2() {
        return P3G2;
    }
    
    public void setP3G2(JLabel P3G2) {
        this.P3G2 = P3G2;
    }
    
    public JLabel getP3G3() {
        return P3G3;
    }
    
    public void setP3G3(JLabel P3G3) {
        this.P3G3 = P3G3;
    }
    
    public JLabel getP3M1() {
        return P3M1;
    }
    
    public void setP3M1(JLabel P3M1) {
        this.P3M1 = P3M1;
    }
    
    public JLabel getP3M2() {
        return P3M2;
    }
    
    public void setP3M2(JLabel P3M2) {
        this.P3M2 = P3M2;
    }
    
    public JLabel getP3O1() {
        return P3O1;
    }
    
    public void setP3O1(JLabel P3O1) {
        this.P3O1 = P3O1;
    }
    
    public JLabel getP3O2() {
        return P3O2;
    }
    
    public void setP3O2(JLabel P3O2) {
        this.P3O2 = P3O2;
    }
    
    public JLabel getP3O3() {
        return P3O3;
    }
    
    public void setP3O3(JLabel P3O3) {
        this.P3O3 = P3O3;
    }
    
    public JLabel getP3P1() {
        return P3P1;
    }
    
    public void setP3P1(JLabel P3P1) {
        this.P3P1 = P3P1;
    }
    
    public JLabel getP3P2() {
        return P3P2;
    }
    
    public void setP3P2(JLabel P3P2) {
        this.P3P2 = P3P2;
    }
    
    public JLabel getP3P3() {
        return P3P3;
    }
    
    public void setP3P3(JLabel P3P3) {
        this.P3P3 = P3P3;
    }
    
    public JLabel getP3R1() {
        return P3R1;
    }
    
    public void setP3R1(JLabel P3R1) {
        this.P3R1 = P3R1;
    }
    
    public JLabel getP3R2() {
        return P3R2;
    }
    
    public void setP3R2(JLabel P3R2) {
        this.P3R2 = P3R2;
    }
    
    public JLabel getP3R3() {
        return P3R3;
    }
    
    public void setP3R3(JLabel P3R3) {
        this.P3R3 = P3R3;
    }
    
    public JLabel getP3RA1() {
        return P3RA1;
    }
    
    public void setP3RA1(JLabel P3RA1) {
        this.P3RA1 = P3RA1;
    }
    
    public JLabel getP3RA2() {
        return P3RA2;
    }
    
    public void setP3RA2(JLabel P3RA2) {
        this.P3RA2 = P3RA2;
    }
    
    public JLabel getP3RA3() {
        return P3RA3;
    }
    
    public void setP3RA3(JLabel P3RA3) {
        this.P3RA3 = P3RA3;
    }
    
    public JLabel getP3RA4() {
        return P3RA4;
    }
    
    public void setP3RA4(JLabel P3RA4) {
        this.P3RA4 = P3RA4;
    }
    
    public JLabel getP3U1() {
        return P3U1;
    }
    
    public void setP3U1(JLabel P3U1) {
        this.P3U1 = P3U1;
    }
    
    public JLabel getP3U2() {
        return P3U2;
    }
    
    public void setP3U2(JLabel P3U2) {
        this.P3U2 = P3U2;
    }
    
    public JLabel getP3Y1() {
        return P3Y1;
    }
    
    public void setP3Y1(JLabel P3Y1) {
        this.P3Y1 = P3Y1;
    }
    
    public JLabel getP3Y2() {
        return P3Y2;
    }
    
    public void setP3Y2(JLabel P3Y2) {
        this.P3Y2 = P3Y2;
    }
    
    public JLabel getP3Y3() {
        return P3Y3;
    }
    
    public void setP3Y3(JLabel P3Y3) {
        this.P3Y3 = P3Y3;
    }
    
    public JLabel getP4B1() {
        return P4B1;
    }
    
    public void setP4B1(JLabel P4B1) {
        this.P4B1 = P4B1;
    }
    
    public JLabel getP4B2() {
        return P4B2;
    }
    
    public void setP4B2(JLabel P4B2) {
        this.P4B2 = P4B2;
    }
    
    public JLabel getP4C1() {
        return P4C1;
    }
    
    public void setP4C1(JLabel P4C1) {
        this.P4C1 = P4C1;
    }
    
    public JLabel getP4C2() {
        return P4C2;
    }
    
    public void setP4C2(JLabel P4C2) {
        this.P4C2 = P4C2;
    }
    
    public JLabel getP4C3() {
        return P4C3;
    }
    
    public void setP4C3(JLabel P4C3) {
        this.P4C3 = P4C3;
    }
    
    public JLabel getP4G1() {
        return P4G1;
    }
    
    public void setP4G1(JLabel P4G1) {
        this.P4G1 = P4G1;
    }
    
    public JLabel getP4G2() {
        return P4G2;
    }
    
    public void setP4G2(JLabel P4G2) {
        this.P4G2 = P4G2;
    }
    
    public JLabel getP4G3() {
        return P4G3;
    }
    
    public void setP4G3(JLabel P4G3) {
        this.P4G3 = P4G3;
    }
    
    public JLabel getP4M1() {
        return P4M1;
    }
    
    public void setP4M1(JLabel P4M1) {
        this.P4M1 = P4M1;
    }
    
    public JLabel getP4M2() {
        return P4M2;
    }
    
    public void setP4M2(JLabel P4M2) {
        this.P4M2 = P4M2;
    }
    
    public JLabel getP4O1() {
        return P4O1;
    }
    
    public void setP4O1(JLabel P4O1) {
        this.P4O1 = P4O1;
    }
    
    public JLabel getP4O2() {
        return P4O2;
    }
    
    public void setP4O2(JLabel P4O2) {
        this.P4O2 = P4O2;
    }
    
    public JLabel getP4O3() {
        return P4O3;
    }
    
    public void setP4O3(JLabel P4O3) {
        this.P4O3 = P4O3;
    }
    
    public JLabel getP4P1() {
        return P4P1;
    }
    
    public void setP4P1(JLabel P4P1) {
        this.P4P1 = P4P1;
    }
    
    public JLabel getP4P2() {
        return P4P2;
    }
    
    public void setP4P2(JLabel P4P2) {
        this.P4P2 = P4P2;
    }
    
    public JLabel getP4P3() {
        return P4P3;
    }
    
    public void setP4P3(JLabel P4P3) {
        this.P4P3 = P4P3;
    }
    
    public JLabel getP4R1() {
        return P4R1;
    }
    
    public void setP4R1(JLabel P4R1) {
        this.P4R1 = P4R1;
    }
    
    public JLabel getP4R2() {
        return P4R2;
    }
    
    public void setP4R2(JLabel P4R2) {
        this.P4R2 = P4R2;
    }
    
    public JLabel getP4R3() {
        return P4R3;
    }
    
    public void setP4R3(JLabel P4R3) {
        this.P4R3 = P4R3;
    }
    
    public JLabel getP4RA1() {
        return P4RA1;
    }
    
    public void setP4RA1(JLabel P4RA1) {
        this.P4RA1 = P4RA1;
    }
    
    public JLabel getP4RA2() {
        return P4RA2;
    }
    
    public void setP4RA2(JLabel P4RA2) {
        this.P4RA2 = P4RA2;
    }
    
    public JLabel getP4RA3() {
        return P4RA3;
    }
    
    public void setP4RA3(JLabel P4RA3) {
        this.P4RA3 = P4RA3;
    }
    
    public JLabel getP4RA4() {
        return P4RA4;
    }
    
    public void setP4RA4(JLabel P4RA4) {
        this.P4RA4 = P4RA4;
    }
    
    public JLabel getP4U1() {
        return P4U1;
    }
    
    public void setP4U1(JLabel P4U1) {
        this.P4U1 = P4U1;
    }
    
    public JLabel getP4U2() {
        return P4U2;
    }
    
    public void setP4U2(JLabel P4U2) {
        this.P4U2 = P4U2;
    }
    
    public JLabel getP4Y1() {
        return P4Y1;
    }
    
    public void setP4Y1(JLabel P4Y1) {
        this.P4Y1 = P4Y1;
    }
    
    public JLabel getP4Y2() {
        return P4Y2;
    }
    
    public void setP4Y2(JLabel P4Y2) {
        this.P4Y2 = P4Y2;
    }
    
    public JLabel getP4Y3() {
        return P4Y3;
    }
    
    public void setP4Y3(JLabel P4Y3) {
        this.P4Y3 = P4Y3;
    }
    
    public JLabel getP5B1() {
        return P5B1;
    }
    
    public void setP5B1(JLabel P5B1) {
        this.P5B1 = P5B1;
    }
    
    public JLabel getP5B2() {
        return P5B2;
    }
    
    public void setP5B2(JLabel P5B2) {
        this.P5B2 = P5B2;
    }
    
    public JLabel getP5C1() {
        return P5C1;
    }
    
    public void setP5C1(JLabel P5C1) {
        this.P5C1 = P5C1;
    }
    
    public JLabel getP5C2() {
        return P5C2;
    }
    
    public void setP5C2(JLabel P5C2) {
        this.P5C2 = P5C2;
    }
    
    public JLabel getP5C3() {
        return P5C3;
    }
    
    public void setP5C3(JLabel P5C3) {
        this.P5C3 = P5C3;
    }
    
    public JLabel getP5G1() {
        return P5G1;
    }
    
    public void setP5G1(JLabel P5G1) {
        this.P5G1 = P5G1;
    }
    
    public JLabel getP5G2() {
        return P5G2;
    }
    
    public void setP5G2(JLabel P5G2) {
        this.P5G2 = P5G2;
    }
    
    public JLabel getP5G3() {
        return P5G3;
    }
    
    public void setP5G3(JLabel P5G3) {
        this.P5G3 = P5G3;
    }
    
    public JLabel getP5M1() {
        return P5M1;
    }
    
    public void setP5M1(JLabel P5M1) {
        this.P5M1 = P5M1;
    }
    
    public JLabel getP5M2() {
        return P5M2;
    }
    
    public void setP5M2(JLabel P5M2) {
        this.P5M2 = P5M2;
    }
    
    public JLabel getP5O1() {
        return P5O1;
    }
    
    public void setP5O1(JLabel P5O1) {
        this.P5O1 = P5O1;
    }
    
    public JLabel getP5O2() {
        return P5O2;
    }
    
    public void setP5O2(JLabel P5O2) {
        this.P5O2 = P5O2;
    }
    
    public JLabel getP5O3() {
        return P5O3;
    }
    
    public void setP5O3(JLabel P5O3) {
        this.P5O3 = P5O3;
    }
    
    public JLabel getP5P1() {
        return P5P1;
    }
    
    public void setP5P1(JLabel P5P1) {
        this.P5P1 = P5P1;
    }
    
    public JLabel getP5P2() {
        return P5P2;
    }
    
    public void setP5P2(JLabel P5P2) {
        this.P5P2 = P5P2;
    }
    
    public JLabel getP5P3() {
        return P5P3;
    }
    
    public void setP5P3(JLabel P5P3) {
        this.P5P3 = P5P3;
    }
    
    public JLabel getP5R1() {
        return P5R1;
    }
    
    public void setP5R1(JLabel P5R1) {
        this.P5R1 = P5R1;
    }
    
    public JLabel getP5R2() {
        return P5R2;
    }
    
    public void setP5R2(JLabel P5R2) {
        this.P5R2 = P5R2;
    }
    
    public JLabel getP5R3() {
        return P5R3;
    }
    
    public void setP5R3(JLabel P5R3) {
        this.P5R3 = P5R3;
    }
    
    public JLabel getP5RA1() {
        return P5RA1;
    }
    
    public void setP5RA1(JLabel P5RA1) {
        this.P5RA1 = P5RA1;
    }
    
    public JLabel getP5RA2() {
        return P5RA2;
    }
    
    public void setP5RA2(JLabel P5RA2) {
        this.P5RA2 = P5RA2;
    }
    
    public JLabel getP5RA3() {
        return P5RA3;
    }
    
    public void setP5RA3(JLabel P5RA3) {
        this.P5RA3 = P5RA3;
    }
    
    public JLabel getP5RA4() {
        return P5RA4;
    }
    
    public void setP5RA4(JLabel P5RA4) {
        this.P5RA4 = P5RA4;
    }
    
    public JLabel getP5U1() {
        return P5U1;
    }
    
    public void setP5U1(JLabel P5U1) {
        this.P5U1 = P5U1;
    }
    
    public JLabel getP5U2() {
        return P5U2;
    }
    
    public void setP5U2(JLabel P5U2) {
        this.P5U2 = P5U2;
    }
    
    public JLabel getP5Y1() {
        return P5Y1;
    }
    
    public void setP5Y1(JLabel P5Y1) {
        this.P5Y1 = P5Y1;
    }
    
    public JLabel getP5Y2() {
        return P5Y2;
    }
    
    public void setP5Y2(JLabel P5Y2) {
        this.P5Y2 = P5Y2;
    }
    
    public JLabel getP5Y3() {
        return P5Y3;
    }
    
    public void setP5Y3(JLabel P5Y3) {
        this.P5Y3 = P5Y3;
    }
    
    public JLabel getP6B1() {
        return P6B1;
    }
    
    public void setP6B1(JLabel P6B1) {
        this.P6B1 = P6B1;
    }
    
    public JLabel getP6B2() {
        return P6B2;
    }
    
    public void setP6B2(JLabel P6B2) {
        this.P6B2 = P6B2;
    }
    
    public JLabel getP6C1() {
        return P6C1;
    }
    
    public void setP6C1(JLabel P6C1) {
        this.P6C1 = P6C1;
    }
    
    public JLabel getP6C2() {
        return P6C2;
    }
    
    public void setP6C2(JLabel P6C2) {
        this.P6C2 = P6C2;
    }
    
    public JLabel getP6C3() {
        return P6C3;
    }
    
    public void setP6C3(JLabel P6C3) {
        this.P6C3 = P6C3;
    }
    
    public JLabel getP6G1() {
        return P6G1;
    }
    
    public void setP6G1(JLabel P6G1) {
        this.P6G1 = P6G1;
    }
    
    public JLabel getP6G2() {
        return P6G2;
    }
    
    public void setP6G2(JLabel P6G2) {
        this.P6G2 = P6G2;
    }
    
    public JLabel getP6G3() {
        return P6G3;
    }
    
    public void setP6G3(JLabel P6G3) {
        this.P6G3 = P6G3;
    }
    
    public JLabel getP6M1() {
        return P6M1;
    }
    
    public void setP6M1(JLabel P6M1) {
        this.P6M1 = P6M1;
    }
    
    public JLabel getP6M2() {
        return P6M2;
    }
    
    public void setP6M2(JLabel P6M2) {
        this.P6M2 = P6M2;
    }
    
    public JLabel getP6O1() {
        return P6O1;
    }
    
    public void setP6O1(JLabel P6O1) {
        this.P6O1 = P6O1;
    }
    
    public JLabel getP6O2() {
        return P6O2;
    }
    
    public void setP6O2(JLabel P6O2) {
        this.P6O2 = P6O2;
    }
    
    public JLabel getP6O3() {
        return P6O3;
    }
    
    public void setP6O3(JLabel P6O3) {
        this.P6O3 = P6O3;
    }
    
    public JLabel getP6P1() {
        return P6P1;
    }
    
    public void setP6P1(JLabel P6P1) {
        this.P6P1 = P6P1;
    }
    
    public JLabel getP6P2() {
        return P6P2;
    }
    
    public void setP6P2(JLabel P6P2) {
        this.P6P2 = P6P2;
    }
    
    public JLabel getP6P3() {
        return P6P3;
    }
    
    public void setP6P3(JLabel P6P3) {
        this.P6P3 = P6P3;
    }
    
    public JLabel getP6R1() {
        return P6R1;
    }
    
    public void setP6R1(JLabel P6R1) {
        this.P6R1 = P6R1;
    }
    
    public JLabel getP6R2() {
        return P6R2;
    }
    
    public void setP6R2(JLabel P6R2) {
        this.P6R2 = P6R2;
    }
    
    public JLabel getP6R3() {
        return P6R3;
    }
    
    public void setP6R3(JLabel P6R3) {
        this.P6R3 = P6R3;
    }
    
    public JLabel getP6RA1() {
        return P6RA1;
    }
    
    public void setP6RA1(JLabel P6RA1) {
        this.P6RA1 = P6RA1;
    }
    
    public JLabel getP6RA2() {
        return P6RA2;
    }
    
    public void setP6RA2(JLabel P6RA2) {
        this.P6RA2 = P6RA2;
    }
    
    public JLabel getP6RA3() {
        return P6RA3;
    }
    
    public void setP6RA3(JLabel P6RA3) {
        this.P6RA3 = P6RA3;
    }
    
    public JLabel getP6RA4() {
        return P6RA4;
    }
    
    public void setP6RA4(JLabel P6RA4) {
        this.P6RA4 = P6RA4;
    }
    
    public JLabel getP6U1() {
        return P6U1;
    }
    
    public void setP6U1(JLabel P6U1) {
        this.P6U1 = P6U1;
    }
    
    public JLabel getP6U2() {
        return P6U2;
    }
    
    public void setP6U2(JLabel P6U2) {
        this.P6U2 = P6U2;
    }
    
    public JLabel getP6Y1() {
        return P6Y1;
    }
    
    public void setP6Y1(JLabel P6Y1) {
        this.P6Y1 = P6Y1;
    }
    
    public JLabel getP6Y2() {
        return P6Y2;
    }
    
    public void setP6Y2(JLabel P6Y2) {
        this.P6Y2 = P6Y2;
    }
    
    public JLabel getP6Y3() {
        return P6Y3;
    }
    
    public void setP6Y3(JLabel P6Y3) {
        this.P6Y3 = P6Y3;
    }
    
    public JLabel getP7B1() {
        return P7B1;
    }
    
    public void setP7B1(JLabel P7B1) {
        this.P7B1 = P7B1;
    }
    
    public JLabel getP7B2() {
        return P7B2;
    }
    
    public void setP7B2(JLabel P7B2) {
        this.P7B2 = P7B2;
    }
    
    public JLabel getP7C1() {
        return P7C1;
    }
    
    public void setP7C1(JLabel P7C1) {
        this.P7C1 = P7C1;
    }
    
    public JLabel getP7C2() {
        return P7C2;
    }
    
    public void setP7C2(JLabel P7C2) {
        this.P7C2 = P7C2;
    }
    
    public JLabel getP7C3() {
        return P7C3;
    }
    
    public void setP7C3(JLabel P7C3) {
        this.P7C3 = P7C3;
    }
    
    public JLabel getP7G1() {
        return P7G1;
    }
    
    public void setP7G1(JLabel P7G1) {
        this.P7G1 = P7G1;
    }
    
    public JLabel getP7G2() {
        return P7G2;
    }
    
    public void setP7G2(JLabel P7G2) {
        this.P7G2 = P7G2;
    }
    
    public JLabel getP7G3() {
        return P7G3;
    }
    
    public void setP7G3(JLabel P7G3) {
        this.P7G3 = P7G3;
    }
    
    public JLabel getP7M1() {
        return P7M1;
    }
    
    public void setP7M1(JLabel P7M1) {
        this.P7M1 = P7M1;
    }
    
    public JLabel getP7M2() {
        return P7M2;
    }
    
    public void setP7M2(JLabel P7M2) {
        this.P7M2 = P7M2;
    }
    
    public JLabel getP7O1() {
        return P7O1;
    }
    
    public void setP7O1(JLabel P7O1) {
        this.P7O1 = P7O1;
    }
    
    public JLabel getP7O2() {
        return P7O2;
    }
    
    public void setP7O2(JLabel P7O2) {
        this.P7O2 = P7O2;
    }
    
    public JLabel getP7O3() {
        return P7O3;
    }
    
    public void setP7O3(JLabel P7O3) {
        this.P7O3 = P7O3;
    }
    
    public JLabel getP7P1() {
        return P7P1;
    }
    
    public void setP7P1(JLabel P7P1) {
        this.P7P1 = P7P1;
    }
    
    public JLabel getP7P2() {
        return P7P2;
    }
    
    public void setP7P2(JLabel P7P2) {
        this.P7P2 = P7P2;
    }
    
    public JLabel getP7P3() {
        return P7P3;
    }
    
    public void setP7P3(JLabel P7P3) {
        this.P7P3 = P7P3;
    }
    
    public JLabel getP7R1() {
        return P7R1;
    }
    
    public void setP7R1(JLabel P7R1) {
        this.P7R1 = P7R1;
    }
    
    public JLabel getP7R2() {
        return P7R2;
    }
    
    public void setP7R2(JLabel P7R2) {
        this.P7R2 = P7R2;
    }
    
    public JLabel getP7R3() {
        return P7R3;
    }
    
    public void setP7R3(JLabel P7R3) {
        this.P7R3 = P7R3;
    }
    
    public JLabel getP7RA1() {
        return P7RA1;
    }
    
    public void setP7RA1(JLabel P7RA1) {
        this.P7RA1 = P7RA1;
    }
    
    public JLabel getP7RA2() {
        return P7RA2;
    }
    
    public void setP7RA2(JLabel P7RA2) {
        this.P7RA2 = P7RA2;
    }
    
    public JLabel getP7RA3() {
        return P7RA3;
    }
    
    public void setP7RA3(JLabel P7RA3) {
        this.P7RA3 = P7RA3;
    }
    
    public JLabel getP7RA4() {
        return P7RA4;
    }
    
    public void setP7RA4(JLabel P7RA4) {
        this.P7RA4 = P7RA4;
    }
    
    public JLabel getP7U1() {
        return P7U1;
    }
    
    public void setP7U1(JLabel P7U1) {
        this.P7U1 = P7U1;
    }
    
    public JLabel getP7U2() {
        return P7U2;
    }
    
    public void setP7U2(JLabel P7U2) {
        this.P7U2 = P7U2;
    }
    
    public JLabel getP7Y1() {
        return P7Y1;
    }
    
    public void setP7Y1(JLabel P7Y1) {
        this.P7Y1 = P7Y1;
    }
    
    public JLabel getP7Y2() {
        return P7Y2;
    }
    
    public void setP7Y2(JLabel P7Y2) {
        this.P7Y2 = P7Y2;
    }
    
    public JLabel getP7Y3() {
        return P7Y3;
    }
    
    public void setP7Y3(JLabel P7Y3) {
        this.P7Y3 = P7Y3;
    }
    
    public JLabel getP8B1() {
        return P8B1;
    }
    
    public void setP8B1(JLabel P8B1) {
        this.P8B1 = P8B1;
    }
    
    public JLabel getP8B2() {
        return P8B2;
    }
    
    public void setP8B2(JLabel P8B2) {
        this.P8B2 = P8B2;
    }
    
    public JLabel getP8C1() {
        return P8C1;
    }
    
    public void setP8C1(JLabel P8C1) {
        this.P8C1 = P8C1;
    }
    
    public JLabel getP8C2() {
        return P8C2;
    }
    
    public void setP8C2(JLabel P8C2) {
        this.P8C2 = P8C2;
    }
    
    public JLabel getP8C3() {
        return P8C3;
    }
    
    public void setP8C3(JLabel P8C3) {
        this.P8C3 = P8C3;
    }
    
    public JLabel getP8G1() {
        return P8G1;
    }
    
    public void setP8G1(JLabel P8G1) {
        this.P8G1 = P8G1;
    }
    
    public JLabel getP8G2() {
        return P8G2;
    }
    
    public void setP8G2(JLabel P8G2) {
        this.P8G2 = P8G2;
    }
    
    public JLabel getP8G3() {
        return P8G3;
    }
    
    public void setP8G3(JLabel P8G3) {
        this.P8G3 = P8G3;
    }
    
    public JLabel getP8M1() {
        return P8M1;
    }
    
    public void setP8M1(JLabel P8M1) {
        this.P8M1 = P8M1;
    }
    
    public JLabel getP8M2() {
        return P8M2;
    }
    
    public void setP8M2(JLabel P8M2) {
        this.P8M2 = P8M2;
    }
    
    public JLabel getP8O1() {
        return P8O1;
    }
    
    public void setP8O1(JLabel P8O1) {
        this.P8O1 = P8O1;
    }
    
    public JLabel getP8O2() {
        return P8O2;
    }
    
    public void setP8O2(JLabel P8O2) {
        this.P8O2 = P8O2;
    }
    
    public JLabel getP8O3() {
        return P8O3;
    }
    
    public void setP8O3(JLabel P8O3) {
        this.P8O3 = P8O3;
    }
    
    public JLabel getP8P1() {
        return P8P1;
    }
    
    public void setP8P1(JLabel P8P1) {
        this.P8P1 = P8P1;
    }
    
    public JLabel getP8P2() {
        return P8P2;
    }
    
    public void setP8P2(JLabel P8P2) {
        this.P8P2 = P8P2;
    }
    
    public JLabel getP8P3() {
        return P8P3;
    }
    
    public void setP8P3(JLabel P8P3) {
        this.P8P3 = P8P3;
    }
    
    public JLabel getP8R1() {
        return P8R1;
    }
    
    public void setP8R1(JLabel P8R1) {
        this.P8R1 = P8R1;
    }
    
    public JLabel getP8R2() {
        return P8R2;
    }
    
    public void setP8R2(JLabel P8R2) {
        this.P8R2 = P8R2;
    }
    
    public JLabel getP8R3() {
        return P8R3;
    }
    
    public void setP8R3(JLabel P8R3) {
        this.P8R3 = P8R3;
    }
    
    public JLabel getP8RA1() {
        return P8RA1;
    }
    
    public void setP8RA1(JLabel P8RA1) {
        this.P8RA1 = P8RA1;
    }
    
    public JLabel getP8RA2() {
        return P8RA2;
    }
    
    public void setP8RA2(JLabel P8RA2) {
        this.P8RA2 = P8RA2;
    }
    
    public JLabel getP8RA3() {
        return P8RA3;
    }
    
    public void setP8RA3(JLabel P8RA3) {
        this.P8RA3 = P8RA3;
    }
    
    public JLabel getP8RA4() {
        return P8RA4;
    }
    
    public void setP8RA4(JLabel P8RA4) {
        this.P8RA4 = P8RA4;
    }
    
    public JLabel getP8U1() {
        return P8U1;
    }
    
    public void setP8U1(JLabel P8U1) {
        this.P8U1 = P8U1;
    }
    
    public JLabel getP8U2() {
        return P8U2;
    }
    
    public void setP8U2(JLabel P8U2) {
        this.P8U2 = P8U2;
    }
    
    public JLabel getP8Y1() {
        return P8Y1;
    }
    
    public void setP8Y1(JLabel P8Y1) {
        this.P8Y1 = P8Y1;
    }
    
    public JLabel getP8Y2() {
        return P8Y2;
    }
    
    public void setP8Y2(JLabel P8Y2) {
        this.P8Y2 = P8Y2;
    }
    
    public JLabel getP8Y3() {
        return P8Y3;
    }
    
    public void setP8Y3(JLabel P8Y3) {
        this.P8Y3 = P8Y3;
    }
    
    public JPopupMenu getPopupProperties() {
        return PopupProperties;
    }
    
    public void setPopupProperties(JPopupMenu PopupProperties) {
        this.PopupProperties = PopupProperties;
    }
    
    public JButton getbAuctionProperty() {
        return bAuctionProperty;
    }
    
    public void setbAuctionProperty(JButton bAuctionProperty) {
        this.bAuctionProperty = bAuctionProperty;
    }
    
    public JButton getbBankrupt() {
        return bBankrupt;
    }
    
    public void setbBankrupt(JButton bBankrupt) {
        this.bBankrupt = bBankrupt;
    }
    
    public JButton getbBuyProperty() {
        return bBuyProperty;
    }
    
    public void setbBuyProperty(JButton bBuyProperty) {
        this.bBuyProperty = bBuyProperty;
    }
    
    public JButton getbDice() {
        return bDice;
    }
    
    public void setbDice(JButton bDice) {
        this.bDice = bDice;
    }
    
    public JButton getbNextTurn() {
        return bNextTurn;
    }
    
    public void setbNextTurn(JButton bNextTurn) {
        this.bNextTurn = bNextTurn;
    }
    
    public JButton getbPayRent() {
        return bPayRent;
    }
    
    public void setbPayRent(JButton bPayRent) {
        this.bPayRent = bPayRent;
    }
    
    public JButton getbPropertyHandle() {
        return bPropertyHandle;
    }
    
    public void setbPropertyHandle(JButton bPropertyHandle) {
        this.bPropertyHandle = bPropertyHandle;
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
    
    public JPanel getDicePane() {
        return dicePane;
    }
    
    public void setDicePane(JPanel dicePane) {
        this.dicePane = dicePane;
    }
    
    public JButton getjButton1() {
        return jButton1;
    }
    
    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }
    
    public JLabel getjLabel1() {
        return jLabel1;
    }
    
    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }
    
    public JLabel getjLabel11() {
        return jLabel11;
    }
    
    public void setjLabel11(JLabel jLabel11) {
        this.jLabel11 = jLabel11;
    }
    
    public JLabel getjLabel2() {
        return jLabel2;
    }
    
    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }
    
    public JLabel getjLabel3() {
        return jLabel3;
    }
    
    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }
    
    public JLabel getjLabel4() {
        return jLabel4;
    }
    
    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }
    
    public JLabel getjLabel5() {
        return jLabel5;
    }
    
    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }
    
    public JLabel getjLabel6() {
        return jLabel6;
    }
    
    public void setjLabel6(JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }
    
    public JLabel getjLabel7() {
        return jLabel7;
    }
    
    public void setjLabel7(JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }
    
    public JLabel getjLabel8() {
        return jLabel8;
    }
    
    public void setjLabel8(JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }
    
    public JLabel getlCell() {
        return lCell;
    }
    
    public void setlCell(JLabel lCell) {
        this.lCell = lCell;
    }
    
    public JLabel getlDice1() {
        return lDice1;
    }
    
    public void setlDice1(JLabel lDice1) {
        this.lDice1 = lDice1;
    }
    
    public JLabel getlDice2() {
        return lDice2;
    }
    
    public void setlDice2(JLabel lDice2) {
        this.lDice2 = lDice2;
    }
    
    public JLabel getlHotelPrice() {
        return lHotelPrice;
    }
    
    public void setlHotelPrice(JLabel lHotelPrice) {
        this.lHotelPrice = lHotelPrice;
    }
    
    public JLabel getlHousePrice() {
        return lHousePrice;
    }
    
    public void setlHousePrice(JLabel lHousePrice) {
        this.lHousePrice = lHousePrice;
    }
    
    public JLabel getlMoney() {
        return lMoney;
    }
    
    public void setlMoney(JLabel lMoney) {
        this.lMoney = lMoney;
    }
    
    public JLabel getlPlayer1() {
        return lPlayer1;
    }
    
    public void setlPlayer1(JLabel lPlayer1) {
        this.lPlayer1 = lPlayer1;
    }
    
    public JLabel getlPlayer2() {
        return lPlayer2;
    }
    
    public void setlPlayer2(JLabel lPlayer2) {
        this.lPlayer2 = lPlayer2;
    }
    
    public JLabel getlPlayer3() {
        return lPlayer3;
    }
    
    public void setlPlayer3(JLabel lPlayer3) {
        this.lPlayer3 = lPlayer3;
    }
    
    public JLabel getlPlayer4() {
        return lPlayer4;
    }
    
    public void setlPlayer4(JLabel lPlayer4) {
        this.lPlayer4 = lPlayer4;
    }
    
    public JLabel getlPlayer5() {
        return lPlayer5;
    }
    
    public void setlPlayer5(JLabel lPlayer5) {
        this.lPlayer5 = lPlayer5;
    }
    
    public JLabel getlPlayer6() {
        return lPlayer6;
    }
    
    public void setlPlayer6(JLabel lPlayer6) {
        this.lPlayer6 = lPlayer6;
    }
    
    public JLabel getlPlayer7() {
        return lPlayer7;
    }
    
    public void setlPlayer7(JLabel lPlayer7) {
        this.lPlayer7 = lPlayer7;
    }
    
    public JLabel getlPlayer8() {
        return lPlayer8;
    }
    
    public void setlPlayer8(JLabel lPlayer8) {
        this.lPlayer8 = lPlayer8;
    }
    
    public JLabel getlPlayerOnTurn() {
        return lPlayerOnTurn;
    }
    
    public void setlPlayerOnTurn(JLabel lPlayerOnTurn) {
        this.lPlayerOnTurn = lPlayerOnTurn;
    }
    
    public JLabel getlPlayers() {
        return lPlayers;
    }
    
    public void setlPlayers(JLabel lPlayers) {
        this.lPlayers = lPlayers;
    }
    
    public JLabel getlProperties() {
        return lProperties;
    }
    
    public void setlProperties(JLabel lProperties) {
        this.lProperties = lProperties;
    }
    
    public JLabel getlRentToPay() {
        return lRentToPay;
    }
    
    public void setlRentToPay(JLabel lRentToPay) {
        this.lRentToPay = lRentToPay;
    }
    
    public JLabel getlTotalDice() {
        return lTotalDice;
    }
    
    public void setlTotalDice(JLabel lTotalDice) {
        this.lTotalDice = lTotalDice;
    }
    
    public JPanel getPlayerPane() {
        return playerPane;
    }
    
    public void setPlayerPane(JPanel playerPane) {
        this.playerPane = playerPane;
    }
    
    
//</editor-fold>
    //GEETER SETTER
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemHotels;
    private javax.swing.JMenuItem MenuItemHouses;
    private javax.swing.JMenuItem MenuItemSell;
    private javax.swing.JMenuItem MenuItemSellHotels;
    private javax.swing.JMenuItem MenuItemSellHouses;
    private javax.swing.JLabel P1B1;
    private javax.swing.JLabel P1B2;
    private javax.swing.JLabel P1C1;
    private javax.swing.JLabel P1C2;
    private javax.swing.JLabel P1C3;
    private javax.swing.JLabel P1G1;
    private javax.swing.JLabel P1G2;
    private javax.swing.JLabel P1G3;
    private javax.swing.JLabel P1M1;
    private javax.swing.JLabel P1M2;
    private javax.swing.JLabel P1O1;
    private javax.swing.JLabel P1O2;
    private javax.swing.JLabel P1O3;
    private javax.swing.JLabel P1P1;
    private javax.swing.JLabel P1P2;
    private javax.swing.JLabel P1P3;
    private javax.swing.JLabel P1R1;
    private javax.swing.JLabel P1R2;
    private javax.swing.JLabel P1R3;
    private javax.swing.JLabel P1RA1;
    private javax.swing.JLabel P1RA2;
    private javax.swing.JLabel P1RA3;
    private javax.swing.JLabel P1RA4;
    private javax.swing.JLabel P1U1;
    private javax.swing.JLabel P1U2;
    private javax.swing.JLabel P1Y1;
    private javax.swing.JLabel P1Y2;
    private javax.swing.JLabel P1Y3;
    private javax.swing.JLabel P2B1;
    private javax.swing.JLabel P2B2;
    private javax.swing.JLabel P2C1;
    private javax.swing.JLabel P2C2;
    private javax.swing.JLabel P2C3;
    private javax.swing.JLabel P2G1;
    private javax.swing.JLabel P2G2;
    private javax.swing.JLabel P2G3;
    private javax.swing.JLabel P2M1;
    private javax.swing.JLabel P2M2;
    private javax.swing.JLabel P2O1;
    private javax.swing.JLabel P2O2;
    private javax.swing.JLabel P2O3;
    private javax.swing.JLabel P2P1;
    private javax.swing.JLabel P2P2;
    private javax.swing.JLabel P2P3;
    private javax.swing.JLabel P2R1;
    private javax.swing.JLabel P2R2;
    private javax.swing.JLabel P2R3;
    private javax.swing.JLabel P2RA1;
    private javax.swing.JLabel P2RA2;
    private javax.swing.JLabel P2RA3;
    private javax.swing.JLabel P2RA4;
    private javax.swing.JLabel P2U1;
    private javax.swing.JLabel P2U2;
    private javax.swing.JLabel P2Y1;
    private javax.swing.JLabel P2Y2;
    private javax.swing.JLabel P2Y3;
    private javax.swing.JLabel P3B1;
    private javax.swing.JLabel P3B2;
    private javax.swing.JLabel P3C1;
    private javax.swing.JLabel P3C2;
    private javax.swing.JLabel P3C3;
    private javax.swing.JLabel P3G1;
    private javax.swing.JLabel P3G2;
    private javax.swing.JLabel P3G3;
    private javax.swing.JLabel P3M1;
    private javax.swing.JLabel P3M2;
    private javax.swing.JLabel P3O1;
    private javax.swing.JLabel P3O2;
    private javax.swing.JLabel P3O3;
    private javax.swing.JLabel P3P1;
    private javax.swing.JLabel P3P2;
    private javax.swing.JLabel P3P3;
    private javax.swing.JLabel P3R1;
    private javax.swing.JLabel P3R2;
    private javax.swing.JLabel P3R3;
    private javax.swing.JLabel P3RA1;
    private javax.swing.JLabel P3RA2;
    private javax.swing.JLabel P3RA3;
    private javax.swing.JLabel P3RA4;
    private javax.swing.JLabel P3U1;
    private javax.swing.JLabel P3U2;
    private javax.swing.JLabel P3Y1;
    private javax.swing.JLabel P3Y2;
    private javax.swing.JLabel P3Y3;
    private javax.swing.JLabel P4B1;
    private javax.swing.JLabel P4B2;
    private javax.swing.JLabel P4C1;
    private javax.swing.JLabel P4C2;
    private javax.swing.JLabel P4C3;
    private javax.swing.JLabel P4G1;
    private javax.swing.JLabel P4G2;
    private javax.swing.JLabel P4G3;
    private javax.swing.JLabel P4M1;
    private javax.swing.JLabel P4M2;
    private javax.swing.JLabel P4O1;
    private javax.swing.JLabel P4O2;
    private javax.swing.JLabel P4O3;
    private javax.swing.JLabel P4P1;
    private javax.swing.JLabel P4P2;
    private javax.swing.JLabel P4P3;
    private javax.swing.JLabel P4R1;
    private javax.swing.JLabel P4R2;
    private javax.swing.JLabel P4R3;
    private javax.swing.JLabel P4RA1;
    private javax.swing.JLabel P4RA2;
    private javax.swing.JLabel P4RA3;
    private javax.swing.JLabel P4RA4;
    private javax.swing.JLabel P4U1;
    private javax.swing.JLabel P4U2;
    private javax.swing.JLabel P4Y1;
    private javax.swing.JLabel P4Y2;
    private javax.swing.JLabel P4Y3;
    private javax.swing.JLabel P5B1;
    private javax.swing.JLabel P5B2;
    private javax.swing.JLabel P5C1;
    private javax.swing.JLabel P5C2;
    private javax.swing.JLabel P5C3;
    private javax.swing.JLabel P5G1;
    private javax.swing.JLabel P5G2;
    private javax.swing.JLabel P5G3;
    private javax.swing.JLabel P5M1;
    private javax.swing.JLabel P5M2;
    private javax.swing.JLabel P5O1;
    private javax.swing.JLabel P5O2;
    private javax.swing.JLabel P5O3;
    private javax.swing.JLabel P5P1;
    private javax.swing.JLabel P5P2;
    private javax.swing.JLabel P5P3;
    private javax.swing.JLabel P5R1;
    private javax.swing.JLabel P5R2;
    private javax.swing.JLabel P5R3;
    private javax.swing.JLabel P5RA1;
    private javax.swing.JLabel P5RA2;
    private javax.swing.JLabel P5RA3;
    private javax.swing.JLabel P5RA4;
    private javax.swing.JLabel P5U1;
    private javax.swing.JLabel P5U2;
    private javax.swing.JLabel P5Y1;
    private javax.swing.JLabel P5Y2;
    private javax.swing.JLabel P5Y3;
    private javax.swing.JLabel P6B1;
    private javax.swing.JLabel P6B2;
    private javax.swing.JLabel P6C1;
    private javax.swing.JLabel P6C2;
    private javax.swing.JLabel P6C3;
    private javax.swing.JLabel P6G1;
    private javax.swing.JLabel P6G2;
    private javax.swing.JLabel P6G3;
    private javax.swing.JLabel P6M1;
    private javax.swing.JLabel P6M2;
    private javax.swing.JLabel P6O1;
    private javax.swing.JLabel P6O2;
    private javax.swing.JLabel P6O3;
    private javax.swing.JLabel P6P1;
    private javax.swing.JLabel P6P2;
    private javax.swing.JLabel P6P3;
    private javax.swing.JLabel P6R1;
    private javax.swing.JLabel P6R2;
    private javax.swing.JLabel P6R3;
    private javax.swing.JLabel P6RA1;
    private javax.swing.JLabel P6RA2;
    private javax.swing.JLabel P6RA3;
    private javax.swing.JLabel P6RA4;
    private javax.swing.JLabel P6U1;
    private javax.swing.JLabel P6U2;
    private javax.swing.JLabel P6Y1;
    private javax.swing.JLabel P6Y2;
    private javax.swing.JLabel P6Y3;
    private javax.swing.JLabel P7B1;
    private javax.swing.JLabel P7B2;
    private javax.swing.JLabel P7C1;
    private javax.swing.JLabel P7C2;
    private javax.swing.JLabel P7C3;
    private javax.swing.JLabel P7G1;
    private javax.swing.JLabel P7G2;
    private javax.swing.JLabel P7G3;
    private javax.swing.JLabel P7M1;
    private javax.swing.JLabel P7M2;
    private javax.swing.JLabel P7O1;
    private javax.swing.JLabel P7O2;
    private javax.swing.JLabel P7O3;
    private javax.swing.JLabel P7P1;
    private javax.swing.JLabel P7P2;
    private javax.swing.JLabel P7P3;
    private javax.swing.JLabel P7R1;
    private javax.swing.JLabel P7R2;
    private javax.swing.JLabel P7R3;
    private javax.swing.JLabel P7RA1;
    private javax.swing.JLabel P7RA2;
    private javax.swing.JLabel P7RA3;
    private javax.swing.JLabel P7RA4;
    private javax.swing.JLabel P7U1;
    private javax.swing.JLabel P7U2;
    private javax.swing.JLabel P7Y1;
    private javax.swing.JLabel P7Y2;
    private javax.swing.JLabel P7Y3;
    private javax.swing.JLabel P8B1;
    private javax.swing.JLabel P8B2;
    private javax.swing.JLabel P8C1;
    private javax.swing.JLabel P8C2;
    private javax.swing.JLabel P8C3;
    private javax.swing.JLabel P8G1;
    private javax.swing.JLabel P8G2;
    private javax.swing.JLabel P8G3;
    private javax.swing.JLabel P8M1;
    private javax.swing.JLabel P8M2;
    private javax.swing.JLabel P8O1;
    private javax.swing.JLabel P8O2;
    private javax.swing.JLabel P8O3;
    private javax.swing.JLabel P8P1;
    private javax.swing.JLabel P8P2;
    private javax.swing.JLabel P8P3;
    private javax.swing.JLabel P8R1;
    private javax.swing.JLabel P8R2;
    private javax.swing.JLabel P8R3;
    private javax.swing.JLabel P8RA1;
    private javax.swing.JLabel P8RA2;
    private javax.swing.JLabel P8RA3;
    private javax.swing.JLabel P8RA4;
    private javax.swing.JLabel P8U1;
    private javax.swing.JLabel P8U2;
    private javax.swing.JLabel P8Y1;
    private javax.swing.JLabel P8Y2;
    private javax.swing.JLabel P8Y3;
    private javax.swing.JPopupMenu PopupProperties;
    private javax.swing.JButton bAuctionProperty;
    private javax.swing.JButton bBankrupt;
    private javax.swing.JButton bBuyProperty;
    private javax.swing.JButton bDice;
    private javax.swing.JButton bNextTurn;
    private javax.swing.JButton bPayRent;
    private javax.swing.JButton bPropertyHandle;
    private java.awt.Canvas canvas;
    private javax.swing.JPanel dicePane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lCell;
    private javax.swing.JLabel lDice1;
    private javax.swing.JLabel lDice2;
    private javax.swing.JLabel lHotelPrice;
    private javax.swing.JLabel lHousePrice;
    private javax.swing.JLabel lMoney;
    private javax.swing.JLabel lPlayer1;
    private javax.swing.JLabel lPlayer2;
    private javax.swing.JLabel lPlayer3;
    private javax.swing.JLabel lPlayer4;
    private javax.swing.JLabel lPlayer5;
    private javax.swing.JLabel lPlayer6;
    private javax.swing.JLabel lPlayer7;
    private javax.swing.JLabel lPlayer8;
    private javax.swing.JLabel lPlayerOnTurn;
    private javax.swing.JLabel lPlayers;
    private javax.swing.JLabel lProperties;
    private javax.swing.JLabel lRentToPay;
    private javax.swing.JLabel lTotalDice;
    private javax.swing.JPanel playerPane;
    // End of variables declaration//GEN-END:variables
}
