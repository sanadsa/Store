package com.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class connection extends JFrame
{
    private JPanel panel;

    connection(JPanel panel)
    {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login:");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ObjectInputStream fromServer;
                ObjectOutputStream  toServer;
                if( userText.getText()=="" || passwordText.getText().equals(""))
                {
                   registerForm.msgbox("pleas fill all!");
                }
                else
                    {
                        try
                        {
                            Socket sockt = new Socket("localhost", 2004);

                            String Line = "search" + "," + userText.getText() + "," + passwordText.getText().toString();

                              fromServer = new ObjectInputStream (sockt.getInputStream());
                              toServer = new ObjectOutputStream(sockt.getOutputStream());
                              toServer.writeObject(Line);
                              Line = fromServer.readObject().toString();
                              if(Line.equals("null"))
                              {
                                  JOptionPane.showMessageDialog(null,"the worker not found,register please");
                              }
                              else {
                                  StoreInventory r = new StoreInventory(Line);
                              }
                        }
                        catch (Exception e1){
                            JOptionPane.showMessageDialog(null, e1.getMessage());}
                    }
            }
        });
        panel.add(loginButton);

        JButton registerButton = new JButton("register:");
        registerButton.setBounds(180, 80, 80, 25);
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new registerForm();
            }
        });
        panel.add(registerButton);
    }
}
