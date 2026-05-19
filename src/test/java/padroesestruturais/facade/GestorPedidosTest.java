package padroesestruturais.facade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroescomportamentais.chainofresponsability.SemDesconto;
import padroescomportamentais.state.Pedido;
import padroescomportamentais.strategy.PagamentoDinheiro;
import padroescomportamentais.strategy.PagamentoPix;
import padroescomportamentais.templatemethod.ProcessoPreparoFit;
import padroescomportamentais.templatemethod.ProcessoPreparoGourmet;
import padroescriacao.abstractfactory.Hamburguer;
import padroesestruturais.composite.ItemCardapio;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Facade - Gestor de Pedidos")
public class GestorPedidosTest {
    private GestorPedidos sistema;

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [FACADE] " + titulo);
        System.out.println("========================================================");
    }

    private void setup() {
        sistema = new GestorPedidos();
    }

    @Test
    @DisplayName("Deve abrir pedido via facade")
    void deveAbrirPedidoViaFacade() {
        imprimirSeparador("Abertura de Pedido Simplicada");
        setup();
        System.out.println();
        ItemCardapio lanche = new Hamburguer("Burger Clássico", 20.0);
        Pedido pedido = sistema.abrirPedido(lanche, false);
        System.out.println("Pedido criado: " + pedido.getCodigo() + " - Estado: " + pedido.getEstadoAtual());
        assertNotNull(pedido);
        assertEquals("Em preparo", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve preparar e pagar pedido via facade")
    void devePrepararEPagarViaFacade() {
        imprimirSeparador("Fluxo de Preparo e Pagamento");
        setup();
        System.out.println();
        ItemCardapio lanche = new Hamburguer("Burger Fit", 25.0);
        Pedido pedido = sistema.abrirPedido(lanche, false);
        double valorFinal = sistema.prepararEPagar(
                pedido,
                new ProcessoPreparoFit(),
                new PagamentoDinheiro(),
                new SemDesconto()
        );
        System.out.println("Valor Final Pago: R$ " + String.format("%.2f", valorFinal));
        assertEquals("Pronto", pedido.getEstadoAtual());
        assertTrue(valorFinal > 0);
    }

    @Test
    @DisplayName("Deve simplificar fluxo completo via facade")
    void deveSimplificarFluxoCompletoViaFacade() {
        imprimirSeparador("Fluxo Completo de Operação");
        setup();
        System.out.println();
        ItemCardapio lanche = new Hamburguer("Burger Gourmet", 35.0);
        Pedido pedido = sistema.abrirPedido(lanche, true);
        assertNotNull(pedido);
        assertTrue(pedido.isRetiradaBalcao());
        double valor = sistema.prepararEPagar(
                pedido,
                new ProcessoPreparoGourmet(),
                new PagamentoPix(),
                new SemDesconto()
        );
        System.out.println("Fluxo finalizado. Valor: R$ " + String.format("%.2f", valor));
        assertTrue(valor > 0);
    }
}
