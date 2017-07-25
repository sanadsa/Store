package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReportForm {
    private JPanel reportPanel = new JPanel();
    private JButton numberOfSales, productReportButton, VIPCustomersButton;
    private JLabel branchName;
    private JComboBox productsJCombo;
    private Socket sockt;
    private String product;

    public ReportForm(String nameOfBranch, Socket socket){
        sockt = socket;
        JFrame frame = new JFrame("ReportForm");
        frame.setSize(300, 150);

        /**
         * get number of all sales in the current branch
         * @param nameOfBranch the name of the current branch
         */
        numberOfSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                    try {
                        String Line = "report" + "," + "numberOfSales" + "," + branchName.getText();
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                        JOptionPane.showMessageDialog(null, "The number of sales sent to database");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
            }
        });
        reportPanel.add(numberOfSales);

        productsJCombo =new JComboBox();
        productsJCombo.addItem("SportsPants");
        productsJCombo.addItem("customMade");
        productsJCombo.addItem("jeans");
        productsJCombo.addItem("tShirt");
        productsJCombo.addItem("TailoredShirt");
        productsJCombo.addItem("coat");
        productsJCombo.addItem("sweater");
        productsJCombo.setBounds(10, 10, 80, 25);
        reportPanel.add(productsJCombo);

        branchName = new JLabel(nameOfBranch);
        branchName.setBounds(10, 130, 80, 25);
        reportPanel.add(branchName);

        /**
         * send the report of products inventory of the current branch to DB
         *  @param nameOfBranch the current branch
         */
        productReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product = (String) productsJCombo.getSelectedItem();

                //connect to store project
                    ObjectInputStream fromServer;
                    ObjectOutputStream toServer;

                    try {
                        String Line = "report" + "," + "reportOfProduct" + "," + product + "," + branchName.getText();
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                        JOptionPane.showMessageDialog(null, "the report of the products in branch " + nameOfBranch + " is sent to the database");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
            }
        });
        reportPanel.add(productReportButton);

        /**
         * send all vip customers in the current branch to the DB
         * @param nameOfBranch the current branch
         */
        VIPCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                if (branchName.getText().equals( "")) {
                    msgbox("please choose branch");
                } else {
                    try {
                        String Line = "report" + "," + "vipCustomers" + "," + branchName.getText();
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                        JOptionPane.showMessageDialog(null, "The vip customers list sent to database");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
            }
        });
        reportPanel.add(VIPCustomersButton);

        reportPanel.setVisible(true);
        frame.add(reportPanel);
        frame.setVisible(true);
    }

    public static void msgbox(String s)
    {
        JOptionPane.showMessageDialog(null, s);
    }
}
