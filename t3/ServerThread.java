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

        for (int x = 0; x < estoque.size(); x++) {
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


        if (!encontrou){
          if(qtd < 0) // DIMINUIR ESTOQUE
            this.aux = "Produto Inexistente";
          else { // ADICIONAR O PRODUTO
            Estoque estq = new Estoque(prod, qtd);
            estoque.add(estq);
            this.aux = "Estoque de " + prod + " e quantidade de: " + qtd;
          }
        }

        enviar = new DataOutputStream(socket.getOutputStream());
        if(this.aux == null)
          enviar.writeUTF("A receber informações ");
        else
          enviar.writeUTF(this.aux);


        for (int x = 0; x < estoque.size(); x++)
          System.out.println("Produto: " + estoque.get(x).produto + "\t\t[" + estoque.get(x).qtd + "]");



        // socket.close();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
