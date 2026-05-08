package br.com.hamburgueria.pedido;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.notificacao.ObservadorPedido;
import br.com.hamburgueria.pedido.estado.EstadoPedido;
import br.com.hamburgueria.pedido.estado.PedidoRecebido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final String codigo;
    private final List<ItemCardapio> itens = new ArrayList<>();
    private final List<ObservadorPedido> observadores = new ArrayList<>();
    private EstadoPedido estado;

    public Pedido(String codigo) {
        this.codigo = codigo;
        this.estado = new PedidoRecebido();
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public void adicionarObservador(ObservadorPedido observador) {
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

    public String getEstadoAtual() {
        return estado.getNome();
    }

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }

    private void notificarObservadores() {
        for (ObservadorPedido observador : observadores) {
            observador.atualizar(this);
        }
    }
}
