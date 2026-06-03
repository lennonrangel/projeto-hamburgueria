package padroesestruturais.flyweight;

public class IngredienteCompartilhado {
    private final String nome;
    private final String categoria;
    private final double precoUnitario;

    public IngredienteCompartilhado(String nome, String categoria, double precoUnitario) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public String getDescricao() {
        return categoria + ": " + nome;
    }
}
