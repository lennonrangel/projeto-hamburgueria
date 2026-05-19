package padroesestruturais.bridge;

public class ProteinaSmash extends Proteina {

    public ProteinaSmash(GrauCoccao grauCoccao) {
        super(grauCoccao);
    }

    @Override
    public String getNome() {
        return "smash burger";
    }

    @Override
    protected double getPrecoBase() {
        return 12.0;
    }
}
