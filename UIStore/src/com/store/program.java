package com.store;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class program
{
    public static void main(String arg[])
    {
        try
        {
            Socket socket = new Socket("localhost", 2004);
            new connection(socket);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());}
    }
}
