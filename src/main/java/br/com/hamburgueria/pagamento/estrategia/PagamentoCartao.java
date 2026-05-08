package br.com.hamburgueria.pagamento.estrategia;

public class PagamentoCartao implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        return valor;
    }

    @Override
    public String getDescricao() {
        return "Cartao sem desconto";
    }
}
