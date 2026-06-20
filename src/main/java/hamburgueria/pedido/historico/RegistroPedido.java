package hamburgueria.pedido.historico;

import hamburgueria.pedido.estado.EstadoPedido;

public class RegistroPedido {
    private final EstadoPedido estado;

    public RegistroPedido(EstadoPedido estado) {
        this.estado = estado;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
}
