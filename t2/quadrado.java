import java.awt.*;
import java.net.*;
import java.io.*;

class quadrado implements Serializable {
  String tipo;
  int l1, l2, l3, l4;

  quadrado(){ }

  public void lerDados(String tp, int lado1, int lado2, int lado3, int lado4){
    tipo = tp;
    l1 = lado1;
    l2 = lado2;
    l3 = lado3;
    l4 = lado4;
  }

  public void indicarTipo(){
    if(l1==l2 && l2==l3 && l3==l4)
      tipo = "Quadrado";
    else if(l1==l2 || l1==l2 || l1==l3 || l1==l4)
      tipo = "Retângulo";
    else
      tipo = "Quadrilátero";
  }
}
