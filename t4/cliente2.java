import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;

class cliente2{
  public static void main( String args[] ) throws IOException {
    int op;
    Socket s;
    String pasta, ipserver, comando;
    DataOutputStream up, enviar;
    DataInputStream down, receber;
    FileOutputStream fileOUT;
    FileInputStream fileIN;
    Scanner ler = new Scanner(System.in);

    System.out.println("IP do Servidor: ");
    ipserver = ler.nextLine();

    while (true) {
      try {
        s = new Socket();
        InetSocketAddress endereco = new InetSocketAddress(ipserver, 4321);
        s.connect(endereco,1000);

        up = new DataOutputStream(s.getOutputStream());

        System.out.println(" 1 - FTP:4321 \n 2 - Comandos Remotos");
        op = ler.nextInt();
        switch (op) {
          case 1:
            System.out.println(" 1 - DOWNLOAD \n 2 - UPLOAD");
            op = ler.nextInt();
            System.out.println("Endereço do arquivo: ");
            pasta = ler.nextLine();

            up.writeInt(op);
            up.writeUTF(pasta);

            if(op == 1){      // RECEBENDO ARQUIVO

              byte[] buffer = new byte[10*1024];
              receber = new DataInputStream(s.getInputStream());
              fileOUT = new FileOutputStream("C/server.txt"); // salvar como
              int lendoBytes = 0;
              while ((lendoBytes = receber.read(buffer, 0, 10*1024)) > 0)
                fileOUT.write(buffer, 0, lendoBytes);
              fileOUT.close();
              receber.close();
              System.out.println("Arquivo recebido!");
            }
            else if(op == 2){ // ENVIANDO ARQUIVO

              byte[] buffer = new byte[10*1024];
              enviar = new DataOutputStream(s.getOutputStream());
              fileIN = new FileInputStream("S/server.txt");
              while(fileIN.read(buffer) > 0)
                enviar.write(buffer);
              enviar.flush();
              fileIN.close();
              enviar.close();
              System.out.println("Arquivo enviado!");
            }
            break;
          case 2:
            up.writeInt(3);
            comando = "mkdir UFT/";
            System.out.println("Comando: \n"+comando);
            // comando = ler.nextLine(); // NÃO ESTÁ FUNCIONANDO
            up.writeUTF(comando);
            System.out.println("Comando enviado!");
            break;

          default:
            break;
        }
        // -----------------
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch( Exception e ) {
        System.out.println( e );
      }
    }
  }
}
