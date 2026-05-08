package br.com.hamburgueria;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.fabricas.CardapioClassico;
import br.com.hamburgueria.cardapio.fabricas.CardapioFactory;
import br.com.hamburgueria.cardapio.fabricas.CardapioFit;
import br.com.hamburgueria.cardapio.fabricas.CardapioGourmet;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.BaconExtra;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.QueijoExtra;
import br.com.hamburgueria.notificacao.ClienteNotificador;
import br.com.hamburgueria.notificacao.CozinhaNotificador;
import br.com.hamburgueria.pagamento.ProcessadorPagamento;
import br.com.hamburgueria.pagamento.estrategia.PagamentoPix;
import br.com.hamburgueria.pedido.Pedido;

public class Main {

    public static void main(String[] args) {
        exibirLinha(CardapioClassico.getInstancia());
        exibirLinha(CardapioGourmet.getInstancia());
        exibirLinha(CardapioFit.getInstancia());

        Pedido pedido = new Pedido("PED-001");
        pedido.adicionarObservador(new ClienteNotificador());
        pedido.adicionarObservador(new CozinhaNotificador());

        ItemCardapio lanche = CardapioGourmet.getInstancia().criarLanchePrincipal();
        lanche = new QueijoExtra(lanche);
        lanche = new BaconExtra(lanche);

        pedido.adicionarItem(lanche);
        pedido.avancar();
        pedido.avancar();

        ProcessadorPagamento pagamento = new ProcessadorPagamento(new PagamentoPix());
        System.out.println("Resumo do pedido:");
        System.out.println(pedido.getResumo());
        System.out.println("Pagamento: " + pagamento.getDescricaoPagamento());
        System.out.println("Total final: R$ " + String.format("%.2f", pagamento.pagar(pedido)));
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
