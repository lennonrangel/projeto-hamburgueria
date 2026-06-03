package padroesestruturais.flyweight;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Flyweight - Ingredientes Compartilhados")
class IngredienteFactoryTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [FLYWEIGHT] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve reutilizar ingrediente compartilhado")
    void deveReutilizarIngredienteCompartilhado() {
        imprimirSeparador("Reutilização de Ingredientes");

        IngredienteFactory factory = new IngredienteFactory();
        IngredienteCompartilhado cheddarPedido = factory.getIngrediente("Cheddar", "Queijo", 3.0);
        IngredienteCompartilhado cheddarEstoque = factory.getIngrediente("Cheddar", "Queijo", 3.0);

        System.out.println("Ingrediente do pedido: " + cheddarPedido.getDescricao());
        System.out.println("Ingrediente do estoque: " + cheddarEstoque.getDescricao());
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

        System.out.println(lotePequeno.getDescricao() + " - R$ " + String.format("%.2f", lotePequeno.getValorTotal()));
        System.out.println(loteGrande.getDescricao() + " - R$ " + String.format("%.2f", loteGrande.getValorTotal()));
        assertSame(lotePequeno.getIngrediente(), loteGrande.getIngrediente());
        assertEquals(20.0, lotePequeno.getValorTotal(), 0.01);
        assertEquals(80.0, loteGrande.getValorTotal(), 0.01);
    }
}
