package padroescriacao.abstractfactory;

import padroesestruturais.composite.ItemCardapio;

public interface HamburguerFactory {
    ItemCardapio criarHamburguerPrincipal();
    ItemCardapio criarHamburguerEspecial();
    String getNomeLinha();
}
