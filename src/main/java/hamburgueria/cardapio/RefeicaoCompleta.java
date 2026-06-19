package hamburgueria.cardapio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RefeicaoCompleta implements MenuItem {

    private final String nome;
    private final List<MenuItem> itens = new ArrayList<>();

    public RefeicaoCompleta(String nome) {
        this.nome = nome;
    }

    public void adicionar(MenuItem item) {
        adicionarItem(item);
    }

    public void adicionarItem(MenuItem item) {
        itens.add(item);
    }

    public void removerItem(MenuItem item) {
        itens.remove(item);
    }

    public List<MenuItem> getItens() {
        return Collections.unmodifiableList(itens);
    }

    @Override
    public String getDescricao() {
        StringBuilder descricao = new StringBuilder(nome).append(" [");

        for (int i = 0; i < itens.size(); i++) {
            descricao.append(itens.get(i).getDescricao());

            if (i < itens.size() - 1) {
                descricao.append(", ");
            }
        }

        descricao.append("]");
        return descricao.toString();
    }

    @Override
    public double getPreco() {
        return itens.stream().mapToDouble(MenuItem::getPreco).sum();
    }
}

