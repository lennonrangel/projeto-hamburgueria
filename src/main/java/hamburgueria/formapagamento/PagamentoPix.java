package hamburgueria.formapagamento;

public class PagamentoPix implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        return valor;
    }

    @Override
    public String getDescricao() {
        return "Pix sem desconto";
    }
}
