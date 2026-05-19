package padroesestruturais.bridge;

public abstract class Proteina {

    private final GrauCoccao grauCoccao;

    protected Proteina(GrauCoccao grauCoccao) {
        this.grauCoccao = grauCoccao;
    }

    public String getDescricao() {
        return getNome() + " " + grauCoccao.getDescricao();
    }

    public double getPreco() {
        return getPrecoBase() + grauCoccao.getAdicionalPreco();
    }

    public GrauCoccao getGrauCoccao() {
        return grauCoccao;
    }

    public abstract String getNome();
    protected abstract double getPrecoBase();
}