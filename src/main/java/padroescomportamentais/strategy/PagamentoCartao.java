package padroescomportamentais.strategy;

public class PagamentoCartao implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        System.out.println("Processando pagamento em cartão: R$ " + valor);
        return valor;
    }

    @Override
    public String getDescricao() {
        return "Cartão sem desconto";
    }
}
