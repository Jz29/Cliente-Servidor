import java.util.ArrayList;
import java.awt.*;
import java.net.*;
import java.io.*;


class ServerThread extends Thread {

  public String prod, aux;
  public int qtd;
  public boolean encontrou = false;
  public ArrayList<Estoque> estoque = new ArrayList<Estoque>();
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

        int x;
        for (x = 0; x < estoque.size(); x++) {
          System.out.println(x + " | " + estoque.get(x).produto + " | ");
          if(estoque.get(x).produto.equals(prod)){
            encontrou = true;
            if(qtd < 0){ // DIMINUIR ESTOQUE
              this.aux = estoque.get(x).rm(prod, qtd);
              if(estoque.get(x).qtd == 0)
                estoque.remove(x);
            }
            else
              this.aux = estoque.get(x).add(prod, qtd);
          }
        }

        System.out.println(this.aux);

        if (!encontrou){
          if(qtd < 0) // DIMINUIR ESTOQUE
            System.out.println("Produto Inexistente");
          else { // ADICIONAR O PRODUTO
            Estoque estq = new Estoque(prod, qtd);
            estoque.add(estq);
            System.out.println("Produto Adicionado!");
          }
        }



        // socket.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
