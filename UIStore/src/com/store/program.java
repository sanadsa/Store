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
            new connection();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());}
    }
}
