package padroesestruturais.bridge;

public class AoPonto implements GrauCoccao {
    @Override
    public String getDescricao() {
        return "ao ponto";
    }

    @Override
    public double getAdicionalPreco() {
        return 0.0;
    }
}
