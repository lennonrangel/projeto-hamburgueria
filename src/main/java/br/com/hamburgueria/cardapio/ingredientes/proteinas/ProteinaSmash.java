package br.com.hamburgueria.cardapio.ingredientes.proteinas;

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
        return 14.0;
    }
}
