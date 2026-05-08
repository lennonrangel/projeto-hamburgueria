package br.com.hamburgueria.pagamento.estrategia;

public class PagamentoPix implements FormaPagamento {

    @Override
    public double calcularValorFinal(double valor) {
        return valor * 0.90;
    }

    @Override
    public String getDescricao() {
        return "Pix com 10% de desconto";
    }
}
