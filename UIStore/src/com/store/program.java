package com.store;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by snir on 25/05/2017.
 */
public class program
{
    public static void main(String arg[])
    {
        try
        {
//           Socket sockt = new Socket("localhost", 7000);
//            Scanner s=new Scanner(sockt.getInputStream());
//           Scanner scanner= new Scanner (System.in);
//           String Line=scanner.nextLine();
//           PrintStream p=new PrintStream(sockt.getOutputStream());
//           p.println(Line);
//            Line=s.nextLine();
//         System.out.println(Line);
           //     System.out.println(input.readLine()); // Read one line and output it
             //   input.close();


            JFrame frame = new JFrame("Store app - LogIn");
            frame.setSize(300, 150);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            new connection(panel);
            frame.setVisible(true);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());}
    }
}
