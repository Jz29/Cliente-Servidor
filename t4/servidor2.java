import java.awt.*;
import java.net.*;
import java.io.*;

class servidor {

  public static void main( String args[] ) {
    ServerSocket server = (ServerSocket)null;
    Socket socket;

    try {
      server = new ServerSocket( 4321,300 );
    } catch( IOException e ) {
      System.out.println( e );
    }

    try {
      while( true ) {
        socket = server.accept();

          ServerThread st = new ServerThread(socket);
          st.start();
        }
      } catch( IOException e ) {
      System.out.println( e );
    }
  }
}
