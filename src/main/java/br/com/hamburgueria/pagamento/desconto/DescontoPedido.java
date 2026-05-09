package br.com.hamburgueria.pagamento.desconto;

import br.com.hamburgueria.pedido.Pedido;

public abstract class DescontoPedido {

    private DescontoPedido proximo;

    public DescontoPedido setProximo(DescontoPedido proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public double aplicar(Pedido pedido, double valorAtual) {
        double valorComDesconto = calcular(pedido, valorAtual);

        if (proximo != null) {
            return proximo.aplicar(pedido, valorComDesconto);
        }

        return valorComDesconto;
    }

    protected abstract double calcular(Pedido pedido, double valorAtual);
}
