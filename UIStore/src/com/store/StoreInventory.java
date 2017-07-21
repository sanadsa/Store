package com.store;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
public class StoreInventory
{
    private JPanel Panel;
    JComboBox typeToSearch, branchNameT;
    JButton buy,sell,search, report, addCustomer, showProducts;
    JLabel amount;
    static String[] values = null;

    public StoreInventory() {
        JFrame frame = new JFrame("store app");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel= new JPanel();
        frame.add(Panel);

        branchNameT = new JComboBox();
        branchNameT.addItem("TLV");
        branchNameT.addItem("Haifa");
        branchNameT.setBounds(100, 130, 160, 25);
        branchNameT.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               updateProducts();
            }
        });
        Panel.add(branchNameT);

        amount= new JLabel("0");
        amount.setBounds(10, 10, 80, 25);
        //Panel.add(amount);

        typeToSearch = new JComboBox();
        updateProducts();
        typeToSearch.setBounds(10, 10, 80, 25);
        Panel.add(typeToSearch);
        //typeToSearch.setVisible(!typeToSearch.isVisible());

        buy=new JButton("buy");
        buy.setLayout(null);
        buy.setBounds(10, 10, 80, 25);
        buy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                try {
                    Socket socket = new Socket("localhost", 2004);
                    String Line = "buy" + "," + branchNameT.getSelectedItem();
                    fromServer = new ObjectInputStream(socket.getInputStream());
                    toServer = new ObjectOutputStream(socket.getOutputStream());
                    toServer.writeObject(Line);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }

                updateProducts();
            }
        });
        Panel.add(buy);

        sell=new JButton("sell");
        sell.setLayout(null);
        sell.setBounds(10, 10, 80, 25);
        sell.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //new CustomersForm();
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                try {
                    Socket socket = new Socket("localhost", 2004);
                    String[] test = typeToSearch.getSelectedItem().toString().split(" ");
                    String Line = "sell" + "," + branchNameT.getSelectedItem() + "," + test[0];
                    fromServer = new ObjectInputStream(socket.getInputStream());
                    toServer = new ObjectOutputStream(socket.getOutputStream());
                    toServer.writeObject(Line);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
            }
        });
        Panel.add(sell);

//        showProducts = new JButton("show products in inventory");
//        showProducts.setBounds(180, 80, 80, 25);
//        showProducts.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                if(typeToSearch.isVisible()) {
//                    typeToSearch.setVisible(typeToSearch.isVisible());
//                }
//                else{
//                    typeToSearch.setVisible(!typeToSearch.isVisible());
//                }
//                getNumberOfSales();
//                typeToSearch.removeAllItems();
//                typeToSearch.addItem("SportsPants " + values[0]);
//                typeToSearch.addItem("customMade " + values[1]);
//                typeToSearch.addItem("jeans " + values[2]);
//                typeToSearch.addItem("tShirt " + values[3]);
//                typeToSearch.addItem("TailoredShirt " + values[4]);
//                typeToSearch.addItem("coat " + values[5]);
//                typeToSearch.addItem("sweater " + values[6]);
//            }
//        });
//        //Panel.add(showProducts);

        addCustomer=new JButton("add Customer");
        addCustomer.setLayout(null);
        addCustomer.setBounds(10, 10, 80, 25);
        addCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new addCustomer();
            }
        });
        Panel.add(addCustomer);

        report=new JButton("report");
        report.setLayout(null);
        report.setBounds(10, 10, 80, 25);
        report.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new ReportForm();
            }
        });
        Panel.add(report);

        search=new JButton("search");
        search.setLayout(null);
        search.setBounds(10, 10, 80, 25);
        Panel.add(search);
        Panel.setVisible(true);
        frame.setVisible(true);
    }

    public void updateProducts(){
        getNumberOfSales();
        typeToSearch.removeAllItems();
        typeToSearch.addItem("SportsPants " + values[0]);
        typeToSearch.addItem("customMade " + values[1]);
        typeToSearch.addItem("jeans " + values[2]);
        typeToSearch.addItem("tShirt " + values[3]);
        typeToSearch.addItem("TailoredShirt " + values[4]);
        typeToSearch.addItem("coat " + values[5]);
        typeToSearch.addItem("sweater " + values[6]);
    }

    public String[] getNumberOfSales(){
        ObjectInputStream fromServer;
        ObjectOutputStream toServer;

        try {
            Socket socket = new Socket("localhost", 2004);
            String Line = "products" + "," + branchNameT.getSelectedItem();
            fromServer = new ObjectInputStream(socket.getInputStream());
            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject(Line);
            values = (String[]) fromServer.readObject();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
        return values;
    }
}
