/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SushiOrderingSystem;

import data.DBOperations;
import model.CheckoutUser;
import data.UserDataAccessObject;
import model.ItemOrdered;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author HP
 */
public class Home extends javax.swing.JFrame {

    private MouseAdapter hoverEffect;
    private ActionListener paymentMethodListener, scrollbutton;
    private CellRendererPane addCustomImageRenderer;

    /**
     * Creates new form Home
     */
    private double total = 0.0;
    private int x = 0;

    public Home() {
        initComponents();
        addActionListeners();
        setTime();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
    }

    public Home(String query, String userName, String userEmail, String userPhoneNumber) {
        initComponents();
        addActionListeners();

        lbluserName.setText(userName);
        txtEmail.setText(userEmail);
        txtPhoneNumber.setText(userPhoneNumber);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {

    }

    private void addActionListeners() {
        paymentMethodListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                String selectedPaymentMethod = null;

                if (source == radCOD) {
                    if (radCOD.isSelected()) {
                        selectedPaymentMethod = "Cash on Delivery (COD)";
                    }
                } else if (source == comCreditorDebit) {
                    selectedPaymentMethod = comCreditorDebit.getSelectedItem().toString();
                } else if (source == comEwallet) {
                    selectedPaymentMethod = comEwallet.getSelectedItem().toString();
                }

                if (selectedPaymentMethod != null) {
                    lblpayment.setText(selectedPaymentMethod);
                }
            }
        };
        scrollbutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JScrollPane scrollPane = (JScrollPane) getContentPane().getComponent(0);
                JViewport viewport = scrollPane.getViewport();
                Point scrollTo = new Point(0, 1700);
                viewport.setViewPosition(scrollTo);
            }
        };
        hoverEffect = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                evt.getComponent().setBackground(new Color(254, 143, 157));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                evt.getComponent().setBackground(new Color(254, 69, 91));
            }
        };
        btnsashimi1.addMouseListener(hoverEffect);
        btnsashimi2.addMouseListener(hoverEffect);
        btnsashimi3.addMouseListener(hoverEffect);
        btnsashimi4.addMouseListener(hoverEffect);
        btnsashimi5.addMouseListener(hoverEffect);
        btnsashimi6.addMouseListener(hoverEffect);
        btnnigiri1.addMouseListener(hoverEffect);
        btnnigiri2.addMouseListener(hoverEffect);
        btnnigiri3.addMouseListener(hoverEffect);
        btnnigiri4.addMouseListener(hoverEffect);
        btnnigiri5.addMouseListener(hoverEffect);
        btnnigiri6.addMouseListener(hoverEffect);
        btnroll1.addMouseListener(hoverEffect);
        btnroll2.addMouseListener(hoverEffect);
        btnroll3.addMouseListener(hoverEffect);
        btnroll4.addMouseListener(hoverEffect);
        btnroll5.addMouseListener(hoverEffect);
        btnroll6.addMouseListener(hoverEffect);
        btnplatter1.addMouseListener(hoverEffect);
        btnplatter2.addMouseListener(hoverEffect);
        btnplatter3.addMouseListener(hoverEffect);
        btnOrderNow.addMouseListener(hoverEffect);
        btnNext.addMouseListener(hoverEffect);
        btnNext2.addMouseListener(hoverEffect);
        btnNext3.addMouseListener(hoverEffect);
        btnAddItem.addMouseListener(hoverEffect);
        btnCheckout.addMouseListener(hoverEffect);
        btnClear.addMouseListener(hoverEffect);
        btnDelete.addMouseListener(hoverEffect);
        btnReceipt.addMouseListener(hoverEffect);
        btnExit.addMouseListener(hoverEffect);
        btnCheckout.addActionListener(scrollbutton);
        radCOD.addActionListener(paymentMethodListener);
        comCreditorDebit.addActionListener(paymentMethodListener);
        comEwallet.addActionListener(paymentMethodListener);
    }

    public void clear() {
        spnsashimi1.setValue(0);
        spnsashimi2.setValue(0);
        spnsashimi3.setValue(0);
        spnsashimi4.setValue(0);
        spnsashimi5.setValue(0);
        spnsashimi6.setValue(0);
        spnnigiri1.setValue(0);
        spnnigiri2.setValue(0);
        spnnigiri3.setValue(0);
        spnnigiri4.setValue(0);
        spnnigiri5.setValue(0);
        spnnigiri6.setValue(0);
        spnroll1.setValue(0);
        spnroll2.setValue(0);
        spnroll3.setValue(0);
        spnroll4.setValue(0);
        spnroll5.setValue(0);
        spnroll6.setValue(0);
        spnplatter1.setValue(0);
        spnplatter2.setValue(0);
        spnplatter3.setValue(0);
        txtArea1.setText("");
        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        tblModel.setRowCount(0);
        txtSubtotal.setText("0.0");
        txtTotal.setText("0.0");
        x = 0;
        total = 0.0;
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPostalCode.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtPhoneNumber.setText("");
        txtOrderId.setText("");
        txtTotalCheckout.setText("");
        comCountry.setSelectedIndex(0);
        comRegion.setSelectedIndex(0);
        comCityProvinces.setSelectedIndex(0);
        comCreditorDebit.setSelectedIndex(0);
        comEwallet.setSelectedIndex(0);
        txtSubtotalCheckout.setText("");
        lblpayment.setText("");
        txtAreaItems.setText("");
    }

    public void Cart() {
        txtArea1.setText("\n*********************************Sushi Lover********************************\n"
                + "\t                      SushiLover.com\n"
                + "\t     123 Rizal Street, Barangay Balanga\n"
                + "\t               Pasig City, Metro Manila\n"
                + "\t                    Philippines, 1600\n"
                + "                 Time: " + txtTime.getText() + "                 Date: " + txtDate.getText() + "\n"
                + "******************************************************************************\n"
                + "  Item Name:\t\t\t" + "              Cost:\n");
    }

    public boolean qtyisZero(int qty) {
        if (qty == 0) {
            JOptionPane.showMessageDialog(null, "Please increase the item quantity");
            return false;
        }
        return true;
    }

    public void setTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-YYYY");
                    String time = tf.format(date);
                    txtTime.setText(time.split(" ")[0] + " " + time.split(" ")[1]);
                    txtDate.setText(df.format(date));
                }
            }
        }).start();
    }

    public void dudate() {
        txtSubtotal.setText(String.valueOf(total));
        txtTotal.setText(String.valueOf(total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel169 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        lbluserName = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        btnOrderNow = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnsashimi1 = new javax.swing.JButton();
        btnsashimi2 = new javax.swing.JButton();
        btnsashimi3 = new javax.swing.JButton();
        btnsashimi4 = new javax.swing.JButton();
        btnsashimi5 = new javax.swing.JButton();
        btnsashimi6 = new javax.swing.JButton();
        spnsashimi1 = new javax.swing.JSpinner();
        spnsashimi2 = new javax.swing.JSpinner();
        spnsashimi3 = new javax.swing.JSpinner();
        spnsashimi4 = new javax.swing.JSpinner();
        spnsashimi5 = new javax.swing.JSpinner();
        spnsashimi6 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        btnnigiri3 = new javax.swing.JButton();
        spnnigiri3 = new javax.swing.JSpinner();
        btnnigiri4 = new javax.swing.JButton();
        spnnigiri4 = new javax.swing.JSpinner();
        btnnigiri5 = new javax.swing.JButton();
        spnnigiri5 = new javax.swing.JSpinner();
        btnnigiri1 = new javax.swing.JButton();
        spnnigiri1 = new javax.swing.JSpinner();
        btnnigiri2 = new javax.swing.JButton();
        spnnigiri2 = new javax.swing.JSpinner();
        btnnigiri6 = new javax.swing.JButton();
        spnnigiri6 = new javax.swing.JSpinner();
        jLabel141 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        btnNext2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        btnroll2 = new javax.swing.JButton();
        spnroll2 = new javax.swing.JSpinner();
        btnroll3 = new javax.swing.JButton();
        spnroll3 = new javax.swing.JSpinner();
        btnroll5 = new javax.swing.JButton();
        spnroll5 = new javax.swing.JSpinner();
        btnroll6 = new javax.swing.JButton();
        spnroll6 = new javax.swing.JSpinner();
        btnroll1 = new javax.swing.JButton();
        spnroll1 = new javax.swing.JSpinner();
        btnroll4 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        spnroll4 = new javax.swing.JSpinner();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        btnNext3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        btnplatter1 = new javax.swing.JButton();
        spnplatter1 = new javax.swing.JSpinner();
        btnplatter2 = new javax.swing.JButton();
        spnplatter2 = new javax.swing.JSpinner();
        btnplatter3 = new javax.swing.JButton();
        spnplatter3 = new javax.swing.JSpinner();
        jLabel109 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txtTax1 = new javax.swing.JTextField();
        txtTotal1 = new javax.swing.JTextField();
        txtSubtotal1 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        javax.swing.JButton btnPlacingOrder = new javax.swing.JButton();
        jLabel154 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JTextField();
        txtTotalCheckout = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        Address = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        comCountry = new javax.swing.JComboBox<>();
        comRegion = new javax.swing.JComboBox<>();
        txtPostalCode = new javax.swing.JTextField();
        comCityProvinces = new javax.swing.JComboBox<>();
        txtAddress = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel155 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        comCreditorDebit = new javax.swing.JComboBox<>();
        comEwallet = new javax.swing.JComboBox<>();
        radCOD = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAreaItems = new javax.swing.JTextArea();
        jLabel162 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        txtShippingFee = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtSubtotalCheckout = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        lblpayment = new javax.swing.JLabel();
        txtShippingAmount = new javax.swing.JTextField();
        jLabel163 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnAddItem = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        btnReceipt = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel172 = new javax.swing.JLabel();
        jLabel173 = new javax.swing.JLabel();
        jLabel174 = new javax.swing.JLabel();
        jLabel175 = new javax.swing.JLabel();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        txtTime = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 1700));
        setUndecorated(true);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                formAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1366, 768));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jScrollPane1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jScrollPane1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(1366, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 1700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 51, 51));
        jPanel5.setForeground(java.awt.Color.white);
        jPanel5.setMinimumSize(new java.awt.Dimension(1366, 100));
        jPanel5.setPreferredSize(new java.awt.Dimension(1366, 100));
        jPanel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel5AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Sashimi");
        jLabel5.setAlignmentY(0.0F);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 31, -1, 30));

        jLabel2.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("Lover");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 70, 60));

        jLabel6.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Nigiri");
        jLabel6.setAlignmentY(0.0F);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 31, -1, 30));

        jLabel7.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Rolls");
        jLabel7.setAlignmentY(0.0F);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 31, -1, 30));

        jLabel8.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("Platter");
        jLabel8.setAlignmentY(0.0F);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 31, -1, 30));

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/redheartlogo.png"))); // NOI18N
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 100, 90));

        jLabel70.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel70.setForeground(java.awt.Color.white);
        jLabel70.setText("Sushi");
        jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel70MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 60, 60));

        jLabel169.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popupicon/usericon.png"))); // NOI18N
        jLabel169.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel169MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel169, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 30, 30, 30));

        jLabel72.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        jLabel72.setForeground(java.awt.Color.white);
        jLabel72.setText("Checkout");
        jLabel72.setAlignmentY(0.0F);
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 31, -1, 30));

        lbluserName.setFont(new java.awt.Font("Franklin Gothic Book", 0, 16)); // NOI18N
        lbluserName.setForeground(java.awt.Color.white);
        lbluserName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel5.add(lbluserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 30, 170, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 90));

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(1366, 1700));
        jTabbedPane1.setName(""); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1366, 1720));

        jPanel3.setBackground(new java.awt.Color(21, 19, 19));
        jPanel3.setMinimumSize(new java.awt.Dimension(1366, 1450));
        jPanel3.setPreferredSize(new java.awt.Dimension(1366, 1600));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel73.setFont(new java.awt.Font("Freestyle Script", 0, 120)); // NOI18N
        jLabel73.setForeground(java.awt.Color.white);
        jLabel73.setText("About us!");
        jPanel3.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 910, -1, -1));

        jLabel76.setFont(new java.awt.Font("Freestyle Script", 0, 120)); // NOI18N
        jLabel76.setForeground(java.awt.Color.white);
        jLabel76.setText("Your Sushi Destination!");
        jPanel3.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, -1, -1));

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/backgroundfront.png"))); // NOI18N
        jPanel3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -22, -1, 770));

        jLabel71.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel71.setForeground(java.awt.Color.white);
        jLabel71.setText("\"Sushi Lover started with a simple idea: to bring the joy");
        jPanel3.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1060, -1, -1));

        jLabel77.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel77.setForeground(java.awt.Color.white);
        jLabel77.setText(" of sushi to everyone. Our team of experienced chefs and");
        jPanel3.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 1090, -1, -1));

        jLabel78.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel78.setForeground(java.awt.Color.white);
        jLabel78.setText("best sushi every time.  We're passionate about creating ");
        jPanel3.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1150, -1, -1));

        jLabel79.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel79.setForeground(java.awt.Color.white);
        jLabel79.setText(" dedicated staff work together to ensure you receive the");
        jPanel3.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 1120, -1, -1));

        btnOrderNow.setBackground(new java.awt.Color(254, 69, 91));
        btnOrderNow.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnOrderNow.setForeground(java.awt.Color.white);
        btnOrderNow.setText("Order Now!");
        btnOrderNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderNowActionPerformed(evt);
            }
        });
        jPanel3.add(btnOrderNow, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 1250, -1, 30));

        jLabel80.setFont(new java.awt.Font("Palatino Linotype", 1, 24)); // NOI18N
        jLabel80.setForeground(java.awt.Color.white);
        jLabel80.setText("memorable dining experiences for sushi lovers.\"");
        jPanel3.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 1180, -1, -1));

        jLabel126.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(173, 166, 166));
        jLabel126.setText("© 2024 Sushi Lover. All rights reserved.");
        jPanel3.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1560, -1, -1));

        jLabel130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconyt.png"))); // NOI18N
        jPanel3.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1560, -1, -1));

        jLabel127.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(173, 166, 166));
        jLabel127.setText("Follow us on:");
        jPanel3.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1560, -1, -1));

        jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconig.png"))); // NOI18N
        jPanel3.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1560, -1, -1));

        jLabel131.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconx.png"))); // NOI18N
        jPanel3.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 1560, -1, -1));

        jLabel128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconfb.png"))); // NOI18N
        jPanel3.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1560, -1, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/background2.png"))); // NOI18N
        jPanel3.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 750, 1360, 870));

        jTabbedPane1.addTab("Home", jPanel3);

        jPanel6.setBackground(new java.awt.Color(21, 19, 19));
        jPanel6.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel17.setForeground(java.awt.Color.white);
        jLabel17.setText("Salmon Sashimi");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1150, -1, -1));

        jLabel18.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel18.setForeground(java.awt.Color.white);
        jLabel18.setText("Php. 299.00");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1170, -1, -1));

        jLabel19.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel19.setForeground(java.awt.Color.white);
        jLabel19.setText("Maguro (Bluefin Tuna)");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1150, -1, -1));

        jLabel20.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel20.setForeground(java.awt.Color.white);
        jLabel20.setText("Php. 279.00");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1170, -1, -1));

        jLabel21.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel21.setForeground(java.awt.Color.white);
        jLabel21.setText("Katsuo (Skipjack Tuna)");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1150, -1, -1));

        jLabel22.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel22.setForeground(java.awt.Color.white);
        jLabel22.setText("Php. 289.00");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1170, -1, -1));

        jLabel23.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel23.setForeground(java.awt.Color.white);
        jLabel23.setText("Hotate (Scallops)");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1480, -1, -1));

        jLabel24.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel24.setForeground(java.awt.Color.white);
        jLabel24.setText("Php. 289.00");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1500, -1, -1));

        jLabel25.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel25.setForeground(java.awt.Color.white);
        jLabel25.setText("Ika Sashimi (Squid)");
        jPanel6.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1480, -1, -1));

        jLabel26.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel26.setForeground(java.awt.Color.white);
        jLabel26.setText("Php. 279.00");
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1500, -1, -1));

        jLabel27.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel27.setForeground(java.awt.Color.white);
        jLabel27.setText("Ahi (Yellowfin Tuna)");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1480, -1, -1));

        jLabel28.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel28.setForeground(java.awt.Color.white);
        jLabel28.setText("Php. 269.00");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1500, -1, 20));

        btnsashimi1.setBackground(new java.awt.Color(254, 69, 91));
        btnsashimi1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnsashimi1.setForeground(java.awt.Color.white);
        btnsashimi1.setText("Order");
        btnsashimi1.setBorderPainted(false);
        btnsashimi1.setPreferredSize(new java.awt.Dimension(70, 25));
        btnsashimi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsashimi1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsashimi1MouseExited(evt);
            }
        });
        btnsashimi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsashimi1ActionPerformed(evt);
            }
        });
        btnsashimi1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btnsashimi1PropertyChange(evt);
            }
        });
        jPanel6.add(btnsashimi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1150, 70, 30));

        btnsashimi2.setBackground(new java.awt.Color(254, 69, 91));
        btnsashimi2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnsashimi2.setForeground(java.awt.Color.white);
        btnsashimi2.setText("Order");
        btnsashimi2.setBorderPainted(false);
        btnsashimi2.setPreferredSize(new java.awt.Dimension(65, 25));
        btnsashimi2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnsashimi2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnsashimi2MouseExited(evt);
            }
        });
        btnsashimi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsashimi2ActionPerformed(evt);
            }
        });
        btnsashimi2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                btnsashimi2PropertyChange(evt);
            }
        });
        jPanel6.add(btnsashimi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1150, 70, 30));

        btnsashimi3.setBackground(new java.awt.Color(254, 69, 91));
        btnsashimi3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnsashimi3.setForeground(java.awt.Color.white);
        btnsashimi3.setText("Order");
        btnsashimi3.setBorderPainted(false);
        btnsashimi3.setPreferredSize(new java.awt.Dimension(65, 25));
        btnsashimi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsashimi3ActionPerformed(evt);
            }
        });
        jPanel6.add(btnsashimi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1150, 70, 30));

        btnsashimi4.setBackground(new java.awt.Color(254, 69, 91));
        btnsashimi4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnsashimi4.setForeground(java.awt.Color.white);
        btnsashimi4.setText("Order");
        btnsashimi4.setBorderPainted(false);
        btnsashimi4.setPreferredSize(new java.awt.Dimension(65, 25));
        btnsashimi4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsashimi4ActionPerformed(evt);
            }
        });
        jPanel6.add(btnsashimi4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1480, 70, 30));

        btnsashimi5.setBackground(new java.awt.Color(254, 69, 91));
        btnsashimi5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnsashimi5.setForeground(java.awt.Color.white);
        btnsashimi5.setText("Order");
        btnsashimi5.setBorderPainted(false);
        btnsashimi5.setPreferredSize(new java.awt.Dimension(65, 25));
        btnsashimi5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsashimi5ActionPerformed(evt);
            }
        });
        jPanel6.add(btnsashimi5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1480, 70, 30));

        btnsashimi6.setBackground(new java.awt.Color(254, 69, 91));
        btnsashimi6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnsashimi6.setForeground(java.awt.Color.white);
        btnsashimi6.setText("Order");
        btnsashimi6.setBorderPainted(false);
        btnsashimi6.setPreferredSize(new java.awt.Dimension(65, 25));
        btnsashimi6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsashimi6ActionPerformed(evt);
            }
        });
        jPanel6.add(btnsashimi6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1480, 70, 30));

        spnsashimi1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnsashimi1.setVerifyInputWhenFocusTarget(false);
        spnsashimi1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnsashimi1MouseClicked(evt);
            }
        });
        jPanel6.add(spnsashimi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1180, 70, 25));

        spnsashimi2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jPanel6.add(spnsashimi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1180, 70, 25));

        spnsashimi3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jPanel6.add(spnsashimi3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1180, 70, 25));

        spnsashimi4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jPanel6.add(spnsashimi4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1510, 70, 25));

        spnsashimi5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jPanel6.add(spnsashimi5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1510, 70, 25));

        spnsashimi6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jPanel6.add(spnsashimi6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1510, 70, 25));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/sashimi1.png"))); // NOI18N
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 930, 300, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/sashimi2.png"))); // NOI18N
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 930, 300, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/sashimi3.png"))); // NOI18N
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 940, 300, 190));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/sashimi4.png"))); // NOI18N
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1260, 300, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/sashimi5.png"))); // NOI18N
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1270, 300, 190));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/sashimi6.png"))); // NOI18N
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1270, 300, 190));

        jLabel133.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconfb.png"))); // NOI18N
        jPanel6.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1560, -1, -1));

        jLabel135.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconx.png"))); // NOI18N
        jPanel6.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 1560, -1, -1));

        jLabel134.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconig.png"))); // NOI18N
        jPanel6.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1560, -1, -1));

        jLabel137.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(173, 166, 166));
        jLabel137.setText("© 2024 Sushi Lover. All rights reserved.");
        jPanel6.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1560, -1, -1));

        jLabel132.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(173, 166, 166));
        jLabel132.setText("Follow us on:");
        jPanel6.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1560, -1, -1));

        jLabel147.setFont(new java.awt.Font("Freestyle Script", 0, 80)); // NOI18N
        jLabel147.setForeground(java.awt.Color.white);
        jLabel147.setText("\"Pure, Fresh, and Delicate.\"");
        jPanel6.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 650, -1));

        jLabel136.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconyt.png"))); // NOI18N
        jPanel6.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1560, -1, -1));

        jLabel148.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel148.setForeground(java.awt.Color.white);
        jLabel148.setText("textures of each cut. Perfect for sushi connoisseurs and those who appreciate simplicity and elegance in their sushi.\"");
        jPanel6.add(jLabel148, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 740, -1, -1));

        jLabel149.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel149.setForeground(java.awt.Color.white);
        jLabel149.setText("\"Indulge in our sashimi selection, featuring the freshest slices of premium-grade fish.");
        jPanel6.add(jLabel149, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 680, -1, -1));

        jLabel151.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel151.setForeground(java.awt.Color.white);
        jLabel151.setText("Our sashimi is prepared with precision and served in its purest form, showcasing the natural flavors and");
        jPanel6.add(jLabel151, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 710, -1, -1));

        btnNext.setBackground(new java.awt.Color(254, 69, 91));
        btnNext.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnNext.setForeground(java.awt.Color.white);
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel6.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 890, -1, 30));

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/backgroundsashimi.jpg"))); // NOI18N
        jPanel6.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPane1.addTab("Sashimi", jPanel6);

        jPanel7.setBackground(new java.awt.Color(21, 19, 19));
        jPanel7.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigiri1.png"))); // NOI18N
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 930, 300, 200));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigiri2.png"))); // NOI18N
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 930, 300, 200));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigiri3.png"))); // NOI18N
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 930, 300, 200));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigiri4.png"))); // NOI18N
        jPanel7.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1260, 300, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigiri5.png"))); // NOI18N
        jPanel7.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1260, 300, 200));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigiri6.png"))); // NOI18N
        jPanel7.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1260, 300, 200));

        jLabel32.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel32.setForeground(java.awt.Color.white);
        jLabel32.setText("Php. 209.00");
        jPanel7.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1170, -1, -1));

        jLabel33.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel33.setForeground(java.awt.Color.white);
        jLabel33.setText("Ebi Nigiri (Shrimp)");
        jPanel7.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1150, -1, -1));

        jLabel34.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel34.setForeground(java.awt.Color.white);
        jLabel34.setText("Php. 199.00");
        jPanel7.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1170, -1, -1));

        jLabel35.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel35.setForeground(java.awt.Color.white);
        jLabel35.setText("Tai Nigiri (Red Snapper)");
        jPanel7.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1150, -1, -1));

        jLabel36.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel36.setForeground(java.awt.Color.white);
        jLabel36.setText("Anago Nigiri (Conger Eel)");
        jPanel7.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1150, -1, -1));

        jLabel37.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel37.setForeground(java.awt.Color.white);
        jLabel37.setText("Php. 219.00");
        jPanel7.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1170, -1, -1));

        jLabel38.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel38.setForeground(java.awt.Color.white);
        jLabel38.setText("Aji (Horse Mackerel)");
        jPanel7.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1480, -1, -1));

        jLabel39.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel39.setForeground(java.awt.Color.white);
        jLabel39.setText("Php. 229.00");
        jPanel7.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1500, -1, -1));

        jLabel40.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel40.setForeground(java.awt.Color.white);
        jLabel40.setText("Hamachi (Yellowtail)");
        jPanel7.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1480, -1, -1));

        jLabel41.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel41.setForeground(java.awt.Color.white);
        jLabel41.setText("Php. 209.00");
        jPanel7.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1500, -1, -1));

        jLabel42.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel42.setForeground(java.awt.Color.white);
        jLabel42.setText("Salmon Nigiri");
        jPanel7.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 1480, -1, -1));

        jLabel43.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel43.setForeground(java.awt.Color.white);
        jLabel43.setText("Php. 239.00");
        jPanel7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 1500, -1, -1));

        btnnigiri3.setBackground(new java.awt.Color(254, 69, 91));
        btnnigiri3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnnigiri3.setForeground(java.awt.Color.white);
        btnnigiri3.setText("Order");
        btnnigiri3.setBorderPainted(false);
        btnnigiri3.setPreferredSize(new java.awt.Dimension(65, 25));
        btnnigiri3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnigiri3ActionPerformed(evt);
            }
        });
        jPanel7.add(btnnigiri3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1150, 70, 30));

        spnnigiri3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnnigiri3.setBorder(null);
        spnnigiri3.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel7.add(spnnigiri3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1180, 70, 25));

        btnnigiri4.setBackground(new java.awt.Color(254, 69, 91));
        btnnigiri4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnnigiri4.setForeground(java.awt.Color.white);
        btnnigiri4.setText("Order");
        btnnigiri4.setBorderPainted(false);
        btnnigiri4.setPreferredSize(new java.awt.Dimension(65, 25));
        btnnigiri4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnigiri4ActionPerformed(evt);
            }
        });
        jPanel7.add(btnnigiri4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1480, 70, 30));

        spnnigiri4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnnigiri4.setBorder(null);
        spnnigiri4.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel7.add(spnnigiri4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1510, 70, 25));

        btnnigiri5.setBackground(new java.awt.Color(254, 69, 91));
        btnnigiri5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnnigiri5.setForeground(java.awt.Color.white);
        btnnigiri5.setText("Order");
        btnnigiri5.setBorderPainted(false);
        btnnigiri5.setPreferredSize(new java.awt.Dimension(65, 25));
        btnnigiri5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnigiri5ActionPerformed(evt);
            }
        });
        jPanel7.add(btnnigiri5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1480, 70, 30));

        spnnigiri5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnnigiri5.setBorder(null);
        spnnigiri5.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel7.add(spnnigiri5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1510, 70, 25));

        btnnigiri1.setBackground(new java.awt.Color(254, 69, 91));
        btnnigiri1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnnigiri1.setForeground(java.awt.Color.white);
        btnnigiri1.setText("Order");
        btnnigiri1.setBorderPainted(false);
        btnnigiri1.setPreferredSize(new java.awt.Dimension(65, 25));
        btnnigiri1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnigiri1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnnigiri1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1150, 70, 30));

        spnnigiri1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnnigiri1.setBorder(null);
        spnnigiri1.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel7.add(spnnigiri1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1180, 70, 25));

        btnnigiri2.setBackground(new java.awt.Color(254, 69, 91));
        btnnigiri2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnnigiri2.setForeground(java.awt.Color.white);
        btnnigiri2.setText("Order");
        btnnigiri2.setBorderPainted(false);
        btnnigiri2.setPreferredSize(new java.awt.Dimension(65, 25));
        btnnigiri2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnigiri2ActionPerformed(evt);
            }
        });
        jPanel7.add(btnnigiri2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1150, 70, 30));

        spnnigiri2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnnigiri2.setBorder(null);
        spnnigiri2.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel7.add(spnnigiri2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1180, 70, 25));

        btnnigiri6.setBackground(new java.awt.Color(254, 69, 91));
        btnnigiri6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnnigiri6.setForeground(java.awt.Color.white);
        btnnigiri6.setText("Order");
        btnnigiri6.setBorderPainted(false);
        btnnigiri6.setPreferredSize(new java.awt.Dimension(65, 25));
        btnnigiri6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnigiri6ActionPerformed(evt);
            }
        });
        jPanel7.add(btnnigiri6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1480, 70, 30));

        spnnigiri6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnnigiri6.setBorder(null);
        spnnigiri6.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel7.add(spnnigiri6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1510, 70, 25));

        jLabel141.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel141.setForeground(new java.awt.Color(173, 166, 166));
        jLabel141.setText("Follow us on:");
        jPanel7.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1560, -1, -1));

        jLabel138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconyt.png"))); // NOI18N
        jPanel7.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1560, -1, -1));

        jLabel139.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconig.png"))); // NOI18N
        jPanel7.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1560, -1, -1));

        jLabel125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconx.png"))); // NOI18N
        jPanel7.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 1560, -1, -1));

        jLabel140.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconfb.png"))); // NOI18N
        jPanel7.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1560, -1, -1));

        jLabel144.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel144.setForeground(java.awt.Color.white);
        jLabel144.setText("We offer both classic and innovative nigiri to suit your preferences.\"");
        jPanel7.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel142.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(173, 166, 166));
        jLabel142.setText("© 2024 Sushi Lover. All rights reserved.");
        jPanel7.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1560, -1, -1));

        jLabel143.setFont(new java.awt.Font("Freestyle Script", 0, 70)); // NOI18N
        jLabel143.setForeground(java.awt.Color.white);
        jLabel143.setText("\"The Perfect Harmony of Fish and Rice.\"");
        jPanel7.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 720, 780, -1));

        jLabel145.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel145.setForeground(java.awt.Color.white);
        jLabel145.setText("\"Explore a wide range of nigiri options, from tender tuna");
        jPanel7.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/backgroundnigiri.png"))); // NOI18N
        jPanel7.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/nigirifront.png"))); // NOI18N
        jPanel7.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 610, -1));

        jLabel146.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel146.setForeground(java.awt.Color.white);
        jLabel146.setText("and succulent salmon to delicate yellowtail and buttery eel.");
        jPanel7.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        btnNext2.setBackground(new java.awt.Color(254, 69, 91));
        btnNext2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnNext2.setForeground(java.awt.Color.white);
        btnNext2.setText("Next");
        btnNext2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext2ActionPerformed(evt);
            }
        });
        jPanel7.add(btnNext2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 880, -1, 30));

        jTabbedPane1.addTab("Nigiri", jPanel7);

        jPanel8.setBackground(new java.awt.Color(21, 19, 19));
        jPanel8.setPreferredSize(new java.awt.Dimension(1366, 1700));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/roll1.png"))); // NOI18N
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 930, 300, 200));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/roll2.png"))); // NOI18N
        jPanel8.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 930, -1, 200));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/roll3.png"))); // NOI18N
        jPanel8.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 930, -1, 200));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/roll4.png"))); // NOI18N
        jPanel8.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1270, -1, 190));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/roll5.png"))); // NOI18N
        jPanel8.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1270, -1, 190));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/roll6.png"))); // NOI18N
        jPanel8.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1270, -1, 190));

        jLabel49.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel49.setForeground(java.awt.Color.white);
        jLabel49.setText("California Maki");
        jPanel8.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1150, -1, -1));

        jLabel50.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel50.setForeground(java.awt.Color.white);
        jLabel50.setText("Php. 239.00");
        jPanel8.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1170, -1, -1));

        jLabel51.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel51.setForeground(java.awt.Color.white);
        jLabel51.setText("Tempura Maki");
        jPanel8.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1150, -1, -1));

        jLabel52.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel52.setForeground(java.awt.Color.white);
        jLabel52.setText("Php. 259.00");
        jPanel8.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1170, -1, -1));

        jLabel53.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel53.setForeground(java.awt.Color.white);
        jLabel53.setText("Volcano Roll");
        jPanel8.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1160, -1, -1));

        jLabel54.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel54.setForeground(java.awt.Color.white);
        jLabel54.setText("Php. 269.00");
        jPanel8.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1180, -1, -1));

        jLabel55.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel55.setForeground(java.awt.Color.white);
        jLabel55.setText("Philadelphia Roll");
        jPanel8.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1480, -1, -1));

        jLabel56.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel56.setForeground(java.awt.Color.white);
        jLabel56.setText("Php. 269.00");
        jPanel8.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 1500, -1, -1));

        jLabel57.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel57.setForeground(java.awt.Color.white);
        jLabel57.setText("Caterpillar Roll");
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1480, -1, -1));

        jLabel58.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel58.setForeground(java.awt.Color.white);
        jLabel58.setText("Php. 239.00");
        jPanel8.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1500, -1, -1));

        jLabel59.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel59.setForeground(java.awt.Color.white);
        jLabel59.setText("Spicy Tuna Roll");
        jPanel8.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1480, -1, -1));

        jLabel60.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel60.setForeground(java.awt.Color.white);
        jLabel60.setText("Php. 249.00");
        jPanel8.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 1500, -1, -1));

        btnroll2.setBackground(new java.awt.Color(254, 69, 91));
        btnroll2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnroll2.setForeground(java.awt.Color.white);
        btnroll2.setText("Order");
        btnroll2.setBorderPainted(false);
        btnroll2.setPreferredSize(new java.awt.Dimension(65, 25));
        btnroll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroll2ActionPerformed(evt);
            }
        });
        jPanel8.add(btnroll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1150, 70, 30));

        spnroll2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnroll2.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel8.add(spnroll2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1180, 70, 25));

        btnroll3.setBackground(new java.awt.Color(254, 69, 91));
        btnroll3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnroll3.setForeground(java.awt.Color.white);
        btnroll3.setText("Order");
        btnroll3.setBorderPainted(false);
        btnroll3.setPreferredSize(new java.awt.Dimension(65, 25));
        btnroll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroll3ActionPerformed(evt);
            }
        });
        jPanel8.add(btnroll3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1150, 70, 30));

        spnroll3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnroll3.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel8.add(spnroll3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1180, 70, 25));

        btnroll5.setBackground(new java.awt.Color(254, 69, 91));
        btnroll5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnroll5.setForeground(java.awt.Color.white);
        btnroll5.setText("Order");
        btnroll5.setBorderPainted(false);
        btnroll5.setPreferredSize(new java.awt.Dimension(65, 25));
        btnroll5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroll5ActionPerformed(evt);
            }
        });
        jPanel8.add(btnroll5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1480, 70, 30));

        spnroll5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnroll5.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel8.add(spnroll5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 1510, 70, 25));

        btnroll6.setBackground(new java.awt.Color(254, 69, 91));
        btnroll6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnroll6.setForeground(java.awt.Color.white);
        btnroll6.setText("Order");
        btnroll6.setBorderPainted(false);
        btnroll6.setPreferredSize(new java.awt.Dimension(65, 25));
        btnroll6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroll6ActionPerformed(evt);
            }
        });
        jPanel8.add(btnroll6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1480, 70, 30));

        spnroll6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnroll6.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel8.add(spnroll6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 1510, 70, 25));

        btnroll1.setBackground(new java.awt.Color(254, 69, 91));
        btnroll1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnroll1.setForeground(java.awt.Color.white);
        btnroll1.setText("Order");
        btnroll1.setBorderPainted(false);
        btnroll1.setPreferredSize(new java.awt.Dimension(65, 25));
        btnroll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroll1ActionPerformed(evt);
            }
        });
        jPanel8.add(btnroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1150, 70, 30));

        spnroll1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnroll1.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel8.add(spnroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1180, 70, 25));

        btnroll4.setBackground(new java.awt.Color(254, 69, 91));
        btnroll4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnroll4.setForeground(java.awt.Color.white);
        btnroll4.setText("Order");
        btnroll4.setBorderPainted(false);
        btnroll4.setPreferredSize(new java.awt.Dimension(65, 25));
        btnroll4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnroll4ActionPerformed(evt);
            }
        });
        jPanel8.add(btnroll4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1480, 70, 30));

        jLabel86.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(237, 220, 220));
        jLabel86.setText("your new favorite.\"");
        jPanel8.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, -1));

        spnroll4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnroll4.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel8.add(spnroll4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 1510, 70, 25));

        jLabel88.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(237, 220, 220));
        jLabel88.setText("\"Explore our diverse selection of ");
        jPanel8.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        jLabel89.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(237, 220, 220));
        jLabel89.setText("sushi rolls, from classic maki to");
        jPanel8.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        jLabel90.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(237, 220, 220));
        jLabel90.setText("specialty creations. Whether you");
        jPanel8.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        jLabel91.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(237, 220, 220));
        jLabel91.setText("bold new flavors, we have something ");
        jPanel8.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        jLabel92.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(237, 220, 220));
        jLabel92.setText("for everyone. Try our signature");
        jPanel8.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        jLabel105.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(237, 220, 220));
        jLabel105.setText("rolls, each with unique fillings");
        jPanel8.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, -1));

        jLabel106.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 24)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(237, 220, 220));
        jLabel106.setText("and delicious sauces, and find ");
        jPanel8.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/backgroundroll.png"))); // NOI18N
        jPanel8.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel110.setFont(new java.awt.Font("Freestyle Script", 0, 80)); // NOI18N
        jLabel110.setForeground(java.awt.Color.white);
        jLabel110.setText("\"Discover Your Favorite Sushi Roll\"");
        jPanel8.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 780, 780, -1));

        jLabel116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconx.png"))); // NOI18N
        jPanel8.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 1560, -1, -1));

        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconfb.png"))); // NOI18N
        jPanel8.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1560, -1, -1));

        jLabel115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconyt.png"))); // NOI18N
        jPanel8.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1560, -1, -1));

        jLabel114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconig.png"))); // NOI18N
        jPanel8.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1560, -1, -1));

        jLabel118.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(173, 166, 166));
        jLabel118.setText("© 2024 Sushi Lover. All rights reserved.");
        jPanel8.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1560, -1, -1));

        jLabel117.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(173, 166, 166));
        jLabel117.setText("Follow us on:");
        jPanel8.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1560, -1, -1));

        btnNext3.setBackground(new java.awt.Color(254, 69, 91));
        btnNext3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnNext3.setForeground(java.awt.Color.white);
        btnNext3.setText("Next");
        btnNext3.setName(""); // NOI18N
        btnNext3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNext3ActionPerformed(evt);
            }
        });
        jPanel8.add(btnNext3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 880, -1, 30));

        jTabbedPane1.addTab("Rolls", jPanel8);

        jPanel4.setBackground(new java.awt.Color(21, 19, 19));
        jPanel4.setMinimumSize(new java.awt.Dimension(1366, 1580));
        jPanel4.setPreferredSize(new java.awt.Dimension(1366, 1600));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/platter1.png"))); // NOI18N
        jPanel4.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1030, -1, -1));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/platter2.png"))); // NOI18N
        jPanel4.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 1050, -1, 270));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/platter3.png"))); // NOI18N
        jLabel63.setToolTipText("");
        jPanel4.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 1030, -1, -1));

        jLabel64.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel64.setForeground(java.awt.Color.white);
        jLabel64.setText("Platter 1");
        jPanel4.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1360, -1, -1));

        jLabel65.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel65.setForeground(java.awt.Color.white);
        jLabel65.setText("Php. 2,399.00");
        jPanel4.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 1380, -1, -1));

        jLabel66.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel66.setForeground(java.awt.Color.white);
        jLabel66.setText("Php. 2,199.00");
        jPanel4.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1380, -1, -1));

        jLabel67.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel67.setForeground(java.awt.Color.white);
        jLabel67.setText("Platter 2");
        jPanel4.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 1360, -1, -1));

        jLabel68.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel68.setForeground(java.awt.Color.white);
        jLabel68.setText("Php. 2,599.00");
        jPanel4.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 1380, -1, -1));

        jLabel69.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 16)); // NOI18N
        jLabel69.setForeground(java.awt.Color.white);
        jLabel69.setText("Platter 3");
        jPanel4.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 1360, -1, -1));

        btnplatter1.setBackground(new java.awt.Color(254, 69, 91));
        btnplatter1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnplatter1.setForeground(java.awt.Color.white);
        btnplatter1.setText("Order");
        btnplatter1.setBorderPainted(false);
        btnplatter1.setPreferredSize(new java.awt.Dimension(65, 25));
        btnplatter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplatter1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnplatter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1360, 70, 30));

        spnplatter1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnplatter1.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel4.add(spnplatter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 1390, 70, 25));

        btnplatter2.setBackground(new java.awt.Color(254, 69, 91));
        btnplatter2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnplatter2.setForeground(java.awt.Color.white);
        btnplatter2.setText("Order");
        btnplatter2.setBorderPainted(false);
        btnplatter2.setPreferredSize(new java.awt.Dimension(65, 25));
        btnplatter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplatter2ActionPerformed(evt);
            }
        });
        jPanel4.add(btnplatter2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1360, 70, 30));

        spnplatter2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnplatter2.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel4.add(spnplatter2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 1390, 70, 25));

        btnplatter3.setBackground(new java.awt.Color(254, 69, 91));
        btnplatter3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnplatter3.setForeground(java.awt.Color.white);
        btnplatter3.setText("Order");
        btnplatter3.setBorderPainted(false);
        btnplatter3.setPreferredSize(new java.awt.Dimension(65, 25));
        btnplatter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplatter3ActionPerformed(evt);
            }
        });
        jPanel4.add(btnplatter3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1360, 70, 30));

        spnplatter3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        spnplatter3.setPreferredSize(new java.awt.Dimension(65, 25));
        jPanel4.add(spnplatter3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1390, 70, 25));

        jLabel109.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel109.setForeground(java.awt.Color.white);
        jLabel109.setText("a variety of sushi options to satisfy all tastes. Choose from our curated platters or customize your");
        jPanel4.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, -1, -1));

        jLabel111.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel111.setForeground(java.awt.Color.white);
        jLabel111.setText("\"Hosting a party or looking to share a sushi feast? Our sushi platters are perfect for gatherings, offering");
        jPanel4.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 630, -1, -1));

        jLabel112.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 22)); // NOI18N
        jLabel112.setForeground(java.awt.Color.white);
        jLabel112.setText("own with your favorite rolls, sashimi. Enjoy a sushi experience that brings people together.\"");
        jPanel4.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 690, -1, -1));

        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/backgroundplatter.png"))); // NOI18N
        jPanel4.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel107.setFont(new java.awt.Font("Freestyle Script", 0, 80)); // NOI18N
        jLabel107.setForeground(java.awt.Color.white);
        jLabel107.setText("\"Share the Love of Sushi.\"");
        jPanel4.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 850, 580, -1));

        jLabel122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconx.png"))); // NOI18N
        jPanel4.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 1560, -1, -1));

        jLabel123.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(173, 166, 166));
        jLabel123.setText("Follow us on:");
        jPanel4.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1560, -1, -1));

        jLabel124.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(173, 166, 166));
        jLabel124.setText("© 2024 Sushi Lover. All rights reserved.");
        jPanel4.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1560, -1, -1));

        jLabel119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconfb.png"))); // NOI18N
        jPanel4.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1560, -1, -1));

        jLabel120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconig.png"))); // NOI18N
        jPanel4.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1560, -1, -1));

        jLabel121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconyt.png"))); // NOI18N
        jPanel4.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1560, -1, -1));

        jTabbedPane1.addTab("Platter", jPanel4);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setMinimumSize(new java.awt.Dimension(1140, 768));
        jPanel2.setPreferredSize(new java.awt.Dimension(1366, 1700));
        jPanel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.black, java.awt.Color.cyan, null));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel97.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        jLabel97.setForeground(java.awt.Color.white);
        jLabel97.setText("SubTotal");
        jPanel9.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));

        jLabel98.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        jLabel98.setForeground(java.awt.Color.white);
        jLabel98.setText("Total");
        jPanel9.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 30));

        txtTotal.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        txtTotal.setForeground(java.awt.Color.darkGray);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0.0");
        txtTotal.setBorder(new javax.swing.border.LineBorder(java.awt.Color.darkGray, 2, true));
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        jPanel9.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 225, 30));

        txtSubtotal.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        txtSubtotal.setForeground(java.awt.Color.darkGray);
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubtotal.setText("0.0");
        txtSubtotal.setBorder(new javax.swing.border.LineBorder(java.awt.Color.darkGray, 2, true));
        jPanel9.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 225, 30));

        jLabel93.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel93.setForeground(java.awt.Color.white);
        jLabel93.setText("Sushi");
        jPanel9.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 80, 90));

        jLabel94.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel94.setForeground(java.awt.Color.red);
        jLabel94.setText("Lover");
        jPanel9.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 90, 90));

        jLabel95.setForeground(java.awt.Color.white);
        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/redheartlogo.png"))); // NOI18N
        jPanel9.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 120, 120));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.black, java.awt.Color.cyan, null));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel99.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        jLabel99.setForeground(java.awt.Color.darkGray);
        jLabel99.setText("Tax");
        jPanel10.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 30));

        jLabel100.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        jLabel100.setForeground(java.awt.Color.darkGray);
        jLabel100.setText("SubTotal");
        jPanel10.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, 30));

        jLabel101.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        jLabel101.setForeground(java.awt.Color.darkGray);
        jLabel101.setText("Total");
        jPanel10.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 30));

        txtTax1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        txtTax1.setForeground(java.awt.Color.darkGray);
        txtTax1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTax1.setText("0.0");
        txtTax1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.darkGray, 2, true));
        txtTax1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTax1ActionPerformed(evt);
            }
        });
        jPanel10.add(txtTax1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 225, 30));

        txtTotal1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        txtTotal1.setForeground(java.awt.Color.darkGray);
        txtTotal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal1.setText("0.0");
        txtTotal1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.darkGray, 2, true));
        jPanel10.add(txtTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 225, 30));

        txtSubtotal1.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 18)); // NOI18N
        txtSubtotal1.setForeground(java.awt.Color.darkGray);
        txtSubtotal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubtotal1.setText("0.0");
        txtSubtotal1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.darkGray, 2, true));
        jPanel10.add(txtSubtotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 225, 30));

        jLabel102.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel102.setText("Sushi");
        jPanel10.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 60, 60));

        jLabel103.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel103.setForeground(java.awt.Color.red);
        jLabel103.setText("Lover");
        jPanel10.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 70, 60));

        jLabel104.setForeground(java.awt.Color.white);
        jLabel104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/redheartlogo.png"))); // NOI18N
        jPanel10.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 100, 90));

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 390, 260));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 410, 260));

        txtArea1.setEditable(false);
        txtArea1.setColumns(20);
        txtArea1.setRows(5);
        txtArea1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.black, java.awt.Color.cyan, null));
        jScrollPane3.setViewportView(txtArea1);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 410, 340));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price", "Quantity", "Cost"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setAutoscrolls(false);
        jTable1.setName("Cart"); // NOI18N
        jTable1.setRowHeight(35);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 540, 600));

        jPanel12.setBackground(java.awt.Color.white);
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.lightGray, null));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel153.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 16)); // NOI18N
        jLabel153.setForeground(java.awt.Color.darkGray);
        jLabel153.setText("TOTAL:");
        jPanel12.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, -1, -1));

        btnPlacingOrder.setBackground(new java.awt.Color(254, 69, 91));
        btnPlacingOrder.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnPlacingOrder.setForeground(java.awt.Color.white);
        btnPlacingOrder.setText("Place Order");
        btnPlacingOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPlacingOrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPlacingOrderMouseExited(evt);
            }
        });
        btnPlacingOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlacingOrderActionPerformed(evt);
            }
        });
        jPanel12.add(btnPlacingOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, 330, 40));

        jLabel154.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel154.setForeground(java.awt.Color.darkGray);
        jLabel154.setText("Shipping Fee:");
        jPanel12.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, 80, 20));

        txtOrderId.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12)); // NOI18N
        txtOrderId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtOrderId.setBorder(null);
        txtOrderId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderIdActionPerformed(evt);
            }
        });
        jPanel12.add(txtOrderId, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 480, 270, 20));

        txtTotalCheckout.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 16)); // NOI18N
        txtTotalCheckout.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalCheckout.setBorder(null);
        txtTotalCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalCheckoutActionPerformed(evt);
            }
        });
        jPanel12.add(txtTotalCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, 200, 20));

        jPanel11.setBackground(java.awt.Color.white);
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel164.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel164.setForeground(java.awt.Color.red);
        jLabel164.setText("Lover");
        jLabel164.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel164MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel164, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 70, 60));

        jLabel165.setFont(new java.awt.Font("Freestyle Script", 1, 36)); // NOI18N
        jLabel165.setText("Sushi");
        jLabel165.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel165MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel165, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 60, 60));

        jLabel166.setForeground(java.awt.Color.white);
        jLabel166.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/redheartlogo.png"))); // NOI18N
        jPanel11.add(jLabel166, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 100, 90));

        jLabel81.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        jLabel81.setForeground(java.awt.Color.darkGray);
        jLabel81.setText("Shipping Details");
        jPanel11.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel150.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel150.setForeground(java.awt.Color.darkGray);
        jLabel150.setText("First Name");
        jPanel11.add(jLabel150, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel152.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel152.setForeground(java.awt.Color.darkGray);
        jLabel152.setText("Country");
        jPanel11.add(jLabel152, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        Address.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        Address.setForeground(java.awt.Color.darkGray);
        Address.setText("Postal Code");
        jPanel11.add(Address, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel157.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel157.setForeground(java.awt.Color.darkGray);
        jLabel157.setText("Region");
        jPanel11.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel158.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel158.setForeground(java.awt.Color.darkGray);
        jLabel158.setText("Address");
        jPanel11.add(jLabel158, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel159.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel159.setForeground(java.awt.Color.darkGray);
        jLabel159.setText("Email");
        jPanel11.add(jLabel159, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jLabel160.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel160.setForeground(java.awt.Color.darkGray);
        jLabel160.setText("Phone Number");
        jPanel11.add(jLabel160, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel85.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel85.setForeground(java.awt.Color.darkGray);
        jLabel85.setText("Last Name");
        jPanel11.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        jLabel156.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 14)); // NOI18N
        jLabel156.setForeground(java.awt.Color.darkGray);
        jLabel156.setText("City/Provinces");
        jPanel11.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, -1, -1));

        txtFirstName.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyReleased(evt);
            }
        });
        jPanel11.add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 290, 30));

        txtLastName.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLastNameKeyReleased(evt);
            }
        });
        jPanel11.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 290, 30));

        comCountry.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        comCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Country", "Philippines" }));
        comCountry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comCountryKeyReleased(evt);
            }
        });
        jPanel11.add(comCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 610, 30));

        comRegion.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        comRegion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Region", "Region I", "Region II", "Region III", "Region IV - A", "Region IV - B", "Region V", "Region VI", "Region VII", "Region VII", "Region IX", "Region X", "Region XI", "Region XII", "Region XIII" }));
        comRegion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comRegionKeyReleased(evt);
            }
        });
        jPanel11.add(comRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 610, 30));

        txtPostalCode.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        txtPostalCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPostalCodeKeyReleased(evt);
            }
        });
        jPanel11.add(txtPostalCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 290, 30));

        comCityProvinces.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        comCityProvinces.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "City/Provinces", "Abra  ", "Agusan del Norte  ", "Agusan del Sur  ", "Aklan  ", "Albay  ", "Antique  ", "Apayao  ", "Aurora  ", "Basilan  ", "Bataan  ", "Batanes  ", "Batangas  ", "Benguet  ", "Biliran  ", "Bohol  ", "Bukidnon  ", "Bulacan  ", "Cagayan  ", "Camarines Norte  ", "Camarines Sur  ", "Camiguin  ", "Capiz  ", "Catanduanes  ", "Cavite  ", "Cebu  ", "Compostela Valley  ", "Cotabato  ", "Davao Occidental  ", "Davao Oriental  ", "Davao del Norte  ", "Davao del Sur  ", "Dinagat Islands  ", "Eastern Samar  ", "Guimaras  ", "Ifugao  ", "Ilocos Norte  ", "Ilocos Sur  ", "Iloilo  ", "Isabela  ", "Kalinga  ", "La Union  ", "Laguna  ", "Lanao del Norte  ", "Lanao del Sur  ", "Leyte  ", "Maguindanao  ", "Marinduque  ", "Masbate  ", "Misamis Occidental  ", "Misamis Oriental  ", "Mountain Province  ", "Negros Occidental  ", "Negros Oriental  ", "Northern Samar  ", "Nueva Ecija  ", "Nueva Vizcaya  ", "Occidental Mindoro  ", "Oriental Mindoro  ", "Palawan  ", "Pampanga  ", "Pangasinan  ", "Quezon  ", "Quirino  ", "Rizal  ", "Romblon  ", "Samar  ", "Sarangani  ", "Siquijor  ", "Sorsogon  ", "South Cotabato  ", "Southern Leyte  ", "Sultan Kudarat  ", "Sulu  ", "Surigao del Norte  ", "Surigao del Sur  ", "Tarlac  ", "Tawi-Tawi  ", "Zambales  ", "Zamboanga Sibugay  ", "Zamboanga del Norte  ", "Zamboanga del Sur  " }));
        comCityProvinces.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comCityProvincesKeyTyped(evt);
            }
        });
        jPanel11.add(comCityProvinces, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 290, 30));

        txtAddress.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        txtAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddressKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAddressKeyReleased(evt);
            }
        });
        jPanel11.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 610, 30));

        txtEmail.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel11.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 610, 30));

        txtPhoneNumber.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        txtPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneNumberKeyReleased(evt);
            }
        });
        jPanel11.add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 610, 30));

        jLabel155.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 20)); // NOI18N
        jLabel155.setForeground(java.awt.Color.darkGray);
        jLabel155.setText("Payment Method");
        jPanel11.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        comCreditorDebit.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        comCreditorDebit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit / Debit Card", "BDO", "VISA", "BPI", "PSBank", "ChinaBank" }));
        jPanel13.add(comCreditorDebit);

        comEwallet.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        comEwallet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E-Wallet", "PayMaya", "Gcash", "PayPal" }));
        jPanel13.add(comEwallet);

        radCOD.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 15)); // NOI18N
        radCOD.setText("Cash on Delivery");
        jPanel13.add(radCOD);

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 610, 30));

        jPanel12.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 660, 620));

        jPanel14.setBackground(java.awt.Color.white);
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, null));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAreaItems.setColumns(20);
        txtAreaItems.setRows(5);
        txtAreaItems.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        jScrollPane4.setViewportView(txtAreaItems);

        jPanel14.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 330));

        jPanel12.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 330, 330));

        jLabel162.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 16)); // NOI18N
        jLabel162.setForeground(java.awt.Color.darkGray);
        jLabel162.setText("Payment Method:");
        jPanel12.add(jLabel162, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 420, 130, 20));

        jLabel161.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel161.setForeground(java.awt.Color.darkGray);
        jLabel161.setText("Order Id:");
        jPanel12.add(jLabel161, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 480, 70, 20));

        txtShippingFee.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        txtShippingFee.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtShippingFee.setText("Free");
        txtShippingFee.setBorder(null);
        txtShippingFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtShippingFeeActionPerformed(evt);
            }
        });
        jPanel12.add(txtShippingFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 380, 200, 20));
        jPanel12.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 452, 330, 10));

        txtSubtotalCheckout.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        txtSubtotalCheckout.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubtotalCheckout.setBorder(null);
        txtSubtotalCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalCheckoutActionPerformed(evt);
            }
        });
        jPanel12.add(txtSubtotalCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, 200, 20));

        jLabel171.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel171.setForeground(java.awt.Color.darkGray);
        jLabel171.setText("SubTotal:");
        jPanel12.add(jLabel171, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 70, 20));

        lblpayment.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        lblpayment.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel12.add(lblpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 200, 20));

        txtShippingAmount.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        txtShippingAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtShippingAmount.setBorder(null);
        txtShippingAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtShippingAmountActionPerformed(evt);
            }
        });
        jPanel12.add(txtShippingAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 400, 200, 20));

        jLabel163.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel163.setForeground(java.awt.Color.darkGray);
        jLabel163.setText("Shipping Amount:");
        jPanel12.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 400, 110, 20));

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 870, 1050, 660));

        jPanel15.setBackground(java.awt.Color.white);
        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        btnAddItem.setBackground(new java.awt.Color(254, 69, 91));
        btnAddItem.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnAddItem.setForeground(java.awt.Color.white);
        btnAddItem.setText("Add Item");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });
        jPanel15.add(btnAddItem);

        btnDelete.setBackground(new java.awt.Color(254, 69, 91));
        btnDelete.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnDelete.setForeground(java.awt.Color.white);
        btnDelete.setText("Delete");
        btnDelete.setBorderPainted(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(65, 25));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel15.add(btnDelete);

        btnClear.setBackground(new java.awt.Color(254, 69, 91));
        btnClear.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnClear.setForeground(java.awt.Color.white);
        btnClear.setText("Clear");
        btnClear.setBorderPainted(false);
        btnClear.setMaximumSize(new java.awt.Dimension(65, 25));
        btnClear.setMinimumSize(new java.awt.Dimension(65, 25));
        btnClear.setPreferredSize(new java.awt.Dimension(65, 25));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        jPanel15.add(btnClear);

        btnCheckout.setBackground(new java.awt.Color(254, 69, 91));
        btnCheckout.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnCheckout.setForeground(java.awt.Color.white);
        btnCheckout.setText("Checkout");
        btnCheckout.setBorderPainted(false);
        btnCheckout.setMaximumSize(new java.awt.Dimension(65, 25));
        btnCheckout.setMinimumSize(new java.awt.Dimension(65, 25));
        btnCheckout.setPreferredSize(new java.awt.Dimension(65, 25));
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });
        jPanel15.add(btnCheckout);

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 410, 30));

        jPanel17.setBackground(java.awt.Color.white);
        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        btnReceipt.setBackground(new java.awt.Color(254, 69, 91));
        btnReceipt.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnReceipt.setForeground(java.awt.Color.white);
        btnReceipt.setText("Print Receipt");
        btnReceipt.setBorderPainted(false);
        btnReceipt.setMaximumSize(new java.awt.Dimension(65, 25));
        btnReceipt.setMinimumSize(new java.awt.Dimension(65, 25));
        btnReceipt.setPreferredSize(new java.awt.Dimension(65, 25));
        btnReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptActionPerformed(evt);
            }
        });
        jPanel17.add(btnReceipt);

        btnExit.setBackground(new java.awt.Color(254, 69, 91));
        btnExit.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        btnExit.setForeground(java.awt.Color.white);
        btnExit.setText("Exit");
        btnExit.setBorderPainted(false);
        btnExit.setMaximumSize(new java.awt.Dimension(65, 25));
        btnExit.setMinimumSize(new java.awt.Dimension(65, 25));
        btnExit.setPreferredSize(new java.awt.Dimension(65, 25));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel17.add(btnExit);

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 225, 30));

        jLabel172.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel172.setForeground(java.awt.Color.darkGray);
        jLabel172.setText("© 2024 Sushi Lover. All rights reserved.");
        jPanel2.add(jLabel172, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 1560, -1, -1));

        jLabel173.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        jLabel173.setForeground(java.awt.Color.darkGray);
        jLabel173.setText("Follow us on:");
        jPanel2.add(jLabel173, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 1560, -1, -1));

        jLabel174.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconfb.png"))); // NOI18N
        jPanel2.add(jLabel174, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 1560, -1, -1));

        jLabel175.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconig.png"))); // NOI18N
        jPanel2.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 1560, -1, -1));

        jLabel176.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconyt.png"))); // NOI18N
        jPanel2.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 1560, -1, -1));

        jLabel177.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image2/iconx.png"))); // NOI18N
        jPanel2.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 1560, -1, -1));

        txtTime.setForeground(java.awt.Color.white);
        jPanel2.add(txtTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 140, 20));

        txtDate.setForeground(java.awt.Color.white);
        jPanel2.add(txtDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 140, 20));

        jTabbedPane1.addTab("Cart", null, jPanel2, "");

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 1710));

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 955, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1366, 1720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel72MouseClicked

    private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel70MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0
        );
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel5AncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel5AncestorAdded

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_formPropertyChange

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_formComponentAdded

    private void jScrollPane1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jScrollPane1AncestorAdded
        // TODO add your handling code here:
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);
        verticalScrollBar.setBlockIncrement(120);
    }//GEN-LAST:event_jScrollPane1AncestorAdded

    private void formAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorMoved
        // TODO add your handling code here:
        JFrame frame = new JFrame();
        frame.add(jScrollPane1);
        frame.setSize(1366, 768);
    }//GEN-LAST:event_formAncestorMoved

    private void jPanel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel2AncestorAdded
        // TODO add your handling code here:
        jPanel2.setPreferredSize(new Dimension(1366, 768));
        jScrollPane1.setVerticalScrollBarPolicy(jScrollPane1.VERTICAL_SCROLLBAR_AS_NEEDED);
    }//GEN-LAST:event_jPanel2AncestorAdded

    private void btnPlacingOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlacingOrderActionPerformed
        // TODO add your handling code here:
        String orderId = java.util.UUID.randomUUID().toString();
        CheckoutUser checkout = new CheckoutUser();
        ItemOrdered itemordered = new ItemOrdered();

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String country = comCountry.getSelectedItem().toString();
        String region = comRegion.getSelectedItem().toString();
        String postalCode = txtPostalCode.getText();
        String cityProvinces = comCityProvinces.getSelectedItem().toString();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String payment = lblpayment.getText();
        String subTotal = txtSubtotalCheckout.getText();
        String total = txtTotalCheckout.getText();

        boolean isCheckoutValid = !firstName.isEmpty() && !lastName.isEmpty() && !country.isEmpty()
                && !region.isEmpty() && !postalCode.isEmpty() && !cityProvinces.isEmpty()
                && !address.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty();

        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();

        if (!isCheckoutValid) {
            JOptionPane.showMessageDialog(null, "Please fill out all required fields before placing your order.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tblModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "The table is empty. No items to order.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        checkout.setOrderId(orderId);
        checkout.setFirstName(firstName);
        checkout.setLastName(lastName);
        checkout.setCountry(country);
        checkout.setRegion(region);
        checkout.setPostalCode(postalCode);
        checkout.setCityProvinces(cityProvinces);
        checkout.setAddress(address);
        checkout.setEmail(email);
        checkout.setPhoneNumber(phoneNumber);
        checkout.setPayment(payment);
        checkout.setSubTotal(subTotal);
        checkout.setTotal(total);

        UserDataAccessObject.checkout(checkout);

        List<String> queries = new ArrayList<>();

        for (int i = 0; i < tblModel.getRowCount(); i++) {
            itemordered.setOrderId(orderId);
            itemordered.setItem(tblModel.getValueAt(i, 0).toString());
            itemordered.setPrice(tblModel.getValueAt(i, 1).toString());
            itemordered.setQuantity(tblModel.getValueAt(i, 2).toString());
            itemordered.setCost(tblModel.getValueAt(i, 3).toString());

            queries.add(UserDataAccessObject.itemordered(itemordered));
        }

        DBOperations.executeBatchQueries(queries, "Items ordered successfully.");

        txtOrderId.setText(orderId);
    }//GEN-LAST:event_btnPlacingOrderActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row < 0) { // If no row is selected
            JOptionPane.showMessageDialog(this, "No item selected, Please select an item to remove", "Select Item", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();

            // Retrieve the item identifier and cost
            String itemToDelete = tblModel.getValueAt(row, 0).toString();
            String costText = tblModel.getValueAt(row, 3).toString();

            double cost = Double.parseDouble(costText.replace("Php. ", "").trim());

            // Update the total and tax values
            total -= cost;

            // Remove the selected row from the JTable
            tblModel.removeRow(row);

            // Update the JTextArea to remove the corresponding line
            String[] textLines = txtArea1.getText().split("\n");
            StringBuilder newText = new StringBuilder();

            for (String line : textLines) {
                if (!line.contains(itemToDelete)) { // Check for containment to remove correct line
                    newText.append(line).append("\n"); // Add lines not matching the item to delete
                }
            }

            txtArea1.setText(newText.toString()); // Update the text area
            dudate(); // Assuming this updates some other component
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
        StringBuilder content = new StringBuilder();

        for (int row = 0; row < tblModel.getRowCount(); row++) {
            for (int col = 0; col < tblModel.getColumnCount(); col++) {
                String columnName = tblModel.getColumnName(col);
                String cellValue = tblModel.getValueAt(row, col).toString();
                content.append(columnName).append(": ").append(cellValue).append("\n");
            }
            content.append("\n");
        }
        String extractedContent = content.toString();
        double shippingAmount = total * 0.05;
        txtAreaItems.setText(extractedContent);
        txtShippingFee.setText("5%");
        txtShippingAmount.setText(String.format("Php. %.2f", shippingAmount));
        txtSubtotalCheckout.setText(txtSubtotal.getText());
        double totalWithShipping = (total + shippingAmount);
        txtTotalCheckout.setText(String.format("Phptring.format. %.2f", totalWithShipping));
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        clear();
        Cart();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptActionPerformed
        // TODO add your handling code here:
        if (total == 0.0) {
            JOptionPane.showMessageDialog(null, "You haven't selected any item");
        } else {

            txtArea1.append("********************************************************************************\n"
                    + "Shipping Fee: \t\t\t" + txtShippingFee.getText() + "\n"
                    + "********************************************************************************\n"
                    + "Sub Total: \t\t\t" + total + "\n"
                    + "********************************************************************************\n"
                    + "Total Amount : \t\t\t" + (total + (total * 0.05)) + "\n"
                    + "********************************************************************************\n"
                    + "\tThank you for ordering from Sushi Lover!\n"
                    + "           Call us at (123) 456-7890 or email support@sushilover.com\n"
                    + "   You can track your order using the website www.sushilover.com/track\n"
                    + "\tFollow us on social media @SushiLover\n"
                    + "*******************************************************************************\n"
            );

            Receipt receipt = new Receipt();
            receipt.txtReceipt.setText(txtArea1.getText());
            receipt.setVisible(true);
        }
    }//GEN-LAST:event_btnReceiptActionPerformed

    private void txtTax1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTax1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTax1ActionPerformed

    private void btnplatter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnplatter3ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnplatter3.getValue().toString());
        if (qtyisZero(qty) && btnplatter3.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 2599.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel69.getText() + "\t\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel69.getText(), jLabel68.getText(), spnplatter3.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnplatter3.setValue(0);
            dudate();
        } else {
            btnplatter3.setSelected(false);
        }
    }//GEN-LAST:event_btnplatter3ActionPerformed

    private void btnplatter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnplatter2ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnplatter2.getValue().toString());
        if (qtyisZero(qty) && btnplatter2.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 2199.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel67.getText() + "\t\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel67.getText(), jLabel66.getText(), spnplatter2.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnplatter2.setValue(0);
            dudate();
        } else {
            btnplatter2.setSelected(false);
        }
    }//GEN-LAST:event_btnplatter2ActionPerformed

    private void btnplatter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnplatter1ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnplatter1.getValue().toString());
        if (qtyisZero(qty) && btnplatter1.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 2399.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel64.getText() + "\t\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel64.getText(), jLabel65.getText(), spnplatter1.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnplatter1.setValue(0);
            dudate();
        } else {
            btnplatter1.setSelected(false);
        }
    }//GEN-LAST:event_btnplatter1ActionPerformed

    private void btnNext3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_btnNext3ActionPerformed

    private void btnroll4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroll4ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnroll4.getValue().toString());
        if (qtyisZero(qty) && btnroll4.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 269.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel55.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel55.getText(), jLabel56.getText(), spnroll4.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnroll4.setValue(0);
            dudate();
        } else {
            btnroll4.setSelected(false);
        }
    }//GEN-LAST:event_btnroll4ActionPerformed

    private void btnroll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroll1ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnroll1.getValue().toString());
        if (qtyisZero(qty) && btnroll1.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 239.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel49.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel49.getText(), jLabel50.getText(), spnroll1.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnroll1.setValue(0);
            dudate();
        } else {
            btnroll1.setSelected(false);
        }
    }//GEN-LAST:event_btnroll1ActionPerformed

    private void btnroll6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroll6ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnroll6.getValue().toString());
        if (qtyisZero(qty) && btnroll6.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 249.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel59.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel59.getText(), jLabel60.getText(), spnroll6.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnroll6.setValue(0);
            dudate();
        } else {
            btnroll6.setSelected(false);
        }
    }//GEN-LAST:event_btnroll6ActionPerformed

    private void btnroll5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroll5ActionPerformed
        // TODO add your handling code here:

        int qty = Integer.parseInt(spnroll5.getValue().toString());
        if (qtyisZero(qty) && btnroll5.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 239.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel57.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel57.getText(), jLabel58.getText(), spnroll5.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnroll5.setValue(0);
            dudate();
        } else {
            btnroll5.setSelected(false);
        }
    }//GEN-LAST:event_btnroll5ActionPerformed

    private void btnroll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroll3ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnroll3.getValue().toString());
        if (qtyisZero(qty) && btnroll3.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 269.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel53.getText() + "\t\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel53.getText(), jLabel54.getText(), spnroll3.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnroll3.setValue(0);
            dudate();
        } else {
            btnroll3.setSelected(false);
        }
    }//GEN-LAST:event_btnroll3ActionPerformed

    private void btnroll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnroll2ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnroll2.getValue().toString());
        if (qtyisZero(qty) && btnroll2.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 259.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel51.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel51.getText(), jLabel52.getText(), spnroll2.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnroll2.setValue(0);
            dudate();
        } else {
            btnroll2.setSelected(false);
        }
    }//GEN-LAST:event_btnroll2ActionPerformed

    private void btnNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNext2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_btnNext2ActionPerformed

    private void btnnigiri6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnigiri6ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnnigiri6.getValue().toString());
        if (qtyisZero(qty) && btnnigiri6.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 239.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel42.getText() + "\t\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel42.getText(), jLabel43.getText(), spnnigiri6.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnnigiri6.setValue(0);
            dudate();
        } else {
            btnnigiri6.setSelected(false);
        }
    }//GEN-LAST:event_btnnigiri6ActionPerformed

    private void btnnigiri2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnigiri2ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnnigiri2.getValue().toString());
        if (qtyisZero(qty) && btnnigiri2.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 199.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel35.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel35.getText(), jLabel34.getText(), spnnigiri2.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnnigiri2.setValue(0);
            dudate();
        } else {
            btnnigiri2.setSelected(false);
        }
    }//GEN-LAST:event_btnnigiri2ActionPerformed

    private void btnnigiri1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnigiri1ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnnigiri1.getValue().toString());
        if (qtyisZero(qty) && btnnigiri1.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 209.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel33.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel33.getText(), jLabel32.getText(), spnnigiri1.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnnigiri1.setValue(0);
            dudate();
        } else {
            btnnigiri1.setSelected(false);
        }
    }//GEN-LAST:event_btnnigiri1ActionPerformed

    private void btnnigiri5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnigiri5ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnnigiri5.getValue().toString());
        if (qtyisZero(qty) && btnnigiri5.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 209.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel40.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel40.getText(), jLabel41.getText(), spnnigiri5.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnnigiri5.setValue(0);
            dudate();
        } else {
            btnnigiri5.setSelected(false);
        }
    }//GEN-LAST:event_btnnigiri5ActionPerformed

    private void btnnigiri4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnigiri4ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnnigiri4.getValue().toString());
        if (qtyisZero(qty) && btnnigiri4.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 229.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel38.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel38.getText(), jLabel39.getText(), spnnigiri4.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnnigiri4.setValue(0);
            dudate();
        } else {
            btnnigiri4.setSelected(false);
        }
    }//GEN-LAST:event_btnnigiri4ActionPerformed

    private void btnnigiri3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnigiri3ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnnigiri3.getValue().toString());
        if (qtyisZero(qty) && btnnigiri3.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 219.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel36.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel36.getText(), jLabel37.getText(), spnnigiri3.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnnigiri3.setValue(0);
            dudate();
        } else {
            btnnigiri3.setSelected(false);
        }
    }//GEN-LAST:event_btnnigiri3ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnNextActionPerformed

    private void spnsashimi1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnsashimi1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spnsashimi1MouseClicked

    private void btnsashimi6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsashimi6ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnsashimi6.getValue().toString());
        if (qtyisZero(qty) && btnsashimi6.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 269.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel27.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel27.getText(), jLabel28.getText(), spnsashimi6.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnsashimi6.setValue(0);
            dudate();
        } else {
            btnsashimi6.setSelected(false);
        }
    }//GEN-LAST:event_btnsashimi6ActionPerformed

    private void btnsashimi5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsashimi5ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnsashimi5.getValue().toString());
        if (qtyisZero(qty) && btnsashimi5.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 279.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel25.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel25.getText(), jLabel26.getText(), spnsashimi5.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnsashimi5.setValue(0);
            dudate();
        } else {
            btnsashimi5.setSelected(false);
        }
    }//GEN-LAST:event_btnsashimi5ActionPerformed

    private void btnsashimi4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsashimi4ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnsashimi4.getValue().toString());
        if (qtyisZero(qty) && btnsashimi4.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 289.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel23.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel23.getText(), jLabel24.getText(), spnsashimi4.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnsashimi4.setValue(0);
            dudate();
        } else {
            btnsashimi4.setSelected(false);
        }
    }//GEN-LAST:event_btnsashimi4ActionPerformed

    private void btnsashimi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsashimi3ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnsashimi3.getValue().toString());
        if (qtyisZero(qty) && btnsashimi3.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 289.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel21.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel21.getText(), jLabel22.getText(), spnsashimi3.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnsashimi3.setValue(0);
            dudate();
        } else {
            btnsashimi3.setSelected(false);
        }
    }//GEN-LAST:event_btnsashimi3ActionPerformed

    private void btnsashimi2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btnsashimi2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsashimi2PropertyChange

    private void btnsashimi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsashimi2ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnsashimi2.getValue().toString());
        if (qtyisZero(qty) && btnsashimi2.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 279.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel19.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel19.getText(), jLabel20.getText(), spnsashimi2.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
            spnsashimi2.setValue(0);
            dudate();
        } else {
            btnsashimi2.setSelected(false);
        }
    }//GEN-LAST:event_btnsashimi2ActionPerformed

    private void btnsashimi2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsashimi2MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btnsashimi2MouseExited

    private void btnsashimi2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsashimi2MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_btnsashimi2MouseEntered

    private void btnsashimi1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_btnsashimi1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsashimi1PropertyChange

    private void btnsashimi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsashimi1ActionPerformed
        // TODO add your handling code here:
        int qty = Integer.parseInt(spnsashimi1.getValue().toString());
        if (qtyisZero(qty) && btnsashimi1.isEnabled()) {
            x++;
            if (x == 1) {
                Cart();
            }
            double cost = qty * 299.00;
            total += cost;

            txtArea1.setText(txtArea1.getText() + jLabel17.getText() + "\t\t" + "         Php: " + cost + "\n");
            String data[] = {jLabel17.getText(), jLabel18.getText(), spnsashimi1.getValue().toString(), "Php. " + Double.toString(cost)};
            DefaultTableModel tblModel = (DefaultTableModel) jTable1.getModel();
            tblModel.addRow(data);
        
            spnsashimi1.setValue(0);
            dudate();
        } else {
            btnsashimi1.setSelected(false);
        }
    }//GEN-LAST:event_btnsashimi1ActionPerformed

    private void btnsashimi1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsashimi1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsashimi1MouseExited

    private void btnsashimi1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsashimi1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsashimi1MouseEntered

    private void btnOrderNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderNowActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnOrderNowActionPerformed

    private void jLabel164MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel164MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel164MouseClicked

    private void jLabel165MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel165MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel165MouseClicked

    private void txtTotalCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalCheckoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalCheckoutActionPerformed

    private void txtShippingFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtShippingFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtShippingFeeActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void txtFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyReleased
        // TODO add your handling code here:\

    }//GEN-LAST:event_txtFirstNameKeyReleased

    private void txtLastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtLastNameKeyReleased

    private void comCountryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comCountryKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_comCountryKeyReleased

    private void comRegionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comRegionKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_comRegionKeyReleased

    private void txtPostalCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPostalCodeKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPostalCodeKeyReleased

    private void comCityProvincesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comCityProvincesKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_comCityProvincesKeyTyped

    private void txtAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressKeyPressed

    private void txtAddressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtAddressKeyReleased

    private void txtPhoneNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneNumberKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtPhoneNumberKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtEmailKeyReleased

    private void btnPlacingOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlacingOrderMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setBackground(new Color(254, 143, 157));
    }//GEN-LAST:event_btnPlacingOrderMouseEntered

    private void btnPlacingOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlacingOrderMouseExited
        // TODO add your handling code here:
        evt.getComponent().setBackground(new Color(254, 69, 91));
    }//GEN-LAST:event_btnPlacingOrderMouseExited

    private void txtOrderIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrderIdActionPerformed

    private void txtSubtotalCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalCheckoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalCheckoutActionPerformed

    private void jLabel169MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel169MouseClicked
        // TODO add your handling code here:
        setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jLabel169MouseClicked

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtShippingAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtShippingAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtShippingAmountActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNext2;
    private javax.swing.JButton btnNext3;
    private javax.swing.JButton btnOrderNow;
    private javax.swing.JButton btnReceipt;
    private javax.swing.JButton btnnigiri1;
    private javax.swing.JButton btnnigiri2;
    private javax.swing.JButton btnnigiri3;
    private javax.swing.JButton btnnigiri4;
    private javax.swing.JButton btnnigiri5;
    private javax.swing.JButton btnnigiri6;
    private javax.swing.JButton btnplatter1;
    private javax.swing.JButton btnplatter2;
    private javax.swing.JButton btnplatter3;
    private javax.swing.JButton btnroll1;
    private javax.swing.JButton btnroll2;
    private javax.swing.JButton btnroll3;
    private javax.swing.JButton btnroll4;
    private javax.swing.JButton btnroll5;
    private javax.swing.JButton btnroll6;
    private javax.swing.JButton btnsashimi1;
    private javax.swing.JButton btnsashimi2;
    private javax.swing.JButton btnsashimi3;
    private javax.swing.JButton btnsashimi4;
    private javax.swing.JButton btnsashimi5;
    private javax.swing.JButton btnsashimi6;
    private javax.swing.JComboBox<String> comCityProvinces;
    private javax.swing.JComboBox<String> comCountry;
    public javax.swing.JComboBox<String> comCreditorDebit;
    public javax.swing.JComboBox<String> comEwallet;
    private javax.swing.JComboBox<String> comRegion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JLabel lblpayment;
    private javax.swing.JLabel lbluserName;
    public javax.swing.JRadioButton radCOD;
    private javax.swing.JSpinner spnnigiri1;
    private javax.swing.JSpinner spnnigiri2;
    private javax.swing.JSpinner spnnigiri3;
    private javax.swing.JSpinner spnnigiri4;
    private javax.swing.JSpinner spnnigiri5;
    private javax.swing.JSpinner spnnigiri6;
    private javax.swing.JSpinner spnplatter1;
    private javax.swing.JSpinner spnplatter2;
    private javax.swing.JSpinner spnplatter3;
    private javax.swing.JSpinner spnroll1;
    private javax.swing.JSpinner spnroll2;
    private javax.swing.JSpinner spnroll3;
    private javax.swing.JSpinner spnroll4;
    private javax.swing.JSpinner spnroll5;
    private javax.swing.JSpinner spnroll6;
    private javax.swing.JSpinner spnsashimi1;
    private javax.swing.JSpinner spnsashimi2;
    private javax.swing.JSpinner spnsashimi3;
    private javax.swing.JSpinner spnsashimi4;
    private javax.swing.JSpinner spnsashimi5;
    private javax.swing.JSpinner spnsashimi6;
    private javax.swing.JTextField txtAddress;
    public javax.swing.JTextArea txtArea1;
    private javax.swing.JTextArea txtAreaItems;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    public javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtPostalCode;
    public javax.swing.JTextField txtShippingAmount;
    public javax.swing.JTextField txtShippingFee;
    public javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtSubtotal1;
    public javax.swing.JTextField txtSubtotalCheckout;
    private javax.swing.JTextField txtTax1;
    private javax.swing.JLabel txtTime;
    public javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal1;
    public javax.swing.JTextField txtTotalCheckout;
    // End of variables declaration//GEN-END:variables

}
