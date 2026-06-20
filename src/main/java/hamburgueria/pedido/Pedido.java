package hamburgueria.pedido;

import hamburgueria.pedido.estado.EstadoPedido;
import hamburgueria.pedido.estado.PedidoRecebido;
import hamburgueria.pedido.historico.RegistroPedido;
import hamburgueria.pedido.relatorio.RelatorioPedido;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.notificacao.MonitorPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final String codigo;
    private final List<MenuItem> itens = new ArrayList<>();
    private final List<MonitorPedido> observadores = new ArrayList<>();
    private EstadoPedido estado;
    private boolean retiradaBalcao;

    public Pedido() {
        this.codigo = GeradorCodigoPedido.getInstancia().proximoCodigo();
        this.estado = new PedidoRecebido();
    }

    public void adicionarItem(MenuItem item) {
        itens.add(item);
    }

    public void removerItem(MenuItem item) {
        itens.remove(item);
    }

    public void adicionarObservador(MonitorPedido observador) {
        observadores.add(observador);
    }

    public void avancar() {
        estado.avancar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        notificarObservadores();
    }

    public void marcarRetiradaBalcao() {
        this.retiradaBalcao = true;
    }

    public void marcarRetiradaDomicilio() {
        this.retiradaBalcao = false;
    }

    public boolean isRetiradaBalcao() {
        return retiradaBalcao;
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(MenuItem::getPreco).sum();
    }

    public String getResumo() {
        StringBuilder resumo = new StringBuilder();
        for (MenuItem item : itens) {
            resumo.append(item.getDescricao()).append(" - R$ ").append(String.format("%.2f", item.getPreco())).append(System.lineSeparator());
        }
        return resumo.toString();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getId() {
        return codigo;
    }

    public String getEstadoAtual() {
        return estado.getNome();
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void restaurarEstado(EstadoPedido estado) {
        this.estado = estado;
        notificarObservadores();
    }

    public RegistroPedido salvarEstado() {
        return new RegistroPedido(this.estado);
    }

    public void restaurar(RegistroPedido registro) {
        restaurarEstado(registro.getEstado());
    }

    public String aceitar(RelatorioPedido visitante) {
        return visitante.visitarPedido(this);
    }

    public List<MenuItem> getItens() {
        return Collections.unmodifiableList(itens);
    }

    private void notificarObservadores() {
        for (MonitorPedido observador : observadores) {
            observador.atualizar(this);
        }
    }
}

