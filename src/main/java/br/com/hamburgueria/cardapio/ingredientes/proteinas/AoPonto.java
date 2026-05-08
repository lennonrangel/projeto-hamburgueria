package br.com.hamburgueria.cardapio.ingredientes.proteinas;

public class AoPonto implements GrauCoccao {

    @Override
    public String getDescricao() {
        return "ao ponto";
    }

    @Override
    public double getAdicionalPreco() {
        return 0.0;
    }
}
