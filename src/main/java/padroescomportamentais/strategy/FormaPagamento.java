package padroescomportamentais.strategy;

public interface FormaPagamento {
    double calcularValorFinal(double valor);
    String getDescricao();
}
