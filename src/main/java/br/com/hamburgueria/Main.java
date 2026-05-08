package br.com.hamburgueria;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.fabricas.CardapioClassico;
import br.com.hamburgueria.cardapio.fabricas.CardapioFactory;
import br.com.hamburgueria.cardapio.fabricas.CardapioFit;
import br.com.hamburgueria.cardapio.fabricas.CardapioGourmet;

public class Main {

    public static void main(String[] args) {
        exibirLinha(CardapioClassico.getInstancia());
        exibirLinha(CardapioGourmet.getInstancia());
        exibirLinha(CardapioFit.getInstancia());
    }

    private static void exibirLinha(CardapioFactory cardapioFactory) {
        System.out.println("Linha: " + cardapioFactory.getNomeLinha());
        imprimirLanche(cardapioFactory.criarLanchePrincipal());
        imprimirLanche(cardapioFactory.criarLancheEspecial());
        System.out.println();
    }

    private static void imprimirLanche(ItemCardapio lanche) {
        System.out.println(lanche.getDescricao() + " - R$ " + String.format("%.2f", lanche.getPreco()));
    }
}
