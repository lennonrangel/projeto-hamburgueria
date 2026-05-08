package br.com.hamburgueria.cardapio.ingredientes.proteinas;

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
        return 18.0;
    }
}
