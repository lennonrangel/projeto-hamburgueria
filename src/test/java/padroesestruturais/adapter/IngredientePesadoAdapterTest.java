package padroesestruturais.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroesestruturais.composite.ItemCardapio;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Adapter - Ingrediente por Peso")
class IngredientePesadoAdapterTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [ADAPTER] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve adaptar ingrediente externo para item de cardapio")
    void deveAdaptarIngredienteExternoParaItemCardapio() {
        imprimirSeparador("Adaptacao de Ingrediente");
        System.out.println();
        IngredientePesadoExterno cheddarExterno = new IngredientePesadoExterno("Cheddar especial", 150.0, 40.0);
        ItemCardapio cheddarAdaptado = new IngredientePesadoAdapter(cheddarExterno);

        System.out.println("Ingrediente externo recebido:");
        System.out.println("Nome: " + cheddarExterno.obterNome());
        System.out.println("Peso: " + String.format("%.0f", cheddarExterno.obterPesoEmGramas()) + "g");
        System.out.println("Preco por kg: R$ " + String.format("%.2f", cheddarExterno.obterPrecoPorQuilo()));
        System.out.println();
        System.out.println("Ingrediente adaptado para ItemCardapio:");
        System.out.println("Descricao: " + cheddarAdaptado.getDescricao());
        System.out.println("Preco calculado: R$ " + String.format("%.2f", cheddarAdaptado.getPreco()));

        assertEquals("Cheddar especial (150g)", cheddarAdaptado.getDescricao());
        assertEquals(6.0, cheddarAdaptado.getPreco(), 0.01);
    }
}
