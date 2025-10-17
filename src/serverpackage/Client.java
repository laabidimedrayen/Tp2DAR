package clientpackage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.server.Operation;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) 
    {
        Scanner intScanner = new Scanner(System.in);
        Scanner strScanner = new Scanner(System.in);

        String op= "", forma = "-?\\d+\\s*[+\\-*/]\\s*-?\\d+";
        String[] parts;
        int r,x,y;

        boolean conti = true ,opt = true,numt = true;


        try
        {
            InetAddress serverIP = InetAddress.getLocalHost();

            System.out.println("Je suis un client pas encore connecté…");  
            
            Socket socket = new Socket(serverIP, 1234);
            System.out.println("je suis un client connecté");  

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            

            while(conti)
            {
                while(opt)
                {
                    System.out.print("Entrer une opération (number * number) ou 'exit' pour quitter : ");
                    op = strScanner.nextLine().trim();
                    if(op.matches("-?\\d+\\s*[+\\-*/]\\s*-?\\d+"))
                    {
                        opt = false;
                    }
                }
                parts = op.split("\\s+");
                x = Integer.parseInt(parts[0]);
                op = parts[1];
                y = Integer.parseInt(parts[2]);
                opt=true;
                opr operation = new opr(x, y, op);
                out.writeObject(operation);
                r = in.read();
                System.out.println("La resulta envoi par le serveur est :"+r+".");
                out.flush();
            }
            socket.close();
            System.out.println("connexion ferme .");

        }catch(IOException e )
        {
            e.printStackTrace();
        }
        
    }
    
}