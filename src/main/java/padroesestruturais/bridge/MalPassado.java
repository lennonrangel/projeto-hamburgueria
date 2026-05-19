package padroesestruturais.bridge;

public class MalPassado implements GrauCoccao {
    @Override
    public String getDescricao() {
        return "mal passado";
    }

    @Override
    public double getAdicionalPreco() {
        return 0.0;
    }
}
