package br.com.hamburgueria.pedido.preparo;

import br.com.hamburgueria.pedido.Pedido;

public class PreparoClassico extends PreparoPedidoTemplate {

    @Override
    protected void prepararLanche(Pedido pedido) {
        System.out.println("Preparando lanche clássico na chapa tradicional.");
    }
}
