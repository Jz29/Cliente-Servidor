import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class cliente2{
    public static void main( String args[] ) throws IOException {

        int c;
	      String msg;
        boolean flag = true;
        Socket s;
        DataInputStream sIn, r1, r2;
        DataOutputStream s1out;

        Scanner ler = new Scanner(System.in);
          System.out.printf("Servidor:\n");
        String ip = ler.nextLine();
          System.out.printf("Porta:\n");
        int porta = ler.nextInt();

        while (flag) {

          System.out.printf("> ");
          int n1 = ler.nextInt();
          System.out.printf("> ");
          int n2 = ler.nextInt();
          System.out.printf("> ");
          int n3 = ler.nextInt();
          // String msg = ler.nextLine();

          // if (ipserver != "terminar") { // CONDIÇÃO DE PARADA


            // System.out.printf("Mensagem:\n");
            // String msg1 = ler.nextLine();

            try {

               // s = new Socket(InetAddress.getByName("127.0.0.1"),4321 ); //pelo numero IP

               s = new Socket();

               InetSocketAddress endereco = new InetSocketAddress(ip, porta);
               //s = new Socket("localhost",4321 );  pelo nome

               s.connect(endereco,1000);
               s1out = new DataOutputStream(s.getOutputStream());
               s1out.writeInt(n1);
               s1out.writeInt(n2);
               s1out.writeInt(n3);

               sIn = new DataInputStream(s.getInputStream()); // recebe msg do servidor
               ip = sIn.readUTF();
               System.out.println("\t\t"+ip);

               r1 = new DataInputStream(s.getInputStream());
               System.out.println("Maior: "+r1.readInt());
               r2 = new DataInputStream(s.getInputStream());
               System.out.println("Menor: "+r2.readInt());

               s.close();
            } catch( IOException e ) {
                System.out.println( e );
            }
          // }
          // else
          //   flag = false;
      }
  }
}
