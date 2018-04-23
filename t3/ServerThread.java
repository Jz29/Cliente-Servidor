import java.util.ArrayList;
import java.awt.*;
import java.net.*;
import java.io.*;


class ServerThread extends Thread {

  public String prod;
  public int qtd;
  public boolean encontrou = false;
  public ArrayList<Estoque> estoque;
  public Socket socket;
  public DataOutputStream enviar;
  public DataInputStream receber;

  ServerThread(ArrayList<Estoque> estoque, Socket s){
    super();
    this.socket = s;
    this.estoque = estoque;
  }

  public void run(){

    while(true){
      try {

        receber = new DataInputStream(socket.getInputStream());
        prod = receber.readUTF();
        if(prod == "terminar")
          break;

        receber = new DataInputStream(socket.getInputStream());
        qtd = receber.readInt();

        for (int x = 0; x < estoque.size() ; x++) {
          if(estoque.get(x).produto == prod){
            encontrou = true;
            if(qtd < 0){ // DIMINUIR ESTOQUE
              estoque.get(x).rm(prod, qtd);
              if(estoque.get(x).qtd == 0)
                estoque.remove(x);
            }
            else
              estoque.get(x).add(prod, qtd);
          }
        }

        if (!encontrou){
          if(qtd < 0) // DIMINUIR ESTOQUE
            System.out.println("Quantidade Insuficiente");
          else { // ADICIONAR O PRODUTO
            Estoque aux = new Estoque(prod, qtd);
            estoque.add(aux);
          }
        }



        socket.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
