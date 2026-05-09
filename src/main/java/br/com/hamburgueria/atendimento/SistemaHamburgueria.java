package br.com.hamburgueria.atendimento;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.notificacao.ClienteNotificador;
import br.com.hamburgueria.notificacao.CozinhaNotificador;
import br.com.hamburgueria.pagamento.ProcessadorPagamento;
import br.com.hamburgueria.pagamento.desconto.DescontoPedido;
import br.com.hamburgueria.pagamento.estrategia.FormaPagamento;
import br.com.hamburgueria.pedido.Pedido;
import br.com.hamburgueria.pedido.preparo.PreparoPedidoTemplate;

public class SistemaHamburgueria {

    private final CentralHamburgueria central;
    private final Atendente atendente;
    private final Caixa caixa;

    public SistemaHamburgueria() {
        this.central = new CentralHamburgueria();
        this.atendente = new Atendente(central);
        this.caixa = new Caixa(central);
    }

    public Pedido abrirPedido(String codigo, ItemCardapio itemCardapio, boolean retiradaBalcao) {
        Pedido pedido = new Pedido(codigo);
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
                                 PreparoPedidoTemplate preparo,
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
