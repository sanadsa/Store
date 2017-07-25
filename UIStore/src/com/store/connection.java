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
    private Socket socket;
    connection(Socket sockt)
    {
        socket = sockt;
        JFrame frame = new JFrame("Store app - LogIn");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Ask for confirmation before terminating the program.
                int option = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to close the application?",
                        "Close Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JPanel panel = new JPanel();
        frame.add(panel);
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

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ObjectInputStream fromServer;
                ObjectOutputStream  toServer;
                if( userText.getText().equals("") || passwordText.getText().equals(""))
                {
                   registerForm.msgbox("pleas fill all!");
                }
                else
                    {
                        try
                        {
                              String Line = "search" + "," + userText.getText() + "," + passwordText.getText().toString();
                              fromServer = new ObjectInputStream (socket.getInputStream());
                              toServer = new ObjectOutputStream(socket.getOutputStream());
                              toServer.writeObject(Line);
                              Line = fromServer.readObject().toString();
                              if(Line.equals("null"))
                              {
                                  JOptionPane.showMessageDialog(null,"the worker not found, register please");
                              }
                              else {
                                  StoreInventory r = new StoreInventory(Line, sockt);
                              }
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
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new registerForm(sockt);
            }
        });
        panel.add(registerButton);

        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }
}
