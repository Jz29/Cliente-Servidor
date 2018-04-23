import java.awt.*;
import java.net.*;
import java.io.*;

class servidor {

  public static void main( String args[] ) {
    ServerSocket server = (ServerSocket)null;
    Socket socket;
    // String cadena = "Tutorial de Java!";
    int n1, n2, n3;
    boolean flag = true;
    DataOutputStream enviar;
    DataInputStream receber1, receber2, receber3;

    try {
      server = new ServerSocket( 4321,300 );
    } catch( IOException e ) {
      System.out.println( e );
    }

    try {
      while( true ) {
        socket = server.accept();

        receber1 = new DataInputStream(socket.getInputStream()); // Recebe do cliente
        receber2 = new DataInputStream(socket.getInputStream()); // Recebe do cliente
        receber3 = new DataInputStream(socket.getInputStream()); // Recebe do cliente

        n1 = receber1.readInt();
        n2 = receber2.readInt();
        n3 = receber3.readInt();

        enviar = new DataOutputStream(socket.getOutputStream()); // Escreve ao cliente
        enviar.writeUTF("Números recebidos ");

        if(n1 < 0)
          break;
        else {
          ServerThread st = new ServerThread(socket);
          st.n1 = n1;
          st.n2 = n2;
          st.n3 = n3;

          st.start();
        }
      }
    } catch( IOException e ) {
      System.out.println( e );
    }
  }
}
