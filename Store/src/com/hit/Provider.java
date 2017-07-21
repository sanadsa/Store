package com.hit; /**
 * Created by snir on 01/07/2017.
 */

import com.hit.customer.Customer;
import com.hit.worker.Worker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Provider
{
    public static BufferedReader DataBaseTLVForRead;
    public static BufferedReader DataBaseHiafaForRead;

    private static PrintWriter DataBaseTLVForWrite;
    private static PrintWriter DataBaseHiafaforWrite;

    static
    {
        try
        {
            DataBaseHiafaforWrite = new PrintWriter(new BufferedWriter
                    (new FileWriter("C:\\dev\\java\\text1.txt")));
            DataBaseTLVForWrite =new PrintWriter( new BufferedWriter
                    (new FileWriter("C:\\dev\\java\\text.txt")));
        }
        catch (final IOException e) {
        throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    ServerSocket providerSocket;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;

    public Provider()
    {
    }

    public static void readDataBase() throws IOException
    {
        try
        {
            StringBuilder sb = new StringBuilder();
            String line = DataBaseTLVForRead.readLine();
            while (line != null)
            {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = DataBaseTLVForRead.readLine();
                String everything = sb.toString();
                /////////
            }

            StringBuilder hifa = new StringBuilder();
            String hifaLine = DataBaseHiafaForRead.readLine();
            while (hifaLine != null)
            {
                hifa.append(line);
                hifa.append(System.lineSeparator());
                hifaLine = DataBaseHiafaForRead.readLine();
                String everythingh = hifa.toString();
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void upDate() throws IOException
    {
        try
        {
            for (Worker workerobj : storeManager.HaifaStore.getWorkerInBranch())
            {
                DataBaseHiafaforWrite.println(workerobj.toString());
                DataBaseHiafaforWrite.flush();
            }
            for (Worker workerobj : storeManager.TLVStore.getWorkerInBranch())
            {
                DataBaseTLVForWrite.write(workerobj.toString());
                DataBaseTLVForWrite.flush();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void sendMessage(String msg)
    {
        try
        {
            out.writeObject(msg);
            out.flush();
            System.out.println("server>" + msg);
        } catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    public void run()
    {
        try
        {
            //1. creating a server socket
            providerSocket = new ServerSocket(2004, 10);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = providerSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new ObjectOutputStream(connection.getOutputStream());
            out.flush();
            in = new ObjectInputStream(connection.getInputStream());
            //4. The two parts communicate via the input and output streams
                try
                {
                    message = (String) in.readObject();
                    System.out.println(message);
                    String[] allParameter = storeManager.getAction(message);
                    switch (allParameter[0])
                    {
                        case "register":
                            Worker newWorker = storeManager.createWorkerAndInsert(allParameter);
                            upDate();
                            break;
                        case "search":
                            Worker LoginWorker = storeManager.searchWorker(allParameter[1], allParameter[2]);
                            if (LoginWorker == null)
                            {
                                out.writeObject(null);
                            } else
                            {
                                sendMessage(LoginWorker.toString());
                            }
                            break;
                        case "report":
                            Report report = new Report();
                            switch (allParameter[1]){
                                case "numberOfSales":
                                    report.getQuantityOfSales(storeManager.getBranch(out.toString()));
                                    break;
                                case "reportOfProduct":
                                    report.showReportOfProduct(allParameter[2]);
                                    break;
                                case "vipCustomers":
                                    report.getVipCustomers(allParameter[2]);
                                    break;
                            }
                        case "customer":
                            Customer newCustomer = storeManager.createCustomer(allParameter);
                            //write to database
                            break;
                        case "products":
                            Map<Product.productType, Integer> test = new HashMap<Product.productType, Integer>();
                            String[] values = new String[Product.productType.values().length];

                            if (allParameter[1].equals("TLV")){
                                test = storeManager.TLVStore.getNumberOfSales();
                            }
                            else if(allParameter[1].equals("Haifa")){
                                test = storeManager.HaifaStore.getNumberOfSales();
                            }

                            for(int i=0; i<Product.productType.values().length; i++){
                                values[i] = test.get(Product.productType.values()[0]).toString();
                            }
                            out.writeObject(values);
                            break;
                        case "buy":
                            storeManager.addProducts(allParameter);
                    }
                    sendMessage(message);

                    System.out.println("client>" + message);
                    if (message.equals("bye"))
                        sendMessage("bye");
                } catch (ClassNotFoundException classnot)
                {
                    System.err.println("Data received in unknown format");
                }

        } catch (IOException ioException)
        {
            ioException.printStackTrace();
        } finally
        {
            //4: Closing connection
            try
            {
                in.close();
                out.close();
                providerSocket.close();
            } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }
}
