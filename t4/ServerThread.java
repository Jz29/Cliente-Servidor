import java.awt.*;
import java.net.*;
import java.io.*;


class ServerThread extends Thread {

  public Socket socket;
  public DataOutputStream enviar;
  public FileInputStream file;

  ServerThread(Socket s){
    super();
    this.socket = s;
  }

  public void run(){

    try {
      // ENVIANDO ARQUIVO
      enviar = new DataOutputStream(socket.getOutputStream());
      file = new FileInputStream("S/server.txt");
      byte[] buffer = new byte[10*1024];
      while(file.read(buffer) > 0) {
        enviar.write(buffer);
		  }
      enviar.flush();
      file.close();
      enviar.close();

      // RECEBENDO ARQUIVO
      // receber = new FileInputStream("server.txt");
      // int lendoBytes = receber.read();
      // while (lendoBytes != -1) {
      //   System.out.println(lendoBytes);
      //   lendoBytes = receber.read();
      // }
      // receber.close();

      socket.close();
      // -----------------
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch( Exception e ) {
      System.out.println( e );
    }
    // finally {
    //   if (this.file != null) this.file.close();
    //   if (this.enviar != null) this.enviar.close();
    //   if (this.socket != null) this.socket.close();
    // }
  }
}
