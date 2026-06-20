package hamburgueria.pedido.estado;

import hamburgueria.pedido.Pedido;

public interface EstadoPedido {
    void avancar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getNome();
}
