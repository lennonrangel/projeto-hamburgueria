package br.com.hamburgueria;

import br.com.hamburgueria.atendimento.Atendente;
import br.com.hamburgueria.atendimento.CentralHamburgueria;
import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.fabricas.CardapioClassico;
import br.com.hamburgueria.cardapio.fabricas.CardapioFactory;
import br.com.hamburgueria.cardapio.fabricas.CardapioFit;
import br.com.hamburgueria.cardapio.fabricas.CardapioGourmet;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.BaconExtra;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.QueijoExtra;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.AoPonto;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.ProteinaSmash;
import br.com.hamburgueria.cardapio.montagem.MontadorLanche;
import br.com.hamburgueria.notificacao.ObservadorPedido;
import br.com.hamburgueria.pagamento.ProcessadorPagamento;
import br.com.hamburgueria.pagamento.desconto.DescontoPedidoGrande;
import br.com.hamburgueria.pagamento.desconto.DescontoRetiradaBalcao;
import br.com.hamburgueria.pagamento.estrategia.PagamentoPix;
import br.com.hamburgueria.pedido.Pedido;
import br.com.hamburgueria.pedido.preparo.PreparoGourmet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HamburgueriaTest {

    @Test
    void deveUsarMesmaInstanciaDoCardapioClassico() {
        assertSame(CardapioClassico.getInstancia(), CardapioClassico.getInstancia());
    }

    @Test
    void deveCriarLanchesDeFamiliasDiferentes() {
        CardapioFactory classico = CardapioClassico.getInstancia();
        CardapioFactory gourmet = CardapioGourmet.getInstancia();
        CardapioFactory fit = CardapioFit.getInstancia();

        assertEquals("Clássicos", classico.getNomeLinha());
        assertEquals("Gourmet", gourmet.getNomeLinha());
        assertEquals("Fit", fit.getNomeLinha());
    }

    @Test
    void deveCriarLancheComProteinaEFormaDePreparoSeparadas() {
        ItemCardapio lanche = CardapioGourmet.getInstancia().criarLanchePrincipal();

        assertTrue(lanche.getDescricao().contains("picanha"));
        assertTrue(lanche.getDescricao().contains("mal passado"));
        assertTrue(lanche.getPreco() > 0);
    }

    @Test
    void deveAdicionarIngredientesExtrasComDecorator() {
        ItemCardapio lanche = CardapioClassico.getInstancia().criarLanchePrincipal();
        double precoOriginal = lanche.getPreco();

        lanche = new QueijoExtra(new BaconExtra(lanche));

        assertTrue(lanche.getDescricao().contains("queijo extra"));
        assertTrue(lanche.getDescricao().contains("bacon extra"));
        assertEquals(precoOriginal + 7.5, lanche.getPreco(), 0.01);
    }

    @Test
    void deveAlterarEstadoDoPedido() {
        Pedido pedido = new Pedido("PED-001");

        assertEquals("Recebido", pedido.getEstadoAtual());

        pedido.avancar();
        assertEquals("Em preparo", pedido.getEstadoAtual());

        pedido.avancar();
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    void deveNotificarObservadoresQuandoEstadoMuda() {
        Pedido pedido = new Pedido("PED-002");
        final int[] quantidadeNotificacoes = {0};

        ObservadorPedido observador = p -> quantidadeNotificacoes[0]++;
        pedido.adicionarObservador(observador);

        pedido.avancar();
        pedido.avancar();

        assertEquals(2, quantidadeNotificacoes[0]);
    }

    @Test
    void deveUsarStrategyParaCalcularPagamento() {
        Pedido pedido = new Pedido("PED-003");
        pedido.adicionarItem(CardapioFit.getInstancia().criarLanchePrincipal());

        ProcessadorPagamento pagamento = new ProcessadorPagamento(new PagamentoPix());

        assertEquals(pedido.calcularTotal() * 0.90, pagamento.pagar(pedido), 0.01);
    }

    @Test
    void deveAplicarDescontosEmCadeia() {
        Pedido pedido = new Pedido("PED-004");
        pedido.marcarRetiradaBalcao();
        pedido.adicionarItem(CardapioGourmet.getInstancia().criarLancheEspecial());
        pedido.adicionarItem(CardapioGourmet.getInstancia().criarLancheEspecial());

        DescontoPedidoGrande desconto = new DescontoPedidoGrande();
        desconto.setProximo(new DescontoRetiradaBalcao());

        double valorComDesconto = desconto.aplicar(pedido, pedido.calcularTotal());

        assertTrue(valorComDesconto < pedido.calcularTotal());
    }

    @Test
    void deveMontarLancheComBuilder() {
        ItemCardapio lanche = new MontadorLanche()
                .comNome("Smash Personalizado")
                .comPao("brioche")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(10.0)
                .comQueijoExtra()
                .comBaconExtra()
                .montar();

        assertTrue(lanche.getDescricao().contains("queijo extra"));
        assertTrue(lanche.getDescricao().contains("bacon extra"));
    }

    @Test
    void deveUsarMediatorParaRegistrarPedido() {
        CentralHamburgueria central = new CentralHamburgueria();
        Atendente atendente = new Atendente(central);
        Pedido pedido = new Pedido("PED-005");

        atendente.receberPedido(pedido);

        assertEquals(1, central.getPedidos().size());
        assertEquals("Em preparo", pedido.getEstadoAtual());
    }

    @Test
    void deveUsarTemplateMethodNoPreparo() {
        Pedido pedido = new Pedido("PED-006");
        pedido.avancar();

        new PreparoGourmet().preparar(pedido);

        assertEquals("Pronto", pedido.getEstadoAtual());
    }
}
