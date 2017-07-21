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
    private JComboBox productsJCombo, branchNameT;
    private Socket socket;
    private String product;

    public ReportForm(){
        JFrame frame = new JFrame("ReportForm");
        frame.setSize(300, 150);

        try {
           socket = new Socket("localhost", 2004);
        }
        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }

        numberOfSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;


                if (branchNameT.getSelectedItem() == "") {
                    msgbox("please choose branch");
                } else {
                    try {
                        String Line = "report" + "," + "numberOfSales" + "," + branchNameT.getSelectedItem();
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(null, "The number of sales sent to database");
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

        branchName = new JLabel("branchName:         ");
        branchName.setBounds(10, 130, 80, 25);
        reportPanel.add(branchName);

        branchNameT = new JComboBox();
        branchNameT.addItem("");
        branchNameT.addItem("TLV");
        branchNameT.addItem("Haifa");
        branchNameT.setBounds(100, 130, 160, 25);
        reportPanel.add(branchNameT);

        productReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                product = (String) productsJCombo.getSelectedItem();
                //productForm p = new productForm();
                //String chosenProduct = p.getProduct();
                //connect to store project
                    ObjectInputStream fromServer;
                    ObjectOutputStream toServer;

                if (branchNameT.getSelectedItem() == "") {
                    msgbox("please choose branch");
                } else {
                    try {
                        String Line = "report" + "," + "reportOfProduct" + "," + product;
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                }
                    JOptionPane.showMessageDialog(null, "the report of the product: " + product + " is sent to the database");
            }
        });
        reportPanel.add(productReportButton);

        VIPCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                if (branchNameT.getSelectedItem() == "") {
                    msgbox("please choose branch");
                } else {
                    try {
                        String Line = "report" + "," + "vipCustomers" + "," + branchNameT.getSelectedItem();
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }

                    JOptionPane.showMessageDialog(null, "The vip customers list sent to database");
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
