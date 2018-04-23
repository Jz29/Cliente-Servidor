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

    while (true) {
      try {
        s = new Socket();
        InetSocketAddress endereco = new InetSocketAddress("localhost", 4321);
        s.connect(endereco,1000);

        Scanner ler = new Scanner(System.in);
        System.out.printf("Produto: ");
        prod = ler.nextLine();
        System.out.printf("Quantidade: ");
        qtd = ler.nextInt();
        // ler.close();

        enviar = new DataOutputStream(s.getOutputStream());
        enviar.writeUTF(prod);
        enviar.writeInt(qtd);

        receber = new DataInputStream(s.getInputStream());
        System.out.println( receber.readUTF() );

        // s.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
