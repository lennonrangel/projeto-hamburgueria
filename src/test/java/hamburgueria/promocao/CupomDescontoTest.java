package hamburgueria.promocao;

import hamburgueria.promocao.condicao.ExpressaoE;
import hamburgueria.promocao.condicao.ExpressaoPedido;
import hamburgueria.promocao.condicao.ExpressaoRetiradaBalcao;
import hamburgueria.promocao.condicao.ExpressaoTotalMaiorQue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.pedido.Pedido;
import hamburgueria.hamburguer.Hamburguer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Interpreter - Cupom de Desconto")
class CupomDescontoTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve aplicar cupom quando regra for interpretada como verdadeira")
    void deveAplicarCupomQuandoRegraVerdadeira() {
        imprimirSeparador("Cupom Aplicado");
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        pedido.adicionarItem(new Hamburguer("Burger Grande", 60.0));

        ExpressaoPedido regra = new ExpressaoE(
                new ExpressaoRetiradaBalcao(),
                new ExpressaoTotalMaiorQue(50.0)
        );
        CupomDesconto cupom = new CupomDesconto("BALCAO10", 0.10, regra);

        double valorFinal = cupom.aplicar(pedido, pedido.calcularTotal());


        assertEquals(54.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Nao deve aplicar cupom quando regra for falsa")
    void naoDeveAplicarCupomQuandoRegraFalsa() {
        imprimirSeparador("Cupom Recusado");
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaDomicilio();
        pedido.adicionarItem(new Hamburguer("Burger Pequeno", 30.0));

        ExpressaoPedido regra = new ExpressaoE(
                new ExpressaoRetiradaBalcao(),
                new ExpressaoTotalMaiorQue(50.0)
        );
        CupomDesconto cupom = new CupomDesconto("BALCAO10", 0.10, regra);

        double valorFinal = cupom.aplicar(pedido, pedido.calcularTotal());


        assertEquals(30.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("Deve interpretar expressao de total maior que")
    void deveInterpretarExpressaoDeTotalMaiorQue() {
        imprimirSeparador("Expressao Total Maior Que");
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Medio", 45.0));
        ExpressaoPedido expressao = new ExpressaoTotalMaiorQue(40.0);

        boolean resultado = expressao.interpretar(pedido);


        assertTrue(resultado);
    }

    @Test
    @DisplayName("Deve interpretar expressao composta como falsa")
    void deveInterpretarExpressaoCompostaComoFalsa() {
        imprimirSeparador("Expressao Composta Falsa");
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        pedido.adicionarItem(new Hamburguer("Burger Pequeno", 35.0));

        ExpressaoPedido regra = new ExpressaoE(
                new ExpressaoRetiradaBalcao(),
                new ExpressaoTotalMaiorQue(50.0)
        );

        boolean resultado = regra.interpretar(pedido);


        assertFalse(resultado);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Cupom getNome retorna nome do cupom")
    void testCupomGetNome() {
        ExpressaoPedido regra = new ExpressaoRetiradaBalcao();
        CupomDesconto cupom = new CupomDesconto("TESTE10", 0.10, regra);
        assertEquals("TESTE10", cupom.getNome());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("ExpressaoTotalMaiorQue limite superior e limite exato")
    void testExpressaoTotalMaiorQueLimite() {
        ExpressaoTotalMaiorQue exp = new ExpressaoTotalMaiorQue(50.0);
        
        Pedido p1 = new Pedido();
        p1.adicionarItem(new Hamburguer("H1", 50.01));
        assertTrue(exp.interpretar(p1));
        
        Pedido p2 = new Pedido();
        p2.adicionarItem(new Hamburguer("H2", 50.00));
        assertFalse(exp.interpretar(p2));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("ExpressaoE falso quando primeira condicao falsa e segunda verdadeira")
    void testExpressaoEPrimeiraFalsaSegundaVerdadeira() {
        Pedido p = new Pedido();
        p.marcarRetiradaDomicilio(); // Primeira falsa
        p.adicionarItem(new Hamburguer("H", 100.0)); // Segunda verdadeira (>50.0)
        
        ExpressaoE expressao = new ExpressaoE(new ExpressaoRetiradaBalcao(), new ExpressaoTotalMaiorQue(50.0));
        assertFalse(expressao.interpretar(p));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("ExpressaoE falso quando ambas condicoes sao falsas")
    void testExpressaoEAmbasFalsas() {
        Pedido p = new Pedido();
        p.marcarRetiradaDomicilio();
        p.adicionarItem(new Hamburguer("H", 20.0)); // < 50.0

        ExpressaoE expressao = new ExpressaoE(new ExpressaoRetiradaBalcao(), new ExpressaoTotalMaiorQue(50.0));
        assertFalse(expressao.interpretar(p));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Cupom com desconto de 0% nao deve alterar preco base")
    void testCupomDescontoPercentualZero() {
        Pedido pedido = new Pedido();
        pedido.marcarRetiradaBalcao();
        ExpressaoPedido regra = new ExpressaoRetiradaBalcao();
        CupomDesconto cupom = new CupomDesconto("ZERO", 0.0, regra);
        assertEquals(50.0, cupom.aplicar(pedido, 50.0), 0.01);
    }
}
