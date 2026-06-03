package padroescriacao.prototype;

import padroesestruturais.composite.ItemCardapio;

public interface HamburguerPrototype extends ItemCardapio {
    HamburguerPrototype clonar();
}
