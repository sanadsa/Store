package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class addCustomer {
    JLabel name,id,phone,branchName, customerType;
    JTextField nameT,idT,phoneT;
    JPasswordField passwordField;
    JComboBox customerTypeCombo, branchNameT;
    JButton submit;
    private String BranchPhoneTLV="03-2323232";
    private String BranchPhoneHaifa="02-2323232";
    private JPanel panel= new JPanel();;

    public addCustomer()
    {
        JFrame frame = new JFrame("Add Customer");
        frame.setSize(250, 450);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        branchName = new JLabel("branchName:         ");
        branchName.setBounds(10, 130, 80, 25);
        panel.add(branchName);

        branchNameT = new JComboBox();
        branchNameT.addItem("");
        branchNameT.addItem("TLV");
        branchNameT.addItem("Haifa");
        branchNameT.setBounds(100, 130, 160, 25);
        panel.add(branchNameT);


        customerType =new JLabel("customer type:");
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

                if((String) customerTypeCombo.getSelectedItem()=="" || nameT.getText()=="" || idT.getText()==""
                        || phoneT.getText()=="")
                {
                    msgbox("pleas fill all!");
                }
                else
                {
                    try
                    {
                        Socket socket = new Socket("localhost", 2004);
                        fromServer = new ObjectInputStream(socket.getInputStream());
                        toServer = new ObjectOutputStream(socket.getOutputStream());
                        String Line = "customer"+","+(String) customerTypeCombo.getSelectedItem()+","+nameT.getText()+","
                                +idT.getText()+","+phoneT.getText()+","+branchNameT.getSelectedItem();

                        toServer.writeObject(Line);
                        Line = (String) fromServer.readObject();
                        System.out.println(Line);
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
                //new worker
                // add worker to branch
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

