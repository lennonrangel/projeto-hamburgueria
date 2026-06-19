package hamburgueria.cardapio;

public class IteradorItensCardapio implements IteradorCardapio {
    private final Cardapio cardapio;
    private int posicao;

    public IteradorItensCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    @Override
    public boolean hasMore() {
        return posicao < cardapio.getItens().size();
    }

    @Override
    public MenuItem getNext() {
        if (!hasMore()) {
            return null;
        }
        return cardapio.getItens().get(posicao++);
    }
}

