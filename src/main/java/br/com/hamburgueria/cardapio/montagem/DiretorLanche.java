package br.com.hamburgueria.cardapio.montagem;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.Proteina;

public class DiretorLanche {

    public ItemCardapio montarComboDaCasa(String nome, String pao, Proteina proteina, double precoBase) {
        return new MontadorLanche()
                .comNome(nome)
                .comPao(pao)
                .comProteina(proteina)
                .comPrecoBase(precoBase)
                .comQueijoExtra()
                .comBaconExtra()
                .comMolhoEspecial()
                .montar();
    }
}
