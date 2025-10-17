package serverpackage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Server {
    public static void main(String[] args) 
    {

        String op; 
        String[] parts;

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

           
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while(conti)
            {
                op = in.readLine();
                parts = op.split("\\s+");
                x = Integer.parseInt(parts[0]);
                op = parts[1];
                y = Integer.parseInt(parts[2]);
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