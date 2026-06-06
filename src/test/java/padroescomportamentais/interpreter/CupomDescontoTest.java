package padroescomportamentais.interpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroescomportamentais.state.Pedido;
import padroescriacao.abstractfactory.Hamburguer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Interpreter - Cupom de Desconto")
class CupomDescontoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [INTERPRETER] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve aplicar cupom quando regra for interpretada como verdadeira")
    void deveAplicarCupomQuandoRegraVerdadeira() {
        imprimirSeparador("Cupom Aplicado");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        pedido.adicionarItem(new Hamburguer("Burger Grande", 60.0));

        ExpressaoPedido regra = new ExpressaoE(
                new ExpressaoRetiradaBalcao(),
                new ExpressaoTotalMaiorQue(50.0)
        );
        CupomDesconto cupom = new CupomDesconto("BALCAO10", 0.10, regra);

        double valorFinal = cupom.aplicar(pedido, pedido.calcularTotal());

        System.out.println("Regra interpretada: retirada no balcao E total maior que R$ 50,00");
        System.out.println("Retirada no balcao: " + pedido.isRetiradaBalcao());
        System.out.println("Total original: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Valor final com cupom: R$ " + String.format("%.2f", valorFinal));

        assertEquals(54.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Nao deve aplicar cupom quando regra for falsa")
    void naoDeveAplicarCupomQuandoRegraFalsa() {
        imprimirSeparador("Cupom Recusado");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaDomicilio();
        pedido.adicionarItem(new Hamburguer("Burger Pequeno", 30.0));

        ExpressaoPedido regra = new ExpressaoE(
                new ExpressaoRetiradaBalcao(),
                new ExpressaoTotalMaiorQue(50.0)
        );
        CupomDesconto cupom = new CupomDesconto("BALCAO10", 0.10, regra);

        double valorFinal = cupom.aplicar(pedido, pedido.calcularTotal());

        System.out.println("Regra interpretada: retirada no balcao E total maior que R$ 50,00");
        System.out.println("Retirada no balcao: " + pedido.isRetiradaBalcao());
        System.out.println("Total original: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Valor final sem cupom: R$ " + String.format("%.2f", valorFinal));

        assertEquals(30.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve interpretar expressao de total maior que")
    void deveInterpretarExpressaoDeTotalMaiorQue() {
        imprimirSeparador("Expressao Total Maior Que");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Medio", 45.0));
        ExpressaoPedido expressao = new ExpressaoTotalMaiorQue(40.0);

        boolean resultado = expressao.interpretar(pedido);

        System.out.println("Total do pedido: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Expressao: total maior que R$ 40,00");
        System.out.println("Resultado interpretado: " + resultado);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve interpretar expressao composta como falsa")
    void deveInterpretarExpressaoCompostaComoFalsa() {
        imprimirSeparador("Expressao Composta Falsa");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        pedido.adicionarItem(new Hamburguer("Burger Pequeno", 35.0));

        ExpressaoPedido regra = new ExpressaoE(
                new ExpressaoRetiradaBalcao(),
                new ExpressaoTotalMaiorQue(50.0)
        );

        boolean resultado = regra.interpretar(pedido);

        System.out.println("Retirada no balcao: " + pedido.isRetiradaBalcao());
        System.out.println("Total do pedido: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Expressao: retirada no balcao E total maior que R$ 50,00");
        System.out.println("Resultado interpretado: " + resultado);

        assertFalse(resultado);
    }
}
