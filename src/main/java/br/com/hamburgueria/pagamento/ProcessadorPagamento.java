package br.com.hamburgueria.pagamento;

import br.com.hamburgueria.pagamento.desconto.DescontoPedido;
import br.com.hamburgueria.pagamento.desconto.SemDesconto;
import br.com.hamburgueria.pagamento.estrategia.FormaPagamento;
import br.com.hamburgueria.pedido.Pedido;

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
