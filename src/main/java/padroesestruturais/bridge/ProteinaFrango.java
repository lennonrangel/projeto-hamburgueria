package padroesestruturais.bridge;

public class ProteinaFrango extends Proteina {

    public ProteinaFrango(GrauCoccao grauCoccao) {
        super(grauCoccao);
    }

    @Override
    public String getNome() {
        return "frango grelhado";
    }

    @Override
    protected double getPrecoBase() {
        return 10.0;
    }
}
