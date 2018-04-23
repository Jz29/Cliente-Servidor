import java.awt.*;
import java.net.*;
import java.io.*;


class ServerThread extends Thread {

  int n1, n2, n3, maior, menor;
  DataOutputStream enviar1, enviar2;
  // DataInputStream receber1, receber2, receber3;
  public Socket socket;

  ServerThread(Socket s){
    super();
    this.socket = s;
  }

  public void run(){

    try {

      if(n1 > n2){
        maior = n1;
        menor = n2;
      }
      else {
        menor = n1;
        maior = n2;
      }

      if(maior < n3)
      maior = n3;

      if(menor > n3)
      menor = n3;

      enviar1 = new DataOutputStream(socket.getOutputStream()); // Escreve ao cliente
      enviar1.writeInt(maior);
      enviar2 = new DataOutputStream(socket.getOutputStream()); // Escreve ao cliente
      enviar2.writeInt(menor);

      socket.close();
    } catch( IOException e ) {
      System.out.println( e );
    }
  }
}
