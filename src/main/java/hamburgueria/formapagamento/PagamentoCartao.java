package hamburgueria.formapagamento;

public class PagamentoCartao implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        return valor;
    }

    @Override
    public String getDescricao() {
        return "Cartão sem desconto";
    }
}
