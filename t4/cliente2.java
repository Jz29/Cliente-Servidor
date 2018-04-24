import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class cliente2{
  public static void main( String args[] ) throws IOException {
    int op;
    Socket s;
    String pasta, ipserver, comando;
    FileOutputStream file;
    DataInputStream receber;
    Scanner ler = new Scanner(System.in);

    System.out.println("IP do Servidor: ");
    ipserver = ler.nextLine();
    System.out.println(" 1 - FTP:4321 \n 2 - Comandos Remotos");
    op = ler.nextInt();
    
    while (true) {
      switch (op) {
        case 1:
          System.out.println("Endereço do arquivo: ");
          pasta = ler.nextLine();

          break;
        case 2:
          System.out.println("Comando: ");
          comando = ler.nextLine();

          break;
        default:
          break;

      }




      try {
        s = new Socket();
        InetSocketAddress endereco = new InetSocketAddress(ipserver, 4321);
        s.connect(endereco,1000);



        try {
          // byte[] buffer = new byte[4096];
          // ENVIANDO ARQUIVO
          // receber = new FileOutputStream("client.txt");

          // RECEBENDO ARQUIVO
          byte[] buffer = new byte[10*1024];
          receber = new DataInputStream(s.getInputStream());
          file = new FileOutputStream("C/server.txt");
          int lendoBytes = 0;
          while ((lendoBytes = receber.read(buffer, 0, 10*1024)) > 0) {
            file.write(buffer, 0, lendoBytes);
          }
          // receber.flush();
          file.close();
          receber.close();
          // -----------------
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
        // finally {
        //   if (file != null) file.close();
        //   if (receber != null) receber.close();
        // }

        // enviar = new ObjectOutputStream(s.getOutputStream());

        // receber = new ObjectInputStream(s.getInputStream());

        // s.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
