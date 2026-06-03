package padroesestruturais.flyweight;

public class ItemEstoque {
    private final IngredienteCompartilhado ingrediente;
    private final int quantidade;

    public ItemEstoque(IngredienteCompartilhado ingrediente, int quantidade) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public IngredienteCompartilhado getIngrediente() {
        return ingrediente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return ingrediente.getPrecoUnitario() * quantidade;
    }

    public String getDescricao() {
        return quantidade + "x " + ingrediente.getDescricao();
    }
}
