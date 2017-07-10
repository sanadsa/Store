package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sanad on 10/07/2017.
 */
public class productForm {
    private JPanel productPanel = new JPanel();
    private JTextPane chooseProductToGetTextPane;
    private JComboBox productsJCombo;
    private JButton enterButton;
    private String product;

    public productForm() {
        JFrame frame = new JFrame("CustomersForm");
        frame.setSize(300, 150);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String product = (String) productsJCombo.getSelectedItem();
            }
        });

        productPanel.add(chooseProductToGetTextPane);
        productPanel.add(productsJCombo);
        productPanel.add(enterButton);
        productPanel.setVisible(true);
        frame.add(productPanel);
        frame.setVisible(true);

    }

    public String getProduct() {
        return product;
    }
}
