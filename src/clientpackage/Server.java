package serverpackage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.*;
import java.rmi.server.Operation;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
    public static void main(String[] args) 
    {

        String op; 
        

        int x,y,r;


        boolean conti=true;

        try
        {
            InetAddress localIp = InetAddress.getLocalHost();
            InetSocketAddress socketAddress = new InetSocketAddress(localIp, 1234);
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(socketAddress);

            System.out.println("Je suis un serveur en attente la connexion d'un client");

            Socket clientSocket = serverSocket.accept();
            System.out.println("un client est connecté"); 

           
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            while(conti)
            {
                opr op = (opr) in.readObject();
                int o1 = op.getO1();
                int o2 = op.getO2();
                op = op.getop();
                switch (op) 
                {
                    case "+"-> 
                    {
                        r=x+y;
                    }
                    case "-"-> 
                    {
                        r=x-y;
                    }
                    case "*"-> 
                    {
                        r=x*y;
                    }
                    case "/"-> 
                    {
                        r=x/y;
                    }
                    default -> throw new AssertionError();

                }
                out.println(r);
                System.out.println("Resulta envoie succefully!");
            }
            clientSocket.close();
            System.out.println("connexion ferme .");

        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}