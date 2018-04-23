class Estoque {
  String produto;
  int qtd;

  Estoque(String prod, int qtd){
    this.produto = prod;
    this.qtd = qtd;
  }

  public void add(String prod, int qtd){
    this.qtd = this.qtd + qtd;
  }

  public void rm(String prod, int qtd){
    if([this.qtd - qtd] >= 0)
      this.qtd = this.qtd - qtd;
    else
      System.out.println("Quantidade Insuficiente!");
  }
}
