package br.com.hamburgueria.pagamento.estrategia;

public class PagamentoDinheiro implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        return valor * 0.95;
    }

    @Override
    public String getDescricao() {
        return "Dinheiro com 5% de desconto";
    }
}
