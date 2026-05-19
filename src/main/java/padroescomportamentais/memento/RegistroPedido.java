package padroescomportamentais.memento;

import padroescomportamentais.state.EstadoPedido;

public class RegistroPedido {
    private final EstadoPedido estado;

    public RegistroPedido(EstadoPedido estado) {
        this.estado = estado;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
}
