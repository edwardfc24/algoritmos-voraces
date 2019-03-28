/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import logic.*;

/**
 *
 * @author AlgoritmicaAvanzada
 *
 * Instructions: 1. To view node information right click over the node. 2. To
 * move a node, left click over the node and then another left click on the new
 * position.
 *
 */
public class MainWindow extends javax.swing.JFrame implements MouseListener {

    private final Graphe _graphe = new Graphe();
    private Node moveNode = null;
    private int id = 0;

    private int iter; //iterator to search equals numbers in kruskal
    private boolean correct; //boolean to use when are equals numbers

    public MainWindow() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.addMouseListener(this);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);

        for (int i = 0; i < _graphe.getNodes().size(); i++) {
            Node node = (Node) _graphe.getNodes().get(i);
            node.Paint(g);
        }
        for (int i = 0; i < _graphe.getConnections().size(); i++) {
            Connection connection = (Connection) _graphe.getConnections().get(i);
            connection.Paint(g);
        }

        if (_graphe.getMinRouteTree().size() > 0) {
            this.removeAll();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);

            for (int i = 0; i < _graphe.getNodes().size(); i++) {
                Node node = (Node) _graphe.getNodes().get(i);
                node.Paint(g);
            }
            for (Object con : _graphe.getConnections()) {
                for (Object object : _graphe.getMinRouteTree()) {
                    if (con == object) {
                        g.setColor(Color.BLUE);

                        Connection connection = (Connection) con;
                        connection.Paint(g);
                        repaint();
                    } else {
                        g.setColor(Color.BLACK);

                        Connection connection = (Connection) con;
                        connection.Paint(g);
                        repaint();
                    }
                }
            }
            btnAddConnection.repaint();
            btnAddNode.repaint();
            menuItemKruskal.repaint();
        }

//        if (_graphe.getMinRouteTree().size() > 0) {
//
//            for (int i = 0; i < _graphe.getMinRouteTree().size(); i++) {
//                for (int j = 0; j < _graphe.getConnections().size(); j++) {
//                    if (_graphe.getMinRouteTree().get(i).equals(_graphe.getConnections().get(j))) {
//                        g.setColor(Color.WHITE);
//                        g.fillRect(0, 0, getWidth(), getHeight());
//                        g.setColor(Color.BLUE);
//                        Connection connection = (Connection) _graphe.getMinRouteTree().get(i);
//                        connection.Paint(g);
//                    } else {
//                        g.setColor(Color.WHITE);
//                        g.fillRect(0, 0, getWidth(), getHeight());
//                        g.setColor(Color.BLACK);
//
//                        Connection connection = (Connection) _graphe.getConnections().get(j);
//                        connection.Paint(g);
//                    }
//                }
//            }
//            this.repaint();
//            this.validate();
//        }
//        if (_graphe.getMinRouteTree().size() > 0) {
//            for (int i = 0; i < _graphe.getConnections().size(); i++) {
//                correct = false;
//                iter = 0;
//
//                while ((!correct) && (iter < _graphe.getConnections().size())) {
//                    if (_graphe.getConnections().get(iter).equals(_graphe.getMinRouteTree().get(i))) {
//                        correct = true;
//                        iter++;
//                    }
//                }
//
//                if (correct) {
//                    g.setColor(Color.WHITE);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                    g.setColor(Color.BLUE);
//                    Connection connection = (Connection) _graphe.getConnections().get(i);
//                    connection.Paint(g);
//                } else {
//                    g.setColor(Color.WHITE);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//                    g.setColor(Color.BLACK);
//                    Connection connection = (Connection) _graphe.getConnections().get(i);
//                    connection.Paint(g);
//                }
//            }
//            this.repaint();
//            this.validate();
//        }
        btnAddConnection.repaint();
        btnAddNode.repaint();
        menuItemKruskal.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddNode = new javax.swing.JButton();
        btnAddConnection = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menuItemKruskal = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kruskal");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 550));

        btnAddNode.setBackground(new java.awt.Color(255, 255, 255));
        btnAddNode.setText("Add Node");
        btnAddNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNodeActionPerformed(evt);
            }
        });

        btnAddConnection.setText("Add Connection");
        btnAddConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddConnectionActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Methods");

        menuItemKruskal.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        menuItemKruskal.setText("Kruskal");
        menuItemKruskal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemKruskalActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemKruskal);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(btnAddNode)
                .addGap(45, 45, 45)
                .addComponent(btnAddConnection)
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNode)
                    .addComponent(btnAddConnection))
                .addContainerGap(521, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNodeActionPerformed

        Node newNode = new Node();
        newNode.setId(id);
        id++;
        newNode.setContent(JOptionPane.showInputDialog("Insert graphe content"));
        newNode.setX(Integer.parseInt(JOptionPane.showInputDialog("X valor")));
        newNode.setY(Integer.parseInt(JOptionPane.showInputDialog("Y valor")) + 50);
        _graphe.insertNode(newNode);
        repaint();

    }//GEN-LAST:event_btnAddNodeActionPerformed

    private void btnAddConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddConnectionActionPerformed
        try {
            int fromId = Integer.parseInt(JOptionPane.showInputDialog("From (insert id)"));
            int toId = Integer.parseInt(JOptionPane.showInputDialog("To (insert id)"));
            int weight = Integer.parseInt(JOptionPane.showInputDialog("Insert connection weight"));

            String result = this._graphe.insertConnection(fromId, toId, weight);
            if (!result.equals("ok")) {
                JOptionPane.showMessageDialog(this, result);
            }
            repaint();
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(rootPane, "Data type error:\n-Empty values are not accepted."
                    + "\n-The weight field must be an integer number.\n"
                    + "-The start field must be an integer number\n"
                    + "-The finish field must be an integer number\nERROR:\n" + error.getMessage());
        } catch (Exception error) {
            JOptionPane.showMessageDialog(rootPane, error.getMessage());
        }

    }//GEN-LAST:event_btnAddConnectionActionPerformed

    private void menuItemKruskalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemKruskalActionPerformed
        this._graphe.insertMinRouteTree();

    }//GEN-LAST:event_menuItemKruskalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new MainWindow().setVisible(true);
                MainWindow w = new MainWindow();

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddConnection;
    private javax.swing.JButton btnAddNode;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItemKruskal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (moveNode == null) {
                for (int i = 0; i < _graphe.getNodes().size(); i++) {
                    Node node = (Node) _graphe.getNodes().get(i);
                    if (new Rectangle(node.getX() - Node.d / 2, node.getY() - Node.d / 2, Node.d, Node.d).contains(e.getPoint())) {
                        moveNode = node;
                    }
                }
            } else {
                moveNode.setX((int) e.getPoint().getX());
                moveNode.setY((int) e.getPoint().getY());
                _graphe.getNodes().replace(moveNode.getId(), moveNode);
                moveNode = null;
                repaint();
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) { //Right click over node to show info about the Node
            for (int i = 0; i < _graphe.getNodes().size(); i++) {
                Node node = (Node) _graphe.getNodes().get(i);

                if (new Rectangle(node.getX() - Node.d / 2, node.getY() - Node.d / 2, Node.d, Node.d).contains(e.getPoint())) {
                    JOptionPane.showMessageDialog(this, node.toString());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
