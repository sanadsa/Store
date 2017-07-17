package com.store;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Sanad on 10/07/2017.
 */
public class productForm {
    private JPanel productPanel = new JPanel();
    private JTextPane chooseProductToGetTextPane;
    private JComboBox productsJCombo;
    private JButton enterButton;
    private String product;
    private boolean isClosed = false;

    public boolean isClosed() {
        return isClosed;
    }

    public productForm() {
        JFrame frameProduct = new JFrame("ProductForm");
        frameProduct.setSize(300, 150);

        frameProduct.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.out.println("A is closing");
                isClosed = true;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("A has closed");
                isClosed = true;
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 product = (String) productsJCombo.getSelectedItem();
            }
        });
        productPanel.add(enterButton);

        productsJCombo =new JComboBox();
        productsJCombo.addItem("SportsPants");
        productsJCombo.addItem("customMade");
        productsJCombo.addItem("jeans");
        productsJCombo.addItem("tShirt");
        productsJCombo.addItem("TailoredShirt");
        productsJCombo.addItem("coat");
        productsJCombo.addItem("sweater");
        productsJCombo.setBounds(10, 10, 80, 25);
        productPanel.add(productsJCombo);

        //productPanel.add(chooseProductToGetTextPane);
        productPanel.setVisible(true);
        frameProduct.add(productPanel);
        frameProduct.setVisible(true);
    }

    public String getProduct() {
        return product;
    }
}
