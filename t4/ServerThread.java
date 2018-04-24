import java.awt.*;
import java.net.*;
import java.io.*;


class ServerThread extends Thread {
  int op;
  Socket socket;
  String instrucao, arquivo;
  DataOutputStream enviar, up;
  DataInputStream receber, down;
  FileOutputStream fileOUT;
  FileInputStream fileIN;

  ServerThread(Socket s){
    super();
    this.socket = s;
  }

  public void run(){

    try {

      down = new DataInputStream(socket.getInputStream());
      op = down.readInt();

      byte[] buffer = new byte[10*1024];
      switch (op) {
        case 1: // ENVIANDO ARQUIVO
          arquivo = down.readUTF();
          enviar = new DataOutputStream(socket.getOutputStream());
          fileIN = new FileInputStream("S/server.txt");
          while(fileIN.read(buffer) > 0)
            enviar.write(buffer);
          enviar.flush();
          fileIN.close();
          enviar.close();
          break;

        case 2: // RECEBENDO ARQUIVO
          arquivo = down.readUTF();
          receber = new DataInputStream(socket.getInputStream());
          fileOUT = new FileOutputStream("S/server.txt"); // salvar como
          int lendoBytes = 0;
          while ((lendoBytes = receber.read(buffer, 0, 10*1024)) > 0)
            fileOUT.write(buffer, 0, lendoBytes);
          fileOUT.close();
          receber.close();
          break;

        case 3:
          instrucao = down.readUTF();
          Runtime run = Runtime.getRuntime();
          run.exec(instrucao);
          break;

        default:
          break;
      }

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
