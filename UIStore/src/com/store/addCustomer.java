package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static com.store.StoreInventory.customer;
import static com.store.StoreInventory.customers;

public class addCustomer {
    JLabel name,id,phone,branchName, customerType;
    JTextField nameT,idT,phoneT;
    JComboBox customerTypeCombo;
    JButton submit;
    private JPanel panel= new JPanel();;
    private Socket sockt;

    public addCustomer(String nameOfBranch, Socket socket)
    {
        sockt = socket;
        JFrame frame = new JFrame("Add Customer");
        frame.setSize(250, 450);

        branchName = new JLabel(nameOfBranch);
        branchName.setBounds(10, 130, 80, 25);
        panel.add(branchName);

        name = new JLabel("full name:");
        name.setBounds(10, 10, 80, 25);
        panel.add(name);

        nameT = new JTextField(20);
        nameT.setBounds(100, 10, 160, 25);
        panel.add(nameT);

        id = new JLabel("id:");
        id.setBounds(10, 40, 80, 25);
        panel.add(id);

        idT = new JTextField(20);
        idT.setBounds(100, 40, 160, 25);
        panel.add(idT);

        phone = new JLabel("phone:");
        phone.setBounds(10, 100, 80, 25);
        panel.add(phone);

        phoneT = new JTextField(20);
        phoneT.setBounds(100, 100, 160, 25);
        panel.add(phoneT);


        customerType = new JLabel("customer type:");
        customerType.setBounds(10, 160, 80, 25);
        panel.add(customerType);

        customerTypeCombo =new JComboBox();
        customerTypeCombo.addItem("");
        customerTypeCombo.addItem("NewCustomer");
        customerTypeCombo.addItem("ReturningCustomer");
        customerTypeCombo.addItem("VIPCustomer");
        panel.add(customerTypeCombo);

        submit=new JButton("submit");
        submit.setBounds(10, 300, 80, 25);
        panel.add(submit);

        submit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ObjectInputStream fromServer;
                ObjectOutputStream toServer;

                if(customerTypeCombo.getSelectedItem().toString().equals("") || nameT.getText().equals("") || idT.getText().equals("")
                        || phoneT.getText().equals(""))
                {
                    msgbox("pleas fill all!");
                }
                else
                {
                    try
                    {
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        String Line = "customer"+","+(String) customerTypeCombo.getSelectedItem()+","+nameT.getText()+","
                                +idT.getText()+","+phoneT.getText()+","+branchName.getText();
                        toServer.writeObject(Line);
                    }
                    catch (Exception e1){
                        JOptionPane.showMessageDialog(null, e1.getMessage());}
                }

                try
                {
                    this.finalize();
                    frame.setVisible(false);
                } catch (Throwable throwable)
                {
                    throwable.printStackTrace();
                }
            }
        });

        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);

    }

    public static void msgbox(String s)
    {
        JOptionPane.showMessageDialog(null, s);
    }
}

