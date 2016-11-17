package managers;

//SE INICIA TRABAJO DE CARDS

import estructures.Lista;
import estructures.Nodo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import estructures.linkedList.Card;
import estructures.linkedList.Chance;
import estructures.linkedList.Chest;
import estructures.linkedList.Player;
import estructures.linkedList.Railroad;
import estructures.linkedList.Tax;
import estructures.linkedList.Utility;
import frames.Game;
import machines.Avenue;
import machines.Corner;

public class Master {

    private final String boardTableRoute;
    private final String chanceCardRoute;
    private final String communityChestCardRoute;

    private Lista table;
    private Lista players;
    private Lista rail;
    private Lista utility;
    private Lista CommunityChestCard;
    private Lista ChanceCard;

    private int dice1, dice2;
    
    public Game g;

    public Master() {
        /*String startingRoute = "./resources/txts/;*/
        boardTableRoute = "./resources/txts/Table.txt";
        chanceCardRoute = "./resources/txts/ChanceCards.txt";
        communityChestCardRoute = "./resources/txts/CommunityChestCards.txt";

        rail = new Lista();
        utility = new Lista();
        table = new Lista();
        players = new Lista();
        CommunityChestCard = new Lista();
        ChanceCard = new Lista();

        start();
    }

    //SET UP CODE
    private void start() {
        if (fileCheck(boardTableRoute, boardTableRoute, boardTableRoute)) {
            createCommunityChestList();
            createChanceList();
            createTable();
        }

    }

