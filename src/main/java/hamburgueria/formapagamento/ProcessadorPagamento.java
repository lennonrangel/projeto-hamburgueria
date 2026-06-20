package hamburgueria.formapagamento;

import hamburgueria.formapagamento.desconto.DescontoPedido;
import hamburgueria.formapagamento.desconto.SemDesconto;

import hamburgueria.pedido.Pedido;

public class ProcessadorPagamento {

    private FormaPagamento formaPagamento;
    private DescontoPedido descontoPedido = new SemDesconto();

    public ProcessadorPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setDescontoPedido(DescontoPedido descontoPedido) {
        this.descontoPedido = descontoPedido;
    }

    public double pagar(Pedido pedido) {
        double valorComDesconto = descontoPedido.aplicar(pedido, pedido.calcularTotal());
        return formaPagamento.calcularValorFinal(valorComDesconto);
    }

    public String getDescricaoPagamento() {
        return formaPagamento.getDescricao();
    }
}
