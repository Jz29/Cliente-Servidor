import java.util.ArrayList;

class p {
  public static void main(String[] args) {
    ArrayList<Estoque> estoque = null;

    Estoque estq = new Estoque("A", 40);
    // estoque.add(estq);
    Estoque estq1 = new Estoque("B", 500);
    estoque.add(estq1);
    Estoque estq2 = new Estoque("A", 20);
    estoque.add(estq2);
    Estoque estq3 = new Estoque("A", -7);
    estoque.add(estq3);

    System.out.println(estoque.isEmpty());
  }
}
