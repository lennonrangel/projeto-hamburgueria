package hamburgueria.pedido.relatorio;

import hamburgueria.pedido.Pedido;

import hamburgueria.hamburguer.Hamburguer;
import hamburgueria.hamburguer.proteina.ProteinaPicanha;
import hamburgueria.hamburguer.ponto.AoPonto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Visitor - Operações no Pedido")
class RelatorioPedidoTest {

    private void imprimirSeparador(String titulo) {
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

    @Test
    @DisplayName("Deve aplicar double dispatch via Pedido.aceitar(visitante)")
    void deveAplicarDoubleDispatchViaAceitar() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("X-Burger", 25.0));

        RelatorioPedido visitor = new CalcularTotal();
        String resultadoDireto = visitor.visitarPedido(pedido);
        String resultadoViaAceitar = pedido.aceitar(visitor);

        assertEquals(resultadoDireto, resultadoViaAceitar);
        assertTrue(resultadoViaAceitar.contains("Total do pedido"));
    }

    @Test
    @DisplayName("Deve calcular total R$ 0,00 para pedido vazio")
    void testCalcularTotalPedidoVazio() {
        Pedido pedido = new Pedido();
        RelatorioPedido visitor = new CalcularTotal();
        String resultado = visitor.visitarPedido(pedido);
        assertTrue(resultado.contains("Total do pedido " + pedido.getCodigo() + ": R$ 0,00") || resultado.contains("R$ 0.00"));
    }

    @Test
    @DisplayName("Deve imprimir resumo vazio para pedido sem itens")
    void testImpressorResumoPedidoVazio() {
        Pedido pedido = new Pedido();
        RelatorioPedido visitor = new ImpressorResumo();
        String resultado = visitor.visitarPedido(pedido);
        assertEquals("Resumo do pedido " + pedido.getCodigo() + ": ", resultado);
    }
}
