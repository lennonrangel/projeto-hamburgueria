package padroescomportamentais.visitor;

import padroescomportamentais.state.Pedido;
import padroescriacao.abstractfactory.Hamburguer;
import padroesestruturais.bridge.ProteinaPicanha;
import padroesestruturais.bridge.AoPonto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Teste do Padrão Visitor - Operações no Pedido")
class RelatorioPedidoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [VISITOR] " + titulo);
        System.out.println("========================================================");
        System.out.println();
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        imprimirSeparador(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Deve calcular total usando visitor")
    void deveCalcularTotalVisitor() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("X-Burger", 25.0));
        RelatorioPedido visitor = new CalcularTotal();
        String resultado = visitor.visitarPedido(pedido);
        assertTrue(resultado.contains("Total do pedido"));
        assertTrue(resultado.contains("R$ 25,00"));
    }

    @Test
    @DisplayName("Deve imprimir resumo usando visitor")
    void deveImprimirResumoVisitor() {
        Pedido pedido = new Pedido();
        Hamburguer gourmet = new Hamburguer("Gourmet Picanha", "Brioche", "cheddar", new ProteinaPicanha(new AoPonto()), 35.0);
        pedido.adicionarItem(gourmet);

        RelatorioPedido visitor = new ImpressorResumo();
        String resultado = visitor.visitarPedido(pedido);

        assertTrue(resultado.contains("Resumo do pedido"));
        assertTrue(resultado.contains("pão Brioche"));
        assertTrue(resultado.contains("queijo cheddar"));
        assertTrue(resultado.contains("picanha ao ponto"));
    }
}
