package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ReportForm {
    private JPanel reportPanel = new JPanel();
    private JButton numberOfSales;
    private JButton productReportButton;
    private JButton VIPCustomersButton;
    private Socket socket;

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

                try
                {
                    String Line = "report" +  "," + "numberOfSales";
                    fromServer = new ObjectInputStream (socket.getInputStream());
                    toServer = new ObjectOutputStream(socket.getOutputStream());
                    toServer.writeObject(Line);
                    Line = fromServer.readObject().toString();
                    System.out.println(Line);
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage());}

                JOptionPane.showMessageDialog(null, "The number of sales sent to database");
            }
        });
        reportPanel.add(numberOfSales);

        productReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productForm p = new productForm();
                String chosenProduct = p.getProduct();
                //connect to store project
                while(p.isClosed() == false) {
                    ObjectInputStream fromServer;
                    ObjectOutputStream toServer;

                    try {
                        String Line = "report" + "," + "reportOfProduct" + "," + chosenProduct;
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        toServer.writeObject(Line);
                        Line = fromServer.readObject().toString();
                        System.out.println(Line);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "the report of the product: " + chosenProduct + " is sent to the database");
                }
            }
        });
        reportPanel.add(productReportButton);

        VIPCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                try
                {
                    String Line = "report" +  "," + "vipCustomers";
                    fromServer = new ObjectInputStream (socket.getInputStream());
                    toServer = new ObjectOutputStream(socket.getOutputStream());
                    toServer.writeObject(Line);
                    Line = fromServer.readObject().toString();
                    System.out.println(Line);
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage());}

                JOptionPane.showMessageDialog(null, "The vip customers list sent to database");
            }
        });
        reportPanel.add(VIPCustomersButton);

        reportPanel.setVisible(true);
        frame.add(reportPanel);
        frame.setVisible(true);
    }
}
