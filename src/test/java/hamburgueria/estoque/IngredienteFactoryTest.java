package hamburgueria.estoque;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Flyweight - Ingredientes Compartilhados")
class IngredienteFactoryTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve reutilizar ingrediente compartilhado")
    void deveReutilizarIngredienteCompartilhado() {
        imprimirSeparador("Reutilização de Ingredientes");

        IngredienteFactory factory = new IngredienteFactory();
        IngredienteCompartilhado cheddarPedido = factory.getIngrediente("Cheddar", "Queijo", 3.0);
        IngredienteCompartilhado cheddarEstoque = factory.getIngrediente("Cheddar", "Queijo", 3.0);

        assertSame(cheddarPedido, cheddarEstoque);
        assertEquals(1, factory.getTotalIngredientesCompartilhados());
    }

    @Test
    @DisplayName("Deve manter estado externo fora do flyweight")
    void deveManterEstadoExternoForaDoFlyweight() {
        imprimirSeparador("Estado Externo do Estoque");

        IngredienteFactory factory = new IngredienteFactory();
        IngredienteCompartilhado bacon = factory.getIngrediente("Bacon", "Adicional", 4.0);

        ItemEstoque lotePequeno = new ItemEstoque(bacon, 5);
        ItemEstoque loteGrande = new ItemEstoque(bacon, 20);

        assertSame(lotePequeno.getIngrediente(), loteGrande.getIngrediente());
        assertEquals(20.0, lotePequeno.getValorTotal(), 0.01);
        assertEquals(80.0, loteGrande.getValorTotal(), 0.01);
    }

    @Test
    @DisplayName("IngredienteCompartilhado possui getters e descrição funcionais")
    void testIngredienteCompartilhadoGettersEDescricao() {
        IngredienteCompartilhado ing = new IngredienteCompartilhado("Bacon", "Extra", 4.0);
        assertEquals("Bacon", ing.getNome());
        assertEquals("Extra", ing.getCategoria());
        assertEquals(4.0, ing.getPrecoUnitario(), 0.01);
        assertEquals("Extra: Bacon", ing.getDescricao());
    }

    @Test
    @DisplayName("IngredienteFactory não incrementa contador ao reutilizar ingrediente")
    void testFactoryNaoIncrementaContadorAoReutilizar() {
        IngredienteFactory factory = new IngredienteFactory();
        factory.getIngrediente("Cebola", "Salada", 1.0);
        int totalAntes = factory.getTotalIngredientesCompartilhados();
        factory.getIngrediente("Cebola", "Salada", 1.0);
        assertEquals(totalAntes, factory.getTotalIngredientesCompartilhados());
    }
}
