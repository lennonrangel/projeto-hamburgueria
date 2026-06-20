package hamburgueria.formapagamento;

public interface FormaPagamento {
    double calcularValorFinal(double valor);
    String getDescricao();
}
