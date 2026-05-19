package padroesestruturais.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RefeicaoCompleta implements ItemCardapio {

    private final String nome;
    private final List<ItemCardapio> itens = new ArrayList<>();

    public RefeicaoCompleta(String nome) {
        this.nome = nome;
    }

    public void adicionar(ItemCardapio item) {
        adicionarItem(item);
    }

    public void adicionarItem(ItemCardapio item) {
        itens.add(item);
    }

    public void removerItem(ItemCardapio item) {
        itens.remove(item);
    }

    public List<ItemCardapio> getItens() {
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
        return itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
    }
}
