package padroescomportamentais.strategy;

public class PagamentoPix implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        System.out.println("Processando pagamento via Pix: R$ " + valor);
        return valor;
    }

    @Override
    public String getDescricao() {
        return "Pix sem desconto";
    }
}
