package hamburgueria.pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.formapagamento.desconto.SemDesconto;
import hamburgueria.formapagamento.PagamentoDinheiro;
import hamburgueria.formapagamento.PagamentoPix;
import hamburgueria.cozinha.ProcessoPreparoFit;
import hamburgueria.cozinha.ProcessoPreparoGourmet;
import hamburgueria.hamburguer.Hamburguer;
import hamburgueria.cardapio.MenuItem;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Facade - Gestor de Pedidos")
public class GestorPedidosTest {
    private GestorPedidos sistema;

    private void imprimirSeparador(String titulo) {
    }

    private void setup() {
        sistema = new GestorPedidos();
    }

    @Test
    @DisplayName("Deve abrir pedido via facade")
    void deveAbrirPedidoViaFacade() {
        imprimirSeparador("Abertura de Pedido Simplicada");
        setup();
        MenuItem lanche = new Hamburguer("Burger Clássico", 20.0);
        Pedido pedido = sistema.abrirPedido(lanche, false);
        assertNotNull(pedido);
        assertEquals("Em preparo", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve preparar e pagar pedido via facade")
    void devePrepararEPagarViaFacade() {
        imprimirSeparador("Fluxo de Preparo e Pagamento");
        setup();
        MenuItem lanche = new Hamburguer("Burger Fit", 25.0);
        Pedido pedido = sistema.abrirPedido(lanche, false);
        double valorFinal = sistema.prepararEPagar(
                pedido,
                new ProcessoPreparoFit(),
                new PagamentoDinheiro(),
                new SemDesconto()
        );
        assertEquals("Pronto", pedido.getEstadoAtual());
        assertTrue(valorFinal > 0);
    }

    @Test
    @DisplayName("Deve simplificar fluxo completo via facade")
    void deveSimplificarFluxoCompletoViaFacade() {
        imprimirSeparador("Fluxo Completo de Operação");
        setup();
        MenuItem lanche = new Hamburguer("Burger Gourmet", 35.0);
        Pedido pedido = sistema.abrirPedido(lanche, true);
        assertNotNull(pedido);
        assertTrue(pedido.isRetiradaBalcao());
        double valor = sistema.prepararEPagar(
                pedido,
                new ProcessoPreparoGourmet(),
                new PagamentoPix(),
                new SemDesconto()
        );
        assertTrue(valor > 0);
    }

    @Test
    @DisplayName("Deve registrar o pedido na central do gestor ao abrir")
    void testGestorPedidosRegistroNaCentral() {
        setup();
        MenuItem lanche = new Hamburguer("Classico", 20.0);
        Pedido pedido = sistema.abrirPedido(lanche, false);
        assertEquals(1, sistema.getCentral().getPedidos().size());
        assertTrue(sistema.getCentral().getPedidos().contains(pedido));
    }

    @Test
    @DisplayName("Deve aplicar desconto do pedido grande e processar pagamento via facade")
    void testGestorPedidosFluxoComDesconto() {
        setup();
        MenuItem lanche = new Hamburguer("Mega Burger", 100.0);
        Pedido pedido = sistema.abrirPedido(lanche, false);
        
        // Desconto de Pedido Grande (10%) + Sem desconto no pagamento (Pix)
        // Preço final deve ser 90.0
        double valorFinal = sistema.prepararEPagar(
                pedido,
                new ProcessoPreparoFit(),
                new PagamentoPix(),
                new hamburgueria.formapagamento.desconto.DescontoPedidoGrande()
        );
        
        assertEquals(90.0, valorFinal, 0.01);
    }
}

