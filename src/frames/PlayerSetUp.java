package frames;

import estructures.linkedList.Player;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import managers.Master;

public class PlayerSetUp extends javax.swing.JFrame {

    private int numberOfPlayers;
    private int cont;
    private Master m;

    private boolean keySleep;

    public PlayerSetUp() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        keySleep = false;
        m = new Master();
        cont = 1;
        enableComponents(pCreate, false);
    }

    private void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pNumber = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tNumberOfPlayers = new javax.swing.JTextField();
        bAcept = new javax.swing.JButton();
        pCreate = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lPlayerNumber = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bAcept1 = new javax.swing.JButton();
        tName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(296, 221));

        jLabel1.setText("Jugadores:");

        tNumberOfPlayers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNumberOfPlayersKeyTyped(evt);
            }
        });

        bAcept.setText("Aceptar");
        bAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pNumberLayout = new javax.swing.GroupLayout(pNumber);
        pNumber.setLayout(pNumberLayout);
        pNumberLayout.setHorizontalGroup(
            pNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pNumberLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bAcept)
                    .addGroup(pNumberLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tNumberOfPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pNumberLayout.setVerticalGroup(
            pNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNumberLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pNumberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tNumberOfPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAcept)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Numero:");

        lPlayerNumber.setText("0");

        jLabel3.setText("Nombre:");

        bAcept1.setText("Aceptar");
        bAcept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAcept1ActionPerformed(evt);
            }
        });

        tName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNameActionPerformed(evt);
            }
        });
        tName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tNameKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pCreateLayout = new javax.swing.GroupLayout(pCreate);
        pCreate.setLayout(pCreateLayout);
        pCreateLayout.setHorizontalGroup(
            pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCreateLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bAcept1)
                    .addGroup(pCreateLayout.createSequentialGroup()
                        .addGroup(pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lPlayerNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(tName))))
                .addContainerGap())
        );
        pCreateLayout.setVerticalGroup(
            pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCreateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lPlayerNumber))
                .addGap(18, 18, 18)
                .addGroup(pCreateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(tName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAcept1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tNumberOfPlayersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNumberOfPlayersKeyTyped
        char enter = evt.getKeyChar();
        evt.consume();
        if (Character.isDigit(enter)) {
            String number = enter + "";
            tNumberOfPlayers.setText(number);
        }
    }//GEN-LAST:event_tNumberOfPlayersKeyTyped

    private void bAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptActionPerformed
        try {
            numberOfPlayers = Integer.valueOf(tNumberOfPlayers.getText());
        } catch (Exception e) {
            numberOfPlayers = 0;
        }
        if (numberOfPlayers < 9 && numberOfPlayers >= 2) {
            lPlayerNumber.setText("1");
            enableComponents(pNumber, false);
            enableComponents(pCreate, true);
        } else {
            JOptionPane.showMessageDialog(null, "El Monopoly se juega de 2 a 8 personas. Ingrese correctamente cuantas personas van a jugar.");
        }
    }//GEN-LAST:event_bAceptActionPerformed

    private void bAcept1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAcept1ActionPerformed
        String name = tName.getText();
        if (name.isEmpty()) {
            name = "Player " + cont;
        }
        m.getPlayers().add(new Player(cont, name));
        cont++;
        if (cont <= numberOfPlayers) {
            tName.setText("");
            lPlayerNumber.setText(Integer.toString(cont));
        } else {
            Player j = (Player) m.getPlayers().getCourrent();
            System.out.println("PLAYER " + j.getPlayerName());
            Game g = new Game(m);
            m.g = g;
            this.dispose();
        }
    }//GEN-LAST:event_bAcept1ActionPerformed

    private void tNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNameActionPerformed

    private void tNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNameKeyTyped
        char enter = evt.getKeyChar();
        evt.consume();
        if (!keySleep) {
            if (tName.getText().length() < 10) {
                String number = tName.getText() + enter + "";
                tName.setText(number);
            }
        } else {
            keySleep = false;
        }
    }//GEN-LAST:event_tNameKeyTyped

    private void tNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
            //tName.setText(tName.getText().substring(0, tName.getText().length() - 1)); //When backspace is released is goind to erase twice... 
            keySleep = true;
        }
    }//GEN-LAST:event_tNameKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAcept;
    private javax.swing.JButton bAcept1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lPlayerNumber;
    private javax.swing.JPanel pCreate;
    private javax.swing.JPanel pNumber;
    private javax.swing.JTextField tName;
    private javax.swing.JTextField tNumberOfPlayers;
    // End of variables declaration//GEN-END:variables
}
