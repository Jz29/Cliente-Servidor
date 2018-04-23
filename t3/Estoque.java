class Estoque {
  String produto;
  int qtd;

  Estoque(String prod, int qtd){
    this.produto = prod;
    this.qtd = qtd;
  }

  public String add(String prod, int qtd){
    this.qtd = this.qtd + qtd;
    return ("Estoque de " + prod + " e quantidade de: " + this.qtd);
  }

  public String rm(String prod, int qtd){
    if((this.qtd + qtd) >= 0){
      this.qtd = this.qtd + qtd;
      return ("Estoque de " + prod + " e quantidade de: " + this.qtd);
    }
    return ("Quantidade Insuficiente!");
  }
}
