package br.com.hamburgueria.cardapio.montagem;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.Proteina;

public interface LancheBuilder {
    LancheBuilder comNome(String nome);
    LancheBuilder comPao(String tipoPao);
    LancheBuilder comProteina(Proteina proteina);
    LancheBuilder comPrecoBase(double precoBase);
    LancheBuilder comQueijoExtra();
    LancheBuilder comBaconExtra();
    LancheBuilder comMolhoEspecial();
    ItemCardapio montar();
}
