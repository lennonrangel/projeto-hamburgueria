package padroescomportamentais.strategy;

import padroescriacao.abstractfactory.ClassicoFactory;
import padroescomportamentais.state.Pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Strategy - Formas de Pagamento")
class PagamentoTest {
    private Pedido pedido;
    private double valorPedido;

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [STRATEGY] " + titulo);
        System.out.println("========================================================");
        System.out.println();
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        imprimirSeparador(testInfo.getDisplayName());
        pedido = new Pedido();
        pedido.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        valorPedido = pedido.calcularTotal();
    }

    @Test
    @DisplayName("Deve processar pagamento em Dinheiro")
    void deveProcessarPagamentoDinheiro() {
        FormaPagamento formaPagamento = new PagamentoDinheiro();
        ProcessadorPagamento processador = new ProcessadorPagamento(formaPagamento);
        double valorPago = processador.pagar(pedido);
        assertEquals(valorPedido * 0.90, valorPago, 0.01);
    }

    @Test
    @DisplayName("Deve processar pagamento em Cartão")
    void deveProcessarPagamentoCartao() {
        FormaPagamento formaPagamento = new PagamentoCartao();
        ProcessadorPagamento processador = new ProcessadorPagamento(formaPagamento);
        double valorPago = processador.pagar(pedido);
        assertTrue(valorPago > 0);
    }

    @Test
    @DisplayName("Deve processar pagamento em Pix")
    void deveProcessarPagamentoPix() {
        FormaPagamento formaPagamento = new PagamentoPix();
        ProcessadorPagamento processador = new ProcessadorPagamento(formaPagamento);
        double valorPago = processador.pagar(pedido);
        assertEquals(valorPedido, valorPago, 0.01);
    }
}
