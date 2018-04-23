import java.awt.*;
import java.net.*;
import java.io.*;


class ServerThread extends Thread {

  public Socket socket;
  public ObjectOutputStream enviar;
  public ObjectInputStream receber;

  ServerThread(Socket s){
    super();
    this.socket = s;
  }

  public void run(){

    try {
      receber = new ObjectInputStream(socket.getInputStream());
      quadrado qdd = (quadrado) receber.readObject();
      qdd.indicarTipo();

      enviar = new ObjectOutputStream(socket.getOutputStream());
      enviar.writeObject(qdd);
      enviar.flush();

      socket.close();
    } catch( Exception e ) {
      System.out.println( e );
    }
  }
}
