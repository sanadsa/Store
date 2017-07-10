package com.store;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
public class StoreInventory
{
    private JPanel Panel;
    JComboBox typeToSearch;
    JButton buy,sell,search, report;
    JLabel amount;

    public StoreInventory() {

        JFrame frame = new JFrame("store app");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Panel= new JPanel();
        frame.add(Panel);
        amount= new JLabel("0");
        amount.setBounds(10, 10, 80, 25);
        Panel.add(amount);

         typeToSearch =new JComboBox();
        typeToSearch.addItem("SportsPants");
        typeToSearch.addItem("customMade");
        typeToSearch.addItem("jeans");
        typeToSearch.addItem("tShirt");
        typeToSearch.addItem("TailoredShirt");
        typeToSearch.addItem("coat");
        typeToSearch.addItem("sweater");
        typeToSearch.setBounds(10, 10, 80, 25);
        Panel.add(typeToSearch);

        buy=new JButton("buy");
        buy.setLayout(null);
        buy.setBounds(10, 10, 80, 25);
        buy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new CustomersForm();
                JOptionPane.showMessageDialog(null, "choose which customer will buy");
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
                new CustomersForm();
                JOptionPane.showMessageDialog(null, "choose which customer will sell");
            }
        });
        Panel.add(sell);

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
}
