package padroesestruturais.bridge;

public class BemPassado implements GrauCoccao {
    @Override
    public String getDescricao() {
        return "bem passado";
    }

    @Override
    public double getAdicionalPreco() {
        return 0.0;
    }
}
