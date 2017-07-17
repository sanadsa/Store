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

    public static void main(String[] args ) throws IOException{

        Provider server = new Provider();
            while(true){
                server.run();
            }

    }
}
