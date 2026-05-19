package hamburgueria;

import padroescomportamentais.mediator.Atendente;
import padroescomportamentais.mediator.Caixa;
import padroescomportamentais.mediator.CentralHamburgueria;
import padroescomportamentais.observer.ClienteNotificador;
import padroescomportamentais.observer.CozinhaNotificador;
import padroescomportamentais.strategy.ProcessadorPagamento;
import padroescomportamentais.chainofresponsability.DescontoPedidoGrande;
import padroescomportamentais.chainofresponsability.DescontoRetiradaBalcao;
import padroescomportamentais.strategy.PagamentoPix;
import padroescomportamentais.state.Pedido;
import padroescomportamentais.templatemethod.ProcessoPreparoGourmet;
import padroescomportamentais.templatemethod.ProcessoPreparo;
import padroescriacao.abstractfactory.*;
import padroesestruturais.composite.ItemCardapio;

public class Main {

    public static void main(String[] args) {
        exibirLinha(ClassicoFactory.getInstancia());
        exibirLinha(GourmetFactory.getInstancia());
        exibirLinha(FitFactory.getInstancia());

        CentralHamburgueria central = new CentralHamburgueria();
        Atendente atendente = new Atendente(central);
        Caixa caixa = new Caixa(central);

        Pedido pedido = new Pedido();
        pedido.adicionarObservador(new ClienteNotificador());
        pedido.adicionarObservador(new CozinhaNotificador());
        pedido.marcarRetiradaBalcao();

        ItemCardapio hamburguer = GourmetFactory.getInstancia().criarHamburguerEspecial();
        pedido.adicionarItem(hamburguer);

        atendente.receberPedido(pedido);

        ProcessoPreparo preparo = new ProcessoPreparoGourmet();
        preparo.preparar(pedido);

        DescontoPedidoGrande descontoPedidoGrande = new DescontoPedidoGrande();
        descontoPedidoGrande.setProximo(new DescontoRetiradaBalcao());

        ProcessadorPagamento pagamento = new ProcessadorPagamento(new PagamentoPix());
        pagamento.setDescontoPedido(descontoPedidoGrande);

        double valorFinal = pagamento.pagar(pedido);
        caixa.finalizarPagamento(pedido, valorFinal);

        System.out.println();
        System.out.println("Resumo do pedido:");
        System.out.println(pedido.getResumo());
        System.out.println("Pagamento: " + pagamento.getDescricaoPagamento());
        System.out.println("Total final: R$ " + String.format("%.2f", valorFinal));
    }

    private static void exibirLinha(HamburguerFactory hamburguerFactory) {
        System.out.println("Linha: " + hamburguerFactory.getNomeLinha());
        imprimirHamburguer(hamburguerFactory.criarHamburguerPrincipal());
        imprimirHamburguer(hamburguerFactory.criarHamburguerEspecial());
        System.out.println();
    }

    private static void imprimirHamburguer(ItemCardapio hamburguer) {
        System.out.println(hamburguer.getDescricao() + " - R$ " + String.format("%.2f", hamburguer.getPreco()));
    }
}
