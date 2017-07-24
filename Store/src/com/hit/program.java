package com.hit;

import com.sun.corba.se.spi.activation.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by snir on 07/06/2017.
 */
public class program
{

    public static void main(String[] args )   throws IOException{

        //1. creating a server socket
        ServerSocket  providerSocket = new ServerSocket(2004, 10);
            while(true){
                System.out.println("wait to connection");
                Socket  connection = providerSocket.accept();
                new Thread(new Provider(connection)).run();
            }

    }
}
