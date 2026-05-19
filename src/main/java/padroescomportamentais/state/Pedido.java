package padroescomportamentais.state;

import padroesestruturais.composite.ItemCardapio;
import padroescomportamentais.observer.MonitorPedido;

import padroescriacao.singleton.GeradorCodigoPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final String codigo;
    private final List<ItemCardapio> itens = new ArrayList<>();
    private final List<MonitorPedido> observadores = new ArrayList<>();
    private EstadoPedido estado;
    private boolean retiradaBalcao;

    public Pedido() {
        this.codigo = GeradorCodigoPedido.getInstancia().proximoCodigo();
        this.estado = new PedidoRecebido();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public void removerItem(ItemCardapio item) {
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
        System.out.println("Pedido " + codigo + " mudando para o estado: " + estado.getNome());
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
        return itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
    }

    public String getResumo() {
        StringBuilder resumo = new StringBuilder();
        for (ItemCardapio item : itens) {
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

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }

    private void notificarObservadores() {
        for (MonitorPedido observador : observadores) {
            observador.atualizar(this);
        }
    }
}
