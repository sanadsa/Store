package com.store;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class registerForm
{
JLabel name,id,password,phone,branchName,acountNumber,typeWorker;
JTextField nameT,idT,phoneT,acountNumberT, passwordField;
JComboBox typeWork,branchNameT;
JButton submit;
    private String BranchPhoneTLV="03-2323232";
    private String BranchPhoneHaifa="02-2323232";
    private JPanel panel= new JPanel();;

    public registerForm()
    {
        JFrame frame = new JFrame("register");
        frame.setSize(250, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        name = new JLabel("name:");
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

        acountNumber = new JLabel("accountNumber:");
        acountNumber.setBounds(10, 70, 80, 25);
        panel.add(acountNumber);

        acountNumberT = new JTextField(20);
        acountNumberT.setBounds(100, 70, 160, 25);
        panel.add(acountNumberT);

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

        password = new JLabel("password:");
        password.setBounds(10, 160, 80, 25);
        panel.add(password);

        passwordField = new JTextField(20);
        passwordField.setBounds(10, 160, 80, 25);
        panel.add(passwordField);

        typeWorker=new JLabel("type worker:");
        typeWorker.setBounds(10, 160, 80, 25);
        panel.add(typeWorker);

        typeWork=new JComboBox();
        typeWork.addItem("");
        typeWork.addItem("responsibleShift");
        typeWork.addItem("Seller");
        typeWork.addItem("Cashier");
        panel.add(typeWork);

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

                if((String)typeWork.getSelectedItem()=="" || nameT.getText()=="" || idT.getText()==""
               || phoneT.getText()=="" ||branchNameT.getSelectedItem()==""||acountNumberT.getText()==""||
                        passwordField.getText().equals(""))
                {
                    msgbox("pleas fill all!");
                }
                else
                    {
                        try
                        {
                            Socket sockt = new Socket("localhost", 2004);
                            fromServer = new ObjectInputStream(sockt.getInputStream());
                            toServer = new ObjectOutputStream(sockt.getOutputStream());
                            String Line = "register"+","+(String)typeWork.getSelectedItem()+","+nameT.getText()+","
                                    +idT.getText()+","+phoneT.getText()+","+branchNameT.getSelectedItem()+
                                    ","+acountNumberT.getText()+","+passwordField.getText();

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
