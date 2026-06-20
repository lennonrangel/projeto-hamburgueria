package hamburgueria.pedido;

import hamburgueria.atendimento.Atendente;
import hamburgueria.atendimento.Caixa;
import hamburgueria.atendimento.CentralHamburgueria;
import hamburgueria.notificacao.ClienteNotificador;
import hamburgueria.notificacao.CozinhaNotificador;
import hamburgueria.formapagamento.ProcessadorPagamento;
import hamburgueria.formapagamento.FormaPagamento;
import hamburgueria.formapagamento.desconto.DescontoPedido;
import hamburgueria.cozinha.ProcessoPreparo;
import hamburgueria.cardapio.MenuItem;

public class GestorPedidos {

    private final CentralHamburgueria central;
    private final Atendente atendente;
    private final Caixa caixa;

    public GestorPedidos() {
        this.central = new CentralHamburgueria();
        this.atendente = new Atendente(central);
        this.caixa = new Caixa(central);
    }

    public Pedido abrirPedido(MenuItem menuItem, boolean retiradaBalcao) {
        Pedido pedido = new Pedido();
        pedido.adicionarObservador(new ClienteNotificador());
        pedido.adicionarObservador(new CozinhaNotificador());
        pedido.adicionarItem(menuItem);

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

