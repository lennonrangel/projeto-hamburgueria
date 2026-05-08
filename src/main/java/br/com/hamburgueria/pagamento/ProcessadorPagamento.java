package br.com.hamburgueria.pagamento;

import br.com.hamburgueria.pagamento.estrategia.FormaPagamento;
import br.com.hamburgueria.pedido.Pedido;

public class ProcessadorPagamento {

    private FormaPagamento formaPagamento;

    public ProcessadorPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double pagar(Pedido pedido) {
        return formaPagamento.calcularValorFinal(pedido.calcularTotal());
    }

    public String getDescricaoPagamento() {
        return formaPagamento.getDescricao();
    }
}
