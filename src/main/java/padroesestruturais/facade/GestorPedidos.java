package padroesestruturais.facade;

import padroescomportamentais.mediator.Atendente;
import padroescomportamentais.mediator.Caixa;
import padroescomportamentais.mediator.CentralHamburgueria;
import padroescomportamentais.observer.ClienteNotificador;
import padroescomportamentais.observer.CozinhaNotificador;
import padroescomportamentais.strategy.ProcessadorPagamento;
import padroescomportamentais.strategy.FormaPagamento;
import padroescomportamentais.chainofresponsability.DescontoPedido;
import padroescomportamentais.state.Pedido;
import padroescomportamentais.templatemethod.ProcessoPreparo;
import padroesestruturais.composite.ItemCardapio;

public class GestorPedidos {

    private final CentralHamburgueria central;
    private final Atendente atendente;
    private final Caixa caixa;

    public GestorPedidos() {
        this.central = new CentralHamburgueria();
        this.atendente = new Atendente(central);
        this.caixa = new Caixa(central);
    }

    public Pedido abrirPedido(ItemCardapio itemCardapio, boolean retiradaBalcao) {
        Pedido pedido = new Pedido();
        pedido.adicionarObservador(new ClienteNotificador());
        pedido.adicionarObservador(new CozinhaNotificador());
        pedido.adicionarItem(itemCardapio);

        if (retiradaBalcao) {
            pedido.marcarRetiradaBalcao();
        }

        atendente.receberPedido(pedido);
        return pedido;
    }

    public double prepararEPagar(Pedido pedido,
                                 ProcessoPreparo preparo,
                                 FormaPagamento formaPagamento,
                                 DescontoPedido descontoPedido) {
        preparo.preparar(pedido);

        ProcessadorPagamento processadorPagamento = new ProcessadorPagamento(formaPagamento);
        processadorPagamento.setDescontoPedido(descontoPedido);

        double valorFinal = processadorPagamento.pagar(pedido);
        caixa.finalizarPagamento(pedido, valorFinal);
        return valorFinal;
    }

    public CentralHamburgueria getCentral() {
        return central;
    }
}
