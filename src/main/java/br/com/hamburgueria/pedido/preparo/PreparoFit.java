package br.com.hamburgueria.pedido.preparo;

import br.com.hamburgueria.pedido.Pedido;

public class PreparoFit extends PreparoPedidoTemplate {

    @Override
    protected void prepararLanche(Pedido pedido) {
        System.out.println("Preparando lanche fit com menos gordura.");
    }
}
