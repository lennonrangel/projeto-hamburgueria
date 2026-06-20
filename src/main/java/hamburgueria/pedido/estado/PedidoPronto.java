package hamburgueria.pedido.estado;

import hamburgueria.pedido.Pedido;

public class PedidoPronto implements EstadoPedido {

    @Override
    public void avancar(Pedido pedido) {
        pedido.setEstado(new PedidoEmRota());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new PedidoCancelado());
    }

    @Override
    public String getNome() {
        return "Pronto";
    }
}
