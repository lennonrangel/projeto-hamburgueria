package padroesestruturais.flyweight;

import java.util.HashMap;
import java.util.Map;

public class IngredienteFactory {
    private final Map<String, IngredienteCompartilhado> ingredientes = new HashMap<>();

    public IngredienteCompartilhado getIngrediente(String nome, String categoria, double precoUnitario) {
        String chave = gerarChave(nome, categoria);
        return ingredientes.computeIfAbsent(chave, item -> new IngredienteCompartilhado(nome, categoria, precoUnitario));
    }

    public int getTotalIngredientesCompartilhados() {
        return ingredientes.size();
    }

    private String gerarChave(String nome, String categoria) {
        return categoria.toLowerCase() + ":" + nome.toLowerCase();
    }
}
