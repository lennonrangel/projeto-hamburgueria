package hamburgueria.formapagamento;

public class PagamentoDinheiro implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        double valorFinal = valor * 0.90;
        return valorFinal;
    }

    @Override
    public String getDescricao() {
        return "Dinheiro com 10% de desconto";
    }
}
