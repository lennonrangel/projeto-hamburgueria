package br.com.hamburgueria.pedido.estado;

import br.com.hamburgueria.pedido.Pedido;

public class PedidoDevolucao implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        throw new IllegalStateException("Pedido devolvido nao pode avancar.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        // Já está em um estado final de erro
    }

    @Override
    public String getNome() {
        return "Devolvido (Estado Final)";
    }
}
