package hamburgueria.formapagamento.desconto;

import hamburgueria.pedido.Pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Chain of Responsibility - Descontos")
class DescontoTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve aplicar desconto de retirada no balcão")
    void deveAplicarDescontoRetirada() {
        imprimirSeparador("Desconto de Retirada (5%)");
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        DescontoRetiradaBalcao desconto = new DescontoRetiradaBalcao();
        double valorFinal = desconto.aplicar(pedido, 100.0);
        assertEquals(95.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve aplicar desconto para pedidos grandes")
    void deveAplicarDescontoPedidoGrande() {
        imprimirSeparador("Desconto Pedido Grande (10%)");
        Pedido pedido = new Pedido();
        DescontoPedidoGrande desconto = new DescontoPedidoGrande();
        double valorFinal = desconto.aplicar(pedido, 60.0);
        assertEquals(54.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve encadear descontos")
    void deveEncadearDescontos() {
        imprimirSeparador("Cadeia de Descontos (Grande + Retirada)");
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        DescontoPedidoGrande chain = new DescontoPedidoGrande();
        chain.setProximo(new DescontoRetiradaBalcao());
        double valorFinal = chain.aplicar(pedido, 100.0);
        assertEquals(85.5, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve aplicar desconto grande para 50.01")
    void testDescontoGrandeAcimaDoLimite() {
        Pedido pedido = new Pedido();
        double valorFinal = new DescontoPedidoGrande().aplicar(pedido, 50.01);
        assertEquals(45.009, valorFinal, 0.001);
    }

    @Test
    @DisplayName("Não deve aplicar desconto grande para exatamente 50.00")
    void testDescontoGrandeNoLimite() {
        Pedido pedido = new Pedido();
        double valorFinal = new DescontoPedidoGrande().aplicar(pedido, 50.00);
        assertEquals(50.00, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Não deve aplicar desconto grande para 49.99")
    void testDescontoGrandeAbaixoDoLimite() {
        Pedido pedido = new Pedido();
        double valorFinal = new DescontoPedidoGrande().aplicar(pedido, 49.99);
        assertEquals(49.99, valorFinal, 0.01);
    }

    @Test
    @DisplayName("SemDesconto não deve alterar o preço")
    void testSemDesconto() {
        Pedido pedido = new Pedido();
        double valorFinal = new SemDesconto().aplicar(pedido, 100.0);
        assertEquals(100.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Não deve aplicar desconto de retirada quando entrega for em domicilio")
    void testDescontoRetiradaBalcaoParaPedidoSemRetirada() {
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaDomicilio();
        DescontoRetiradaBalcao desconto = new DescontoRetiradaBalcao();
        double valorFinal = desconto.aplicar(pedido, 100.0);
        assertEquals(100.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Cadeia deve terminar corretamente quando proximo for nulo")
    void testProximoComRetornoNulo() {
        Pedido pedido = new Pedido();
        DescontoPedidoGrande desconto = new DescontoPedidoGrande();
        desconto.setProximo(null);
        double valorFinal = desconto.aplicar(pedido, 100.0);
        assertEquals(90.0, valorFinal, 0.01);
    }
}
