package hamburgueria.formapagamento;

import hamburgueria.linhaproduto.ClassicoFactory;
import hamburgueria.pedido.Pedido;

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

    @Test
    @DisplayName("Deve permitir alterar a forma de pagamento dinamicamente")
    void testAlterarFormaPagamentoDinamico() {
        ProcessadorPagamento processador = new ProcessadorPagamento(new PagamentoCartao());
        assertEquals(valorPedido, processador.pagar(pedido), 0.01);

        processador.setFormaPagamento(new PagamentoDinheiro());
        assertEquals(valorPedido * 0.90, processador.pagar(pedido), 0.01);
    }

    @Test
    @DisplayName("Deve obter a descricao correta da forma de pagamento")
    void testGetDescricaoPagamento() {
        ProcessadorPagamento processador = new ProcessadorPagamento(new PagamentoDinheiro());
        assertEquals("Dinheiro com 10% de desconto", processador.getDescricaoPagamento());

        processador.setFormaPagamento(new PagamentoCartao());
        assertEquals("Cartão sem desconto", processador.getDescricaoPagamento());

        processador.setFormaPagamento(new PagamentoPix());
        assertEquals("Pix sem desconto", processador.getDescricaoPagamento());
    }
}
