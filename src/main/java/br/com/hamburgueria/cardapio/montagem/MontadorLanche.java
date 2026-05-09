package br.com.hamburgueria.cardapio.montagem;

import br.com.hamburgueria.cardapio.ItemCardapio;
import br.com.hamburgueria.cardapio.Lanche;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.BaconExtra;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.MolhoEspecial;
import br.com.hamburgueria.cardapio.ingredientes.adicionais.QueijoExtra;
import br.com.hamburgueria.cardapio.ingredientes.proteinas.Proteina;

public class MontadorLanche implements LancheBuilder {

    private String nome;
    private String tipoPao;
    private Proteina proteina;
    private double precoBase;
    private boolean queijoExtra;
    private boolean baconExtra;
    private boolean molhoEspecial;

    @Override
    public LancheBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public LancheBuilder comPao(String tipoPao) {
        this.tipoPao = tipoPao;
        return this;
    }

    @Override
    public LancheBuilder comProteina(Proteina proteina) {
        this.proteina = proteina;
        return this;
    }

    @Override
    public LancheBuilder comPrecoBase(double precoBase) {
        this.precoBase = precoBase;
        return this;
    }

    @Override
    public LancheBuilder comQueijoExtra() {
        this.queijoExtra = true;
        return this;
    }

    @Override
    public LancheBuilder comBaconExtra() {
        this.baconExtra = true;
        return this;
    }

    @Override
    public LancheBuilder comMolhoEspecial() {
        this.molhoEspecial = true;
        return this;
    }

    @Override
    public ItemCardapio montar() {
        ItemCardapio lanche = new Lanche(nome, tipoPao, proteina, precoBase);

        if (queijoExtra) {
            lanche = new QueijoExtra(lanche);
        }

        if (baconExtra) {
            lanche = new BaconExtra(lanche);
        }

        if (molhoEspecial) {
            lanche = new MolhoEspecial(lanche);
        }

        return lanche;
    }
}
