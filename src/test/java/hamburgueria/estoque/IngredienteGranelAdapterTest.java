package hamburgueria.estoque;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.cardapio.MenuItem;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Adapter - Ingrediente por Peso (Granel)")
class IngredienteGranelAdapterTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve adaptar ingrediente a granel para item de cardapio")
    void deveAdaptarIngredienteGranelParaMenuItem() {
        imprimirSeparador("Adaptacao de Ingrediente");
        IngredienteGranel cheddarExterno = new IngredienteGranel("Cheddar especial", 150.0, 40.0);
        MenuItem cheddarAdaptado = new IngredienteGranelAdapter(cheddarExterno);


        assertEquals("Cheddar especial (150g)", cheddarAdaptado.getDescricao());
        assertEquals(6.0, cheddarAdaptado.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve calcular preco como zero quando peso for zero")
    void deveCalcularPrecoZeroQuandoPesoZero() {
        IngredienteGranel ingrediente = new IngredienteGranel("Tomate seco", 0.0, 50.0);
        MenuItem adaptado = new IngredienteGranelAdapter(ingrediente);
        assertEquals(0.0, adaptado.getPreco(), 0.01);
        assertEquals("Tomate seco (0g)", adaptado.getDescricao());
    }

    @Test
    @DisplayName("Deve calcular preco como zero quando preco por quilo for zero")
    void deveCalcularPrecoZeroQuandoPrecoPorQuiloZero() {
        IngredienteGranel ingrediente = new IngredienteGranel("Sal", 10.0, 0.0);
        MenuItem adaptado = new IngredienteGranelAdapter(ingrediente);
        assertEquals(0.0, adaptado.getPreco(), 0.01);
        assertEquals("Sal (10g)", adaptado.getDescricao());
    }

    @Test
    @DisplayName("Deve formatar peso decimal para inteiro mais proximo")
    void deveArredondarPesoDecimalNaDescricao() {
        IngredienteGranel ingrediente = new IngredienteGranel("Rucula", 12.6, 30.0);
        MenuItem adaptado = new IngredienteGranelAdapter(ingrediente);
        assertEquals("Rucula (13g)", adaptado.getDescricao());
    }
}

