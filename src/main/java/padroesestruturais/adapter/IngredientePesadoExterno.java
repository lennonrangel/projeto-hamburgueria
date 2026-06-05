package padroesestruturais.adapter;

public class IngredientePesadoExterno {

    private final String nome;
    private final double pesoEmGramas;
    private final double precoPorQuilo;

    public IngredientePesadoExterno(String nome, double pesoEmGramas, double precoPorQuilo) {
        this.nome = nome;
        this.pesoEmGramas = pesoEmGramas;
        this.precoPorQuilo = precoPorQuilo;
    }

    public String obterNome() {
        return nome;
    }

    public double obterPesoEmGramas() {
        return pesoEmGramas;
    }

    public double obterPrecoPorQuilo() {
        return precoPorQuilo;
    }
}