    private void createTable() {
        try {

            FileReader fr = new FileReader(boardTableRoute);
            BufferedReader br = new BufferedReader(fr);

            table.add(new Corner("Go", 1, 200));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                int number = Integer.valueOf(fields[0]);
                String name = fields[1];

                //Number|Name|Color|Price|Rent|1 House|2 Houses|3 Houses|4 Houses|Hotel|Mortgage|Houses Value|Hotels Value|
                if (name.contains("Avenue") || name.contains("Place") || name.contains("Gardens") || name.contains("walk")) {
                    table.add(new Avenue(line));
                } else if (name.contains("Tax")) {
                    table.add(new Tax(number, Integer.valueOf(fields[2])));
                } else if (number == 11 || number == 21 || number == 31) {
                    table.add(new Corner(fields[1], number));
                } else if (name.contains("Railroad") || name.contains("Line")) {
                    Railroad r = new Railroad(fields[1], number);
                    Railroad rCopy = new Railroad(fields[1], number);
                    table.add(r);
                    rail.add(rCopy);
                } else if (name.contains("Works") || name.contains("Company")) {
                    Utility u = new Utility(name, number);
                    Utility uCopy = new Utility(name, number);
                    table.add(u);
                    utility.add(uCopy);
                } else if (name.contains("Chance")) {
                    table.add(new Chance(number));
                } else if (name.contains("Chest")) {
                    table.add(new Chest(number));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCommunityChestList() {
        try {
            FileReader fr = new FileReader(new File(communityChestCardRoute));
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                //System.out.println(fields[1]);
                CommunityChestCard.add(new Card(Integer.valueOf(fields[0]), "CommunityChest", fields[1], fields[2], fields[3], Integer.valueOf(fields[4]), Integer.valueOf(fields[5]),this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createChanceList() {
        try {
            FileReader fr = new FileReader(new File(chanceCardRoute));
            BufferedReader br = new BufferedReader(fr);

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                //System.out.println(fields[1]);
                ChanceCard.add(new Card(Integer.valueOf(fields[0]), "Chance", fields[1], fields[2], fields[3], Integer.valueOf(fields[4]), Integer.valueOf(fields[5]),this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean fileCheck(String route1, String route2, String route3) {
        try {
            File f1 = new File(route1);
            File f2 = new File(route2);
            File f3 = new File(route3);
            if (f1.exists() && f2.exists() && f3.exists()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Data Base was corrupted!");
        }
        return false;
    }

    //GAME CODE
    public int throwDice() {
        Random r = new Random();
        dice1 = r.nextInt(6) + 1;
        dice2 = r.nextInt(6) + 1;
        return dice1 + dice2;
    }

    public void venta(Nodo propiedad, Player vendedor, Player comprador, int valorDeCompra) {
        if (propiedad.getNombre().equals("Avenue")) {
            Avenue a = (Avenue) propiedad;
            a.setOwner(comprador);
        } else if (propiedad.getNombre().equals("Railroad")) {
            Railroad r = (Railroad) propiedad;
            r.setOwner(comprador);
        } else if (propiedad.getNombre().equals("Utility")) {
            Utility u = (Utility) propiedad;
            u.setOwner(comprador);
        }
        vendedor.setMoney(vendedor.getMoney() + valorDeCompra);
        comprador.setMoney(comprador.getMoney() - valorDeCompra);
    }

    public boolean compra(Nodo propiedad, Player comprador, int valorDeCompra) {
        int finalMoney = comprador.getMoney() - valorDeCompra;
        if (finalMoney > 0) {
            if (propiedad.getNombre().equals("Avenue")) {
                Avenue a = (Avenue) propiedad;
                a.setOwner(comprador);
            } else if (propiedad.getNombre().equals("Railroad")) {
                Railroad r = (Railroad) propiedad;
                r.setOwner(comprador);
                Railroad rCopy = (Railroad) rail.BuscarConId(propiedad.getId());
                rCopy.setOwner(comprador);
            } else if (propiedad.getNombre().equals("Utility")) {
                Utility u = (Utility) propiedad;
                u.setOwner(comprador);
                Utility uCopy = (Utility) utility.BuscarConId(propiedad.getId());
                uCopy.setOwner(comprador);
                enableUtilityRentPrice(comprador);
            }
            comprador.setMoney(finalMoney);
            return true;
        } else {
            System.out.println("dinero insuficiente!");
        }
        return false;
    }

    //PLAYER CODE
    //AVENUE CODE
    public int getAvenueNumber(Nodo q) {
        Avenue avenue = (Avenue) q;
        String color = avenue.getAvenueColor();

        int cont = 0;
        Nodo p = table.getCourrent();
        while (p != q) {
            p = table.next();
        }

        for (int i = 0; i < 4; i++) {
            table.next();
            p = table.getCourrent();
            try {
                Avenue P = (Avenue) p;
                if (P.getAvenueColor().equals(avenue.getAvenueColor())) {
                    cont++;
                } else {
                    break;
                }
            } catch (Exception e) {
                //System.out.println("Works!");
            }
        }

        if (avenue.getAvenueColor().equals("Brown") || avenue.getAvenueColor().equals("Dark Blue")) {
            switch (cont) {
                case 1:
                    return 1;
                case 0:
                    return 2;
                default:
                    break;
            }
        } else {
            switch (cont) {
                case 2:
                    return 1;
                case 1:
                    return 2;
                case 0:
                    return 3;
                default:
                    break;
            }
        }

        return 0;
    }

    //CARD CODE
    public Card drawRandomCard(int Chance1Chest2) {
        Random r = new Random();
        if (Chance1Chest2 == 1) {
            int x = r.nextInt(ChanceCard.length()) ;//+ 1;
            return (Card) ChanceCard.BuscarConId(x);
        } else {
            int x = r.nextInt(CommunityChestCard.length()) ;//+ 1;
            return (Card) CommunityChestCard.BuscarConId(x);
        }
    }

    //RAIL CODE
    public int getRailNumber(Nodo q) {
        int number = q.getId();
        if (number == 6) {
            return 1;
        } else if (number == 16) {
            return 2;
        } else if (number == 26) {
            return 3;
        } else if (number == 36) {
            return 4;
        }
        return 0;
    }

    public int getRailRentPrice(Player owner) { //added..
        int cont = 0;
        Railroad PTR = (Railroad) rail.getCourrent();
        Railroad p = PTR;
        do {
            if (p.getOwner() == owner) {
                cont++;
            }
            rail.next();
            p = (Railroad) rail.getCourrent();
        } while (p != PTR);

        if (cont == 1) {
            return PTR.getRentPrice();
        } else if (cont == 2) {
            return PTR.getRent2Railroad();
        } else if (cont == 3) {
            return PTR.getRent3Railroad();
        } else if (cont == 4) {
            return PTR.getRent4Railroad();
        } else {
            System.out.println("Error");
        }
        return 0;
    }

    //UTILITY CODE
    public int getUtilityNumber(Nodo q) {
        int number = q.getId();
        if (number == 13) {
            return 1;
        } else if (number == 29) {
            return 2;
        }
        return 0;
    }

    public void enableUtilityRentPrice(Player owner) { //added... its 'works' really good for more than two utilities...
        String vision = "";
        Utility PTR = (Utility) utility.getCourrent();
        Utility p = PTR;
        do {
            if (p.getOwner() != null) {
                if (p.getOwner() == owner) {
                    vision = vision + "1-";
                } else {
                    vision = vision + "0-";
                }
            } else {
                vision = vision + "0-";
            }
            utility.next();
            p = (Utility) utility.getCourrent();
        } while (p != PTR);

        int cont = 0;
        String[] fields = vision.split("\\-");
        for (String s : fields) {
            if (s.equals("1")) {
                cont++;
            }
        }

        Utility pCopy;
        for (String s : fields) {
            p = (Utility) utility.getCourrent();
            pCopy = (Utility) table.BuscarConId(p.getId());
            if (cont == 1) {
                if (s.equals("1")) {
                    p.setRentDicex4(true);
                    p.setRentDicex10(false);
                    pCopy.setRentDicex4(true);
                    pCopy.setRentDicex10(false);
                }
            } else if (cont == 2) {
                if (s.equals("1")) {
                    p.setRentDicex4(false);
                    p.setRentDicex10(true);
                    pCopy.setRentDicex4(false);
                    pCopy.setRentDicex10(true);
                }
            }
            utility.next();
        }
    }

    //GETTERS SETTERS
    public Lista getPlayers() {
        return players;
    }

    public Lista getTable() {
        return table;
    }

    public Lista getRail() {
        return rail;
    }

    public void setRail(Lista rail) {
        this.rail = rail;
    }

    public Lista getUtility() {
        return utility;
    }

    public void setUtility(Lista utility) {
        this.utility = utility;
    }

    public Lista getCommunityChestCard() {
        return CommunityChestCard;
    }

    public void setCommunityChestCard(Lista CommunityChestCard) {
        this.CommunityChestCard = CommunityChestCard;
    }

    public Lista getChanceCard() {
        return ChanceCard;
    }

    public void setChanceCard(Lista ChanceCard) {
        this.ChanceCard = ChanceCard;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

}
