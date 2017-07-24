package com.store;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    JComboBox typeToSearch;
    static JComboBox customers;
    JButton buy,sell,search, report, addCustomer, showProducts, logOut;
    JLabel branch;
    private String name;
    static String[] values = null;
    static String customer = null;

    public StoreInventory(String nameOfBranch) {
        name = nameOfBranch;
        JFrame frame = new JFrame("Store App");
        frame.setSize(300, 150);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Ask for confirmation before terminating the program.
                int option = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to close the application?",
                        "Close Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        Panel= new JPanel();
        frame.add(Panel);

        branch = new JLabel(nameOfBranch);
        branch.setBounds(100, 130, 160, 25);
        Panel.add(branch);

        typeToSearch = new JComboBox();
        updateProducts();
        typeToSearch.setBounds(10, 10, 80, 25);
        Panel.add(typeToSearch);
        //typeToSearch.setVisible(!typeToSearch.isVisible());

        updateProducts();

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
                    String Line = "buy" + "," + nameOfBranch;
                    fromServer = new ObjectInputStream(socket.getInputStream());
                    toServer = new ObjectOutputStream(socket.getOutputStream());
                    toServer.writeObject(Line);
                    updateProducts();
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
                    String Line = "sell" + "," + nameOfBranch + "," + test[0];
                    fromServer = new ObjectInputStream(socket.getInputStream());
                    toServer = new ObjectOutputStream(socket.getOutputStream());
                    toServer.writeObject(Line);
                    updateProducts();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage());
                }
                updateProducts();
            }
        });
        Panel.add(sell);

        addCustomer=new JButton("add Customer");
        addCustomer.setLayout(null);
        addCustomer.setBounds(10, 10, 80, 25);
        addCustomer.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new addCustomer();
                customers.setVisible(true);
                //customers.addItem(customer);
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

        customers = new JComboBox();
        customers.setBounds(100, 130, 160, 25);
        // get customers
        Panel.add(customers);
        //customers.setVisible(!customers.isVisible());

        logOut=new JButton("logOut");
        //logOut.setLayout(null);
        logOut.setBounds(10, 10, 80, 25);
        logOut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        Panel.add(logOut);

        Panel.setVisible(true);
        frame.setVisible(true);
    }

    public String[] getCustomers() {
        ObjectInputStream fromServer;
        ObjectOutputStream toServer;
        String[] customersArr = null;

        try {
            Socket socket = new Socket("localhost", 2004);
            String Line = "getCustomers" + "," + name;
            fromServer = new ObjectInputStream(socket.getInputStream());
            toServer = new ObjectOutputStream(socket.getOutputStream());
            toServer.writeObject(Line);
            customersArr = (String[]) fromServer.readObject();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
        return customersArr;
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
            String Line = "products" + "," + name;
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
