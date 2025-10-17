package clientpackage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) 
    {
        Scanner intScanner = new Scanner(System.in);
        Scanner strScanner = new Scanner(System.in);

        int x,y;

        String op="";

        boolean conti = true ,opt = true;


        try
        {
            InetAddress serverIP = InetAddress.getLocalHost();

            System.out.println("Je suis un client pas encore connecté…");  
            
            Socket socket = new Socket(serverIP, 1234);
            System.out.println("je suis un client connecté");  

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            

            while(conti)
            {
                System.out.print("Entrer Le premier nomber(0 si vous desirez quitter) : ");
                x = intScanner.nextInt();
                out.writeInt(x);
                System.out.print("Entrer Le Deuxieme nomber(0 si vous desirez quitter) : ");
                y = intScanner.nextInt();
                out.writeInt(y);
                opt=true;
                while(opt)
                {
                    System.out.print("Entrer L operation( + | - | * | / ) : ");
                    op = strScanner.nextLine();
                    if(op.equals("+")|| op.equals("-") || op.equals("/") || op.equals("*"))
                    {
                        opt = false;
                    }
                }
                out.writeUTF(op);

                if(x==0 && y==0)
                {
                    conti = false;
                }
                x = in.readInt();

                System.out.println("La resulta envoi par le serveur est :"+x+".");
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