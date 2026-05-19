package padroescomportamentais.chainofresponsability;

import padroescomportamentais.state.Pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Chain of Responsibility - Descontos")
class DescontoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [CHAIN OF RESPONSIBILITY] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve aplicar desconto de retirada no balcão")
    void deveAplicarDescontoRetirada() {
        imprimirSeparador("Desconto de Retirada (5%)");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        DescontoRetiradaBalcao desconto = new DescontoRetiradaBalcao();
        double valorFinal = desconto.aplicar(pedido, 100.0);
        System.out.println("Valor Original: R$ 100,00");
        System.out.println("Valor com Desconto: R$ " + String.format("%.2f", valorFinal));
        assertEquals(95.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve aplicar desconto para pedidos grandes")
    void deveAplicarDescontoPedidoGrande() {
        imprimirSeparador("Desconto Pedido Grande (10%)");
        System.out.println();
        Pedido pedido = new Pedido();
        DescontoPedidoGrande desconto = new DescontoPedidoGrande();
        double valorFinal = desconto.aplicar(pedido, 60.0);
        System.out.println("Valor Original: R$ 60,00");
        System.out.println("Valor com Desconto: R$ " + String.format("%.2f", valorFinal));
        assertEquals(54.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve encadear descontos")
    void deveEncadearDescontos() {
        imprimirSeparador("Cadeia de Descontos (Grande + Retirada)");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        DescontoPedidoGrande chain = new DescontoPedidoGrande();
        chain.setProximo(new DescontoRetiradaBalcao());
        double valorFinal = chain.aplicar(pedido, 100.0);
        System.out.println("Valor Original: R$ 100,00");
        System.out.println("Valor após Cadeia: R$ " + String.format("%.2f", valorFinal));
        assertEquals(85.5, valorFinal, 0.01);
    }
}
