import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class cliente2{
  public static void main( String args[] ) throws IOException {
    int l1, l2, l3, l4;
    String tipo;
    Socket s;
    ObjectOutputStream enviar;
    ObjectInputStream receber;
    Scanner ler = new Scanner(System.in);

    while (true) {
      try {
        s = new Socket();
        InetSocketAddress endereco = new InetSocketAddress("localhost", 4321);
        s.connect(endereco,1000);

        System.out.printf("lado 1: ");
        l1 = ler.nextInt();
        System.out.printf("lado 2: ");
        l2 = ler.nextInt();
        System.out.printf("lado 3: ");
        l3 = ler.nextInt();
        System.out.printf("lado 4: ");
        l4 = ler.nextInt();
        quadrado qdd = new quadrado();
        qdd.lerDados("",l1,l2,l3,l4);

        enviar = new ObjectOutputStream(s.getOutputStream());
        enviar.writeObject(qdd);
        enviar.flush();

        receber = new ObjectInputStream(s.getInputStream());
        qdd = (quadrado) receber.readObject();

        System.out.printf("Tipo é: " + qdd.tipo + "\n");

        s.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
