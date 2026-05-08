package br.com.hamburgueria.cardapio.fabricas;

import br.com.hamburgueria.cardapio.ItemCardapio;

public interface CardapioFactory {
    ItemCardapio criarLanchePrincipal();
    ItemCardapio criarLancheEspecial();
    String getNomeLinha();
}
