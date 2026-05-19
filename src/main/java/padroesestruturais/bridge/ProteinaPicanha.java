package padroesestruturais.bridge;

public class ProteinaPicanha extends Proteina {

    public ProteinaPicanha(GrauCoccao grauCoccao) {
        super(grauCoccao);
    }

    @Override
    public String getNome() {
        return "picanha";
    }

    @Override
    protected double getPrecoBase() {
        return 16.0;
    }
}
