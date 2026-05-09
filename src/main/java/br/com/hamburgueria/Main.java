package br.com.hamburgueria;

import br.com.hamburgueria.atendimento.Atendente;
import br.com.hamburgueria.atendimento.Caixa;
import br.com.hamburgueria.atendimento.CentralHamburgueria;
import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.fabricas.CardapioClassico;
import br.com.hamburgueria.cardapio.fabricas.CardapioFactory;
import br.com.hamburgueria.cardapio.fabricas.CardapioFit;
import br.com.hamburgueria.cardapio.fabricas.CardapioGourmet;
import br.com.hamburgueria.notificacao.ClienteNotificador;
import br.com.hamburgueria.notificacao.CozinhaNotificador;
import br.com.hamburgueria.pagamento.ProcessadorPagamento;
import br.com.hamburgueria.pagamento.desconto.DescontoPedidoGrande;
import br.com.hamburgueria.pagamento.desconto.DescontoRetiradaBalcao;
import br.com.hamburgueria.pagamento.estrategia.PagamentoPix;
import br.com.hamburgueria.pedido.Pedido;
import br.com.hamburgueria.pedido.preparo.PreparoGourmet;
import br.com.hamburgueria.pedido.preparo.PreparoPedidoTemplate;

public class Main {

    public static void main(String[] args) {
        exibirLinha(CardapioClassico.getInstancia());
        exibirLinha(CardapioGourmet.getInstancia());
        exibirLinha(CardapioFit.getInstancia());

        CentralHamburgueria central = new CentralHamburgueria();
        Atendente atendente = new Atendente(central);
        Caixa caixa = new Caixa(central);

        Pedido pedido = new Pedido("PED-001");
        pedido.adicionarObservador(new ClienteNotificador());
        pedido.adicionarObservador(new CozinhaNotificador());
        pedido.marcarRetiradaBalcao();

        ItemCardapio lanche = CardapioGourmet.getInstancia().criarLancheEspecial();
        pedido.adicionarItem(lanche);

        atendente.receberPedido(pedido);

        PreparoPedidoTemplate preparo = new PreparoGourmet();
        preparo.preparar(pedido);

        DescontoPedidoGrande descontoPedidoGrande = new DescontoPedidoGrande();
        descontoPedidoGrande.setProximo(new DescontoRetiradaBalcao());

        ProcessadorPagamento pagamento = new ProcessadorPagamento(new PagamentoPix());
        pagamento.setDescontoPedido(descontoPedidoGrande);

        double valorFinal = pagamento.pagar(pedido);
        caixa.finalizarPagamento(pedido, valorFinal);

        System.out.println("Resumo do pedido:");
        System.out.println(pedido.getResumo());
        System.out.println("Pagamento: " + pagamento.getDescricaoPagamento());
        System.out.println("Total final: R$ " + String.format("%.2f", valorFinal));
    }

    private static void exibirLinha(CardapioFactory cardapioFactory) {
        System.out.println("Linha: " + cardapioFactory.getNomeLinha());
        imprimirLanche(cardapioFactory.criarLanchePrincipal());
        imprimirLanche(cardapioFactory.criarLancheEspecial());
        System.out.println();
    }

    private static void imprimirLanche(ItemCardapio lanche) {
        System.out.println(lanche.getDescricao() + " - R$ " + String.format("%.2f", lanche.getPreco()));
    }
}
