package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sanad on 10/07/2017.
 */
public class CustomersForm {
    private JButton returningButton;
    private JButton newButton;
    private JButton VIPButton;
    private JPanel customersPanel = new JPanel();

    public CustomersForm() {
        JFrame frame = new JFrame("CustomersForm");
        frame.setSize(300, 150);

        VIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "VIP customer");
            }
        });
        returningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Returning customer");
            }
        });
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "New customer");
            }
        });

        customersPanel.add(VIPButton);
        customersPanel.add(returningButton);
        customersPanel.add(newButton);
        customersPanel.setVisible(true);
        frame.add(customersPanel);
        frame.setVisible(true);
    }
}
