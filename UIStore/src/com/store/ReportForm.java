package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportForm {
    private JPanel reportPanel = new JPanel();
    private JButton numberOfSales;
    private JButton productReportButton;
    private JButton VIPCustomersButton;

    public ReportForm(){
        JFrame frame = new JFrame("ReportForm");
        frame.setSize(300, 150);

        numberOfSales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                JOptionPane.showMessageDialog(null, "The number of sales sent to database");
            }
        });

        productReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productForm p = new productForm();
                String chosenProduct = p.getProduct();
                //connect to store project
                JOptionPane.showMessageDialog(null, "the report of the product: " + chosenProduct + " is sent to the database");
            }
        });

        VIPCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //connect to store project
                JOptionPane.showMessageDialog(null, "The vip customers list sent to database");
            }
        });

        reportPanel.add(numberOfSales);
        reportPanel.add(productReportButton);
        reportPanel.add(VIPCustomersButton);
        reportPanel.setVisible(true);
        frame.add(reportPanel);
        frame.setVisible(true);
    }

}
