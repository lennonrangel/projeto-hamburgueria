package br.com.hamburgueria.pagamento.estrategia;

public interface FormaPagamento {
    double calcularValorFinal(double valor);
    String getDescricao();
}
