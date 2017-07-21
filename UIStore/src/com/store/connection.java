package com.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login:");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               StoreInventory r = new StoreInventory();

                ObjectInputStream fromServer;
                ObjectOutputStream  toServer;
                if( userText.getText()=="" || passwordText.getPassword().equals(""))
                {
                   registerForm.msgbox("pleas fill all!");
                }
                else
                    {
                        try
                        {
                            Socket sockt = new Socket("localhost", 2004);

                            String Line = "search" + "," + userText.getText() + "," + passwordText.getPassword().toString();

                              fromServer = new ObjectInputStream (sockt.getInputStream());
                              toServer = new ObjectOutputStream(sockt.getOutputStream());
                              toServer.writeObject(Line);
                              Line = fromServer.readObject().toString();
                              System.out.println(Line);
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
