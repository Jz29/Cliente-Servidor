import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class cliente2{
  public static void main( String args[] ) throws IOException {
    int qtd;
    String prod;
    Socket s;
    DataOutputStream enviar;
    DataInputStream receber;
    Scanner ler = new Scanner(System.in);

    while (true) {
      try {
        s = new Socket();
        InetSocketAddress endereco = new InetSocketAddress("localhost", 4321);
        s.connect(endereco,1000);

        System.out.printf("Produto: ");
        prod = ler.nextInt();
        System.out.printf("Quantidade: ");
        qtd = ler.nextInt();

        enviar = new DataOutputStream(s.getOutputStream());
        enviar.writeUTF(prod);
        enviar.writeInt(qtd);

        s.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
