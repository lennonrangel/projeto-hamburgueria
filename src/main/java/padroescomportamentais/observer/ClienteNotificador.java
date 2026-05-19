package padroescomportamentais.observer;

import padroescomportamentais.state.Pedido;

public class ClienteNotificador implements MonitorPedido {

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("Cliente avisado: pedido " + pedido.getCodigo() + " esta " + pedido.getEstadoAtual());
    }
}
