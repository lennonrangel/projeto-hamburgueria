package padroescomportamentais.strategy;

public class PagamentoDinheiro implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        double valorFinal = valor * 0.90;
        System.out.println("Processando pagamento em dinheiro (10% desconto): R$ " + String.format("%.2f", valorFinal));
        return valorFinal;
    }

    @Override
    public String getDescricao() {
        return "Dinheiro com 10% de desconto";
    }
}
